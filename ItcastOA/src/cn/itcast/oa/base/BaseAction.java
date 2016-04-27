package cn.itcast.oa.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;



import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.ApplicationService;
import cn.itcast.oa.service.ApplicationTemplateService;
import cn.itcast.oa.service.DepartmentService;
import cn.itcast.oa.service.ForumManageService;
import cn.itcast.oa.service.ForumService;
import cn.itcast.oa.service.PriviledgeService;
import cn.itcast.oa.service.ProcessDefinitionService;
import cn.itcast.oa.service.ReplyService;
import cn.itcast.oa.service.RoleService;
import cn.itcast.oa.service.TopicService;
import cn.itcast.oa.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction extends ActionSupport{
	@Resource
	protected  DepartmentService departmentService;
	@Resource
	protected  UserService userService;
	@Resource
	protected  RoleService roleService;
	@Resource
	protected ForumManageService forumManageService;
	@Resource
	protected TopicService topicService;
	@Resource
	protected ForumService forumService;
	@Resource
	protected ReplyService replyService;
	@Resource  
	protected PriviledgeService priviledgeService;
	@Resource
	protected ProcessDefinitionService processDefinitionService;
	@Resource
	protected ApplicationTemplateService applicationTemplateService;
	@Resource
	protected ApplicationService applicationService;
	
	
    protected int pageNum=1;
    
    public int getPageNum() {
		return pageNum;
	}



	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
    /**
	 * 获取session里的user
	 * @return
	 */
    protected User getSessionUser(){
		
		return (User)ActionContext
				.getContext()
				.getSession().get("user");
	}
     /**
      * 上传文件
      * @return
      */
	protected String fileUpload(File upload) {
		// 防止单个文件夹存储的文件过多以后系统变慢
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
		// 得到真是路径
		String realPath = ServletActionContext.getServletContext().getRealPath(
				"/WEB-INF/upload-files");

		String subPath = sdf.format(new Date());
		File dir = new File(realPath + subPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 以UUID给文件命名，防止重复的成熟解决方案
		String path = realPath + subPath + UUID.randomUUID().toString();
		upload.renameTo(new File(path));// 如果目标文件夹不存在，或是目标文件已存在，就会不成功，返回false，但不报错。
		return path;
	}

}
