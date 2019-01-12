package com.wl.app.repository.search;

import com.wl.app.domain.GoodsSource;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the GoodsSource entity.
 */
public interface GoodsSourceSearchRepository extends ElasticsearchRepository<GoodsSource, Long> {
}
