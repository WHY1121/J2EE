package cn.itcast.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
@Controller
@Scope("prototype")
public class ForumAction extends BaseAction<Forum>{

	
	
	/**
	 * 板块列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception{
		List<Forum> forums=forumService.findAll();
		ActionContext.getContext().put("forums", forums);
		return "list";
	}
}
