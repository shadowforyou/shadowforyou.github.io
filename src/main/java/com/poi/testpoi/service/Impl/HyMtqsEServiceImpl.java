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

import com.poi.testpoi.mapper.HyMtqsEMapper;
import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.pojo.HyMtqsE;
import com.poi.testpoi.service.HyMtqsEService;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyMtqsEServiceImpl implements HyMtqsEService {

	@Autowired
	private HyMtqsEMapper hyMtqsEMapper;
	@Autowired
	private HyStscAMapper hyStscAMapper;
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		List<HyMtqsE> hyMtqsEList = new ArrayList<>();
		SheetUtil sheetUtil = new SheetUtil();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		HyMtqsE hyMtqsE;
		String sdtp = "悬移质";
		String stcd;
		String mth;
		String mth0;
		String avqs;
		String mxdyqs;
		String mxdyqsdt0;
		String mxdyqsdt1;
		String date0;
		Date mxdyqsdt;
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
			hyMtqsE=new HyMtqsE();
			year = cellTypeUtil.cellTypeYear(fileName, file, r, 1);
			mth0 = cellTypeUtil.cellTypecommon(fileName, file, r, 2);
			String tempMth[] = mth0.split("\\.");
			mth = tempMth[0];
			avqs =cellTypeUtil.cellTypecommon(fileName, file, r, 3);
			mxdyqs =  cellTypeUtil.cellTypecommon(fileName, file, r, 4);
			mxdyqsdt0 = cellTypeUtil.cellTypecommon(fileName, file, r, 5);
			String tempmxdyqs[] = mxdyqsdt0.split("\\.");
			mxdyqsdt1 = tempmxdyqs[0];
			date0 = year + "-" + mth + "-" + mxdyqsdt1;
			mxdyqsdt =new SimpleDateFormat("yyyy-MM-dd").parse(date0);
			hyMtqsE.setMth(Integer.parseInt(mth));
			hyMtqsE.setStcd(stcd);
			hyMtqsE.setSdtp(sdtp);
			hyMtqsE.setYear(year);
			hyMtqsE.setAvqs(avqs);
			hyMtqsE.setMxdyqs(mxdyqs);
			hyMtqsE.setMxdyqsodt(mxdyqsdt);
			hyMtqsEList.add(hyMtqsE);
		}
		Integer count = null;
		for(HyMtqsE hyMtqsEResord : hyMtqsEList){
			count = hyMtqsEMapper.selectstcd(hyMtqsEResord);
			if(count >0){
				hyMtqsEMapper.delete(hyMtqsEResord);
			}
			hyMtqsEMapper.add(hyMtqsEResord);
		}
		return notNull;
	}

}

