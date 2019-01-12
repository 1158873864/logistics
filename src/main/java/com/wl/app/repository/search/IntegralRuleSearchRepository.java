package com.wl.app.repository.search;

import com.wl.app.domain.IntegralRule;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the IntegralRule entity.
 */
public interface IntegralRuleSearchRepository extends ElasticsearchRepository<IntegralRule, Long> {
}
