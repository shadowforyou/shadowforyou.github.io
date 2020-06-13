package com.poi.testpoi.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.mapper.HyWfdzFMapper;
import com.poi.testpoi.pojo.HyWfdzF;
import com.poi.testpoi.service.HyWfdzFService;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyWfdzFServiceImpl implements HyWfdzFService {

	@Autowired
	private HyWfdzFMapper hyWfdzFMapper;// 保证率水位表
	@Autowired
	private HyStscAMapper hyStscAMapper;
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		
		List<HyWfdzF> hyWfdzFList = new ArrayList<>();
		SheetUtil sheetUtil = new SheetUtil();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		
		String stcd;
		String year;
		String rz;
		
		for (int r = 2; r <= sheet.getLastRowNum(); r++) {// r = 2表示从第三行开始循环
			stcd = cellTypeUtil.cellTypeStcd(fileName, file, r, 0);
			if (stcd == null || "".equals(stcd)) {
				break;
			}
			int i = hyStscAMapper.selectstcd(stcd);
			if (i <= 0) {
				continue;
			}
			Row row = sheet.getRow(r);// 通过sheet表单对象得到 行对象
			if (row == null) {
				continue;
			}
			year = cellTypeUtil.cellTypeYear(fileName, file, r, 1);
			for (int c = 2; c <= 8; c++) {
				rz = cellTypeUtil.cellTypecommon(fileName, file, r, c);
				if (c == 2) {
					HyWfdzF hyWfdzF1 = new HyWfdzF();
					hyWfdzF1.setStcd(stcd);
					hyWfdzF1.setWf("1");
					hyWfdzF1.setRz(rz);
					hyWfdzF1.setYear(year);
					hyWfdzFList.add(hyWfdzF1);
				}else if (c == 3) {
					HyWfdzF hyWfdzF2 = new HyWfdzF();
					hyWfdzF2.setStcd(stcd);
					hyWfdzF2.setWf("15");
					hyWfdzF2.setRz(rz);
					hyWfdzF2.setYear(year);
					hyWfdzFList.add(hyWfdzF2);
				}else if (c == 4) {
					HyWfdzF hyWfdzF3 = new HyWfdzF();
					hyWfdzF3.setStcd(stcd);
					hyWfdzF3.setWf("30");
					hyWfdzF3.setRz(rz);
					hyWfdzF3.setYear(year);
					hyWfdzFList.add(hyWfdzF3);
				}else if (c == 5) {
					HyWfdzF hyWfdzF4 = new HyWfdzF();
					hyWfdzF4.setStcd(stcd);
					hyWfdzF4.setWf("90");
					hyWfdzF4.setRz(rz);
					hyWfdzF4.setYear(year);
					hyWfdzFList.add(hyWfdzF4);
				}else if (c == 6) {
					HyWfdzF hyWfdzF5 = new HyWfdzF();
					hyWfdzF5.setStcd(stcd);
					hyWfdzF5.setWf("180");
					hyWfdzF5.setRz(rz);
					hyWfdzF5.setYear(year);
					hyWfdzFList.add(hyWfdzF5);
				}else if (c == 7) {
					HyWfdzF hyWfdzF6 = new HyWfdzF();
					hyWfdzF6.setStcd(stcd);
					hyWfdzF6.setWf("270");
					hyWfdzF6.setRz(rz);
					hyWfdzF6.setYear(year);
					hyWfdzFList.add(hyWfdzF6);
				}else if (c == 8) {
					HyWfdzF hyWfdzF7 = new HyWfdzF();
					hyWfdzF7.setStcd(stcd);
					hyWfdzF7.setWf("360");
					hyWfdzF7.setRz(rz);
					hyWfdzF7.setYear(year);
					hyWfdzFList.add(hyWfdzF7);
				}
			}
		}
		Integer count = null;

		for (HyWfdzF hyWfdzFResord : hyWfdzFList) {
			count = hyWfdzFMapper.selectstcd(hyWfdzFResord);
			if (count > 0) {
				hyWfdzFMapper.delete(hyWfdzFResord);
			}
			hyWfdzFMapper.add(hyWfdzFResord);
		}
		
		return notNull;
	}

}
