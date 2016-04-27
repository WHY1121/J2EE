package cn.itcast.oa.domain;

import java.io.Serializable;
import java.util.Set;
/**
 * 申请模版javaBean
 * @author fsdfsdsss
 *
 */
public class ApplicationTemplate implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String processDefinitionKey;//流程定义的key
	private String path; // 文件存储的路径
	
	
	private Set<Application> applications;
	
	
	public Set<Application> getApplications() {
		return applications;
	}
	public void setApplications(Set<Application> applications) {
		this.applications = applications;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}
	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
