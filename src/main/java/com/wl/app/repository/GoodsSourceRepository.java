package com.wl.app.repository;

import com.wl.app.domain.GoodsSource;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GoodsSource entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GoodsSourceRepository extends JpaRepository<GoodsSource, Long> {

}
