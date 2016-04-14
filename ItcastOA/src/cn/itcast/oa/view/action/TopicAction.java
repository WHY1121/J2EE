package cn.itcast.oa.view.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.domain.User;


@Controller
@Scope("prototype")
public class TopicAction extends BaseAction<Topic>{

	private Long forumId;

	public String show() throws Exception{
		//准备数据：主题
		Topic topic=topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);
//		//准备数据：回复
//		List<Reply> replyList=replyService.findByTopic(topic);
//		ActionContext.getContext().put("replyList", replyList);
		
		//准备数据：分页显示
		PageBean pageBean=replyService.getPageBean(pageNum,topic);
		//放入栈顶
		ActionContext.getContext().getValueStack().push(pageBean);
		
		
		
		return "show";
	}
	
	
	
	/**
	 * 发帖页面
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception{
		Forum forum=forumService.getById(forumId);
		ActionContext.getContext().put("forum",forum);
		
		return "addUI";
	}
	/**
	 * 发帖
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
	
		
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setAuthor(getSessionUser());
		model.setCreateTime(new Date());
		model.setForum(forumService.getById(forumId));
		topicService.save(model);
		return "toShow";
	}
	
	///============================
	public Long getForumId() {
		return forumId;
	}
	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}




	
	
	

}
