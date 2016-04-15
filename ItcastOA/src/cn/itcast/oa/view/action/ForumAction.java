package cn.itcast.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.util.HqlHelper;

@Controller
@Scope("prototype")
public class ForumAction extends BaseAction<Forum> {

	/**
	 * 0:'全部主题'<br>
	 * 1:'全部精华贴'<br>
	 * 2:'当天的主题'<br>
	 * 3:'本周的主题'<br>
	 * 4:'本月的主题'
	 */
	private int viewType = 0;
	/**
	 * 0:'默认排序（按最后更新时间排序，但所有置顶帖都在前面）'<br>
	 * 1:'按最后更新时间排序'<br>
	 * 2:'按主题发表时间排序'<br>
	 * 3:'按回复数量排序'
	 */
	private int orderBy = 0;
	/**
	 * true为升序<br>
	 * false为降序
	 */
	private boolean asc=false;

	/**
	 * 板块列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		List<Forum> forums = forumService.findAll();
		ActionContext.getContext().put("forums", forums);
		return "list";
	}

	/**
	 * 主题展示
	 * 
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().put("forum", forum);
		// List<Topic> topiclList=topicService.findByForum(forum);
		// ActionContext.getContext().put("topicList", topiclList);
		// PageBean pageBean=topicService.getPageBean(pageNum,forum);
		// ActionContext.getContext().getValueStack().push(pageBean);
		// 公共方法分页
//		String hql = "FROM Topic t WHERE t.forum=? order by (CASE t.type WHEN 2 then 2 ELSE 0 END) DESC,t.lastUpdateTime DESC";
//		Object[] parameters = new Object[] { forum };
//		PageBean pageBean = topicService.getPageBean(pageNum, hql, parameters);
//		ActionContext.getContext().getValueStack().push(pageBean);
		
		
		
		
		//hqlHelper的查询工具类             
	new HqlHelper(Topic.class,"t")
	    .addCondition("t.forum=?", forum)
		.addCondition(viewType==1,"t.type=?", Topic.TYPE_BEST)//1:'全部精华贴
//		.addCondition(viewType==2,"t.createTime=?", 2)   //2:'当天的主题          	
//		.addCondition(viewType==3,"t.createTime=?", 3)      //3:'本周的主题        	
//		.addCondition(viewType==4,"t.createTime=?", 4)        	//4:'本月的主题      	
		.addOrderBy(orderBy==0,"(CASE t.type WHEN 2 then 2 ELSE 0 END)", false)  
		.addOrderBy(orderBy==0,"t.lastUpdateTime", false)    //    * 0:'默认排序（按最后更新时间排序，但所有置顶帖都在前面）
		.addOrderBy(orderBy==1, "t.lastUpdateTime", asc)//	  * 1:'按最后更新时间排序
		.addOrderBy(orderBy==2, "t.createTime", asc)//     * 2:'按主题发表时间排序
		.addOrderBy(orderBy==3, "t.replyCount", asc)// * 3:'按回复数量排序'
		.buildPageBean(pageNum,topicService);//struct2环境使用	  
		
		
		
		 
		
		                           	                 
		return "show";                                           
	}
}
