package com.wl.app.repository;

import com.wl.app.domain.Goods;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Goods entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {

}
