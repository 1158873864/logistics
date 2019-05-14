package com.wl.app.repository;

import com.wl.app.domain.Topic;
import com.wl.app.domain.TopicFabulous;
import com.wl.app.domain.UserInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the TopicFabulous entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TopicFabulousRepository extends JpaRepository<TopicFabulous, Long> {
    TopicFabulous findTopicFabulousByUserInfoAndTopic(UserInfo userInfo, Topic topic);
    List<TopicFabulous> findAllByUserInfoId(Long Userid);
    //List<TopicForward> findTopicCommentByTopic(TopicForward topicForward);
    List<TopicFabulous> findTopicFabulousByTopic(Topic topic);
}
