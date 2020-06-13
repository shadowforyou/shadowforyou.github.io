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

import com.poi.testpoi.mapper.HyDcsCMapper;
import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.pojo.HyDcsC;
import com.poi.testpoi.service.HyDcsCService;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyDcsCServiceImpl implements HyDcsCService {
	
	@Autowired
	private HyDcsCMapper hyDcsCMapper;//日含沙量表
	@Autowired
	private HyStscAMapper hyStscAMapper;
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		List<HyDcsC> hyDcsCList = new ArrayList<>();
		
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		SheetUtil sheetUtil = new SheetUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		
		HyDcsC hyDcsC;
		
		String avcs;
		String avcsrcd;
		String date0;
		String year;
		String stcd;
		/**
		 * 日平均含沙量
		 */
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
			year = cellTypeUtil.cellTypeYear(fileName, file, r, 1);
			String mth0 = cellTypeUtil.cellTypecommon(fileName, file, r, 2);
			String tempMth[] = mth0.split("\\.");
			String mth = tempMth[0];
			int mth1=Integer.parseInt(mth);
			for (int c = 3; c <= 63; c += 2) {
				hyDcsC = new HyDcsC();
				int d = (c - 1) / 2;
				// 日期
				date0 = year + "-" + mth + "-" + d;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// 判断是否闰年
				int x = Integer.valueOf(year).intValue() % 4;
				if (x != 0 && (c == 59 && mth1 == 2)) {
					continue;
				}
				if ((c == 61 && mth1 == 2) || (c == 63 && mth1 == 2)) {
					continue;
				}
				if ((c == 63 && mth1 == 4) || (c == 63 && mth1 == 6) || (c == 63 && mth1 == 9) || (c == 63 && mth1 == 11)) {
					continue;
				}
				Date dt = sdf.parse(date0);
				avcs =cellTypeUtil.cellTypecommon(fileName, file, r, c);
				avcsrcd = cellTypeUtil.cellTypecommon(fileName, file, r, c + 1);
				hyDcsC.setAvcs(avcs);
				hyDcsC.setAvcsrcd(avcsrcd);
				hyDcsC.setStcd(stcd);
				hyDcsC.setDt(dt);
				hyDcsCList.add(hyDcsC);
			}
		}
		
		Integer count = null;
		
		for(HyDcsC hyDcsCResord : hyDcsCList){
			count = hyDcsCMapper.selectstcd(hyDcsCResord);
			if(count > 0){
				hyDcsCMapper.delete(hyDcsCResord);
			}
			hyDcsCMapper.add(hyDcsCResord);
		}
		return notNull;
	}

}
