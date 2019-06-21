package com.wl.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wl.app.domain.LogisticsDdnWWW;
import com.wl.app.domain.enumeration.Status;



/**
 * Spring Data  repository for the LogisticsDdnWWW entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LogisticsDdnWWWRepository extends JpaRepository<LogisticsDdnWWW, Long> {

	List<LogisticsDdnWWW> findAllByLogisticsDdnIdAndStatus(long logisticsDdnId,Status status);
	void deleteByLogisticsDdnId(long id);
}
