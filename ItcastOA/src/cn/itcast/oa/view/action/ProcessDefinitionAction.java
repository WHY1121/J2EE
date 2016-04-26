package cn.itcast.oa.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
@Controller
@Scope("prototype")
public class ProcessDefinitionAction extends BaseAction{
	private String id;
	private String key;//流程的key
	private File upload;//上传的文件
	private InputStream inputStream;
	//列表
	public String list() throws Exception{
		List<ProcessDefinition> processDefinitions=processDefinitionService.findAllLatestVersions();
	    ActionContext.getContext().put("processDefinitions", processDefinitions);
		return "list";
	}
	//部署页面
	public String addUI() throws Exception{
		return "addUI";
	}
	//部署
	public String add() throws Exception{
		ZipInputStream ziStream=new ZipInputStream(new FileInputStream(upload));
		try{
		processDefinitionService.deploy(ziStream);
		}finally{
			ziStream.close();
		}
		return "toList";
	}
	//删除（级联）
	public String delete() throws Exception{
		key=URLDecoder.decode(key,"utf-8");
		processDefinitionService.deleteByKey(key);
		return "toList";
	}
	//下载图片
	public String downloadImage() throws Exception{
		id=URLDecoder.decode(id,"utf-8");
		inputStream=processDefinitionService.getProcessDefinitionAsStream(id);
		return "downloadImage";
	}
	
	
	//===========================
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	
}
