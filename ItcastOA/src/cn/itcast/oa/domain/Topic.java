package cn.itcast.oa.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javassist.expr.NewArray;


/**
 * 主帖
 * @author fsdfsdsss
 *
 */
public class Topic extends Article implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//普通贴
	public static final int TYPR_NORMAL=0;
	//精华贴
	public static final int TYPE_BEST=1; 
	//置顶帖
	public static final int TYPE_TOP=2;
	
	private ForumManage forumManage;//所属板块
	
	private Set<Reply> replies=new HashSet<Reply>();
	
	private int type;//主帖类型
	
	private int replyCount;//回复数量
	
	private Reply lastreReply;//最后回复
	
	private Date lastUpdateTime;//最后更新时间(主题发表时间或最后回复时间)

	public ForumManage getForumManage() {
		return forumManage;
	}

	public void setForumManage(ForumManage forumManage) {
		this.forumManage = forumManage;
	}

	public Set<Reply> getReplies() {
		return replies;
	}

	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public Reply getLastreReply() {
		return lastreReply;
	}

	public void setLastreReply(Reply lastreReply) {
		this.lastreReply = lastreReply;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
}
