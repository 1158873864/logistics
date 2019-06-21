package com.wl.app.repository;

import com.wl.app.domain.Member;
//import com.wl.app.domain.MemberTwo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Member entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    //MemberTwo findMemberByUserInfoMobilePhone(String MobilePhone);
    Member findMemberByUserInfoMobilePhone(String MobilePhone);
}
