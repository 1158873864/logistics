package com.wl.app.service;

import com.wl.app.domain.DdnUpdate;
import com.wl.app.domain.LogisticsDdn;
import com.wl.app.service.dto.CannotGetOpenIdAndSessionKeyException;
import com.wl.app.service.dto.OpenIdAndSessionKeyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing LogisticsDdn.
 */
public interface DdnUpdateService {

    DdnUpdate save(DdnUpdate ddnUpdate);

    DdnUpdate update(DdnUpdate ddnUpdate);

    Page<DdnUpdate> findAll(Pageable pageable);

    int size();

    Optional<DdnUpdate> findOne(Long id);

    void delete(Long id);

    Page<DdnUpdate> find(String query, Pageable pageable);
}
