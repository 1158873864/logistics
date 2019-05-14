package com.wl.app.service.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.wl.app.domain.enumeration.Status;

import java.io.Serializable;

/**
 * area导入模板
 * @author Donny.
 */
@ExcelTarget("AreaImportDTO")
public class AreaImportDTO implements Serializable {

	@Excel(name = "省")
	private String province="";

	@Excel(name = "市")
	private String city="";

	@Excel(name = "区县")
    private String town="";


	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}
}
