package cn.itcast.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.util.DepartmentUtil;

import com.opensymphony.xwork2.ActionContext;


@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department>{
    
	private Long parentId;
	
	
	public String list() throws Exception{
		List<Department> deList=null;
		if(parentId==null){
			//查询所有顶级部门列表
			deList=departmentService.findTopList();
		}else{
			deList=departmentService.findAllChildren(parentId);
			Department parent=departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		ActionContext.getContext().put("deList", deList);
		return "list";
	}
	public String addUI() throws Exception{
		//显示部门树状结构
		List<Department> topList=departmentService.findTopList();
		List<Department> dList=DepartmentUtil.getAllDepartment(topList);
		ActionContext.getContext().put("dList", dList);
		return "addUI";
	}
	public String add() throws Exception{
		//1.新建对象并封装属性，也可以使用model
		model.setParent(departmentService.getById(parentId));
		//2.保存到数据库
		departmentService.save(model);
		return "toList";
	}
	public String delete() throws Exception{
		departmentService.delete(model.getId());
		return "toList";
	}
	public String editUI() throws Exception{
		//树状结构
		List<Department> topList=departmentService.findTopList();
		List<Department> dList=DepartmentUtil.getAllDepartment(topList);
		ActionContext.getContext().put("dList", dList);
		//回显信息
		Department department=departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		if(department.getParent()!=null){
			parentId=department.getParent().getId();
		}
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
