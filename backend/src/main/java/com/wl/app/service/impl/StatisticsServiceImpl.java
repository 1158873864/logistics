package com.wl.app.service.impl;

import com.wl.app.domain.*;
import com.wl.app.repository.*;
import com.wl.app.service.StatisticsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.TimeZone;

/**
 * Service Implementation for managing UserInfo.
 */
@Service
@Transactional
public class StatisticsServiceImpl implements StatisticsService {

    private final UserRepository userRepository;

    private final GoodsSourceRepository goodsSourceRepository;

    private final MemberRepository memberRepository;

    private final TopicRepository topicRepository;

    private final TopicCommentRepository topicCommentRepository;

    private final LogisticsDdnRepository logisticsDdnRepository;

    public StatisticsServiceImpl(UserRepository userRepository, GoodsSourceRepository goodsSourceRepository, MemberRepository memberRepository, TopicRepository topicRepository, TopicCommentRepository topicCommentRepository, LogisticsDdnRepository logisticsDdnRepository) {
        this.userRepository = userRepository;
        this.goodsSourceRepository = goodsSourceRepository;
        this.memberRepository = memberRepository;
        this.topicRepository = topicRepository;
        this.topicCommentRepository = topicCommentRepository;
        this.logisticsDdnRepository = logisticsDdnRepository;
    }


    @Override
    public int userToday() {
        return 0;
    }

    @Override
    public int userAll() {

        return userRepository.findAll().size();
    }

    @Override
    public int goodsToday() {
        int result=0;
        List<GoodsSource> goodsSources=goodsSourceRepository.findAll();
        for(GoodsSource goodsSource:goodsSources){
            Instant goodsTime=goodsSource.getLayTime();
            Instant now=Instant.now();
            if(isSameDay(goodsTime.toEpochMilli(),now.toEpochMilli(),TimeZone.getDefault())){
                result++;
            }
        }
        return result;
    }

    @Override
    public int goodsAll() {
        return goodsSourceRepository.findAll().size();
    }

    @Override
    public int memberToday() {
        int result=0;
        List<Member> members=memberRepository.findAll();
        for(Member member:members){
            Instant memberTime=member.getStartDate();
            Instant now=Instant.now();
            if(isSameDay(memberTime.toEpochMilli(),now.toEpochMilli(),TimeZone.getDefault())){
                result++;
            }
        }
        return result;
    }

    @Override
    public int memberAll() {
        return memberRepository.findAll().size();
    }

    @Override
    public int topicToday() {
        int result=0;
        List<Topic> topics=topicRepository.findAll();
        for(Topic topic:topics){
            Instant topicTime=topic.getPublished();
            Instant now=Instant.now();
            if(isSameDay(topicTime.toEpochMilli(),now.toEpochMilli(),TimeZone.getDefault())){
                result++;
            }
        }
        return result;
    }

    @Override
    public int topicAll() {
        return topicRepository.findAll().size();
    }

    @Override
    public int commentToday() {
        int result=0;
        List<TopicComment> topicComments=topicCommentRepository.findAll();
        for(TopicComment topicComment:topicComments){
            Instant topicCommentTime=topicComment.getoDateTime();
            Instant now=Instant.now();
            if(isSameDay(topicCommentTime.toEpochMilli(),now.toEpochMilli(),TimeZone.getDefault())){
                result++;
            }
        }
        return result;
    }

    @Override
    public int commentAll() {
        return topicCommentRepository.findAll().size();
    }

    @Override
    public int ddn(String start) {
        return logisticsDdnRepository.findByLocationCity(start).size();
    }

    @Override
    public List<String> findStartCitys() {
        return logisticsDdnRepository.findStartCitys();
    }


    public static boolean isSameDay(long millis1, long millis2, TimeZone timeZone) {
        long interval = millis1 - millis2;
        return interval < 86400000 && interval > -86400000 && millis2Days(millis1, timeZone) == millis2Days(millis2, timeZone);
    }

    private static long millis2Days(long millis, TimeZone timeZone) {
        return (((long) timeZone.getOffset(millis)) + millis) / 86400000;
    }


}
