package com.wl.app.repository;

import com.wl.app.domain.Browse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BrowseRepository extends JpaRepository<Browse, Long> {

}
