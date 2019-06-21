package com.wl.app.service.dto;

import java.io.Serializable;
import java.time.Instant;

import io.swagger.annotations.ApiModelProperty;

public class PreferentialActivitiesDTO implements Serializable {

    private Long id;

    /**
     * 活动名称
     */
    @ApiModelProperty(value = "活动名称")
    private String name;

    /**
     * 活动封面图片
     */
    @ApiModelProperty(value = "活动封面图片")
    private String cover;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Instant startDate;

    /**
     * 截止时间
     */
    @ApiModelProperty(value = "截止时间")
    private Instant endDate;

    /**
     * 活动内容
     */
    @ApiModelProperty(value = "活动内容")
    private String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public Instant getStartDate() {
		return startDate;
	}

	public void setStartDate(Instant startDate) {
		this.startDate = startDate;
	}

	public Instant getEndDate() {
		return endDate;
	}

	public void setEndDate(Instant endDate) {
		this.endDate = endDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

    
}