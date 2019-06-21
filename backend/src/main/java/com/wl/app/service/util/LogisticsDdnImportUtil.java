package com.wl.app.service.util;

import java.io.File;
import java.util.List;

import com.wl.app.service.dto.LogisticsDdnImportDTO;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;

public class LogisticsDdnImportUtil {

	public static void main(String[] args) {
		ImportParams params = new ImportParams();
		List<LogisticsDdnImportDTO> logisticsDdnImportList = ExcelImportUtil.importExcel(new File("/home/donny/Documents/物流/物流/专线录入(1).xls"), LogisticsDdnImportDTO.class, params);
		for(LogisticsDdnImportDTO logisticsDdnImportDTO:logisticsDdnImportList) {
			System.out.println(logisticsDdnImportDTO.getTitle());
			System.out.println(logisticsDdnImportDTO.getAddress());
		}
	}
}
