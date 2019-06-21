package com.wl.app.service.dto;

import java.io.Serializable;

import com.wl.app.domain.enumeration.Status;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * 物流专线网点导入模板
 * @author Donny.
 */
@ExcelTarget("logisticsDdnImportDTO")
public class LogisticsDdnWwwImportDTO implements Serializable {

	
	@Excel(name = "名称")
	private String name = "";
	
	@Excel(name = "所属专线")
	private String title;
	
	@Excel(name = "手机号")
    private String ｍobilePhone = "";
	
	@Excel(name = "电话号码")
    private String phone  = "";
	
	@Excel(name = "地址")
    private String remark  = "";
	
	@Excel(name = "状态")
	private String status;
	
	@Excel(name = "联系人")
    private String contacts  = "";

	@Excel(name = "经理名称")
    private String managerFullname;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getＭobilePhone() {
		return ｍobilePhone;
	}

	public void setＭobilePhone(String ｍobilePhone) {
		this.ｍobilePhone = ｍobilePhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getManagerFullname() {
		return managerFullname;
	}

	public void setManagerFullname(String managerFullname) {
		this.managerFullname = managerFullname;
	}
	
	public Status getStatusEnum() {
		if(status!=null) {
			if(status.trim().equals("启用")) {
				return Status.ENABLE;
			}
		}
		return Status.DISABLE;
	}
    
}