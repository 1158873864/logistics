package com.wl.app.repository.search;

import com.wl.app.domain.TopicForward;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the TopicForward entity.
 */
public interface TopicForwardSearchRepository extends ElasticsearchRepository<TopicForward, Long> {
}
