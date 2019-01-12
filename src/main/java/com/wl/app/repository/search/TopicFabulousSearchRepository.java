package com.wl.app.repository.search;

import com.wl.app.domain.TopicFabulous;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the TopicFabulous entity.
 */
public interface TopicFabulousSearchRepository extends ElasticsearchRepository<TopicFabulous, Long> {
}
