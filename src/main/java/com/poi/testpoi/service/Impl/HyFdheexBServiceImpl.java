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

import com.poi.testpoi.mapper.HyFdheexBMapper;
import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.pojo.HyFdheexB;
import com.poi.testpoi.service.HyFdheexBService;
import com.poi.testpoi.util.CellDateUtil;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyFdheexBServiceImpl implements HyFdheexBService {

	@Autowired
	private HyFdheexBMapper hyFdheexBMapper;
	@Autowired
	private HyStscAMapper hyStscAMapper;

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		CellDateUtil cellDateUtil = new CellDateUtil();
		SheetUtil sheetUtil = new SheetUtil();
		List<HyFdheexB> hyFdheexBList = new ArrayList<>();

		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}

		HyFdheexB hyFdheexB;
		String year ;
		System.out.println(fileName);
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
				year = cellTypeUtil.cellTypeYear(fileName, file, r, 1);
				hyFdheexB = new HyFdheexB();
				hyFdheexB.setStcd(stcd);
				//月日
				Date Msq = cellDateUtil.cellTypeFromFromat(fileName, file, year, r, 2);
				String M = String.format("%tm", Msq);
				String D = String.format("%td", Msq);

				hyFdheexB.setTm(cellDateUtil.cellTypeFromMeasure(fileName,file, year, M, D, r, 3));


				//水位
				hyFdheexB.setZ(cellTypeUtil.cellTypecommon(fileName,file,r,4));
				//流量
				hyFdheexB.setQ(cellTypeUtil.cellTypecommon(fileName,file,r,5));
				//含沙量
				String s =cellTypeUtil.cellTypecommon(fileName,file,r,6);
				if(s==null){hyFdheexB.setS(null);
				}else {hyFdheexB.setS(s);}

				hyFdheexBList.add(hyFdheexB);
			}

		for (HyFdheexB hyFdheexBResord : hyFdheexBList) {
			Integer count =	hyFdheexBMapper.selectstcd(hyFdheexBResord);
			if (count > 0) {
				hyFdheexBMapper.delete(hyFdheexBResord);
			}
			hyFdheexBMapper.add(hyFdheexBResord);
		}


		return notNull;
	}

}
