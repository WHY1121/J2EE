package cn.itcast.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.itcast.oa.domain.Department;

public class DepartmentUtil {
    /**
     * 返回所有部门树
     * @param topList
     * @return
     */
	public static List<Department> getAllDepartment(List<Department> topList) {
		List<Department> list=new ArrayList<Department>();
		walkDepartmentTree(topList,"┣",list);
		return list;
	}
    /**
     * 转换为部门树
     * @param topList
     * @param list
     */
	private static void walkDepartmentTree(Collection<Department> topList,String prefix,List<Department> list) {
		for(Department department:topList){
			//顶点
			Department copy=new Department();// 原对象是在Session中的对象，是持久化状态，所以要使用副本。
			copy.setId(department.getId());
			copy.setName(prefix+department.getName());
			list.add(copy);
			//子树
			walkDepartmentTree(department.getChildren(),"　"+prefix,list);
		}
		
	}

}
