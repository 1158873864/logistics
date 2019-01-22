package com.wl.app.repository;

import com.wl.app.domain.UserDdnFavorites;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the UserDdnFavorites entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserDdnFavoritesRepository extends JpaRepository<UserDdnFavorites, Long> {
    List<UserDdnFavorites> getUserDdnFavoritesByUserInfoId(long user_id);
}
