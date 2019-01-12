package com.wl.app.repository;

import com.wl.app.domain.TopicViewed;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TopicViewed entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TopicViewedRepository extends JpaRepository<TopicViewed, Long> {

}
