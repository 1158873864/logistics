package com.wl.app.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.wl.app.config.ApplicationProperties;
import com.wl.app.domain.Area;
import com.wl.app.domain.LogisticsDdn;
import com.wl.app.domain.LogisticsDdnPic;
import com.wl.app.domain.enumeration.Status;
import com.wl.app.repository.*;
import com.wl.app.service.AreaService;
import com.wl.app.service.dto.AreaImportDTO;
import com.wl.app.service.dto.LogisticsDdnImportDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

/**
 * Service Implementation for managing LogisticsDdn.
 */
@Service
@Transactional
public class AreaServiceImpl implements AreaService {

	private final AreaRepository areaRepository;

	private final ApplicationProperties applicationProperties;

	public AreaServiceImpl(AreaRepository areaRepository, ApplicationProperties applicationProperties) {
		this.areaRepository = areaRepository;
		this.applicationProperties = applicationProperties;
	}

	@Override
	public boolean ImportArea(String path) {
		ImportParams params = new ImportParams();
		List<AreaImportDTO> areaImportDTOS = ExcelImportUtil.importExcel(
				new File(applicationProperties.getStaticResourcePath() + "/" + path), AreaImportDTO.class,
				params);
        String province="";
        String city="";
        int pid=0;
        int cid=0;
        int provinceId=0;
        int cityId=1000;
        int townId=10000;
		for(AreaImportDTO areaImportDTO:areaImportDTOS){
            if(areaImportDTO.getProvince()!=null&&!areaImportDTO.getProvince().trim().equals("")){
            	if(!areaImportDTO.getProvince().trim().equals(province)){
					province=areaImportDTO.getProvince().trim();
					provinceId++;
					areaRepository.save(new Area((long)provinceId,0,province));
				}
			}

			if(areaImportDTO.getCity()!=null&&!areaImportDTO.getCity().trim().equals("")){
            	if(!areaImportDTO.getCity().trim().equals(city)){
					city=areaImportDTO.getCity().trim();
					cityId++;
					areaRepository.save(new Area((long)cityId,provinceId,city));
				}
			}

			if(areaImportDTO.getTown()!=null&&!areaImportDTO.getTown().trim().equals("")){
            	townId++;
            	areaRepository.save(new Area((long)townId,cityId,areaImportDTO.getTown().trim()));
			}
		}
		return true;
	}
}
