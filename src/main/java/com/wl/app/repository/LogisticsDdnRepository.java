package com.wl.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wl.app.domain.LogisticsDdn;


/**
 * Spring Data  repository for the LogisticsDdn entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LogisticsDdnRepository extends JpaRepository<LogisticsDdn, Long> {

	List<LogisticsDdn> findAllByTitleAndManagerFullname(String title,String managerFullname);
	
	@Query("SELECT distinct locationCity FROM LogisticsDdn")
	List<String> findStartCitys();
	
	List<LogisticsDdn> findAllByHome(boolean isHome);
	List<LogisticsDdn> findAllByBanner(boolean isBanner);
	LogisticsDdn findLogisticsDdnByManagerMobilePhone(String managerMobilePhone);
	List<LogisticsDdn> findAllBySpecialTransport(boolean SpecialTransport);
    List<LogisticsDdn> findAllByGood(boolean Good);
}
