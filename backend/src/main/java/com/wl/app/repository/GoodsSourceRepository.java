package com.wl.app.repository;

import com.wl.app.domain.GoodsSource;
import com.wl.app.domain.SysRecruitmentInformation;
import com.wl.app.domain.enumeration.GoodsSourceProperty;
import com.wl.app.domain.enumeration.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the GoodsSource entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GoodsSourceRepository extends JpaRepository<GoodsSource, Long> {
    List<GoodsSource> findAllByUserInfoId(Long id);
    List<GoodsSource> findAllByStartAndEndOrGoodsSourceProperty(String start, String End, GoodsSourceProperty goodsSourceProperty);
    List<GoodsSource> findAllByStatusAndUserInfoId(Status status,Long id);
    List<GoodsSource> findAllByGoodsSourceProperty(GoodsSourceProperty goodsSourceProperty);
    //List<GoodsSource> findAllByStartAndeAndEndAndGoodsSourceProperty(String start,String end,GoodsSourceProperty goodsSourceProperty);
    }
