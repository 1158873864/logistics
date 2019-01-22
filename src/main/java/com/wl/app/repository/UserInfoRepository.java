package com.wl.app.repository;

import com.wl.app.domain.User;
import com.wl.app.domain.UserInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the UserInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
      Optional<UserInfo> getUserInfoByMobilePhone(String mobilePhone);
}
