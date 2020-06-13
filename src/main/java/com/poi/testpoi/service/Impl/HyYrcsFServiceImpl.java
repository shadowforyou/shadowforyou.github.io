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

import com.poi.testpoi.mapper.HyDaexIMapper;
import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.mapper.HyYrcsFMapper;
import com.poi.testpoi.pojo.HyDaexI;
import com.poi.testpoi.pojo.HyYrcsF;
import com.poi.testpoi.service.HyYrcsFService;
import com.poi.testpoi.util.CellDateUtil;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyYrcsFServiceImpl implements HyYrcsFService {

	@Autowired
	private HyYrcsFMapper hyYrcsFMapper;
	@Autowired
	private HyStscAMapper hyStscAMapper;
	@Autowired
	private HyDaexIMapper hyDaexIMapper;// 附录
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		List<HyYrcsF> hyYrcsFList = new ArrayList<>();
		SheetUtil sheetUtil = new SheetUtil();
		CellDateUtil cellDateUtil = new CellDateUtil();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		HyYrcsF hyYrcsF;
		HyDaexI hyDaexI = new HyDaexI();
		String year;
		String stcd;
		String avcs;
		String avcsrcd;
		String mxs;
		String mxsrcd;
		Date mxsdt;
		String mns;
		String mnsrcd;
		Date mnsdt;
		String nt;
		Integer count = null;
		
		for (int r = 2; r <= sheet.getLastRowNum(); r++) {// r = 2表示从第三行开始循环
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
			hyYrcsF=new HyYrcsF();
			year = cellTypeUtil.cellTypeYear(fileName, file, r, 1);
			avcs = cellTypeUtil.cellTypecommon(fileName, file, r, 2);
			avcsrcd =cellTypeUtil.cellTypecommon(fileName, file, r, 3);
			mxs = cellTypeUtil.cellTypecommon(fileName, file, r, 4);
			mxsrcd =  cellTypeUtil.cellTypecommon(fileName, file, r, 5);
			mxsdt = cellDateUtil.cellTypeFromFromat(fileName, file,year, r, 6);
			mns =  cellTypeUtil.cellTypecommon(fileName, file, r, 7);
			mnsrcd = cellTypeUtil.cellTypecommon(fileName, file, r,8);
			mnsdt = cellDateUtil.cellTypeFromFromat(fileName, file,year, r, 9);
			nt = cellTypeUtil.cellTypecommon(fileName, file,r, 10);
			
			hyYrcsF.setStcd(stcd);
			hyYrcsF.setYear(year);
			hyYrcsF.setAvcs(avcs);
			hyYrcsF.setAvcsrcd(avcsrcd);
			hyYrcsF.setMxs(mxs);
			hyYrcsF.setMxsrcd(mxsrcd);
			hyYrcsF.setMxsdt(mxsdt);
			hyYrcsF.setMns(mns);
			hyYrcsF.setMnsrcd(mnsrcd);
			hyYrcsF.setMnsdt(mnsdt);
			hyYrcsFList.add(hyYrcsF);
			
			hyDaexI.setStcd(stcd);
			hyDaexI.setTbid("HY_DCS_C");
			hyDaexI.setYear(year);
			if (nt == null || "".equals(nt)) {
				count = hyDaexIMapper.selectstcd(hyDaexI);
				if (count > 0) {
					hyDaexIMapper.delete(hyDaexI);
				}
			} else {
				hyDaexI.setNt(nt);
				count = hyDaexIMapper.selectstcd(hyDaexI);
				if (count > 0) {
					hyDaexIMapper.delete(hyDaexI);
				}
				hyDaexIMapper.add(hyDaexI);
			}
		}
		
		for(HyYrcsF hyYrcsFResord : hyYrcsFList){
			count = hyYrcsFMapper.selectstcd(hyYrcsFResord);
			if(count >0){
				hyYrcsFMapper.delete(hyYrcsFResord);
			}
			hyYrcsFMapper.add(hyYrcsFResord);
		}
		return notNull;
	}

}


