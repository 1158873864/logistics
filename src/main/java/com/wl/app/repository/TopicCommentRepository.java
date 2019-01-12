package com.wl.app.repository;

import com.wl.app.domain.TopicComment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TopicComment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TopicCommentRepository extends JpaRepository<TopicComment, Long> {

}
