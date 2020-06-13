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

import com.poi.testpoi.mapper.HyImxfwFMapper;
import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.pojo.HyImxfwF;
import com.poi.testpoi.service.HyImxfwFService;
import com.poi.testpoi.util.CellDateUtil;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyImxfwFServiceImpl implements HyImxfwFService {

	@Autowired
	private HyImxfwFMapper hyImxfwFMapper;
	@Autowired
	private HyStscAMapper hyStscAMapper;
	
	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		
		List<HyImxfwF> hyImxfwFList = new ArrayList<>();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		CellDateUtil cellDateUtil = new CellDateUtil();
		SheetUtil sheetUtil = new SheetUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if(sheet!=null){
			notNull = true;
		}
		String stcd;
		String result = null;
		Date bgdt = null;
		String mxw = null;
		String year;
		for (int r = 2; r<=sheet.getLastRowNum(); r++) {
			Row row = sheet.getRow(r);//通过sheet表单对象得到 行对象
			if (row == null){continue;}
			year = cellTypeUtil.cellTypeYear(fileName, file, r, 1);
			stcd = cellTypeUtil.cellTypeStcd(fileName, file, r, 0);
			if(stcd==null || "".equals(stcd)) {break;}
			int i=hyStscAMapper.selectstcd(stcd);
			if(i<=0){
				continue;
			}
			HyImxfwF hyImxfwF1 = new HyImxfwF();
				for(int c=2; c<=3;c++) {
					result = cellTypeUtil.cellTypecommon(fileName,file,r, c);
					hyImxfwF1.setYear(year);
					hyImxfwF1.setStcd(stcd);
					if(c==2) {
						if(result == null || "".equals(result)) {
							mxw = null;
						}else {
							hyImxfwF1.setMxw(Float.parseFloat(result));
						}
					}
					if(c==3) {
						if(result == null || "".equals(result)) {
							bgdt = null;
						}else {
							bgdt = cellDateUtil.cellTypeFromFromat(fileName, file, year, r, c);
							hyImxfwF1.setBgdt(bgdt);
						}
					}
				}
				if((bgdt == null || "".equals(bgdt)) && (mxw == null || "".equals(mxw))) {
					continue;
				}else {
					hyImxfwF1.setMxwdr("1");
					hyImxfwFList.add(hyImxfwF1);
				}
				HyImxfwF hyImxfwF2 = new HyImxfwF();
				for(int c=4; c<=5;c++) {
					result = cellTypeUtil.cellTypecommon(fileName,file,r, c);
					hyImxfwF2.setYear(year);
					hyImxfwF2.setStcd(stcd);
					if(c==4) {
						if(result == null || "".equals(result)) {
							mxw = null;
						}else {
							hyImxfwF2.setMxw(Float.parseFloat(result));
						}
					}	
					if(c==5) {
						if(result == null || "".equals(result)) {
							bgdt = null;
						}else {
							bgdt = cellDateUtil.cellTypeFromFromat(fileName, file, year, r, c);
							hyImxfwF2.setBgdt(bgdt);
						}
					}
					
				}
				if((bgdt == null || "".equals(bgdt)) && (mxw == null || "".equals(mxw))) {
					continue;
				}else {
					hyImxfwF2.setMxwdr("3");
					hyImxfwFList.add(hyImxfwF2);
				}
				HyImxfwF hyImxfwF3 = new HyImxfwF();
				for(int c=6; c<=7;c++) {
					result = cellTypeUtil.cellTypecommon(fileName,file,r, c);
					hyImxfwF3.setYear(year);
					hyImxfwF3.setStcd(stcd);
					
					if(c==6) {
						if(result == null || "".equals(result)) {
							mxw = null;
						}else {
							hyImxfwF3.setMxw(Float.parseFloat(result));
						}
					}
					if(c==7) {
						if(result == null || "".equals(result)) {
							bgdt = null;
						}else {
							bgdt = cellDateUtil.cellTypeFromFromat(fileName, file, year, r, c);
							hyImxfwF3.setBgdt(bgdt);
						}
					}
				}
				if((bgdt == null || "".equals(bgdt)) && (mxw == null || "".equals(mxw))) {
					continue;
				}else {
					hyImxfwF3.setMxwdr("7");
					hyImxfwFList.add(hyImxfwF3);
				}
				HyImxfwF hyImxfwF4 = new HyImxfwF();
				for(int c=8; c<=9;c++) {
					result = cellTypeUtil.cellTypecommon(fileName,file,r, c);
					hyImxfwF4.setYear(year);
					hyImxfwF4.setStcd(stcd);
					if(c==8) {
						if(result == null || "".equals(result)) {
							mxw = null;
						}else {
							hyImxfwF4.setMxw(Float.parseFloat(result));
						}
					}
					if(c==9) {
						if(result == null || "".equals(result)) {
							bgdt = null;
						}else {
							bgdt = cellDateUtil.cellTypeFromFromat(fileName, file, year, r, c);
							hyImxfwF4.setBgdt(bgdt);
						}
					}
				}
				if((bgdt == null || "".equals(bgdt)) && (mxw == null || "".equals(mxw))) {
					continue;
				}else {
					hyImxfwF4.setMxwdr("15");
					hyImxfwFList.add(hyImxfwF4);
				}
				HyImxfwF hyImxfwF5 = new HyImxfwF();
				for(int c=10; c<=11;c++) {
					result = cellTypeUtil.cellTypecommon(fileName,file,r, c);
					hyImxfwF5.setYear(year);
					hyImxfwF5.setStcd(stcd);
					if(c==10) {
						if(result == null || "".equals(result)) {
							mxw = null;
						}else {
							hyImxfwF5.setMxw(Float.parseFloat(result));
						}
					}
					if(c==11) {
						if(result == null || "".equals(result)) {
							bgdt = null;
						}else {
							bgdt = cellDateUtil.cellTypeFromFromat(fileName, file, year, r, c);
							hyImxfwF5.setBgdt(bgdt);
						}
					}
				}
				if((bgdt == null || "".equals(bgdt)) && (mxw == null || "".equals(mxw))) {
					continue;
				}else {
					hyImxfwF5.setMxwdr("30");
					hyImxfwFList.add(hyImxfwF5);
				}
				HyImxfwF hyImxfwF6 = new HyImxfwF();
				for(int c=12; c<=13;c++) {
					result = cellTypeUtil.cellTypecommon(fileName,file,r, c);
					hyImxfwF6.setYear(year);
					hyImxfwF6.setStcd(stcd);
					if(c==12) {
						if(result == null || "".equals(result)) {
							mxw = null;
						}else {
							hyImxfwF6.setMxw(Float.parseFloat(result));
						}
					}	
					if(c==13) {
						if(result == null || "".equals(result)) {
							bgdt = null;
						}else {
							bgdt = cellDateUtil.cellTypeFromFromat(fileName, file, year, r, c);
							hyImxfwF6.setBgdt(bgdt);
						}
					}
				}
				if((bgdt == null || "".equals(bgdt)) && (mxw == null || "".equals(mxw))) {
					continue;
				}else {
					hyImxfwF6.setMxwdr("60");
					hyImxfwFList.add(hyImxfwF6);
				}
			}
		for (HyImxfwF hyImxfwFResord : hyImxfwFList) {
			Integer count =hyImxfwFMapper.selectstcd(hyImxfwFResord);
			if(count>0){
				hyImxfwFMapper.delete(hyImxfwFResord);
			}
			hyImxfwFMapper.add(hyImxfwFResord);
		}
		return notNull;
	}

}
