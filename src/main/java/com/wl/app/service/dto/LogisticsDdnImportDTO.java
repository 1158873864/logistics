package com.wl.app.service.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.wl.app.domain.enumeration.Status;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import io.swagger.annotations.ApiModelProperty;

/**
 * 物流专线导入模板
 * @author Donny.
 */
@ExcelTarget("logisticsDdnImportDTO")
public class LogisticsDdnImportDTO implements Serializable {

	@Excel(name = "名称")
	private String title = "";
	
    /**
     * 经理名字
     */
	@Excel(name = "经理名称")
    private String managerFullname = "";

    /**
     * 经理手机号码
     */
	@Excel(name = "手机")
    private String managerMobilePhone = "";
    
    /**
     * 查货电话
     */
	@Excel(name = "查货电话")
    private String managerPhone = "";
	
	/**
     * 业务电话
     */
	@Excel(name = "业务电话")
    private String businessPhone = "";
    
    /**
     * 专线所在城市
     */
	@Excel(name = "所在城市")
    private String locationCity = "";

    /**
     * 专线地址
     */
	@Excel(name = "地址")
    private String address = "";


    /**
     * 专线覆盖城市
     */
    @Excel(name = "覆盖城市")
    private String coverCity = "";

    /**
     * 直达城市
     */
    @Excel(name = "直达城市")
    private String throughCity = "";

    /**
     * 网站
     */
    @Excel(name = "网站")
    private String www = "";

    /**
     * 是否特种运输
     */
    @Excel(name = "是否属于好专线")
    private String specialTransport = "";

    /**
     * 是否好专线
     */
    @Excel(name = "是否属于特种运输")
    private String good = "";
    
    /**
     * 是否往返
     */
    @Excel(name = "是否往返")
    private String back = "";
    
    /**
     * 认证
     */
    @Excel(name = "是否认证")
    private String auth = "";
    
    /**
     * 是否首页收藏显示
     */
    @Excel(name = "是否首页收藏显示")
    private String home = "";
    
    /**
     * 是否首页Banner显示
     */
    @Excel(name = "是否首页Banner显示")
    private String banner = "";

    @Excel(name = "状态")
    private String status = "";

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getManagerFullname() {
		return managerFullname;
	}

	public void setManagerFullname(String managerFullname) {
		this.managerFullname = managerFullname;
	}

	public String getManagerMobilePhone() {
		return managerMobilePhone  == null ? "" : managerMobilePhone;
	}

	public void setManagerMobilePhone(String managerMobilePhone) {
		this.managerMobilePhone = managerMobilePhone;
	}

	public String getManagerPhone() {
		return managerPhone == null ? "" : managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getLocationCity() {
		return locationCity;
	}

	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCoverCity() {
		return coverCity;
	}

	public void setCoverCity(String coverCity) {
		this.coverCity = coverCity;
	}

	public String getThroughCity() {
		return throughCity;
	}

	public void setThroughCity(String throughCity) {
		this.throughCity = throughCity;
	}

	public String getWww() {
		return www;
	}

	public void setWww(String www) {
		this.www = www;
	}

	public String getSpecialTransport() {
		return specialTransport;
	}
	
	public Boolean getSpecialTransportBoolean() {
		if(specialTransport!=null) {
			if(specialTransport.trim().equals("是")) {
				return true;
			}
		}
		return false;
	}

	public void setSpecialTransport(String specialTransport) {
		this.specialTransport = specialTransport;
	}

	public String getGood() {
		return good;
	}
	
	public Boolean getGoodBoolean() {
		if(good!=null) {
			if(good.trim().equals("是")) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean getAuthBoolean() {
		if(auth!=null) {
			if(auth.trim().equals("是")) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean getBackBoolean() {
		if(back!=null) {
			if(back.trim().equals("是")) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean getHomeBoolean() {
		if(home!=null) {
			if(home.trim().equals("是")) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean getBannerBoolean() {
		if(banner!=null) {
			if(banner.trim().equals("是")) {
				return true;
			}
		}
		return false;
	}

	public void setGood(String good) {
		this.good = good;
	}

	public String getStatus() {
		return status;
	}
	
	public Status getStatusEnum() {
		if(status!=null) {
			if(status.trim().equals("启用")) {
				return Status.ENABLE;
			}
		}
		return Status.DISABLE;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	public String getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	public String getBack() {
		return back;
	}

	public void setBack(String back) {
		this.back = back;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}
	
	
}
