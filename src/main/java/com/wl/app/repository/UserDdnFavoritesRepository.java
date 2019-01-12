package com.wl.app.repository;

import com.wl.app.domain.UserDdnFavorites;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UserDdnFavorites entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserDdnFavoritesRepository extends JpaRepository<UserDdnFavorites, Long> {

}
