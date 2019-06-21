package com.wl.app.repository;

import com.wl.app.domain.PreferentialActivities;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PreferentialActivities entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PreferentialActivitiesRepository extends JpaRepository<PreferentialActivities, Long> {

}
