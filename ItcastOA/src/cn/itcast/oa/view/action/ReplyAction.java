package cn.itcast.oa.view.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Reply;

@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply>{
     
	
	/**
	 * 回帖页面
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception{
		
		
		return "addUI";
	}
	/**
	 * 回帖
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		
		
		return "toTopic";
	}
}
