package com.wl.app.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wl.app.config.ApplicationProperties;
import com.wl.app.service.StorageService;

@Controller
@RequestMapping("/api/static")
public class StaticController {

//	private final Logger log = LoggerFactory.getLogger(StaticController.class);

	private final ResourceLoader resourceLoader;
	private final ApplicationProperties applicationProperties;
	
	@Autowired
	private StorageService storageService;

	public StaticController(ResourceLoader resourceLoader, ApplicationProperties applicationProperties) {
		this.resourceLoader = resourceLoader;
		this.applicationProperties = applicationProperties;
	}
	

	
	@PostMapping("/upload-logistics-ddn-pic")
	@ResponseBody
    public Map<String,String> uploadLogisticsddnPic(@RequestParam("file") MultipartFile file) {
		
		Map<String,String> result =  new HashMap<>();
		
		String path = storageService.store("logistics-ddn",file);
        if(!path.equals("error")) {
        	 result.put("success", "true");
             result.put("path", path);
        }else {
        	
        	result.put("success", "false");
            result.put("path", "");
        }
        return result;
    }
	
	@PostMapping("/upload-logistics-ddn-pics")
	@ResponseBody
    public Map<String,String> uploadLogisticsddnPics(@RequestParam("file") MultipartFile file) {
		
		Map<String,String> result =  new HashMap<>();
		
		String path = storageService.store("logistics-ddn-pics",file);
        if(!path.equals("error")) {
        	 result.put("success", "true");
             result.put("path", path);
        }else {
        	
        	result.put("success", "false");
            result.put("path", "");
        }
        return result;
    }
	
	@PostMapping("/upload-goods-cover")
	@ResponseBody
    public Map<String,String> uploadGoodsCover(@RequestParam("file") MultipartFile file) {
		
		Map<String,String> result =  new HashMap<>();
		
		String path = storageService.store("goods-cover",file);
        if(!path.equals("error")) {
        	 result.put("success", "true");
             result.put("path", path);
        }else {
        	
        	result.put("success", "false");
            result.put("path", "");
        }
        return result;
    }
	
	@PostMapping("/upload-goods-pics")
	@ResponseBody
    public Map<String,Object> uploadGoodsPics(@RequestParam("file") MultipartFile[] files) {
		
		Map<String,Object> result =  new HashMap<>();
		
		List<String> pics = new ArrayList<>();
		for(MultipartFile file : files){
			String path = storageService.store("goods-pics",file);
			pics.add("/api/static/"+path);
		}
		
		
        if(!pics.isEmpty()) {
        	 result.put("errno", "0");
             result.put("data", pics.toArray(new String[pics.size()]));
        }else {
        	result.put("errno", "-1");
            result.put("data", new String[] {});
        }
        return result;
    }
	
	@PostMapping("/upload-sys-recruitment-information-pics")
	@ResponseBody
    public Map<String,Object> uploadSysRecruitmentInformationPics(@RequestParam("file") MultipartFile[] files) {
		
		Map<String,Object> result =  new HashMap<>();
		
		List<String> pics = new ArrayList<>();
		for(MultipartFile file : files){
			String path = storageService.store("sys-recruitment-information",file);
			pics.add("/api/static/"+path);
		}
		
		
        if(!pics.isEmpty()) {
        	 result.put("errno", "0");
             result.put("data", pics.toArray(new String[pics.size()]));
        }else {
        	result.put("errno", "-1");
            result.put("data", new String[] {});
        }
        return result;
    }
	
	@PostMapping("/upload-sys-preferential-activities-pics")
	@ResponseBody
    public Map<String,Object> uploadPreferentialActivitiesPics(@RequestParam("file") MultipartFile[] files) {
		
		Map<String,Object> result =  new HashMap<>();
		
		List<String> pics = new ArrayList<>();
		for(MultipartFile file : files){
			String path = storageService.store("preferential-activities",file);
			pics.add("/api/static/"+path);
		}
		
		
        if(!pics.isEmpty()) {
        	 result.put("errno", "0");
             result.put("data", pics.toArray(new String[pics.size()]));
        }else {
        	result.put("errno", "-1");
            result.put("data", new String[] {});
        }
        return result;
    }
	
	@PostMapping("/upload-preferential-activities-cover")
	@ResponseBody
    public Map<String,String> uploadPreferentialActivitiesCover(@RequestParam("file") MultipartFile file) {
		
		Map<String,String> result =  new HashMap<>();
		
		String path = storageService.store("preferential-activities",file);
        if(!path.equals("error")) {
        	 result.put("success", "true");
             result.put("path", path);
        }else {
        	
        	result.put("success", "false");
            result.put("path", "");
        }
        return result;
    }
	
	@PostMapping("/upload-topic-pics")
	@ResponseBody
    public Map<String,Object> uploadTopicPics(@RequestParam("file") MultipartFile[] files) {
		
		Map<String,Object> result =  new HashMap<>();
		
		List<String> pics = new ArrayList<>();
		for(MultipartFile file : files){
			String path = storageService.store("topic",file);
			pics.add("/api/static/"+path);
		}
		
		
        if(!pics.isEmpty()) {
        	 result.put("errno", "0");
             result.put("data", pics.toArray(new String[pics.size()]));
        }else {
        	result.put("errno", "-1");
            result.put("data", new String[] {});
        }
        return result;
    }
}
