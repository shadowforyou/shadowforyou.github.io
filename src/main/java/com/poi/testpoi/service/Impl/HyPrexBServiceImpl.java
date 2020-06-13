package com.poi.testpoi.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.poi.testpoi.mapper.HyPrexBMapper;
import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.pojo.HyPrexB;
import com.poi.testpoi.service.HyPrexBService;
import com.poi.testpoi.util.CellDateUtil;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyPrexBServiceImpl implements HyPrexBService {

	@Autowired
	private HyPrexBMapper hyPrexBMapper;
	@Autowired
	private HyStscAMapper hyStscAMapper;

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		List<HyPrexB> hyPrexBList = new ArrayList<>();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		CellDateUtil cellDateUtil = new CellDateUtil();
		SheetUtil sheetUtil = new SheetUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		HyPrexB hyPrexB;
		String year;
		String stcd = null;
			for (int r = 2; r <= sheet.getLastRowNum(); r++) {
				stcd = cellTypeUtil.cellTypeStcd(fileName, file, r, 0);
				if (stcd == null || "".equals(stcd)) {
					break;
				}
				int i=hyStscAMapper.selectstcd(stcd);
				if(i<=0){
					continue;
				}
				Row row = sheet.getRow(r);// 通过sheet表单对象得到 行对象
				if (row == null) {
					continue;
				}
				hyPrexB = new HyPrexB();
				year = cellTypeUtil.cellTypeYear(fileName, file, r,1);
				hyPrexB.setStcd(stcd);
				//月日
				Date Msq = cellDateUtil.cellTypeFromFromat(fileName, file, year, r, 2);
				String M = String.format("%tm", Msq);
				String D = String.format("%td", Msq);
				hyPrexB.setBgtm(cellDateUtil.cellTypeFromMeasure(fileName,file, year, M, D, r, 3));
				hyPrexB.setEndtm(cellDateUtil.cellTypeFromMeasure(fileName,file, year, M, D, r, 4));

				hyPrexB.setP(cellTypeUtil.cellTypecommon(fileName,file,r,5));

				hyPrexBList.add(hyPrexB);
			}

		for (HyPrexB hyFdheexBResord : hyPrexBList) {
			Integer count =	hyPrexBMapper.selectstcd(hyFdheexBResord);
			if (count > 0) {
				hyPrexBMapper.delete(hyFdheexBResord);
			}
			hyPrexBMapper.add(hyFdheexBResord);
		}

		return notNull;
	}
}
