package com.wl.app.repository.search;

import com.wl.app.domain.PreferentialActivities;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PreferentialActivities entity.
 */
public interface PreferentialActivitiesSearchRepository extends ElasticsearchRepository<PreferentialActivities, Long> {
}
