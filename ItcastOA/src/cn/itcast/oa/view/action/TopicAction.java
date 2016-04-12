package cn.itcast.oa.view.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.domain.User;


@Controller
@Scope("prototype")
public class TopicAction extends BaseAction<Topic>{

	
	/**
	 * 主题展示
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception{
		Forum forum=forumService.getById(model.getId());
		ActionContext.getContext().put("forum",forum);
		List<Topic> topiclList=topicService.findAll(forum);
		ActionContext.getContext().put("topicList", topiclList);
		return "show";
	}
	/**
	 * 发帖页面
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception{
		Forum forum=forumService.getById(model.getId());
		ActionContext.getContext().put("forum",forum);
		
		return "addUI";
	}
	/**
	 * 发帖
	 * @return
	 * @throws Exception
	 */
	public String add(HttpServletRequest request) throws Exception{
		//TODO
		Forum forum=forumService.getById(model.getId());
		model.setTitle(model.getTitle());
		model.setFaceIcon(model.getFaceIcon());
		model.setContent(model.getContent());
		
		model.setIpAddr(request.getRemoteAddr());
		model.setAuthor(getSessionUser());
		model.setForum(forum);
		topicService.save(model);
		return "toTopicShow";
	}
	

}
