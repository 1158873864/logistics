package com.wl.app.repository;

import com.wl.app.domain.Topic;
import com.wl.app.domain.TopicComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Spring Data  repository for the TopicComment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TopicCommentRepository extends JpaRepository<TopicComment, Long> {
    Page<TopicComment> findTopicCommentByTopic(Topic topic,Pageable pageable);

    TopicComment findTopicCommentsById(long id);
    List<TopicComment> findAllByUserInfoId(Long id);
    List<TopicComment> findAllByTopicId(Long id);
    List<TopicComment> findTopicCommentByTopic(Topic topic);
    @Transactional
    void deleteAllByTopicId(Long id);

    @Transactional
    void deleteTopicCommentByTopicId(Long id);
    @Transactional
    public void deleteByTopicId(Long id);
    @Transactional
    public void deleteByTopic(Topic topic);
    @Transactional
    void deleteTopicCommentByTopic(Topic topic);


}
