package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.Priviledge;

public interface PriviledgeService extends BaseDao<Priviledge>{
    /**
     * 查询顶层权限
     * @return
     */
	List<Priviledge> findTopList();
     /**
      * 返回数据库存储的url
      * @return
      */
	List<String> getAllPriviledgeUrl();

}
