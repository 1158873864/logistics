package com.wl.app.repository.search;

import com.wl.app.domain.IntegralChangeRecord;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the IntegralChangeRecord entity.
 */
public interface IntegralChangeRecordSearchRepository extends ElasticsearchRepository<IntegralChangeRecord, Long> {
}
