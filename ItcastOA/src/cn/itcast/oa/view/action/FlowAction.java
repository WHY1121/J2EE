package cn.itcast.oa.view.action;

import java.io.File;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Application;
import cn.itcast.oa.domain.ApplicationTemplate;
import cn.itcast.oa.util.HqlHelper;

@Controller
@Scope("prototype")
public class FlowAction extends BaseAction {
	
	private File upload;
	private Long applicationTemplateId;
	private String status;
	
	/** 起草申请（模板列表） */
	public String applicationTemplateList() throws Exception {
		List<ApplicationTemplate> appList=applicationTemplateService.findAll();
		ActionContext.getContext().put("appList",appList);
		return "applicationTemplateList";
	}

	/** 提交申请页面 */
	public String submitUI() throws Exception {
		
		return "submitUI";
	}

	/** 提交申请 */
	public String submit() throws Exception {

		// 保存提交申请的信息
		Application application = new Application();
		application.setApplicant(getSessionUser());
		application.setPath(fileUpload(upload));
		application.setApplicationTemplate(applicationTemplateService
				.getById(applicationTemplateId));
		applicationService.submit(application);

		return "toMyApplicationList"; //成功后跳转到我的申请查询页面
	}

	/** 我的申请查询 */
	public String myApplicationList() throws Exception {
		new HqlHelper(Application.class, "a")//
		.addCondition("a.applicant=?", getSessionUser())
		.addCondition(applicationTemplateId != null, "a.applicationTemplate.id=?",applicationTemplateId)
		.addCondition(StringUtils.isNotEmpty(status), "a.status=?", status)
		.addOrderBy("a.applyTime", false)
		.buildPageBean(pageNum, applicationService);
		// 准备数据
		List<ApplicationTemplate> applicationTemplateList = applicationTemplateService.findAll();
		ActionContext.getContext().put("applicationTemplateList", applicationTemplateList);
		return "myApplicationList";
	}

	// ================================== 审批人有关

	/** 待我审批（我的任务列表） */
	public String myTaskList() throws Exception {
		
		return "myTaskList";
	}

	/** 审批处理页面 */
	public String approveUI() throws Exception {
		
		return "approveUI";
	}

	/** 审批处理 */
	public String approve() throws Exception {
		
		return "toMyTaskList"; // 成功后转到待我审批页面
	}

	/** 查看流转记录 */
	public String approveHistory() throws Exception {
	
		return "approveHistory";
	}

	
	
	//============================================================
	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public Long getApplicationTemplateId() {
		return applicationTemplateId;
	}

	public void setApplicationTemplateId(Long applicationTemplateId) {
		this.applicationTemplateId = applicationTemplateId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
