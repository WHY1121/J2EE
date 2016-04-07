package cn.itcast.oa.install;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.domain.Priviledge;
import cn.itcast.oa.domain.User;

/**
 * 导入数据
 * @author fsdfsdsss
 *
 */
@Component
public class Install {
	
	
	@Resource
	private SessionFactory sessionFactory;
	
	@Transactional
	public void execute(){
		Session session=sessionFactory.getCurrentSession();
		//保存一个超级管理员
		User user=new User();
		user.setName("超级管理员");
		user.setLoginName("admin");
		user.setPassword(DigestUtils.md5Hex("admin"));
		session.save(user);
		//权限数据
		Priviledge menu,menu1,menu2,menu3,menu4,menu5;
		menu=new Priviledge(null,"系统管理","FUNC20082.gif",null);
		menu1=new Priviledge("userAction_list","用户管理",null,menu);
		menu2=new Priviledge("roleAction_list","岗位管理",null,menu);
		menu3=new Priviledge("departmentAction_list","部门管理",null,menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
	
		session.save(new Priviledge("userAction_list","用户列表",null,menu1));
		session.save(new Priviledge("userAction_add","用户添加",null,menu1));
		session.save(new Priviledge("userAction_delete","用户删除",null,menu1));
		session.save(new Priviledge("userAction_edit","用户修改",null,menu1));
		session.save(new Priviledge("userAction_initPassword","用户初始化密码",null,menu1));
		
		
		session.save(new Priviledge("roleAction_list","岗位列表",null,menu2));
		session.save(new Priviledge("roleAction_add","岗位添加",null,menu2));
		session.save(new Priviledge("roleAction_delete","岗位删除",null,menu2));
		session.save(new Priviledge("roleAction_edit","岗位修改",null,menu2));
		
		
		session.save(new Priviledge("departmentAction_list","部门列表",null,menu3));
		session.save(new Priviledge("departmentAction_add","部门添加",null,menu3));
		session.save(new Priviledge("departmentAction_delete","部门删除",null,menu3));
		session.save(new Priviledge("departmentAction_edit","部门修改",null,menu3));

		
		
		//=====================================================
		
		menu=new Priviledge(null,"网上交流","FUNC20064.gif",null);
		menu1=new Priviledge("forumDefinitionAction_list","论坛交流",null,menu);
		menu2=new Priviledge("forumAction_list","论坛",null,menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		
		
		//====================================================
		menu=new Priviledge(null,"审批流转","FUNC20057.gif",null);
		menu1=new Priviledge("processDefinitionAction_list","审批流程管理",null,menu);
		menu2=new Priviledge("applicationTemplateAction_list","申请模版管理",null,menu);
		menu3=new Priviledge("flowAction_applicationTemplatelist","起草申请",null,menu);
		menu4=new Priviledge("flowAction_myTasklist","待我审批",null,menu);
		menu5=new Priviledge("flowAction_myApplicationlist","我的申请查询",null,menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
		
	}
	
	public static void main(String[] args) {
		System.out.println("正在安装..........");
		ApplicationContext aContext=new ClassPathXmlApplicationContext("applicationContext.xml");
		Install install=(Install) aContext.getBean("install");
		install.execute();
		System.out.println("安装成功");
	}

}
