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

import com.poi.testpoi.mapper.HyDzCMapper;
import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.pojo.HyDzC;
import com.poi.testpoi.service.HyDzCService;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyDzCServiceImpl implements HyDzCService {

	@Autowired
	private HyDzCMapper hyDzCMapper;// 日平均水位表
	@Autowired
	private HyStscAMapper hyStscAMapper;

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {

		List<HyDzC> hyDzCList = new ArrayList<>();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		SheetUtil sheetUtil = new SheetUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		System.out.println(fileName);

		HyDzC hyDzC;
		String stcd;
		String mth0;
		String mth;
		int mth1;
		String date0;
		String avz;
		String avzrcd;
		String year;
		 
		for (int r = 2; r <= sheet.getLastRowNum(); r++) {// r = 2表示从第3行开始循环
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
				hyDzC = new HyDzC();
				// 日期
				int d = (c-1)/2;
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
				avz = cellTypeUtil.cellTypecommon(fileName, file, r, c);
				avzrcd = cellTypeUtil.cellTypecommon(fileName, file, r, c+1);
				if(avz == null || "".equals(avz)){
					hyDzC.setAvz(null);
				}else{
					hyDzC.setAvz(avz);
				}
				hyDzC.setAvzrcd(avzrcd);
				hyDzC.setStcd(stcd);

				// 把所得的对象传进去
				hyDzC.setDt(dt);
				hyDzCList.add(hyDzC);
			}
		}
		
		Integer count = null;

		for (HyDzC hyDzCResord : hyDzCList) {
			count = hyDzCMapper.selectstcd(hyDzCResord);
			if (count > 0) {
				hyDzCMapper.delete(hyDzCResord);
			}
			hyDzCMapper.add(hyDzCResord);
		}
		return notNull;
	}
}
