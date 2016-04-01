package cn.itcast.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Department;
import com.opensymphony.xwork2.ActionContext;


@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department>{
    
	private Long parentId;
	
	
	public String list() throws Exception{
		List<Department> deList=departmentService.findAll();
		ActionContext.getContext().put("deList", deList);
		return "list";
	}
	public String addUI() throws Exception{
		return "addUI";
	}
	public String add() throws Exception{
		departmentService.save(model);
		return "toList";
	}
	public String delete() throws Exception{
		departmentService.delete(model.getId());
		return "toList";
	}
	public String editUI() throws Exception{
		Department department=departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		return "editUI";
	}
	public String edit() throws Exception{
		Department department=departmentService.getById(model.getId());
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		department.setParent(departmentService.getById(parentId));
		departmentService.update(department);
		return "toList";
	}
	
	
	//========================================
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
