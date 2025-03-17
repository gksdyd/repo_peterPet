package com.peterpet.demo.module.codegroup;

public class CodeGroupVo {

	//	paging
	private int thisPage = 1;									// 현재 페이지
	private int rowNumToShow = 5;								// 화면에 보여줄 데이터 줄 갯수
	private int pageNumToShow = 5;								// 화면에 보여줄 페이징 번호 갯수

	private int totalRows;										// 전체 데이터 갯수
	private int totalPages;										// 전체 페이지 번호
	private int startPage;										// 시작 페이지 번호
	private int endPage;										// 마지막 페이지 번호

	private int startRnumForMysql = 0;							// 쿼리 시작 row

	public int getThisPage() {
		return thisPage;
	}

	public void setThisPage(int thisPage) {
		this.thisPage = thisPage;
	}

	public int getRowNumToShow() {
		return rowNumToShow;
	}

	public void setRowNumToShow(int rowNumToShow) {
		this.rowNumToShow = rowNumToShow;
	}

	public int getPageNumToShow() {
		return pageNumToShow;
	}

	public void setPageNumToShow(int pageNumToShow) {
		this.pageNumToShow = pageNumToShow;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartRnumForMysql() {
		return startRnumForMysql;
	}

	public void setStartRnumForMysql(int startRnumForMysql) {
		this.startRnumForMysql = startRnumForMysql;
	}
	
	public void setParamsPaging(int totalRows) {
//		setThisPage(3);

		setTotalRows(totalRows);	// 총 데이터 갯수 설정

		if (getTotalRows() == 0) {	// 페이지 갯수 설정
			setTotalPages(1);		// 1개로 셋팅
		} else {
			setTotalPages(getTotalRows() / getRowNumToShow());	// 총 데이터 갯수 / 한 페이지에 보여주는 데이터 갯수
		}

		if (getTotalRows() % getRowNumToShow() > 0) {	// 총 데이터가 한 페이지의 갯수로 맞아 떨어지지 않는 경우
			setTotalPages(getTotalPages() + 1);	// 페이지 갯수 1 증가
		}

		if (getTotalPages() < getThisPage()) {	// 현재 페이지가 총 페이지 갯수보다 큰경우
			setThisPage(getTotalPages());	// 현재 페이지를 마지막 페이지로 설정
		}
		
		// 페이지 시작하는 번호 설정
		setStartPage(((getThisPage() - 1) / getPageNumToShow()) * getPageNumToShow() + 1);

		// 페이지 마지막 번호 설정
		setEndPage(getStartPage() + getPageNumToShow() - 1);

		if (getEndPage() > getTotalPages()) {	// 마지막 페이지 번호가 총 페이지 갯수보다 클 때
			setEndPage(getTotalPages());	// 마지막 페이지 번호를 총 페이지 갯수로 설정
		}
		
//		setEndRnumForOracle((getRowNumToShow() * getThisPage()));
//		setStartRnumForOracle((getEndRnumForOracle() - getRowNumToShow()) + 1);
//		if (getStartRnumForOracle() < 1) setStartRnumForOracle(1);
		
		if (thisPage == 1) {	// 쿼리에 불러올 데이터 시작부분 전달
			setStartRnumForMysql(0);
		} else {
			setStartRnumForMysql((getRowNumToShow() * (getThisPage()-1)));
		}
		
		System.out.println("getThisPage():" + getThisPage());
		System.out.println("getTotalRows():" + getTotalRows());
		System.out.println("getRowNumToShow():" + getRowNumToShow());
		System.out.println("getTotalPages():" + getTotalPages());
		System.out.println("getStartPage():" + getStartPage());
		System.out.println("getEndPage():" + getEndPage());		
//		System.out.println("getStartRnumForOracle():" + getStartRnumForOracle());
//		System.out.println("getEndRnumForOracle():" + getEndRnumForOracle());
		System.out.println("getStartRnumForMysql(): " + getStartRnumForMysql());	
	}
}
