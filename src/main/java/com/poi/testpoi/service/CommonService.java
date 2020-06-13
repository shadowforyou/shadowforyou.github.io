package com.poi.testpoi.service;

import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.multipart.MultipartFile;

public interface CommonService {
	Sheet batchImport(String fileName, MultipartFile file) throws Exception;
}
