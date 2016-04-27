package cn.itcast.oa.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.codehaus.janino.Java.NewAnonymousClassInstance;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.domain.Application;
import cn.itcast.oa.domain.ApproveInfo;
import cn.itcast.oa.domain.TaskView;
import cn.itcast.oa.domain.User;
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


	@Override
	public List<TaskView> getMyTaskView(User sessionUser) {
		
		//查询任务
		List<Task> taskList=processEngine.getTaskService().findPersonalTasks(sessionUser.getLoginName());
		
		List<TaskView> taskViews=new ArrayList<TaskView>();
		//找出每个任务对应的流程信息
		for(Task task:taskList){
			Application application=(Application) processEngine.getTaskService().getVariable(task.getId(), "application");
			taskViews.add(new TaskView(task, application));
		}
		
		
		return taskViews;
	}


	@Override
	public Set<String> getOutcomesByTaskId(String taskId) {
		//获得指定活动化所有的连线
		return processEngine.getTaskService().getOutcomes(taskId);
	}


	@Override
	public void approval(ApproveInfo approveInfo, String taskId, String outcome) {
		//保存审批人信息
		getSession().save(approveInfo);
		// 2，办理完任务
		Task task = processEngine.getTaskService().getTask(taskId); // 一定要先取出Task对象，再完成任务，否则拿不到，因为执行完就变成历史信息了。
		if (outcome == null) {
			processEngine.getTaskService().completeTask(taskId);
		} else {
			processEngine.getTaskService().completeTask(taskId, outcome);
		}
		//3.维护申请状态
		// >> 取出所属的流程实例，如果取出的为null，说明流程实例执行完成了，已经变成了历史记录
		ProcessInstance pi = processEngine.getExecutionService().findProcessInstanceById(task.getExecutionId());
		Application application=approveInfo.getApplication();
		if(!approveInfo.isApproval()){//如果没同意
			
			if(application!=null){
				processEngine.getExecutionService().endProcessInstance(task.getId(), ProcessInstance.STATE_ENDED);
			}
		    application.setStatus(Application.STATUS_REJECTED);    
		}else {
			// 如果本环节同意，而且本环节是最后一个环节，则流程实例正常结束，申请的状态为：已通过
			if (pi == null) { // 本环节是最后一个环节，即流程已经结束了
				application.setStatus(Application.STATUS_APPROVED);
			}
		}
		getSession().update(application);

		
	}


	

}
