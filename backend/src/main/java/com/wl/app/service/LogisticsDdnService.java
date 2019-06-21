package com.wl.app.service;

import com.wl.app.domain.LogisticsDdn;

import com.wl.app.service.dto.CannotGetOpenIdAndSessionKeyException;
import com.wl.app.service.dto.OpenIdAndSessionKeyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing LogisticsDdn.
 */
public interface LogisticsDdnService {

    /**
     * Save a logisticsDdn.
     *
     * @param logisticsDdn the entity to save
     * @return the persisted entity
     */
    LogisticsDdn save(LogisticsDdn logisticsDdn);


     /**
     * Get all the logisticsDdns.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LogisticsDdn> findAll(Pageable pageable);
    
    //Page<LogisticsDdn> findAll(String startLine,String endLine,Pageable pageable);
    List<LogisticsDdn> findAll(String startLine,String endLine,Pageable pageable);
    /**
     * Get the "id" logisticsDdn.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LogisticsDdn> findOne(Long id);

    /**
     * Delete the "id" logisticsDdn.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    abstract Page<LogisticsDdn> search(String query, Pageable pageable);
    boolean batchImportDDN(String path);
    boolean importDDNPics(String ddmTitle,String managerFullname,String path,boolean isCover);

    OpenIdAndSessionKeyResponse getOpenIdAndSessionKey(String jsCode) throws CannotGetOpenIdAndSessionKeyException;

    String decrypt(String sessionKey, String encryptedData, String iv) throws Exception;

    List<String> findStartCitys();
    
    List<LogisticsDdn> findAllByHome(boolean isHome);
    List<LogisticsDdn> findAllByBanner(boolean isBanner);
    LogisticsDdn findAllByPhoneNumber(String PhoneNumber);
    //LogisticsDdn UpadateByNumber(LogisticsDdn logisticsDdn);

    List<LogisticsDdn> findAllByspecialTransport(boolean specialTransport);

    List<LogisticsDdn> findAllBygood(boolean good);

    Page<LogisticsDdn> find(String query, Pageable pageable);
}
