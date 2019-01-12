package com.wl.app.service.impl;

import com.wl.app.service.MemberService;
import com.wl.app.domain.Member;
import com.wl.app.repository.MemberRepository;
import com.wl.app.repository.search.MemberSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    private final MemberRepository memberRepository;

    private final MemberSearchRepository memberSearchRepository;

    public MemberServiceImpl(MemberRepository memberRepository, MemberSearchRepository memberSearchRepository) {
        this.memberRepository = memberRepository;
        this.memberSearchRepository = memberSearchRepository;
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
        return StreamSupport
            .stream(memberSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}
