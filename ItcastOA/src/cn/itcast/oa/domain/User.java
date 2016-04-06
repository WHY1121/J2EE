package cn.itcast.oa.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 实体：实体用户
 * @author why
 * 
 */
public class User {
	private Long id;
	private Department department;
	private Set<Role> roles = new HashSet<Role>();

	private String loginName; // 登录名
	private String password; // 密码
	private String name; // 真实姓名
	private String gender; // 性别
	private String phoneNumber; // 电话号码
	private String email; // 电子邮件
	private String description; // 说明
    
	
	
	/**
	 * 根据权限明判断有没有该权限
	 * @param name
	 * @return
	 */
	public boolean hasPriviledgeByName(String priviledgeName) {
		/**
		 *超级管理员
		 */
		if (isAdmin()) {
			return true;
		}
	   //普通用户
		for (Role role : roles) {
			for (Priviledge priviledge : role.getPriviledges()) {
				if (priviledge.getName().equals(priviledgeName)) {
					return true;
				}
			}
		}

		return false;

	}
	
	private boolean isAdmin() {
		
		return "admin".equals(loginName);
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
