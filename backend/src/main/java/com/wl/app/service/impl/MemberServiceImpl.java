package com.wl.app.service.impl;

import com.wl.app.config.Constants;
import com.wl.app.domain.*;
//import com.wl.app.domain.MemberTwo;
import com.wl.app.repository.*;
//import com.wl.app.repository.MemberTwoRepository;
//import com.wl.app.repository.search.MemberTwoSearchRepository;
import com.wl.app.repository.search.MemberTwoSearchRepository;
import com.wl.app.service.MemberService;
import com.wl.app.repository.search.MemberSearchRepository;

import com.wl.app.service.dto.UserDTO;
import com.wl.app.service.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cache.CacheManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Member.
 */
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final MemberTwoRepository memberTwoRepository;
    private final MemberSearchRepository memberSearchRepository;
    private final LogisticsDdnRepository logisticsDdnRepository;
    private final MemberTwoSearchRepository memberTwoSearchRepository;
    private final AuthorityRepository authorityRepository;
    private final UserInfoRepository userInfoRepository;
    //private final CacheManager cacheManager;


    public MemberServiceImpl(MemberRepository memberRepository, MemberSearchRepository memberSearchRepository,LogisticsDdnRepository logisticsDdnRepository
   ,MemberTwoRepository memberTwoRepository, MemberTwoSearchRepository memberTwoSearchRepository,PasswordEncoder passwordEncoder,AuthorityRepository authorityRepository,UserRepository userRepository,
    UserInfoRepository userInfoRepository) {
        this.memberRepository = memberRepository;
        this.memberSearchRepository = memberSearchRepository;
        this.logisticsDdnRepository=logisticsDdnRepository;
        this.memberTwoRepository=memberTwoRepository;
        this.memberTwoSearchRepository=memberTwoSearchRepository;
        this.passwordEncoder=passwordEncoder;
        this.authorityRepository=authorityRepository;
        this.userRepository=userRepository;
        this.userInfoRepository=userInfoRepository;
        //this.cacheManager=cacheManager;
    }

    /**
     * Save a member.
     *
     * @param member the entity to save
     * @return the persisted entity
     */
    @Override
    public Member save(Member member) {
        log.debug("Request to save Member : {}", member);        Member result = memberRepository.save(member);
        memberSearchRepository.save(result);
        return result;
    }

    /**
     * 添加（可以录入）
     */
    @Override
     public Member input(Member member) {
        System.out.println("input======="+member.getUserInfo().getMobilePhone()+"00000000000");
        //先通过手机号码查询出会员信息，如果有信息就代表是会员
        //没找到就代表不是会员，不是会员就录入，修改专线vip为true
        Member Allmember=memberRepository.findMemberByUserInfoMobilePhone(member.getUserInfo().getMobilePhone());
        System.out.println("Allmember======="+Allmember+"=====11111111111111");
        System.out.println("1111111111===="+(Allmember==null)+"======");
       //Optional<UserInfo> AllUserinfo=userInfoRepository.findUserInfoByMobilePhone(member.getUserInfo().getMobilePhone());
        if (Allmember==null||Allmember.getUserInfo().getMobilePhone()==null){
            System.out.println("6666666666666666666");
            //录入会员。而不是录入用户
            Optional<UserInfo> AllUserinfo=userInfoRepository.findUserInfoByMobilePhone(member.getUserInfo().getMobilePhone());
            //System.out.println("===AllUserinfo=="+AllUserinfo.get().getUser()+"=======");
            //System.out.println("===AllUserinfoUser=="+AllUserinfo.get()+"=======");
            member.getUserInfo().getUser().setResetKey(RandomUtil.generateResetKey());
            member.getUserInfo().getUser().setResetDate(Instant.now());
            String encryptedPasswordone = passwordEncoder.encode(RandomUtil.generatePassword());
            member.getUserInfo().getUser().setPassword(encryptedPasswordone);
            //找到一个是用户而不是会员得人
            member.getUserInfo().getUser().setLogin(StringUtils.lowerCase(member.getUserInfo().getUser().getLogin(), Locale.ENGLISH));
            userRepository.save(member.getUserInfo().getUser());
            System.out.println("0000====000");
            userInfoRepository.save(member.getUserInfo());
            System.out.println("11111===111");
            memberRepository.save(member);
            //memberSearchRepository.save(resultone);
            return null;
        }
        if(Allmember!=null||Allmember.getUserInfo().getMobilePhone().equals(member.getUserInfo().getMobilePhone())||Allmember.getUserInfo().getMobilePhone()!=null){
            //代表是会员
            System.out.println("我进入IF判断了");
            Optional<UserInfo> AllUserinfo=userInfoRepository.findUserInfoByMobilePhone(member.getUserInfo().getMobilePhone());
            System.out.println("===AllUserinfoUser=="+AllUserinfo.get()+"=======");
            LogisticsDdn logisticsDdn = logisticsDdnRepository.findLogisticsDdnByManagerMobilePhone(member.getUserInfo().getMobilePhone());
            System.out.println("===logisticsDdn=="+logisticsDdn+"=======");
            if(logisticsDdn==null){

            }
            System.out.println("000000===="+logisticsDdn+"=========");
            logisticsDdn.setVip(true);
            LogisticsDdn result = logisticsDdnRepository.save(logisticsDdn);
            logisticsDdnRepository.save(result);
            return member;
        }


        /*
        if (member.getUserInfo().getUser().getAuthorities() != null) {
            Set<Authority> authorities = userDTO.getAuthorities().stream()
                    .map(authorityRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
            user.setAuthorities(authorities);
        }

*/
        /*user.setLogin(member.getUserInfo().getUser().getLogin().toLowerCase());
        user.setFirstName(member.getUserInfo().getUser().getFirstName());
        user.setLastName(member.getUserInfo().getUser().getLastName());
        user.setEmail(member.getUserInfo().getUser().getEmail().toLowerCase());
        user.setImageUrl(member.getUserInfo().getUser().getImageUrl());*/
        //语言
       /* if (member.getUserInfo().getUser().getLangKey() == null) {
            user.setLangKey(Constants.DEFAULT_LANGUAGE); // default language
        } else {
            user.setLangKey(member.getUserInfo().getUser().getLangKey());
        }
        String encryptedPasswordone = passwordEncoder.encode(RandomUtil.generatePassword());
        user.setPassword(encryptedPasswordone);
        user.setResetKey(RandomUtil.generateResetKey());
        user.setResetDate(Instant.now());
        user.setActivated(true);*/
        //为空


           /*//先查询出会员的所有信息；
         List<Member> Allmember =  memberRepository.findAll();
         System.out.println("Allmember========"+Allmember.size()+"=======");
         for (int i = 0; i < Allmember.size(); i++) {
             if (Allmember.get(i).getUserInfo().getMobilePhone().equals(member.getUserInfo().getMobilePhone())) {
                 System.out.println("member.getMobilePhone()====="+member.getUserInfo().getMobilePhone()+"=========");
                 System.out.println("AllmembergetMobilePhone()====="+Allmember.get(i).getUserInfo().getMobilePhone()+"=========");
                 return member;
                 //System.out.println("sortedList------"+sortedList.size()+"---------sortedList");
             }
              for (int j = 0; j < Allmember.size(); j++) {
                  //通过手机号查询出某条专线得详情
                  List<LogisticsDdn> vip = logisticsDdnRepository.findBymanagerMobilePhone(member.getUserInfo().getMobilePhone());
                  System.out.println("vip=======" + vip + "====vip");
                  vip.get(j).setVip(true);
                  //需要修改upadate
                  LogisticsDdn result = logisticsDdnRepository.save(vip.get(j));
                  logisticsDdnRepository.save(result);
                  memberSearchRepository.save(member);
              }
         }*/
           return null;
     }

   /* private void clearUserCaches(User user) {
        Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE)).evict(user.getLogin());
        Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE)).evict(user.getEmail());
    }*/




    /**
     * 和上面做对比，进行猜想
     * @param member
     * @return
     */
    @Override
    public Member inputmember(Member member) {
        System.out.println("input=======" + member.getUserInfo().getMobilePhone() + "00000000000");
        //先通过手机号码查询出会员信息，如果有信息就代表是会员
        //没找到就代表不是会员，不是会员就录入，修改专线vip为true
        Member Allmember = memberRepository.findMemberByUserInfoMobilePhone(member.getUserInfo().getMobilePhone());
        System.out.println("Allmember=======" + Allmember + "=====11111111111111");
        System.out.println("1111111111====" + (Allmember == null) + "======");
        //Optional<UserInfo> AllUserinfo=userInfoRepository.findUserInfoByMobilePhone(member.getUserInfo().getMobilePhone());
        if (Allmember == null || Allmember.getUserInfo().getMobilePhone() == null) {
            System.out.println("6666666666666666666");
            //录入会员。而不是录入用户
            Optional<UserInfo> AllUserinfo = userInfoRepository.findUserInfoByMobilePhone(member.getUserInfo().getMobilePhone());
            //System.out.println("===AllUserinfo=="+AllUserinfo.get().getUser()+"=======");
            //System.out.println("===AllUserinfoUser=="+AllUserinfo.get()+"=======");
           /* member.getUserInfo().getUser().setResetKey(RandomUtil.generateResetKey());
            member.getUserInfo().getUser().setResetDate(Instant.now());
            String encryptedPasswordone = passwordEncoder.encode(RandomUtil.generatePassword());
            member.getUserInfo().getUser().setPassword(encryptedPasswordone);*/
            //找到一个是用户而不是会员得人
            //member.getUserInfo().getUser().setLogin(member.getUserInfo().getMobilePhone());
            userRepository.save(AllUserinfo.get().getUser());
            System.out.println("0000====000");
            userInfoRepository.save(AllUserinfo.get());
            System.out.println("11111===111");
            memberRepository.save(member);
            //memberSearchRepository.save(resultone);
            return null;
        }
        if (Allmember != null || Allmember.getUserInfo().getMobilePhone().equals(member.getUserInfo().getMobilePhone()) || Allmember.getUserInfo().getMobilePhone() != null) {
            //代表是会员
            System.out.println("我进入IF判断了");
            Optional<UserInfo> AllUserinfo = userInfoRepository.findUserInfoByMobilePhone(member.getUserInfo().getMobilePhone());
            System.out.println("===AllUserinfoUser==" + AllUserinfo.get() + "=======");
            LogisticsDdn logisticsDdn = logisticsDdnRepository.findLogisticsDdnByManagerMobilePhone(member.getUserInfo().getMobilePhone());
            System.out.println("===logisticsDdn==" + logisticsDdn + "=======");
            if (logisticsDdn == null) {

            }
            System.out.println("000000====" + logisticsDdn + "=========");
            logisticsDdn.setVip(true);
            LogisticsDdn result = logisticsDdnRepository.save(logisticsDdn);
            logisticsDdnRepository.save(result);
            return member;
        }
        return null;
    }




    /**
     * 录入3.0
     * @param member
     * @return
     */
    @Override
    public MemberTwo inputMemberTwo(MemberTwo member) {
        System.out.println("input======="+member.getMobilePhone()+"00000000000");
        //先通过手机号码查询出会员信息，如果有信息就代表是会员
        //没找到就代表不是会员，不是会员就录入，修改专线vip为true
        MemberTwo AllmemberTwo=memberTwoRepository.findMemberTwoByMobilePhone(member.getMobilePhone());
        System.out.println("Allmember======="+AllmemberTwo+"=====11111111111111");
        if (AllmemberTwo==null||AllmemberTwo.getMobilePhone()==null){
            //System.out.println("vip======="+logisticsDdn.getManagerMobilePhone()+"===vip");
            MemberTwo resultone = memberTwoRepository.save(member);
            memberTwoSearchRepository.save(resultone);
            String  A=member.getMobilePhone();
            Instant B=member.getEndDate();
            Instant C=member.getStartDate();
            Member Allmember=memberRepository.findMemberByUserInfoMobilePhone(member.getMobilePhone());
            Allmember.setStartDate(C);
            System.out.println("000000000000000==="+A+"2222222222");
            Allmember.setEndDate(B);
            Allmember.getUserInfo().setMobilePhone(A);
            Member resultTwo = memberRepository.save(Allmember);
            memberSearchRepository.save(resultTwo);
        }

        if(AllmemberTwo!=null&&AllmemberTwo.getMobilePhone().equals(member.getMobilePhone())){
            //代表是会员
            LogisticsDdn logisticsDdn = logisticsDdnRepository.findLogisticsDdnByManagerMobilePhone(member.getMobilePhone());
            logisticsDdn.setVip(true);
            LogisticsDdn result = logisticsDdnRepository.save(logisticsDdn);
            logisticsDdnRepository.save(result);
            return member;
        }

        return null;
    }


    /**
     * Get all the members.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Member> findAll() {
        log.debug("Request to get all Members");
        return memberRepository.findAll();
    }


    /**
     * Get one member by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Member> findOne(Long id) {
        log.debug("Request to get Member : {}", id);
        return memberRepository.findById(id);
    }

    /**
     * Delete the member by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Member : {}", id);
        memberRepository.deleteById(id);
        memberSearchRepository.deleteById(id);
    }

    /**
     * Search for the member corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Member> search(String query) {
        log.debug("Request to search Members for query {}", query);
        List<Member> members=memberRepository.findAll();
        List<Member> sortedList=new ArrayList<>();
        for(int i=0;i<members.size();i++){
            Member member=members.get(i);
            if(member.getEndDate().toString().indexOf(query)!=(-1)||member.getStartDate().toString().indexOf(query)!=(-1)||member.getUserInfo().getMobilePhone().indexOf(query)!=(-1)||String.valueOf(member.getId()).indexOf(query)!=(-1)){
                sortedList.add(member);
            }
        }
        return sortedList;
    }

    /*@Override
    @Transactional(readOnly = true)
    public List<Member> queryMember(String query){
        return memberRepository.findAllmember(query);
    }*/


}
