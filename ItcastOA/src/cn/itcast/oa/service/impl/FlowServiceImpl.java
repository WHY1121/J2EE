package cn.itcast.oa.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.janino.Java.NewAnonymousClassInstance;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.domain.Application;
import cn.itcast.oa.service.ApplicationService;
@Service
public class FlowServiceImpl extends BaseDaoImpl<Application> implements ApplicationService{
	@Resource
	private ProcessEngine processEngine;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	
	@Override
	public void submit(Application application) {
		application.setApplyTime(new Date());//申请事件
		application.setStatus(Application.STATUS_RUNNING);//申请状态
		application.setTitle(application.getApplicationTemplate().getName()+"_"+//
		        application.getApplicant().getName()+"_"+//
				sdf.format(application.getApplyTime()));//申请标题
		getSession().save(application);//保存申请信息
		//启动实例开始流转
		//1.设置流程变量
		Map<String, Object> variableMap=new HashMap<String, Object>();
		variableMap.put("application", application);
		//启动流程实例 ，附带流程变量
		String templateKey=application.getApplicationTemplate().getProcessDefinitionKey();
		ProcessInstance pi=processEngine.getExecutionService()//
				.startProcessInstanceByKey(templateKey, variableMap);
		//办理第一个任务，提交申请
		Task task=processEngine.getTaskService().createTaskQuery()//
		.processInstanceId(pi.getId()).uniqueResult();//查询本流程中仅有的一个提交申请
		//
		processEngine.getTaskService().completeTask(task.getId());
		
	}

}
