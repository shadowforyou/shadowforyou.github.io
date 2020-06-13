package com.poi.testpoi.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.poi.testpoi.mapper.HyMtpEMapper;
import com.poi.testpoi.pojo.HyMtpE;
import com.poi.testpoi.service.HyMtpEService;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyMtpEServiceImpl implements  HyMtpEService{

	@Autowired
	private HyMtpEMapper hyMtpEMapper;
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		List<HyMtpE> hyMtpEList = new ArrayList<>();
		SheetUtil sheetUtil = new SheetUtil();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		HyMtpE hyMtpE;
		String stcd;
		String mth;
		String mth0;
		String p;
		String prcd;
		String pdynum;
		String pdynumrcd;
		String mxdyp;
		String mxdyprcd;
		String year;
		
		for (int r = 2; r <= sheet.getLastRowNum(); r++) {// r = 2表示从第三行开始循环
			stcd = cellTypeUtil.cellTypeStcd(fileName, file, r, 0);
			if (stcd == null || "".equals(stcd)) {
				break;
			}
			year = cellTypeUtil.cellTypeYear(fileName, file, r, 1);
			int i=hyMtpEMapper.selectstcd(stcd,year);
			if(i>0){
				continue;
			}
			Row row = sheet.getRow(r);// 通过sheet表单对象得到 行对象
			if (row == null) {
				continue;
			}
			hyMtpE=new HyMtpE();
			
			mth0 = cellTypeUtil.cellTypecommon(fileName, file, r, 2);
			String tempMth[] = mth0.split("\\.");
			mth = tempMth[0];
			p = cellTypeUtil.cellTypecommon(fileName, file, r, 4);
			prcd = cellTypeUtil.cellTypecommon(fileName, file, r, 5);
			pdynum=cellTypeUtil.cellTypecommon(fileName, file, r, 6);
			pdynumrcd = cellTypeUtil.cellTypecommon(fileName, file, r, 7);
			mxdyp=cellTypeUtil.cellTypecommon(fileName, file, r, 8);
			mxdyprcd=cellTypeUtil.cellTypecommon(fileName, file, r, 9);
			hyMtpE.setMth(Integer.parseInt(mth));
			hyMtpE.setStcd(stcd);
			hyMtpE.setYear(year);
			hyMtpE.setP(p);
			hyMtpE.setPrcd(prcd);
			hyMtpE.setPdynum(pdynum);
			hyMtpE.setPdynumrcd(pdynumrcd);
			hyMtpE.setMxdyp(mxdyp);
			hyMtpE.setMxdyprcd(mxdyprcd);
			hyMtpEList.add(hyMtpE);
		}
		for (HyMtpE hydCResord : hyMtpEList){
			hyMtpEMapper.add(hydCResord);
		}
		return notNull;
	}

}
