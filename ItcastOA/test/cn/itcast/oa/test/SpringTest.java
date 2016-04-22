package cn.itcast.oa.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.jbpm.api.ProcessEngine;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    private Log  log=LogFactory.getLog(getClass());

	// 测试SessionFactory
	@Test
	public void testSessionFactory() throws Exception {
		SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
		System.out.println(sessionFactory);
	}

	// 测试事务
	@Test
	public void testTransaction() throws Exception {
		TestService testService  = (TestService) ac.getBean("testService");
		testService.saveTwoUsers();
	}
	//测试processEngine
	@Test
	public void testProcessEngin() throws Exception{
		ProcessEngine processEngine=(ProcessEngine) ac.getBean("processEngine");
		System.out.println("--->"+processEngine);
		
	}
}
