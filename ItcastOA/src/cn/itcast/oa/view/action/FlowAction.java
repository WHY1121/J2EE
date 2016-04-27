package cn.itcast.oa.view.action;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Application;
import cn.itcast.oa.domain.ApplicationTemplate;
import cn.itcast.oa.domain.ApproveInfo;
import cn.itcast.oa.domain.TaskView;
import cn.itcast.oa.util.HqlHelper;

@Controller
@Scope("prototype")
public class FlowAction extends BaseAction {
	
	private File upload;
	private Long applicationTemplateId;
	private String status;
	
	private Long applicationId;
	private String comment;
	private boolean approval;
	private String taskId;
	private String outcome;
	
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
		List<TaskView> taskViews=applicationService.getMyTaskView(getSessionUser());

		ActionContext.getContext().put("taskViews",taskViews);
		return "myTaskList";
	}

	/** 审批处理页面 */
	public String approveUI() throws Exception {
		Set<String> outcomes = applicationService.getOutcomesByTaskId(taskId);
		ActionContext.getContext().put("outcomes", outcomes);
		return "approveUI";
	}

	/** 审批处理 */
	public String approve() throws Exception {
		ApproveInfo approveInfo=new ApproveInfo();
		approveInfo.setApplication(applicationService.getById(applicationId));
		approveInfo.setComment(comment);
		approveInfo.setApproval(approval);
		approveInfo.setApproverUser(getSessionUser());
		approveInfo.setApproveTime(new Date());
		// 调用用业务方法（保存本次审批信息，并办理完任务，并维护申请的状态）
		applicationService.approval(approveInfo,taskId,outcome);
		return "toMyTaskList"; // 成功后转到待我审批页面
	}

	/** 查看流转记录 */
	public String approveHistory() throws Exception {
		Application application = applicationService.getById(applicationId);
		ActionContext.getContext().put("approveInfos", application.getApproveInfos());
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

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	
	
	
	
}
