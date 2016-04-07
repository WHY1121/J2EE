package cn.itcast.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限
 * @author Why
 *
 */
public class Priviledge implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String url;//路径
	
	private String name;//权限名字
	
	private String icon;//图片名字
	
	private Priviledge parent;//父亲接点
	
	private Set<Priviledge> children = new HashSet<Priviledge>(); ;//孩子
	
	private Set<Role> roles = new HashSet<Role>();//岗位

	
	
	
	public Priviledge() {
		
	}

	public Priviledge(String url, String name, String icon, Priviledge parent) {
		this.url = url;
		this.name = name;
		this.icon = icon;
		this.parent = parent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
    

	public Priviledge getParent() {
		return parent;
	}

	public void setParent(Priviledge parent) {
		this.parent = parent;
	}

	public Set<Priviledge> getChildren() {
		return children;
	}

	public void setChildren(Set<Priviledge> children) {
		this.children = children;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	

}
