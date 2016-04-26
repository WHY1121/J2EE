package cn.itcast.oa.service;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.jbpm.api.ProcessDefinition;

public interface ProcessDefinitionService {
    /**
     * 返回最新版本的流程定义
     * @return
     */
	List<ProcessDefinition> findAllLatestVersions();
    /**
     * 根据key删除定义
     * @param key
     */
	void deleteByKey(String key);
	/**
	 * 部署
	 * @param ziStream
	 */
	void deploy(ZipInputStream ziStream);
	InputStream getProcessDefinitionAsStream(String id);

}
