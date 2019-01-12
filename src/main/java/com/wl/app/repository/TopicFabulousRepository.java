package com.wl.app.repository;

import com.wl.app.domain.TopicFabulous;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TopicFabulous entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TopicFabulousRepository extends JpaRepository<TopicFabulous, Long> {

}
