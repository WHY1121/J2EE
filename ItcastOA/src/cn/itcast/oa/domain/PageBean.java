package cn.itcast.oa.domain;

import java.util.List;

public class PageBean {
   //需要传递的参数
	private int currentPage;
	private int pageSize;
	
	
	//数据库查询
	private List recordList;
	private int recordCount;
	
	//计算的出的结果
	private int pageCount;
	private int beginPageIndex;
	private int endPageIndex;
	
	public PageBean(int currentPage, int pageSize, List recordList,int recordCount) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordList = recordList;
		this.recordCount = recordCount;
		
		//总页数
		pageCount=(recordCount+pageSize-1) / pageSize;
		
		//计算遍历的起始页和结束页
		if(pageCount<=10){
			beginPageIndex=1;
			endPageIndex=pageCount;
		}else{
			//如果不满10页，显示附近的10个页码
			//前4页+当前页+后5页
			
			beginPageIndex=currentPage-4;//6-4=2
			endPageIndex=currentPage+5;//6+5=1
			//如果当前页小于前4页,显示前10页
			if(beginPageIndex<1){
				beginPageIndex=1;
				endPageIndex=10;
		    //如果当前页+5大于pageCount
			}else if(endPageIndex>pageCount){
				endPageIndex=pageCount;
				beginPageIndex=pageCount-9;
				
			}
		}
	
	}
	
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List getRecordList() {
		return recordList;
	}
	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}


	public int getPageCount() {
		return pageCount;
	}


	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}


	public int getBeginPageIndex() {
		return beginPageIndex;
	}


	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}


	public int getEndPageIndex() {
		return endPageIndex;
	}


	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}
	
	
	
	
	
	
	
	
}
