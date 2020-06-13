package com.poi.testpoi.service.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.mapper.HyWsfhexBMapper;
import com.poi.testpoi.pojo.HyWsfhexB;
import com.poi.testpoi.service.HyWsfhexBService;
import com.poi.testpoi.util.CellDateUtil;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyWsfhexBServiceImpl implements HyWsfhexBService {

	@Autowired
	private HyWsfhexBMapper hyWsfhexBMapper;
	@Autowired
	private HyStscAMapper hyStscAMapper;

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		List<HyWsfhexB> hyWsfhexBList = new ArrayList<>();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		CellDateUtil cellDateUtil = new CellDateUtil();
		SheetUtil sheetUtil = new SheetUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		HyWsfhexB hyWsfhexB;
		String year;
		String stcd = null;
		String q;
		BigDecimal bdl = null;

		for (int r = 2; r <= sheet.getLastRowNum(); r++) {
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
			hyWsfhexB = new HyWsfhexB();
			year = cellTypeUtil.cellTypeYear(fileName, file, r, 1);
			hyWsfhexB.setStcd(stcd);
			// 月日
			Date Msq = cellDateUtil.cellTypeFromFromat(fileName, file, year, r, 2);
			String M = String.format("%tm", Msq);
			String D = String.format("%td", Msq);
			// 时间
			hyWsfhexB.setTm(cellDateUtil.cellTypeFromMeasure(fileName, file, year, M, D, r, 3));

			// 闸上水位
			hyWsfhexB.setUpz(cellTypeUtil.cellTypecommon(fileName, file, r, 4));

			// 闸下水位
			hyWsfhexB.setDwz(cellTypeUtil.cellTypecommon(fileName, file, r, 5));

			// 流量
			q = cellTypeUtil.cellTypecommon(fileName, file, r, 8);
			if (q == null || "".equals(q)) {hyWsfhexB.setQ(null);
			}else{
				bdl = new BigDecimal(q);
				hyWsfhexB.setQ(bdl);}

			// 含沙量
			hyWsfhexB.setS(cellTypeUtil.cellTypecommon(fileName, file, r, 9));

			hyWsfhexBList.add(hyWsfhexB);
		}

		for (HyWsfhexB hyWsfhexBResord : hyWsfhexBList) {
			Integer count = hyWsfhexBMapper.selectstcd(hyWsfhexBResord);
			if (count > 0) {
				hyWsfhexBMapper.delete(hyWsfhexBResord);
			}

			hyWsfhexBMapper.add(hyWsfhexBResord);
		}
		return notNull;
	}

}
