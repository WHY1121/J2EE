package cn.itcast.oa.util;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.domain.PageBean;
import cn.itcast.oa.service.TopicService;

/**
 *  用于辅助拼接生成HQL的工具类
 * @author fsdfsdsss
 *
 */
public class HqlHelper {
     
	private String fromCause;//from 子句
	
	private String whereCause="";//where 条件拼接==空字符串，防止sql报错
	
	private String orderByCause="";//oederBy 条件拼接
	
	private List<Object> parameters=new ArrayList<Object>();//查询参数

	public HqlHelper(Class clazz) {
		this.fromCause="FROM "+clazz.getSimpleName()+" o";
	}
	
	public HqlHelper(Class clazz,String adName) {
		this.fromCause="FROM "+clazz.getSimpleName()+" "+adName;
	}
	
	/**
	 *  拼接WHERE子句
	 *  是第一个参数加上
	 * @param condition
	 * @param params
	 */
	public HqlHelper addCondition(String condition,Object... params ){
		if(whereCause.length()==0){
			whereCause=" WHERE "+condition;
		}else{
			whereCause+=" AND "+condition;
		}
		if( params != null && params.length>0 ){
			for(Object obj:params){
				parameters.add(obj);
			}
				
		}
		return  this;
		
	}
	/**
	 * 重载getCondition方法
	 * @param b
	 * @param string
	 * @param params
	 */
	public HqlHelper addCondition(boolean append, String condition, Object... params) {
		if(append==true){
			addCondition(condition, params);
		}
		return  this;
	}
	
	/**
	 * 拼接orderBY子句
	 * @return
	 */
	
	public HqlHelper addOrderBy(String propertyName, boolean isASC){
		if(orderByCause.length()==0){
		    orderByCause=" ORDER BY "+propertyName+(isASC ? " ASC":" DESC");
		}else{
			orderByCause+=", "+propertyName+(isASC ? " ASC":" DESC");
		}
		return  this;
	}
	/**
	 * 拼接orderBY子句
	 * @return
	 */
	
	public HqlHelper addOrderBy(Boolean append,String propertyName, boolean isASC){
		if(append==true){
			addOrderBy(propertyName, isASC);
		}
		return  this;
	}
	
	/**
	 * 获取hql语句
	 * @return
	 */
	public String getQueryListHql(){
		
		return fromCause+whereCause+orderByCause;
	}
	/**
	 * 获取hql查询所数量的语句
	 * @return
	 */
    public String getQueryCountHql(){
		
		return "SELECT COUNT(*) "+fromCause+whereCause;
	}
	
	

	public List<Object> getParameters() {
		return parameters;
	}

	public void setParameters(List<Object> parameters) {
		this.parameters = parameters;
	}

	public HqlHelper buildPageBean(int pageNum, BaseDao<?> service) {
		PageBean pageBean=service.getPageBean(pageNum, this);
		ActionContext.getContext().getValueStack().push(pageBean);
		return this;
	}

	
	

	
	
}
