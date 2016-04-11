package cn.itcast.oa.domain;

import java.io.Serializable;

public class Reply extends Article implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Topic topic;//属于那个主题
	
	
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
}
