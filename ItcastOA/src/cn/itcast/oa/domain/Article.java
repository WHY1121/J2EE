package cn.itcast.oa.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 文章
 * @author fsdfsdsss
 *
 */
public class Article implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String title;//标题
	private String faceIcon;//表情
	private String content;//内容
	private Date  createDate ;//创建时间
	private User  author;//作者
	private String ipAddr;//ip地址
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFaceIcon() {
		return faceIcon;
	}
	public void setFaceIcon(String faceIcon) {
		this.faceIcon = faceIcon;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	

}
