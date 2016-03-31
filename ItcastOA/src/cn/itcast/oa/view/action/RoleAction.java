package cn.itcast.oa.view.action;

import java.util.List;


import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.domain.Role;
import cn.itcast.oa.service.RoleService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


@Controller
@Scope("prototype")
public class RoleAction extends ActionSupport implements ModelDriven<Role> {
	
	
	private Role model=new Role();
	@Override
	public Role getModel() {
		return model;
	}
	
	
	
	@Resource
	private RoleService roleService;
     
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
		roleService.edit(model);
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
