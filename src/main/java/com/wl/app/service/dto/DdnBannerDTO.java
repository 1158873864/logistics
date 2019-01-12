package com.wl.app.service.dto;

import io.swagger.annotations.ApiModelProperty;

public class DdnBannerDTO {

	@ApiModelProperty(value = "专线ID")
    private Long id;
    /**
     * 专线展示图
     */
    @ApiModelProperty(value = "专线展示图")
    private String pic;
	public DdnBannerDTO(Long id, String pic) {
		super();
		this.id = id;
		this.pic = pic;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
}