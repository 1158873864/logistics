package com.wl.app.repository.search;

import com.wl.app.domain.TopicForward;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Spring Data Elasticsearch repository for the TopicForward entity.
 */
public interface TopicForwardSearchRepository extends ElasticsearchRepository<TopicForward, Long> {

}
