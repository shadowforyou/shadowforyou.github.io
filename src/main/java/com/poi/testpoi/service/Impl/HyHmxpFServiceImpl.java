package com.poi.testpoi.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.poi.testpoi.mapper.HyHmxpFMapper;
import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.pojo.HyHmxpF;
import com.poi.testpoi.service.HyHmxpFService;
import com.poi.testpoi.util.CellDateUtil;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyHmxpFServiceImpl implements HyHmxpFService {
	@Autowired
	private HyHmxpFMapper hyHmxpFMapper;
	@Autowired
	private HyStscAMapper hyStscAMapper;

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {

		List<HyHmxpF> hyHmxpFList = new ArrayList<>();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		CellDateUtil cellDateUtil = new CellDateUtil();
		SheetUtil sheetUtil = new SheetUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		System.out.println(fileName);
		
		String stcd;
		String mxp;
		Date bgtm;
		String mxprc;
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
			year = cellTypeUtil.cellTypeYear(fileName, file, r, 1);
			for(int c=2 ; c <= 17; c+=3){
				mxp = cellTypeUtil.cellTypecommon(fileName, file, r, c);
				mxprc = cellTypeUtil.cellTypecommon(fileName, file, r, c+1);
				bgtm = cellDateUtil.cellTypeFromFromat(fileName, file,year, r, c+2);
				if(c==2){
					HyHmxpF hyHmxpF1 = new HyHmxpF();
					hyHmxpF1.setStcd(stcd);
					hyHmxpF1.setYear(year);
					hyHmxpF1.setMxp(mxp);
					hyHmxpF1.setMxprc(mxprc);
					hyHmxpF1.setBgtm(bgtm);
					hyHmxpF1.setMxpdr("1");
					hyHmxpFList.add(hyHmxpF1);
				}else if(c==5){
					HyHmxpF hyHmxpF2 = new HyHmxpF();
					hyHmxpF2.setStcd(stcd);
					hyHmxpF2.setYear(year);
					hyHmxpF2.setMxp(mxp);
					hyHmxpF2.setMxprc(mxprc);
					hyHmxpF2.setBgtm(bgtm);
					hyHmxpF2.setMxpdr("2");
					hyHmxpFList.add(hyHmxpF2);
				}else if(c==8){
					HyHmxpF hyHmxpF3 = new HyHmxpF();
					hyHmxpF3.setStcd(stcd);
					hyHmxpF3.setYear(year);
					hyHmxpF3.setMxp(mxp);
					hyHmxpF3.setMxprc(mxprc);
					hyHmxpF3.setBgtm(bgtm);
					hyHmxpF3.setMxpdr("3");
					hyHmxpFList.add(hyHmxpF3);
				}else if(c==11){
					HyHmxpF hyHmxpF4 = new HyHmxpF();
					hyHmxpF4.setStcd(stcd);
					hyHmxpF4.setYear(year);
					hyHmxpF4.setMxp(mxp);
					hyHmxpF4.setMxprc(mxprc);
					hyHmxpF4.setBgtm(bgtm);
					hyHmxpF4.setMxpdr("6");
					hyHmxpFList.add(hyHmxpF4);
				}else if(c==14){
					HyHmxpF hyHmxpF5 = new HyHmxpF();
					hyHmxpF5.setStcd(stcd);
					hyHmxpF5.setYear(year);
					hyHmxpF5.setMxp(mxp);
					hyHmxpF5.setMxprc(mxprc);
					hyHmxpF5.setBgtm(bgtm);
					hyHmxpF5.setMxpdr("12");
					hyHmxpFList.add(hyHmxpF5);
				}else if(c==17){
					HyHmxpF hyHmxpF6 = new HyHmxpF();
					hyHmxpF6.setStcd(stcd);
					hyHmxpF6.setYear(year);
					hyHmxpF6.setMxp(mxp);
					hyHmxpF6.setMxprc(mxprc);
					hyHmxpF6.setBgtm(bgtm);
					hyHmxpF6.setMxpdr("24");
					hyHmxpFList.add(hyHmxpF6);
				}
			}
		}

		for (HyHmxpF hyHmxpFResord : hyHmxpFList) {
			Integer count = hyHmxpFMapper.selectstcd(hyHmxpFResord);
			if (count > 0) {
				hyHmxpFMapper.delete(hyHmxpFResord);
			}
			hyHmxpFMapper.add(hyHmxpFResord);
		}
		return notNull;
	}

}
