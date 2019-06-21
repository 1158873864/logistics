package com.wl.app.repository;

import com.wl.app.domain.GoodsExchange;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GoodsExchange entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GoodsExchangeRepository extends JpaRepository<GoodsExchange, Long> {

}
