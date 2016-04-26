package cn.itcast.oa.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.service.ProcessDefinitionService;
@Service
@Transactional
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {
   
	
	@Resource
	private ProcessEngine processEngine;
	@Override
	public List<ProcessDefinition> findAllLatestVersions() {
		List<ProcessDefinition> all = processEngine.getRepositoryService()//
		   .createProcessDefinitionQuery()//
		   .orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION)//根据版本升序查询
		   .list();
		
		//过滤低版本
		Map<String, ProcessDefinition> map=new HashMap<String, ProcessDefinition>();
		for(ProcessDefinition pd:all){
			map.put(pd.getKey(), pd);
		}
		return new ArrayList<ProcessDefinition>(map.values());
	}

	@Override
	public void deleteByKey(String key) {
		
		//查询指定key的所有流程定义
		List<ProcessDefinition> list = processEngine.getRepositoryService()//
		.createProcessDefinitionQuery()//
		.processDefinitionKey(key)//
		.list();
		//循环删除
		for(ProcessDefinition pd:list){
			processEngine.getRepositoryService().deleteDeploymentCascade(pd.getDeploymentId());
		}
		
		
	}

	@Override
	public void deploy(ZipInputStream ziStream) {
		processEngine.getRepositoryService()//
		.createDeployment()//
		.addResourcesFromZipInputStream(ziStream)//
		.deploy();
		
	}

	@Override
	public InputStream getProcessDefinitionAsStream(String id) {
		//根据id取出对应的流程定义对象
		ProcessDefinition pdDefinition=processEngine.getRepositoryService()//
				.createProcessDefinitionQuery()//
				.processDefinitionId(id)//
				.uniqueResult();
		//返回图片资源
		return processEngine.getRepositoryService().getResourceAsStream(pdDefinition.getDeploymentId(), pdDefinition.getImageResourceName());
	}

}
