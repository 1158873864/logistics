package com.wl.app.repository.search;

import com.wl.app.domain.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Goods entity.
 */
public interface GoodsSearchRepository extends ElasticsearchRepository<Goods, Long> {
}
