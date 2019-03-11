package com.wl.app.repository;

import com.wl.app.domain.Topic;
import com.wl.app.domain.TopicComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TopicComment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TopicCommentRepository extends JpaRepository<TopicComment, Long> {
    Page<TopicComment> findTopicCommentByTopic(Topic topic,Pageable pageable);
    TopicComment findTopicCommentsById(long id);
}
