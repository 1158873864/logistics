package com.wl.app.repository;

import com.wl.app.domain.MemberTwo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@SuppressWarnings("unused")
@Repository
public interface MemberTwoRepository  extends JpaRepository<MemberTwo, Long> {
    MemberTwo findMemberTwoByMobilePhone(String MobilePhone);
}
