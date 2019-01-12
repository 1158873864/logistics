package com.wl.app.repository;

import com.wl.app.domain.IntegralRule;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the IntegralRule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IntegralRuleRepository extends JpaRepository<IntegralRule, Long> {

}
