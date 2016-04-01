package cn.itcast.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Role;
import com.opensymphony.xwork2.ActionContext;



@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	
     
	/**
	 * 列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		List<Role> roleList=roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}
	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		roleService.delete(model.getId());
		return "toList";
	}
    /**
     * 增加
     * @return
     * @throws Exception
     */
	public String add() throws Exception {
        roleService.save(model);
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
		//我疑惑的地方
		Role role=roleService.getById(model.getId());
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		roleService.update(role);
		return "toList";
	}
	  /**
     * 修改页面
     * @return
     * @throws Exception
     */
	public String editUI() throws Exception {
        
        Role role=roleService.getById(model.getId());
        ActionContext.getContext().getValueStack().push(role);
		return "editUI";
	}
	
	


}
