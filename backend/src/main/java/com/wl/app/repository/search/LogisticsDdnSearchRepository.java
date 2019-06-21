package com.wl.app.repository.search;

import com.wl.app.domain.LogisticsDdn;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the LogisticsDdn entity.
 */
public interface LogisticsDdnSearchRepository extends ElasticsearchRepository<LogisticsDdn, Long> {
}
