package com.poi.testpoi.service;

import org.springframework.web.multipart.MultipartFile;

public interface HyStscAService {
    boolean batchImport(String fileName, MultipartFile file)throws Exception;
}