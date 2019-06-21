package com.wl.app.repository;

import com.wl.app.domain.IntegralChangeRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the IntegralChangeRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IntegralChangeRecordRepository extends JpaRepository<IntegralChangeRecord, Long> {

}
