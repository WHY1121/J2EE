package cn.itcast.oa.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.itcast.oa.domain.Priviledge;
import cn.itcast.oa.service.PriviledgeService;
/**
 * 配置监听器，初始化权限数据
 * @author fsdfsdsss
 *
 */
public class InitServletContextListener implements ServletContextListener{

	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application=sce.getServletContext();
		//得到service的实例对象
		ApplicationContext ac=WebApplicationContextUtils.getWebApplicationContext(application);
		PriviledgeService priviledgeService=(PriviledgeService) ac.getBean("priviledgeServiceImpl");
		//准备所有顶级菜单项
		List<Priviledge> priviledges=priviledgeService.findTopList();
		application.setAttribute("priviledges", priviledges);
		System.out.println("================菜单数据初始化完毕");
		
		//返回数据库存储的所有权限
		List<String> allPriviledgeURls=priviledgeService.getAllPriviledgeUrl();
		application.setAttribute("allPriviledgeURls", allPriviledgeURls);
		System.out.println("================存储url初始化完毕");
		
		
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		
	}

	

}
