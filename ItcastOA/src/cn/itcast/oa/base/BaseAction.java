package cn.itcast.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import cn.itcast.oa.domain.Department;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.DepartmentService;
import cn.itcast.oa.service.ForumManageService;
import cn.itcast.oa.service.ForumService;
import cn.itcast.oa.service.PriviledgeService;
import cn.itcast.oa.service.ReplyService;
import cn.itcast.oa.service.RoleService;
import cn.itcast.oa.service.TopicService;
import cn.itcast.oa.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("unchecked")
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
	
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
	
	protected T model;
	
	public BaseAction() {
		
		try {
			ParameterizedType psType=(ParameterizedType)this.getClass()
					.getGenericSuperclass();
			Class clzz=(Class)psType.getActualTypeArguments()[0];
			model=(T) clzz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	public T getModel() {
		return model;
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
