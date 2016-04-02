package cn.itcast.oa.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;
import cn.itcast.oa.base.BaseDaoImpl;

import cn.itcast.oa.domain.Department;
import cn.itcast.oa.service.DepartmentService;

@Service
@SuppressWarnings("unchecked")
public class DepartmentServiceImpl extends BaseDaoImpl<Department> implements DepartmentService  {

	
	@Override
	public List<Department> findTopList() {
		return getSession().createQuery(//
				"FROM Department d WHERE d.parent IS NULL")//
				.list();
	}

	@Override
	public List<Department> findAllChildren(Long parentId) {
		
		return  getSession().createQuery(//
				"FROM Department d WHERE d.parent.id=?")//
				.setParameter(0, parentId)//
				.list();
	}

	
	
}
