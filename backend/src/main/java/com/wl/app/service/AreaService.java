package com.wl.app.service;

import com.wl.app.domain.LogisticsDdn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing LogisticsDdn.
 */
public interface AreaService {


    boolean ImportArea(String path);

}
