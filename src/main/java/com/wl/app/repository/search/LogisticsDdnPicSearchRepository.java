package com.wl.app.repository.search;

import com.wl.app.domain.LogisticsDdnPic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the LogisticsDdnPic entity.
 */
public interface LogisticsDdnPicSearchRepository extends ElasticsearchRepository<LogisticsDdnPic, Long> {
}
