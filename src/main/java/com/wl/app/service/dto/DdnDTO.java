package com.wl.app.service.dto;

import java.util.ArrayList;
import java.util.List;

import com.wl.app.domain.LogisticsDdn;
import com.wl.app.domain.LogisticsDdnPic;
import com.wl.app.domain.LogisticsDdnWWW;

public class DdnDTO {

	private long id;
	
	private LogisticsDdn info;
	
	private List<LogisticsDdnWWW> branchs  = new ArrayList<>();
	
	private List<LogisticsDdnPic> pics  = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LogisticsDdn getInfo() {
		return info;
	}

	public void setInfo(LogisticsDdn info) {
		this.info = info;
	}

	public List<LogisticsDdnWWW> getBranchs() {
		return branchs;
	}

	public void setBranchs(List<LogisticsDdnWWW> branchs) {
		this.branchs = branchs;
	}

	public List<LogisticsDdnPic> getPics() {
		return pics;
	}

	public void setPics(List<LogisticsDdnPic> pics) {
		this.pics = pics;
	}
	
	
	
	
}
