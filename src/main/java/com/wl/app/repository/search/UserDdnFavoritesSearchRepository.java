package com.wl.app.repository.search;

import com.wl.app.domain.UserDdnFavorites;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the UserDdnFavorites entity.
 */
public interface UserDdnFavoritesSearchRepository extends ElasticsearchRepository<UserDdnFavorites, Long> {
}
