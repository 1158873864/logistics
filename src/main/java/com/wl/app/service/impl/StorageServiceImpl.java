package com.wl.app.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wl.app.config.ApplicationProperties;
import com.wl.app.service.StorageService;

@Service
public class StorageServiceImpl implements StorageService {

	@Autowired
	private ApplicationProperties applicationProperties;

	@Override
	public String store(String saveDir,MultipartFile file) {

		if (file.isEmpty()) {
			return "error";
		}

		String filename = file.getOriginalFilename();
		try {
			Path path = Paths.get(applicationProperties.getStaticResourcePath() + "/"+saveDir, filename);
			if (!Files.exists(path)) {
				Files.createDirectories(path);
			}
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			return saveDir+"/" + filename;
		} catch (IOException e) {
			return "error";
		}

	}
}
