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

import com.poi.testpoi.mapper.HyDaexIMapper;
import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.mapper.HyYrzFMapper;
import com.poi.testpoi.pojo.HyDaexI;
import com.poi.testpoi.pojo.HyYrzF;
import com.poi.testpoi.service.HyYrzFService;
import com.poi.testpoi.util.CellDateUtil;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyYrzFServiceImpl implements HyYrzFService {
	
	@Autowired
	private HyYrzFMapper hyYrzFMapper;
	@Autowired
	private HyStscAMapper hyStscAMapper;
	@Autowired
	private HyDaexIMapper hyDaexIMapper;// 附录
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		List<HyYrzF> hyYrzFList = new ArrayList<>();
		SheetUtil sheetUtil = new SheetUtil();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		CellDateUtil cellDateUtil = new CellDateUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		HyYrzF hyYrzF;
		HyDaexI hyDaexI = new HyDaexI();
		String year;
		String stcd;
		String avz;
		String avzrcd;
		String htz;
		String htzrcd;
		Date htzdt;
		String mnz;
		String mnzrcd;
		Date mnzdt;
		String nt;
		Integer count = null;
		
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
			hyYrzF=new HyYrzF();
			year = cellTypeUtil.cellTypeYear(fileName, file, r, 1);
			avz = cellTypeUtil.cellTypecommon(fileName, file, r, 2);
			avzrcd =cellTypeUtil.cellTypecommon(fileName, file, r, 3);
			htz = cellTypeUtil.cellTypecommon(fileName, file, r, 4);
			htzrcd = cellTypeUtil.cellTypecommon(fileName, file, r, 5);
			htzdt = cellDateUtil.cellTypeFromFromat(fileName, file,year, r,6);
			mnz = cellTypeUtil.cellTypecommon(fileName, file, r, 7);
			mnzrcd = cellTypeUtil.cellTypecommon(fileName, file, r, 8);
			mnzdt = cellDateUtil.cellTypeFromFromat(fileName, file,year, r, 9);
			
			nt = cellTypeUtil.cellTypecommon(fileName, file,r, 10);
			
			hyYrzF.setStcd(stcd);
			hyYrzF.setYear(year);
			hyYrzF.setAvz(avz);
			hyYrzF.setAvzrcd(avzrcd);
			hyYrzF.setHtz(htz);
			hyYrzF.setHtzrcd(htzrcd);
			hyYrzF.setHtzdt(htzdt);
			hyYrzF.setMnz(mnz);
			hyYrzF.setMnzrcd(mnzrcd);
			hyYrzF.setMnzdt(mnzdt);
			
			hyYrzFList.add(hyYrzF);
			
			hyDaexI.setStcd(stcd);
			hyDaexI.setTbid("HY_DZ_C");
			hyDaexI.setYear(year);
			if (nt == null || "".equals(nt)) {
				count = hyDaexIMapper.selectstcd(hyDaexI);
				if (count > 0) {
					hyDaexIMapper.delete(hyDaexI);
				}
			} else {
				hyDaexI.setNt(nt);
				count = hyDaexIMapper.selectstcd(hyDaexI);
				if (count > 0) {
					hyDaexIMapper.delete(hyDaexI);
				}
				hyDaexIMapper.add(hyDaexI);
			}
		}
		
		for(HyYrzF hyYrzFResord : hyYrzFList){
			count = hyYrzFMapper.selectstcd(hyYrzFResord);
			if(count >0){
				hyYrzFMapper.delete(hyYrzFResord);
			}
			hyYrzFMapper.add(hyYrzFResord);
		}
		return notNull;
	}

}

