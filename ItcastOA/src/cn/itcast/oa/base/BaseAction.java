package cn.itcast.oa.base;

import javax.annotation.Resource;



import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.DepartmentService;
import cn.itcast.oa.service.ForumManageService;
import cn.itcast.oa.service.ForumService;
import cn.itcast.oa.service.PriviledgeService;
import cn.itcast.oa.service.ProcessDefinitionService;
import cn.itcast.oa.service.ReplyService;
import cn.itcast.oa.service.RoleService;
import cn.itcast.oa.service.TopicService;
import cn.itcast.oa.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction extends ActionSupport{
	@Resource
	protected  DepartmentService departmentService;
	@Resource
	protected  UserService userService;
	@Resource
	protected  RoleService roleService;
	@Resource
	protected ForumManageService forumManageService;
	@Resource
	protected TopicService topicService;
	@Resource
	protected ForumService forumService;
	@Resource
	protected ReplyService replyService;
	
	
	@Resource  
	protected PriviledgeService priviledgeService;
	
	@Resource
	protected ProcessDefinitionService processDefinitionService;
	
	
	
    protected int pageNum=1;
    
    public int getPageNum() {
		return pageNum;
	}



	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
    /**
	 * 获取session里的user
	 * @return
	 */
    protected User getSessionUser(){
		
		return (User)ActionContext
				.getContext()
				.getSession().get("user");
	}

}
