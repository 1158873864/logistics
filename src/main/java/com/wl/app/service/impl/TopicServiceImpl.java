package com.wl.app.service.impl;

import com.wl.app.domain.*;
import com.wl.app.repository.*;
import com.wl.app.service.TopicService;
import com.wl.app.repository.search.TopicSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Topic.
 */
@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    private final Logger log = LoggerFactory.getLogger(TopicServiceImpl.class);

    private final TopicRepository topicRepository;

    private final TopicSearchRepository topicSearchRepository;
    private final TopicCommentRepository topicCommentRepository;
    private final TopicForwardRepository topicForwardRepository;
    private final TopicFabulousRepository topicFabulousRepository;
    private final TopicViewedRepository topicViewedRepository;

    public TopicServiceImpl(TopicRepository topicRepository, TopicSearchRepository topicSearchRepository,TopicCommentRepository topicCommentRepository,
                            TopicForwardRepository topicForwardRepository,TopicFabulousRepository topicFabulousRepository,TopicViewedRepository topicViewedRepository) {
        this.topicRepository = topicRepository;
        this.topicSearchRepository = topicSearchRepository;
        this.topicCommentRepository=topicCommentRepository;
        this.topicForwardRepository=topicForwardRepository;
        this.topicFabulousRepository=topicFabulousRepository;
        this.topicViewedRepository=topicViewedRepository;
    }

    /**
     * Save a topic.
     *
     * @param topic the entity to save
     * @return the persisted entity
     */
    @Override//
    public Topic save(Topic topic) {
        log.debug("Request to save Topic : {}", topic);        Topic result = topicRepository.save(topic);
        topicSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the topics.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Topic> findAll(Pageable pageable) {
        log.debug("Request to get all Topics");
        return topicRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Topic> findAllTopic(int size,int page) {
        log.debug("Request to get all Topics");
        return topicRepository.findAll(PageRequest.of(size,page, Sort.Direction.DESC, "published"));
    }

    /**
     * Get one topic by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Topic> findOne(Long id) {
        log.debug("Request to get Topic : {}", id);
        return topicRepository.findById(id);
    }

    /**
     * Delete the topic by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        System.out.println("====="+id);
        //先删除该话题下面所有评论在删除
        topicCommentRepository.deleteAllByTopicId(id);
        topicRepository.deleteById(id);
    }

    @Override
    public void deleteone(Long id) {
        System.out.println("====="+id);
        //先删除该话题下面所有评论在删除
        topicCommentRepository.deleteTopicCommentByTopicId(id);
        topicRepository.deleteById(id);
    }

    @Override
    public void deletetwo(Long id) {
        System.out.println("====="+id);
        //先删除该话题下面所有评论在删除
        topicCommentRepository.deleteByTopicId(id);
        topicRepository.deleteById(id);
    }


    @Override
    public void deleteThree(Long id) {
        System.out.println("====="+id);
        //先删除该话题下面所有评论在删除
        Optional<Topic> topic=topicRepository.findById(id);
        System.out.println("===topic=="+topic.get());
        //查出该话题下呦多少话题评论
        List<TopicComment> topicComments=topicCommentRepository.findTopicCommentByTopic(topic.get());
        System.out.println("===topicComments=="+topicComments.size());
        //查出改话题下有多少话题转发
        List<TopicForward> topicForword=topicForwardRepository.findTopicForwardByTopic(topic.get());
        System.out.println("===topicForword=="+topicForword.size());
        //查出改话题下呦多少话题浏览
        List<TopicViewed> topicVieweds=topicViewedRepository.findTopicViewedByTopic(topic.get());
        System.out.println("===topicVieweds=="+topicVieweds.size());
        //查出改话题下呦多少话题点赞
        List<TopicFabulous>  topicFabulous=topicFabulousRepository.findTopicFabulousByTopic(topic.get());
        System.out.println("===topicFabulous=="+topicFabulous.size());

        if (topicComments.size()!=0||topicFabulous.size()!=0||topicVieweds.size()!=0||topicForword.size()!=0){
            for (int i = 0; i < topicComments.size(); i++) {
                TopicComment logisticsDdn = topicComments.get(i);
                System.out.println("===logisticsDdn===" + logisticsDdn.getId() + "==我进入评论方法了");
                if (logisticsDdn.getId() != null) {
                    topicCommentRepository.deleteById(logisticsDdn.getId());
                }

            }

            for (int i = 0; i < topicForword.size(); i++) {
                TopicForward logisticsDdn = topicForword.get(i);
                System.out.println("===logisticsDdn==="+logisticsDdn.getId()+"==我进入转发方法了");
                if(logisticsDdn.getId()!=null){
                    topicForwardRepository.deleteById(logisticsDdn.getId());
                }

            }

            for (int i = 0; i < topicVieweds.size(); i++) {
                TopicViewed logisticsDdn = topicVieweds.get(i);
                System.out.println("===logisticsDdn==="+logisticsDdn.getId()+"==我进入浏览方法了");
                if(logisticsDdn.getId()!=null){
                    topicViewedRepository.deleteById(logisticsDdn.getId());
                }
            }

            for (int i = 0; i < topicFabulous.size(); i++) {
                TopicFabulous logisticsDdn = topicFabulous.get(i);
                System.out.println("===logisticsDdn==="+logisticsDdn.getId()+"==我进入点赞方法了");
                if(logisticsDdn.getId()!=null){
                    topicFabulousRepository.deleteById(logisticsDdn.getId());
                }

            }

        }
        if(topicComments.size()==0&&topicFabulous.size()==0&&topicVieweds.size()==0&&topicForword.size()==0){
            System.out.println("==我进入删除方法了===");
            topicRepository.delete(topic.get());
        }

    }

    @Override
    public void deletefree(Long id) {
        System.out.println("====="+id);
        //先删除该话题下面所有评论在删除
        Optional<Topic> topic=topicRepository.findById(id);
        System.out.println("===topic=="+topic.get());
        TopicComment topicComment=new TopicComment();
        topicCommentRepository.deleteTopicCommentByTopic(topic.get());
        topicRepository.deleteById(id);
    }


    /**
     * Search for the topic corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Topic> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Topics for query {}", query);
        return topicSearchRepository.search(queryStringQuery(query), pageable);
    }


    /**
     * 通过此方法查询该用户所有的话题
     * @param id
     * @return
     */
    @Override
    public List<Topic> findAllTopicById(Long id) {
        System.out.println("=====id==="+id+"=====");
        List<Topic>AllTopicByID=topicRepository.findAllByUserInfoId(id);
        return AllTopicByID;
    }
}
