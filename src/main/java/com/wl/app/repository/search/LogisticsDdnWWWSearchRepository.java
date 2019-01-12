package com.wl.app.repository.search;

import com.wl.app.domain.LogisticsDdnWWW;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the LogisticsDdnWWW entity.
 */
public interface LogisticsDdnWWWSearchRepository extends ElasticsearchRepository<LogisticsDdnWWW, Long> {
}
