package com.wl.app.service.impl;

import com.wl.app.domain.DdnUpdate;
import com.wl.app.domain.LogisticsDdn;
import com.wl.app.repository.*;
import com.wl.app.service.DdnUpdateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing LogisticsDdn.
 */
@Service
@Transactional
public class DdnUpdateServiceImpl implements DdnUpdateService {

	private final DdnUpdateRepository ddnUpdateRepository;

	public DdnUpdateServiceImpl(DdnUpdateRepository ddnUpdateRepository) {
		this.ddnUpdateRepository = ddnUpdateRepository;
	}


	@Override
	public DdnUpdate save(DdnUpdate ddnUpdate) {
		return ddnUpdateRepository.save(ddnUpdate);
	}

	@Override
	public DdnUpdate update(DdnUpdate ddnUpdate) {
		Optional<DdnUpdate> ddnUpdates=ddnUpdateRepository.findById(ddnUpdate.getId());
		DdnUpdate newDdnUpdate=null;
		if(ddnUpdates.isPresent()){
			newDdnUpdate=ddnUpdates.get();
			newDdnUpdate.setStatus(ddnUpdate.getStatus());
			newDdnUpdate.setAddress(ddnUpdate.getAddress());
			newDdnUpdate.setBusinessPhone(ddnUpdate.getBusinessPhone());
			newDdnUpdate.setCode(ddnUpdate.getCode());
			newDdnUpdate.setCoverCity(ddnUpdate.getCoverCity());
			newDdnUpdate.setLocationCity(ddnUpdate.getLocationCity());
			newDdnUpdate.setManagerFullname(ddnUpdate.getManagerFullname());
			newDdnUpdate.setManagerMobilePhone(ddnUpdate.getManagerMobilePhone());
			newDdnUpdate.setManagerPhone(ddnUpdate.getManagerPhone());
			newDdnUpdate.setPic(ddnUpdate.getPic());
			newDdnUpdate.setRemark(ddnUpdate.getRemark());
			newDdnUpdate.setThroughCity(ddnUpdate.getThroughCity());
			newDdnUpdate.setTitle(ddnUpdate.getTitle());
			newDdnUpdate.setType(ddnUpdate.getType());
			newDdnUpdate.setWww(ddnUpdate.getWww());
			newDdnUpdate.setUserMobilephone(ddnUpdate.getUserMobilephone());
			ddnUpdateRepository.save(newDdnUpdate);
		}
		return newDdnUpdate;
	}

	@Override
	public Page<DdnUpdate> findAll(Pageable pageable) {
		return ddnUpdateRepository.findAll(pageable);
	}

	@Override
	public int size() {
		return ddnUpdateRepository.findAll().size()/50+1;
	}

	@Override
	public Optional<DdnUpdate> findOne(Long id) {
		return ddnUpdateRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
        ddnUpdateRepository.deleteById(id);
	}

	@Override
	public Page<DdnUpdate> find(String query, Pageable pageable){
		List<DdnUpdate> ddnUpdates=ddnUpdateRepository.findAll();
		List<DdnUpdate> sortedList=new ArrayList<>();
		for(int i=0;i<ddnUpdates.size();i++){
			DdnUpdate logisticsDdn=ddnUpdates.get(i);
			if(logisticsDdn.getThroughCity().indexOf(query)!=(-1)||logisticsDdn.getCoverCity().indexOf(query)!=(-1)||logisticsDdn.getUserMobilephone().indexOf(query)!=(-1)||logisticsDdn.getBusinessPhone().indexOf(query)!=(-1)||logisticsDdn.getManagerPhone().indexOf(query)!=(-1)||logisticsDdn.getManagerMobilePhone().indexOf(query)!=(-1)||logisticsDdn.getAddress().indexOf(query)!=(-1)||logisticsDdn.getLocationCity().indexOf(query)!=(-1)||logisticsDdn.getTitle().indexOf(query)!=(-1)||logisticsDdn.getManagerFullname().indexOf(query)!=(-1)||logisticsDdn.getRemark().indexOf(query)!=(-1)||logisticsDdn.getCode().indexOf(query)!=(-1)||String.valueOf(logisticsDdn.getId()).indexOf(query)!=(-1)){
				sortedList.add(logisticsDdn);
			}
		}
		return listConvertToPage(sortedList,pageable);
	}


	public <T> Page<T> listConvertToPage(List<T> list, Pageable pageable) {
		int start = (int)pageable.getOffset();
		int end = (start + pageable.getPageSize()) > list.size() ? list.size() : ( start + pageable.getPageSize());
		return new PageImpl<T>(list.subList(start, end), pageable, list.size());
	}
}
