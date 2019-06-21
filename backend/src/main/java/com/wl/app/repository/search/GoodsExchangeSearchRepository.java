package com.wl.app.repository.search;

import com.wl.app.domain.GoodsExchange;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Elasticsearch repository for the GoodsExchange entity.
 */
public interface GoodsExchangeSearchRepository extends ElasticsearchRepository<GoodsExchange, Long> {
}
