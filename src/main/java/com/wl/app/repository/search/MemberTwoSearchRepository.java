package com.wl.app.repository.search;

import com.wl.app.domain.MemberTwo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MemberTwoSearchRepository extends ElasticsearchRepository<MemberTwo, Long> {
}

