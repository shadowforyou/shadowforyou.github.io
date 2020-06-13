package com.poi.testpoi.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.poi.testpoi.mapper.HyMtcsEMapper;
import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.pojo.HyMtcsE;
import com.poi.testpoi.service.HyMtcsEService;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyMtcsEServiceImpl implements HyMtcsEService {

	@Autowired
	private HyMtcsEMapper hyMtcsEMapper;
	@Autowired
	private HyStscAMapper hyStscAMapper;
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		List<HyMtcsE> hyMtcsEList = new ArrayList<>();
		SheetUtil sheetUtil = new SheetUtil();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		HyMtcsE hyMtcsE;
		String stcd;
		String mth;
		String mth0;
		String avcs;
		String avcsrcd;
		String mxs;
		String mxsrcd;
		String mxsdt0;
		String mxsdt1;
		String date0;
		Date mxsdt;
		String mns;
		String mnsrcd;
		String mnsdt0;
		String mnsdt1;
		String date1;
		Date mnsdt;
		String year;
		
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
			hyMtcsE=new HyMtcsE();
			year = cellTypeUtil.cellTypeYear(fileName, file, r, 1);
			mth0 = cellTypeUtil.cellTypecommon(fileName, file, r, 2);
			String tempMth[] = mth0.split("\\.");
			mth = tempMth[0];
			avcs =cellTypeUtil.cellTypecommon(fileName, file, r, 3);
			avcsrcd = cellTypeUtil.cellTypecommon(fileName, file, r, 4);
			mxs =  cellTypeUtil.cellTypecommon(fileName, file, r, 5);
			mxsrcd = cellTypeUtil.cellTypecommon(fileName, file, r, 6);
			mxsdt0 = cellTypeUtil.cellTypecommon(fileName, file, r, 7);
			String tempMxs[] = mxsdt0.split("\\.");
			mxsdt1 = tempMxs[0];
			date0 = year + "-" + mth + "-" + mxsdt1;
			mxsdt =new SimpleDateFormat("yyyy-MM-dd").parse(date0);
			mns =  cellTypeUtil.cellTypecommon(fileName, file, r, 8);
			mnsrcd = cellTypeUtil.cellTypecommon(fileName, file, r, 9);
			mnsdt0 = cellTypeUtil.cellTypecommon(fileName, file, r, 10);
			String tempMns[] = mnsdt0.split("\\.");
			mnsdt1 = tempMns[0];
			date1 = year + "-" + mth + "-" + mnsdt1;
			mnsdt =new SimpleDateFormat("yyyy-MM-dd").parse(date1);
			hyMtcsE.setMth(Integer.parseInt(mth));
			hyMtcsE.setStcd(stcd);
			hyMtcsE.setYear(year);
			hyMtcsE.setAvcs(avcs);
			hyMtcsE.setAvcsrcd(avcsrcd);
			hyMtcsE.setMxs(mxs);
			hyMtcsE.setMxsrcd(mxsrcd);
			hyMtcsE.setMxsdt(mxsdt);
			hyMtcsE.setMns(mns);
			hyMtcsE.setMnsrcd(mnsrcd);
			hyMtcsE.setMnsdt(mnsdt);
			hyMtcsEList.add(hyMtcsE);
		}
		Integer count = null;
		for (HyMtcsE hyMtcsEResord : hyMtcsEList){
			count = hyMtcsEMapper.selectstcd(hyMtcsEResord);
			if(count >0){
				hyMtcsEMapper.delete(hyMtcsEResord);
			}
			hyMtcsEMapper.add(hyMtcsEResord);
		}
		return notNull;
	}

}
