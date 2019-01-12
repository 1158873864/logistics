package com.wl.app.repository.search;

import com.wl.app.domain.TopicViewed;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the TopicViewed entity.
 */
public interface TopicViewedSearchRepository extends ElasticsearchRepository<TopicViewed, Long> {
}
