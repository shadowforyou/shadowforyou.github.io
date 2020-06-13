package com.poi.testpoi.service;

import org.springframework.web.multipart.MultipartFile;

public interface HyWfdzFService {
	boolean batchImport(String fileName, MultipartFile file) throws Exception;
	}
