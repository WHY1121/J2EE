package cn.itcast.oa.service;


import java.util.List;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.Department;

public interface DepartmentService extends BaseDao<Department>{
     /**
      * 得到所有的顶级部门列表
      * @return
      */
	List<Department> findTopList();
	/**
	 * 查找所有孩子
	 * @return
	 */
	List<Department> findAllChildren(Long parentId);
    
	

}
