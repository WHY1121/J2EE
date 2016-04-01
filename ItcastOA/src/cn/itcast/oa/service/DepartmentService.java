package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.Department;

public interface DepartmentService {
    
	
	/**
	 * 查找所有部门
	 * @return
	 */
	List<Department> findAll();
    /**
     * 保存部门
     * @param model
     */
	void save(Department department);
	/**
	 * 修改部门
	 * @param model
	 */
	void edit(Department department);
	/**
	 * 根据id查询部门
	 * @param id
	 * @return
	 */
	Department getById(Long id);
	/**
	 * 删除部门
	 * @param id
	 */
	void delete(Long id);

}
