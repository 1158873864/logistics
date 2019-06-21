package com.wl.app.service;


import java.util.List;

/**
 * Service Interface for managing Goods.
 */
public interface StatisticsService {

    int userToday();
    int userAll();
    int goodsToday();
    int goodsAll();
    int memberToday();
    int memberAll();
    int topicToday();
    int topicAll();
    int commentToday();
    int commentAll();
    int ddn(String start);
    List<String> findStartCitys();
}
