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

import com.poi.testpoi.mapper.HyRvfhexBMapper;
import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.pojo.HyRvfhexB;
import com.poi.testpoi.service.HyRvfhexBService;
import com.poi.testpoi.util.CellDateUtil;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyRvfhexBServiceImpl implements HyRvfhexBService {

	@Autowired
	private HyRvfhexBMapper hyRvfhexBMapper;
	@Autowired
	private HyStscAMapper hyStscAMapper;

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {

		List<HyRvfhexB> hyRvfhexBList = new ArrayList<>();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		CellDateUtil cellDateUtil = new CellDateUtil();
		SheetUtil sheetUtil = new SheetUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}

		HyRvfhexB hyRvfhexB;

		String year;
		String stcd;
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
			hyRvfhexB = new HyRvfhexB();
			year = cellTypeUtil.cellTypeYear(fileName, file, r, 1);
			hyRvfhexB.setStcd(stcd);
			//月日
			Date Msq = cellDateUtil.cellTypeFromFromat(fileName, file, year, r, 2);
			String M = String.format("%tm", Msq);
			String D = String.format("%td", Msq);
			hyRvfhexB.setTm(cellDateUtil.cellTypeFromMeasure(fileName,file, year, M, D, r, 3));
			hyRvfhexB.setDambhdz(cellTypeUtil.cellTypecommon(fileName,file,r,4));				
			hyRvfhexB.setW(cellTypeUtil.cellTypecommon(fileName,file,r,6));
			hyRvfhexB.setQ(cellTypeUtil.cellTypecommon(fileName,file,r,7));
			
			hyRvfhexBList.add(hyRvfhexB);
		}

		Integer count = null;
		for (HyRvfhexB hyRvfhexBResord : hyRvfhexBList) {
			count = hyRvfhexBMapper.selectstcd(hyRvfhexBResord);
			if (count > 0) {
				hyRvfhexBMapper.delete(hyRvfhexBResord);
			}
			hyRvfhexBMapper.add(hyRvfhexBResord);
		}
		return notNull;
	}
}
