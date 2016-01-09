package com.owen.wms.web.dao;

import java.util.List;

public class Page {
	    // 1.每页显示数量(everyPage)  
	    private int pageSize;  
	    // 2.总记录数(totalCount)  
	    private int totalCount;  
	    // 3.总页数(totalPage)  
	    private int totalPage;  
	    // 4.当前页(currentPage)  
	    private int currentPage;  
	    // 5.是否有上一页(hasPrePage)  
	    private boolean hasPrePage;  
	    // 6.是否有下一页(hasNextPage)  
	    private boolean hasNextPage;
	    //content
	    private List list;
	    
	    public Page(){}
	    
	    public Page(int currentPage,int pageSize,int totalCount,List list){
	    	this.pageSize = pageSize;
	    	this.currentPage = currentPage;
	    	this.totalCount=totalCount;
	    	this.list = list;
	    	if(pageSize>0){
	    		if(this.totalCount % this.pageSize ==0){
	    			this.totalPage = this.totalCount/this.pageSize;
	    		}else{
	    			this.totalPage = this.totalCount/this.pageSize + 1;
	    		}
	    		this.hasNextPage = this.currentPage < this.totalPage;
	    		this.hasPrePage =  this.currentPage > 1 ;
	    	}
	    }
	    
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		public int getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}
		public int getTotalPage() {
			return totalPage;
		}
		public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}
		public int getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}
		public boolean isHasPrePage() {
			return hasPrePage;
		}
		public void setHasPrePage(boolean hasPrePage) {
			this.hasPrePage = hasPrePage;
		}
		public boolean isHasNextPage() {
			return hasNextPage;
		}
		public void setHasNextPage(boolean hasNextPage) {
			this.hasNextPage = hasNextPage;
		}
		public List getList() {
			return list;
		}
		public void setList(List list) {
			this.list = list;
		}
	    
	    
}
