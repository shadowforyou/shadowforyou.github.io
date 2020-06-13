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

import com.poi.testpoi.mapper.HyDweCMapper;
import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.pojo.HyDweC;
import com.poi.testpoi.service.HyDweCService;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyDweCServiceImpl implements HyDweCService {

	@Autowired
	private HyDweCMapper hyDweCMapper;
	@Autowired
	private HyStscAMapper hyStscAMapper;

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {

		List<HyDweC> hyDweCList = new ArrayList<>();
		SheetUtil sheetUtil = new SheetUtil();
		boolean notNull = false;
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}

		HyDweC hyDweC;
		String year = cellTypeUtil.cellTypeYear(fileName, file, 0, 0);
		System.out.println(year+"-----"+fileName);
		
		for (int r = 2; r <= sheet.getLastRowNum(); r++) {// r = 2表示从第3行开始循环
			String stcd = cellTypeUtil.cellTypeStcd(fileName, file, r, 0);
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
			String mth0 =cellTypeUtil.cellTypecommon(fileName, file, r, 2);
			String tempMth[] = mth0.split("\\.");
			String mth = tempMth[0];
			int mth1=Integer.parseInt(mth);
			for (int c = 3; c <= 63; c+=2) {
				hyDweC = new HyDweC();
				// 日期
				int d = (c-1)/2;
				
				String date0 = year + "-" + mth + "-" + d;
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
				String wsfe = cellTypeUtil.cellTypecommon(fileName, file, r, c);
				String wsfercd = cellTypeUtil.cellTypecommon(fileName, file, r, c+1);
				hyDweC.setWsfe(wsfe);
				hyDweC.setWsfercd(wsfercd);
				hyDweC.setStcd(stcd);
				// 把所得的对象传进去
				hyDweC.setDt(dt);
				hyDweC.setEetp("E-601型蒸发器");
				hyDweCList.add(hyDweC);

			}
		}

		Integer count = null;

		for (HyDweC hyDweCResord : hyDweCList) {
			count = hyDweCMapper.selectstcd(hyDweCResord);
			if (count > 0) {
				hyDweCMapper.delete(hyDweCResord);
			}
			hyDweCMapper.add(hyDweCResord);
		}

		return notNull;
	}

}
