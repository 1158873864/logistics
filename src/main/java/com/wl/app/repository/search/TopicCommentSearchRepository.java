package com.wl.app.repository.search;

import com.wl.app.domain.TopicComment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the TopicComment entity.
 */
public interface TopicCommentSearchRepository extends ElasticsearchRepository<TopicComment, Long> {
}
