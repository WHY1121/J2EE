package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.dao.DepartmentDao;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.service.DepartmentService;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	
	@Resource
	private DepartmentDao departmentDao;
	
	@Override
	public List<Department> findAll() {
		List<Department> departments=departmentDao.findAll();
		return departments;
	}

	@Override
	public void save(Department department) {
		departmentDao.save(department);
		
	}

	@Override
	public void edit(Department department) {
		departmentDao.update(department);
		
	}

	@Override
	public Department getById(Long id) {
		Department department=departmentDao.getById(id);
		return department;
	}

	@Override
	public void delete(Long id) {
		departmentDao.delete(id);
		
	}

}
