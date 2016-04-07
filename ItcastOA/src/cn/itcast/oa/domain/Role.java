package cn.itcast.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 实体：岗位
 * 
 * @author why
 * 
 */
public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String description;
	private Set<User> users = new HashSet<User>();
	
	private Set<Priviledge> priviledges=new HashSet<Priviledge>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Priviledge> getPriviledges() {
		return priviledges;
	}

	public void setPriviledges(Set<Priviledge> priviledges) {
		this.priviledges = priviledges;
	}
	

}
