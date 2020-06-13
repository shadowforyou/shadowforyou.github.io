package com.poi.testpoi.service.Impl;

import java.math.BigDecimal;
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

import com.poi.testpoi.mapper.HyDqCMapper;
import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.pojo.HyDqC;
import com.poi.testpoi.service.HyDqCService;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyDqCServiceImpl implements HyDqCService {

	@Autowired
	private HyDqCMapper hyDqCMapper;// 日流量表
	@Autowired
	private HyStscAMapper hyStscAMapper;

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {

		List<HyDqC> hyDqCList = new ArrayList<>();
		SheetUtil sheetUtil = new SheetUtil();
		boolean notNull = false;
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		HyDqC hyDqC;
		String stcd;
		String mth;
		int mth1;
		String mth0;
		String date0;
		String avq;
		String avqrcd;
		String year;
		/**
		 * 日流量表
		 */
		for (int r = 2; r <=sheet.getLastRowNum(); r++) {
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
			mth0 =cellTypeUtil.cellTypecommon(fileName, file, r, 2);
			String tempMth[] = mth0.split("\\.");
			mth = tempMth[0];
			mth1=Integer.parseInt(mth);
			for (int c = 3; c <= 63; c+=2) {
				hyDqC = new HyDqC();
				int d = (c-1)/2;
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
				avq = cellTypeUtil.cellTypecommon(fileName, file, r, c);

				avqrcd = cellTypeUtil.cellTypecommon(fileName, file, r, c+1);
				BigDecimal bdl = new BigDecimal(avq);
				hyDqC.setAvq(bdl);

				hyDqC.setAvqrcd(avqrcd);
				hyDqC.setStcd(stcd);
				// 把所得的对象传进去
				hyDqC.setDt(dt);
				hyDqCList.add(hyDqC);
			}
		}

		Integer count = null;
		for (HyDqC hydCResord : hyDqCList){
			count = hyDqCMapper.selectstcd(hydCResord);
			if (count > 0){
				hyDqCMapper.delete(hydCResord);
			}
			hyDqCMapper.add(hydCResord);
		}
		return notNull;

	}

}
