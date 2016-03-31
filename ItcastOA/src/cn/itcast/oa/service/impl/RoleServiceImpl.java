package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.dao.RoleDao;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
    
	@Resource
	private RoleDao roleDao;

	@Override
	public List<Role> findAll() {
		List<Role> roleList=roleDao.findAll();
		return roleList;
	}

	@Override
	public void delete(Long id) {
		roleDao.delete(id);
		
	}

	@Override
	public void save(Role role) {
		roleDao.save(role);
		
	}

	@Override
	public Role getById(Long id) {
		Role role=roleDao.getById(id);
		return role;
	}

	@Override
	public void edit(Role role) {
		roleDao.update(role);
		
	}
}
