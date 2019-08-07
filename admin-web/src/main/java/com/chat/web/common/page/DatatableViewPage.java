package com.chat.web.common.page;

public class DatatableViewPage {
	
	private boolean legal;
	private String message;
	private Object data;
	
	private Integer draw; // Client request times
    private Long recordsTotal; // Total records number without conditions
    private Long recordsFiltered; // Total records number with conditions
    
	public DatatableViewPage() {}
	
	public DatatableViewPage(boolean legal, String msg, Object data, DatatablePage dPage) {
		this.legal = legal;
		this.message = msg;
		this.data = data;
		this.draw = dPage.getDraw();
		this.recordsTotal = dPage.getPage().getTotal();
		this.recordsFiltered = dPage.getPage().getTotal();
	}
	
	public DatatableViewPage(boolean legal, String msg, DatatablePage dPage) {
		this.legal = legal;
		this.message = msg;
		this.data = dPage.getPage().getResult();
		this.draw = dPage.getDraw();
		this.recordsTotal = dPage.getPage().getTotal();
		this.recordsFiltered = dPage.getPage().getTotal();
	}
	
	public DatatableViewPage(boolean legal, String msg, Object data, Long recordsTotal) {
		this.legal = legal;
		this.message = msg;
		this.data = data;
		this.recordsTotal = recordsTotal;
	}
	
	public DatatableViewPage(boolean legal, String msg, Object data, Integer draw, Long recordsFiltered, Long recordsTotal) {
		this.legal = legal;
		this.message = msg;
		this.data = data;
		this.draw = draw;
		this.recordsFiltered = recordsFiltered;
		this.recordsTotal = recordsTotal;
	}
	
	public DatatableViewPage(boolean legal, String msg) {
		this(legal, msg, null);
	}

	public boolean isNotLegal(){
		return !this.legal;
	}
	
	public DatatableViewPage onErrorMessage(String message){
		this.legal = false;
		this.message = message;
		return this;
	}
	
	public DatatableViewPage onInputEmptyMessage(String title){
		this.legal = false;
		this.message = "请输入" + title + "！";
		return this;
	}
	
	public DatatableViewPage onSelectEmptyMessage(String title){
		this.legal = false;
		this.message = "请选择" + title + "！";
		return this;
	}
	
	public DatatableViewPage onLengthErrorMessage(String title, int length){
		this.legal = false;
		this.message = "您输入的" + title + "过长，请确认" + title + "在" +  length + "字符之内！";
		return this;
	}
		
	public DatatableViewPage onSuccessMessage(){
		this.legal = true;
		return this;
	}
	
	public DatatableViewPage onSuccessMessage(String message){
		this.legal = true;
		this.message = message;
		return this;
	}

	public boolean isLegal() {
		return legal;
	}

	public void setLegal(boolean legal) {
		this.legal = legal;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	
}
