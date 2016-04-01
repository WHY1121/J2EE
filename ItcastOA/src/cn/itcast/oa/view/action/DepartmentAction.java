package cn.itcast.oa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import cn.itcast.oa.dao.DepartmentDao;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.service.DepartmentService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class DepartmentAction extends ActionSupport implements ModelDriven<Department>{
      
	@Resource
	private  DepartmentService departmentService;
	
	private Department model=new Department();
	@Override
	public Department getModel() {
		return model;
	}
	
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
		departmentService.edit(model);
		return "toList";
	}

}
