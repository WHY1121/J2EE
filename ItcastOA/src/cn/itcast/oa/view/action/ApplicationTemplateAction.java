package cn.itcast.oa.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.codehaus.janino.Java.NewAnonymousClassInstance;
import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.ModelBaseAction;
import cn.itcast.oa.domain.ApplicationTemplate;

@Controller
@Scope("prototype")
public class ApplicationTemplateAction extends
		ModelBaseAction<ApplicationTemplate> {

	private File upload;// 上传文件
	private InputStream inputStream;

	// 列表
	public String list() throws Exception {
		List<ApplicationTemplate> apList = applicationTemplateService.findAll();
		ActionContext.getContext().put("apList", apList);
		return "list";
	}

	// 增加页面
	public String addUI() throws Exception {
		List<ProcessDefinition> pList = processDefinitionService
				.findAllLatestVersions();
		ActionContext.getContext().put("pList", pList);
		return "saveUI";
	}

	// 增加
	public String add() throws Exception {
		//上传文件
		String path = fileUpload(upload);
		model.setPath(path);
		// 保存
		applicationTemplateService.save(model);
		return "toList";
	}

	

	// 下载
	public String download() throws Exception {
		ApplicationTemplate applicationTemplate=applicationTemplateService.getById(model.getId());
		inputStream=new FileInputStream(applicationTemplate.getPath());
		//文件名解决中文乱码问题
		String fileName=URLEncoder.encode(applicationTemplate.getName(), "utf-8");//第一种方式
		//String fileName=new String(applicationTemplate.getName().getBytes("GBK"),"iso-8859-1");//第二种方案，内存中没有乱码问题 安gbk编码后。转为本地编码
	    ActionContext.getContext().put("fileName",fileName);
		return "download";
	}

	// 删除
	public String delete() throws Exception {
		applicationTemplateService.delete(model.getId());
		return "toList";
	}

	// 修改页面
	public String editUI() throws Exception {
		//准备数据
		List<ProcessDefinition> pList = processDefinitionService
				.findAllLatestVersions();
		ActionContext.getContext().put("pList", pList);
		//准备
        ApplicationTemplate applicationTemplate=applicationTemplateService.getById(model.getId());
        ActionContext.getContext().getValueStack().push(applicationTemplate);
		return "saveUI";
	}

	// 修改
	public String edit() throws Exception {
        ApplicationTemplate applicationTemplate=applicationTemplateService.getById(model.getId());
        applicationTemplate.setName(model.getName());
        applicationTemplate.setProcessDefinitionKey(model.getProcessDefinitionKey());

        if(upload!=null){
        	File file=new File(applicationTemplate.getPath());
        	if(file.exists()){
        		file.delete();
        	}
        	String path=fileUpload(upload);
        	applicationTemplate.setPath(path);
        }
        //更新到数据库
        applicationTemplateService.update(applicationTemplate);
        
		return "toList";
	}


	// =====================================
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
