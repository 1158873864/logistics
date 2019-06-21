package com.wl.app.service;

import com.wl.app.domain.Browse;

/**
 * Service Interface for managing Goods.
 */
public interface BrowseService {

    int get(Long ddnid);

    void add(Long ddnid);
}
