package cn.itcast.oa.view.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;

@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply>{
     
	private Long topicId;
	/**
	 * 回帖页面
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception{
		Topic topic=topicService.getById(topicId);
		ActionContext.getContext().put("topic", topic);
		
		return "addUI";
	}
	/**
	 * 回帖
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		//topic已经封装可content,title,faceIcon
		model.setTopic(topicService.getById(topicId));
		model.setAuthor(getSessionUser());
		
		model.setCreateTime(new Date());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		replyService.save(model);
		return "toTopicShow";
	}

	
	//===============================
	
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	
}
