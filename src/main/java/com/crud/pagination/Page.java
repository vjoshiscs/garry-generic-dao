/**
 * 
 */
package com.crud.pagination;

/**
 * @author Vishal Joshi
 *
 */
public class Page {
	
	

	public Page() {
		// TODO Auto-generated constructor stub
	}

	public Page(int currentPageNo, int previousPageNo, int nextPageNo,
			int recordPerPage, int startIndex) {
		super();
		this.currentPageNo = currentPageNo;
		this.previousPageNo = previousPageNo;
		this.nextPageNo = nextPageNo;
		this.recordPerPage = recordPerPage;
		this.startIndex = startIndex;
	}



	private int currentPageNo = 1;
	
	private int previousPageNo;
	
	private int nextPageNo;
	
	private int recordPerPage = 4;
	
	private int startIndex = 0;

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getPreviousPageNo() {
		return previousPageNo;
	}

	public void setPreviousPageNo(int previousPageNo) {
		this.previousPageNo = previousPageNo;
	}

	public int getNextPageNo() {
		return nextPageNo;
	}

	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}

	public int getRecordPerPage() {
		return recordPerPage;
	}

	public void setRecordPerPage(int recordPerPage) {
		this.recordPerPage = recordPerPage;
	}

	public int getStartIndex() {
		if(this.currentPageNo!=1){
		this.startIndex = (this.currentPageNo-1)*this.recordPerPage +1;
		System.out.println("Here :: "+this.startIndex);
		}
		return this.startIndex;
	}

	
	
	
	
	
	
}
