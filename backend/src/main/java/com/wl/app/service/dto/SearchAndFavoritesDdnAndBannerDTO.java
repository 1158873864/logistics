package com.wl.app.service.dto;

import java.util.List;

public class SearchAndFavoritesDdnAndBannerDTO {

	private List<SearchAndFavoritesDdnDTO> searchAndFavoritesDdns;
	private List<DdnBannerDTO> ddnBanners;
	public List<SearchAndFavoritesDdnDTO> getSearchAndFavoritesDdns() {
		return searchAndFavoritesDdns;
	}
	public void setSearchAndFavoritesDdns(List<SearchAndFavoritesDdnDTO> searchAndFavoritesDdns) {
		this.searchAndFavoritesDdns = searchAndFavoritesDdns;
	}
	public List<DdnBannerDTO> getDdnBanners() {
		return ddnBanners;
	}
	public void setDdnBanners(List<DdnBannerDTO> ddnBanners) {
		this.ddnBanners = ddnBanners;
	}
	
}
