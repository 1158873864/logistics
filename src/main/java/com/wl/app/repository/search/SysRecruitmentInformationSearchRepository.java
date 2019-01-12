package com.wl.app.repository.search;

import com.wl.app.domain.SysRecruitmentInformation;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the SysRecruitmentInformation entity.
 */
public interface SysRecruitmentInformationSearchRepository extends ElasticsearchRepository<SysRecruitmentInformation, Long> {
}
