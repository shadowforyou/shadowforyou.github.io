package com.poi.testpoi.service;

import org.springframework.web.multipart.MultipartFile;

public interface StnmService {
	String batchImport(String fileName, MultipartFile file) throws Exception;
}
