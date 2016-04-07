package cn.itcast.oa.interceptor;

import cn.itcast.oa.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 过滤权限
 * @author fsdfsdsss
 *
 */
public class CheckPriviledgeInceptor extends AbstractInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		//获得用户
		User user=(User) ActionContext.getContext().getSession().get("user");
		//获取访问权限的url，并去掉当前应用程序的前缀（namespace+actionName）
		String namespace=arg0.getProxy().getNamespace();
		String actionName=arg0.getProxy().getActionName();
		String priviledgeURL=null;
		if(namespace.endsWith("/")){
			priviledgeURL=namespace+actionName;	
		}else{
			priviledgeURL=namespace+"/"+actionName;
		}
		//去掉开头的"/"
		if(priviledgeURL.startsWith("/")){
			priviledgeURL=priviledgeURL.substring(1);
			
		}
		//判断用户有没有登录，如果没登录就退回到登录界面
		if(user==null){
			if(priviledgeURL.startsWith("userAction_login")){
				 return arg0.invoke();
			}else{
				return "loginUI";
			}
		}else{
			//查看用户有没有权限，
			//有，放行
			//没有，返回没有权限的页面
			if(user.hasPrivilegeByUrl(priviledgeURL)){
				return arg0.invoke();
			}else{
				return "noPriviledgeError";
			}
			
		}
		
	}

}
