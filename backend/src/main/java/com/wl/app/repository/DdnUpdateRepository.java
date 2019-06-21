package com.wl.app.repository;

import com.wl.app.domain.DdnUpdate;
import com.wl.app.domain.LogisticsDdn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the LogisticsDdn entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DdnUpdateRepository extends JpaRepository<DdnUpdate, Long> {

	List<DdnUpdate> findAllByTitleAndManagerFullname(String title, String managerFullname);
	
	@Query("SELECT distinct locationCity FROM DdnUpdate")
	List<String> findStartCitys();

	List<DdnUpdate> findByLocationCity(String locationCity);

	DdnUpdate findLogisticsDdnByManagerMobilePhone(String managerMobilePhone);

}
