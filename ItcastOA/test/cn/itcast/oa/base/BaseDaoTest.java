package cn.itcast.oa.base;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.itcast.oa.dao.RoleDao;
import cn.itcast.oa.dao.UserDao;
import cn.itcast.oa.dao.impl.RoleDaoImpl;
import cn.itcast.oa.dao.impl.UserDaoImpl;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.domain.User;

public class BaseDaoTest {

	@Test
	public void testGetById() {
		UserDao userdao=new UserDaoImpl();
		RoleDao roleDao=new RoleDaoImpl();
		
		User user=userdao.getById(1L);
		Role role=roleDao.getById(1L);
	}

}
