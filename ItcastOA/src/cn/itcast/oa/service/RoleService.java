package cn.itcast.oa.service;

import java.util.List;
import cn.itcast.oa.domain.Role;

public interface RoleService {
    /**
     * 查询岗位列表
     * @return
     */
	List<Role> findAll();
    /**
     * 删除岗位
     * @param id
     */
	void delete(Long id);
	/**
	 * 保存岗位
	 * @param model
	 */
	void save(Role role);
	/**
	 * 根据id查询岗位
	 * @param id
	 * @return
	 */
	Role getById(Long id);
	/**
	 * 修改岗位
	 * @param model
	 */
	void edit(Role role);

}
