package cn.itcast.oa.service;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.User;

public interface UserService extends BaseDao<User>{
    /**
     * 根据登录名和密码查询用户
     * @param loginName
     * @param password
     * @return
     */
	User getByLoginNameAndPwd(String loginName, String password);
	
	

}
