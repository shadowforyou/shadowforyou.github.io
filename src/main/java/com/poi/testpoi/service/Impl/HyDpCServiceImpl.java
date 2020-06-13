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

import com.poi.testpoi.mapper.HyDpCMapper;
import com.poi.testpoi.pojo.HyDpC;
import com.poi.testpoi.service.HyDpCService;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyDpCServiceImpl implements HyDpCService {

	@Autowired
	private HyDpCMapper hyDpCMapper;// 日降水量表

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {

		List<HyDpC> hyDpCList = new ArrayList<>();
		SheetUtil sheetUtil = new SheetUtil();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		HyDpC hyDpC =new HyDpC();
		String stcd;
		String p;
		String prcd;
		String year;
		String date0;
		/**
		 * 日降水量表
		 */
		for (int r = 2; r <= sheet.getLastRowNum(); r++) {// r = 2表示从第三行开始循环
			stcd = cellTypeUtil.cellTypeStcd(fileName, file, r, 0);
			if (stcd == null || "".equals(stcd)) {
				break;
			}
			year = cellTypeUtil.cellTypeYear(fileName, file, r, 1);
			int i=hyDpCMapper.selectstcd(stcd,year);
			if(i>0){
				continue;
			}
			Row row = sheet.getRow(r);// 通过sheet表单对象得到 行对象
			if (row == null) {
				continue;
			}
			
			
			date0 = cellTypeUtil.cellTypecommon(fileName, file, r, 2);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Date dt = sdf.parse(date0);
			p = cellTypeUtil.cellTypecommon(fileName, file, r, 3);
			prcd = cellTypeUtil.cellTypecommon(fileName, file, r, 4);
			hyDpC.setP(p);
			hyDpC.setPrcd(prcd);
			hyDpC.setStcd(stcd);
			// 把所得的对象传进去
			hyDpC.setDt(dt);
			hyDpCList.add(hyDpC);
		}

		for (HyDpC hydCResord : hyDpCList){
			hyDpCMapper.add(hydCResord);
		}
		return notNull;
	}
}
