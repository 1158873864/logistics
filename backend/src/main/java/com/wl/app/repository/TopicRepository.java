package com.wl.app.repository;

import com.wl.app.domain.Topic;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Spring Data  repository for the Topic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findAllByUserInfoId(Long id);
   /* @Transactional
    public void deleteByTopicId(Long id);*/

}
