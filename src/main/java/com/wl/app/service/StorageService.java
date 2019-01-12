package com.wl.app.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	String store(String saveDir, MultipartFile file);
}
