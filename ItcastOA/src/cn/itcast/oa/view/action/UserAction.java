package cn.itcast.oa.view.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.util.DepartmentUtil;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
     
	private Long departmentId;
	private Long[] roleIds;
	/**
	 * 用户列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		List<User> userList = userService.findAll();
		ActionContext.getContext().put("userList", userList);
		return "list";
	}
    
	/**
	 * 增加界面
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
		//部门显示树状结构
        List<Department> topList=departmentService.findTopList();
		List<Department> dList=DepartmentUtil.getAllDepartment(topList);
		List<Role> roleList=roleService.findAll();
		ActionContext.getContext().put("dList", dList);
		ActionContext.getContext().put("roleList", roleList);		
		return "addUI";
	}
    /**
     * 增加
     * @return
     * @throws Exception
     */
	public String add() throws Exception {
		List<Role> roles=roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roles));
		Department department=departmentService.getById(departmentId);
		model.setDepartment(department);
		model.setPassword(DigestUtils.md5Hex("1234"));
		userService.save(model);
		return "toList";
	}
    /**
     * 删除
     * @return
     * @throws Exception
     */
	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";
	}
    /**
     * 修改界面
     * @return
     * @throws Exception
     */
	public String editUI() throws Exception {
		List<Department> topList=departmentService.findTopList();
		
		List<Department> dList=DepartmentUtil.getAllDepartment(topList);
		
		List<Role> roleList=roleService.findAll();
		User user=userService.getById(model.getId());
		ActionContext.getContext().put("dList", dList);
		ActionContext.getContext().put("roleList", roleList);
		ActionContext.getContext().getValueStack().push(user);
		if(user.getDepartment()!=null){
			departmentId=user.getDepartment().getId();
		}
		if(user.getRoles().size()>0){
			roleIds=new Long[user.getRoles().size()];
			int index=0;
			for(Role role:user.getRoles()){
				roleIds[index++]=role.getId();
			}
		}
		
		return "addUI";
	}
     /**
      * 修改
      * @return
      * @throws Exception
      */
	public String edit() throws Exception {
		//取出用户
		User user=userService.getById(model.getId());
		//修改普通属性
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		//查到部门
		Department department=departmentService.getById(departmentId);
		user.setDepartment(department);
		//查到岗位
		List<Role> roles=roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roles));
		//更新用户
		userService.update(user);
		return "toList";
	}
	/**
	 * 初始化密码为1234
	 * @return
	 * @throws Exception
	 */
	public String initPassword() throws Exception {
		User user=userService.getById(model.getId());
		user.setPassword(DigestUtils.md5Hex("1234"));
		userService.update(user);
		return "toList";
	}
	
	
	
	/**
     * 登录界面
     * @return
     * @throws Exception
     */
	public String loginUI() throws Exception {
	
		return "loginUI";
	}
	/**
     * 登录
     * @return
     * @throws Exception
     */
	public String login() throws Exception {
	   User user=userService.getByLoginNameAndPwd(model.getLoginName(),model.getPassword());
	   if(user==null){
		   addFieldError("login","用户名或密码不正确");
		   return "loginUI";
	   }else{
		   ActionContext.getContext().getSession().put("user", user);
		   return "toIndex";
	   }
		
	}
	
	/**
     * 注销界面
     * @return
     * @throws Exception
     */
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("user");
	
		return "logout";
	}
//=================================================

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}
	
}
