package com.wl.app.service.dto;

import io.swagger.annotations.ApiModelProperty;

public class SearchAndFavoritesDdnDTO {

	@ApiModelProperty(value = "专线ID")
	private long id;

	@ApiModelProperty(value = "专线名称")
	private String title;

	@ApiModelProperty(value = "开始路线")
	private String startline;
	
	@ApiModelProperty(value = "目的路线")
	private String endline;

	@ApiModelProperty(value = "经理名称")
	private String managerFullname;
	
	@ApiModelProperty(value = "手机号码")
	private String managerPhone;

	@ApiModelProperty(value = "是否VIP")
	private boolean isVip;
	
	public SearchAndFavoritesDdnDTO() {}

	public SearchAndFavoritesDdnDTO(long id, String title, String managerPhone ,String startline, String endline, String managerFullname,
			boolean isVip) {
		super();
		this.id = id;
		this.title = title;
		this.managerPhone = managerPhone;
		this.startline = startline;
		this.endline = endline;
		this.managerFullname = managerFullname;
		this.isVip = isVip;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartline() {
		return startline;
	}

	public void setStartline(String startline) {
		this.startline = startline;
	}

	public String getEndline() {
		return endline;
	}

	public void setEndline(String endline) {
		this.endline = endline;
	}

	public String getManagerFullname() {
		return managerFullname;
	}

	public void setManagerFullname(String managerFullname) {
		this.managerFullname = managerFullname;
	}

	public boolean isVip() {
		return isVip;
	}

	public void setVip(boolean isVip) {
		this.isVip = isVip;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	
}
