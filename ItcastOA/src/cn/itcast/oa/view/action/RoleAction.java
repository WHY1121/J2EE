package cn.itcast.oa.view.action;

import java.util.HashSet;
import java.util.List;

import javax.jms.Session;
import javax.swing.text.html.ListView;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Priviledge;
import cn.itcast.oa.domain.Role;
import com.opensymphony.xwork2.ActionContext;



@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	
     private Long[] priviledgeIds;
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
		return "addUI";
	}
	 /**
     * 修改权限
     * @return
     * @throws Exception
     */
	public String setPriviledge() throws Exception {
		//获取源对象
	    Role role=roleService.getById(model.getId());
	    //获取权限id
		List<Priviledge> priviledges=priviledgeService.getByIds(priviledgeIds);
		role.setPriviledges(new HashSet<Priviledge>(priviledges));
		roleService.update(role);
		return "toList";
	}
	  /**
    * 设置权限页面
    * @return
    * @throws Exception
    */
	public String setPriviledgeUI() throws Exception {
       //返回权限数据
	   List<Priviledge> topPriviledges=priviledgeService.findTopList();
	   ActionContext.getContext().put("topPriviledges", topPriviledges);
	   //显示为岗位安排权限
       Role role=roleService.getById(model.getId());
       ActionContext.getContext().put("role", role);
       //回显数据
       priviledgeIds=new Long[role.getPriviledges().size()];
       int index=0;
       for(Priviledge priviledge:role.getPriviledges()){
    	   priviledgeIds[index++]=priviledge.getId();  
       }
       ActionContext.getContext().getValueStack().push(priviledgeIds);
		return "setPriviledgeUI";
	}
	//===============================
	public Long[] getPriviledgeIds() {
		return priviledgeIds;
	}
	public void setPriviledgeIds(Long[] priviledgeIds) {
		this.priviledgeIds = priviledgeIds;
	}
	


}
