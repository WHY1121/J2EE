package cn.itcast.oa.service;

import java.util.List;
import java.util.Set;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.Application;
import cn.itcast.oa.domain.ApproveInfo;
import cn.itcast.oa.domain.TaskView;
import cn.itcast.oa.domain.User;

public interface ApplicationService extends BaseDao<Application>{
    /**
     * 提交申请
     * @param application
     */
	void submit(Application application);
    /**
     * 查询我的任务
     * @param sessionUser
     * @return
     */
	List<TaskView> getMyTaskView(User sessionUser);
	/**
	 * 选择分支路线
	 * @param taskId
	 * @return
	 */
	Set<String> getOutcomesByTaskId(String taskId);
	
     /**
      * 审批
      * @param approveInfo
      * @param taskId
      * @param outcome
      */
	void approval(ApproveInfo approveInfo, String taskId, String outcome);

}
