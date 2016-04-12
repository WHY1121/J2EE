package cn.itcast.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;


import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;


@Controller
@Scope("prototype")
public class ForumManageAction extends BaseAction<Forum>{

	
	
	
	public String list() throws Exception {
	    List<Forum> foList=forumManageService.findAll();
	    ActionContext.getContext().put("foList", foList);
		return "list";
	}
	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
	   
		forumManageService.delete(model.getId());
		return "toList";
	}
    /**
     * 增加
     * @return
     * @throws Exception
     */
	public String add() throws Exception {
        forumManageService.save(model);
		return "toList";
	}
	/**
	 * 增加页面
	 * @return
	 * @throws Exception
	 */
	public String addUI() throws Exception {
         
		return "addUI";
	}
     /**
      * 修改
      * @return
      * @throws Exception
      */
	public String edit() throws Exception {
		Forum fManage=forumManageService.getById(model.getId());
		fManage.setName(model.getName());
		fManage.setDescription(model.getDescription());
		forumManageService.update(fManage);
		return "toList";
	}
	  /**
     * 修改页面
     * @return
     * @throws Exception
     */
	public String editUI() throws Exception {
		Forum fManage=forumManageService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(fManage);
      
		return "addUI";
	}
	 /**
     * 上移
     * @return
     * @throws Exception
     */
	public String moveUp() throws Exception {
		forumManageService.moveUp(model.getId());
		return "toList";
	}
	  /**
    * 下移
    * @return
    * @throws Exception
    */
	public String moveDown() throws Exception {
       
		forumManageService.moveDown(model.getId());
		return "toList";
	}
}
