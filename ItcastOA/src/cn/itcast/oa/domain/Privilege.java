package cn.itcast.oa.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 权限
 * @author Why
 *
 */
public class Privilege {
	
	private Long id;
	
	private String url;//路径
	
	private String name;//权限名字
	
	private String icon;//图片名字
	
	private Privilege parent;//父亲接点
	
	private Set<Privilege> children = new HashSet<Privilege>(); ;//孩子
	
	private Set<Role> roles = new HashSet<Role>();//岗位

	
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Privilege getParent() {
		return parent;
	}

	public void setParent(Privilege parent) {
		this.parent = parent;
	}

	public Set<Privilege> getChildren() {
		return children;
	}

	public void setChildren(Set<Privilege> children) {
		this.children = children;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	

}
