package com.wl.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wl.app.domain.LogisticsDdnPic;
import com.wl.app.domain.enumeration.Status;


/**
 * Spring Data  repository for the LogisticsDdnPic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LogisticsDdnPicRepository extends JpaRepository<LogisticsDdnPic, Long> {

	List<LogisticsDdnPic> findAllByLogisticsDdnIdAndStatus(long logisticsDdnId,Status status);
	void deleteByLogisticsDdnId(long id);
}
