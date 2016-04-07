package cn.itcast.oa.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.domain.Priviledge;
import cn.itcast.oa.service.PriviledgeService;


@Service
public class PriviledgeServiceImpl extends BaseDaoImpl<Priviledge> implements PriviledgeService {

	@Override
	public List<Priviledge> findTopList() {
		
		return getSession()
				.createQuery("FROM Priviledge p WHERE p.parent IS NULL")
				.list();
	}

	@Override
	public List<String> getAllPriviledgeUrl() {
		
		return getSession()
				.createQuery("SELECT distinct p.url FROM Priviledge p WHERE p.url IS NOT NULL")
				.list();
	}

	
}
