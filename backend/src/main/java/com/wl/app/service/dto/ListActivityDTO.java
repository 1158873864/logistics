package com.wl.app.service.dto;

import io.swagger.annotations.ApiModelProperty;

public class ListActivityDTO {

	@ApiModelProperty(value = "活动ID")
    private Long id;
    @ApiModelProperty(value = "专线标题")
    private String title;
    @ApiModelProperty(value = "活动封面图片")
    private String cover;
    
    public ListActivityDTO() {}
    
	public ListActivityDTO(Long id, String title, String cover) {
		super();
		this.id = id;
		this.title = title;
		this.cover = cover;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
    
    
}
