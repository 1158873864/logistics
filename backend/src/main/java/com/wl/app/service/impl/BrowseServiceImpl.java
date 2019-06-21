package com.wl.app.service.impl;

import com.wl.app.domain.Browse;
import com.wl.app.repository.BrowseRepository;
import com.wl.app.service.BrowseService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Service Implementation for managing UserInfo.
 */
@Service
@Transactional
public class BrowseServiceImpl implements BrowseService {

    private final BrowseRepository browseRepository;

    public BrowseServiceImpl(BrowseRepository browseRepository) {
        this.browseRepository = browseRepository;
    }

    @Override
    public int get(Long ddnid) {
        Optional<Browse> optionalBrowse=browseRepository.findById(ddnid);
        if(optionalBrowse.isPresent()){
            return optionalBrowse.get().getNumber();
        }
        else{
            browseRepository.save(new Browse(ddnid,0));
            return 0;
        }
    }

    @Override
    public void add(Long ddnid) {
        Optional<Browse> optionalBrowse=browseRepository.findById(ddnid);
        if(optionalBrowse.isPresent()){
            Browse browse=optionalBrowse.get();
            browse.setNumber(browse.getNumber()+1);
            browseRepository.save(browse);
        }
        else{
            browseRepository.save(new Browse(ddnid,1));
        }
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void updateLocalNews() {
        Random random=new Random();
        List<Browse> browses=browseRepository.findAll();
        // 在这里写你要执行的内容
        for(Browse browse :browses){
            int number=random.nextInt(9)+1;
            browse.setNumber(browse.getNumber()+number);
            browseRepository.save(browse);
        }

    }
}
