package com.wl.app.repository.search;

import com.wl.app.domain.Member;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Member entity.
 */
public interface MemberSearchRepository extends ElasticsearchRepository<Member, Long> {
}
