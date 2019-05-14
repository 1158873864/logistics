package com.wl.app.repository;

import com.wl.app.domain.Topic;
import com.wl.app.domain.TopicViewed;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the TopicViewed entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TopicViewedRepository extends JpaRepository<TopicViewed, Long> {
    List<TopicViewed> findTopicViewedByTopic(Topic topic);
}
