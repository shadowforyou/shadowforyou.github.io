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

import com.poi.testpoi.mapper.HyDmxpFMapper;
import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.pojo.HyDmxpF;
import com.poi.testpoi.service.HyDmxpFService;
import com.poi.testpoi.util.CellDateUtil;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyDmxpFServiceImpl implements HyDmxpFService {

	@Autowired
	private HyDmxpFMapper hyDmxpFMapper;// 日时段最大降水量
	@Autowired
	private HyStscAMapper hyStscAMapper;
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {

		List<HyDmxpF> hyDmxpFList = new ArrayList<>();
		SheetUtil sheetUtil = new SheetUtil();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		CellDateUtil cellDateUtil = new CellDateUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		String stcd;
		String year;
		String mxp;
		Date bgdt;
		
		for (int r = 2; r <= sheet.getLastRowNum(); r++) {// r = 2表示从第三行开始循环
			stcd = cellTypeUtil.cellTypeStcd(fileName, file, r, 0);
			if (stcd == null || "".equals(stcd)) {
				break;
			}
			int i = hyStscAMapper.selectstcd(stcd);
			if (i <= 0) {
				continue;
			}
			Row row = sheet.getRow(r);// 通过sheet表单对象得到 行对象
			if (row == null) {
				continue;
			}
			year = cellTypeUtil.cellTypeYear(fileName, file, r, 1);
			for (int c = 2; c <= 10; c+=2) {
				mxp = cellTypeUtil.cellTypecommon(fileName, file, r, c);
				bgdt = cellDateUtil.cellTypeFromFromat(fileName, file,year, r, c+1);
				if(c==2){
					HyDmxpF hyDmxpF1 = new HyDmxpF();
					hyDmxpF1.setBgdt(bgdt);
					hyDmxpF1.setMxp(mxp);
					hyDmxpF1.setMxpdr("1");
					hyDmxpF1.setStcd(stcd);
					hyDmxpF1.setYear(year);
					hyDmxpFList.add(hyDmxpF1);
				}else if(c==4){
					HyDmxpF hyDmxpF2 = new HyDmxpF();
					hyDmxpF2.setBgdt(bgdt);
					hyDmxpF2.setMxp(mxp);
					hyDmxpF2.setMxpdr("3");
					hyDmxpF2.setStcd(stcd);
					hyDmxpF2.setYear(year);
					hyDmxpFList.add(hyDmxpF2);
				}else if(c==6){
					HyDmxpF hyDmxpF3 = new HyDmxpF();
					hyDmxpF3.setBgdt(bgdt);
					hyDmxpF3.setMxp(mxp);
					hyDmxpF3.setMxpdr("7");
					hyDmxpF3.setStcd(stcd);
					hyDmxpF3.setYear(year);
					hyDmxpFList.add(hyDmxpF3);
				}else if(c==8){
					HyDmxpF hyDmxpF4 = new HyDmxpF();
					hyDmxpF4.setBgdt(bgdt);
					hyDmxpF4.setMxp(mxp);
					hyDmxpF4.setMxpdr("15");
					hyDmxpF4.setStcd(stcd);
					hyDmxpF4.setYear(year);
					hyDmxpFList.add(hyDmxpF4);
				}else if(c==10){
					HyDmxpF hyDmxpF5 = new HyDmxpF();
					hyDmxpF5.setBgdt(bgdt);
					hyDmxpF5.setMxp(mxp);
					hyDmxpF5.setMxpdr("30");
					hyDmxpF5.setStcd(stcd);
					hyDmxpF5.setYear(year);
					hyDmxpFList.add(hyDmxpF5);
				}
			}
		}
		Integer count = null;
		for (HyDmxpF HyDmxpFResord : hyDmxpFList) {
			count = hyDmxpFMapper.selectstcd(HyDmxpFResord);
			if (count > 0) {
				hyDmxpFMapper.delete(HyDmxpFResord);
			}
			hyDmxpFMapper.add(HyDmxpFResord);
		}
		return notNull;
	}

}
