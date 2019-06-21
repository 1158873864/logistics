package com.wl.app.service;

import com.wl.app.domain.Goods;
import com.wl.app.domain.Version;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Goods.
 */
public interface VersionService {

    Version get();

    void set(String number,String path,String info);

    Version save();

    Version get2();

    void set2(String number, String path, String info);

    Version save2();
}
