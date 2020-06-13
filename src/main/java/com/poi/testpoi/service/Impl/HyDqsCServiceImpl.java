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

import com.poi.testpoi.mapper.HyDqsCMapper;
import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.pojo.HyDqsC;
import com.poi.testpoi.service.HyDqsCService;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyDqsCServiceImpl implements HyDqsCService {
	
	@Autowired
	private HyDqsCMapper hyDqsCMapper;//日输沙率表
	@Autowired
	private HyStscAMapper hyStscAMapper;
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		List<HyDqsC> hyDqsCList = new ArrayList<>();
		
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		SheetUtil sheetUtil = new SheetUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		
		HyDqsC hyDqsC;
		
		String sdtp = "悬移质";
		String avqs;
		String date0;
		String year;
		String stcd;
		/**
		 * 日平均输沙率
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
			for (int c = 3; c <= 33; c ++) {
				hyDqsC = new HyDqsC();
				int d = c-2;
				// 日期
				date0 = year + "-" + mth + "-" + d;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// 判断是否闰年
				int x = Integer.valueOf(year).intValue() % 4;
				if (x != 0 && (c == 31 && mth1 == 2)) {
					continue;
				}
				if ((c == 32 && mth1 == 2) || (c == 33 && mth1 == 2)) {
					continue;
				}
				if ((c == 33 && mth1 == 4) || (c == 33 && mth1 == 6) || (c == 33 && mth1 == 9) || (c == 33 && mth1 == 11)) {
					continue;
				}
				Date dt = sdf.parse(date0);
				avqs =cellTypeUtil.cellTypecommon(fileName, file, r, c);
				hyDqsC.setAvqs(avqs);
				hyDqsC.setStcd(stcd);
				hyDqsC.setDt(dt);
				hyDqsC.setSdtp(sdtp);
				hyDqsCList.add(hyDqsC);
			}
		}
		
		Integer count = null;
		
		for(HyDqsC hyDqsCResord : hyDqsCList){
			count = hyDqsCMapper.selectstcd(hyDqsCResord);
			if(count>0){
				hyDqsCMapper.delete(hyDqsCResord);
			}
			hyDqsCMapper.add(hyDqsCResord);
		}
		
		return notNull;
	}

}
