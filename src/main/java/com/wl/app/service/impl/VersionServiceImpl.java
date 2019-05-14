package com.wl.app.service.impl;

import com.wl.app.domain.Version;
import com.wl.app.repository.VersionRepository;
import com.wl.app.service.VersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * Service Implementation for managing UserInfo.
 */
@Service
@Transactional
public class VersionServiceImpl implements VersionService {

    private final VersionRepository versionRepository;

    public VersionServiceImpl(VersionRepository versionRepository) {
        this.versionRepository = versionRepository;
    }

    @Override
    public Version get() {
        Optional<Version> optionalVersion=versionRepository.findById((long)1);
        if(optionalVersion.isPresent()){
            return optionalVersion.get();
        }
        else{
            return new Version((long)0,"0","0","0");
        }

    }

    @Override
    public void set(String number, String path,String info) {
        Optional<Version> optionalVersion=versionRepository.findById((long)1);
        Version version=null;
        if(optionalVersion.isPresent()){
            version=optionalVersion.get();
        }
        version.setNumber(number);
        version.setPath(path);
        version.setInfo(info);
        versionRepository.save(version);
    }

    @Override
    public Version save() {
        return versionRepository.save(new Version((long)1,"1","",""));
    }
}
