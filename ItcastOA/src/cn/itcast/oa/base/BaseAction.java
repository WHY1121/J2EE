package cn.itcast.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import cn.itcast.oa.domain.Department;
import cn.itcast.oa.service.DepartmentService;
import cn.itcast.oa.service.RoleService;
import cn.itcast.oa.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("unchecked")
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
	
	@Resource
	protected  DepartmentService departmentService;
	@Resource
	protected  UserService userService;
	@Resource
	protected  RoleService roleService;
	
	protected T model;
	
	public BaseAction() {
		
		try {
			ParameterizedType psType=(ParameterizedType)this.getClass()
					.getGenericSuperclass();
			Class clzz=(Class)psType.getActualTypeArguments()[0];
			model=(T) clzz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	public T getModel() {
		return model;
	}

}
