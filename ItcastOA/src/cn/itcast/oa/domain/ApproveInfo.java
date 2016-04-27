package cn.itcast.oa.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 审批信息
 * @author fsdfsdsss
 *
 */
public class ApproveInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String comment;//审批的信息
	private boolean approval;//审批的结果
	private Date approveTime;//审批时间
	private User approverUser;//审批人
	
	
	/**
	 * 申请信息
	 */
	private Application application;
	
	
	
	
	
	
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Date getApproveTime() {
		return approveTime;
	}
	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}
	public User getApproverUser() {
		return approverUser;
	}
	public void setApproverUser(User approverUser) {
		this.approverUser = approverUser;
	}
	
	
	
	
}
