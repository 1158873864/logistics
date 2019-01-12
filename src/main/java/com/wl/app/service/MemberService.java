package com.wl.app.service;

import com.wl.app.domain.Member;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Member.
 */
public interface MemberService {

    /**
     * Save a member.
     *
     * @param member the entity to save
     * @return the persisted entity
     */
    Member save(Member member);

    /**
     * Get all the members.
     *
     * @return the list of entities
     */
    List<Member> findAll();


    /**
     * Get the "id" member.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Member> findOne(Long id);

    /**
     * Delete the "id" member.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the member corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<Member> search(String query);
}
