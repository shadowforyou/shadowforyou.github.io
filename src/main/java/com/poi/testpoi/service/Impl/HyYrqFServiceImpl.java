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

import com.poi.testpoi.mapper.HyDaexIMapper;
import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.mapper.HyYrqFMapper;
import com.poi.testpoi.pojo.HyDaexI;
import com.poi.testpoi.pojo.HyYrqF;
import com.poi.testpoi.service.HyYrqFService;
import com.poi.testpoi.util.CellDateUtil;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyYrqFServiceImpl implements HyYrqFService {

	@Autowired
	private HyYrqFMapper hyYrqFMapper;
	@Autowired
	private HyStscAMapper hyStscAMapper;
	@Autowired
	private HyDaexIMapper hyDaexIMapper;// 附录
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		List<HyYrqF> hyYrqFList = new ArrayList<>();
		SheetUtil sheetUtil = new SheetUtil();
		CellDateUtil cellDateUtil = new CellDateUtil();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		HyYrqF hyYrqF;
		HyDaexI hyDaexI = new HyDaexI();
		String year;
		String stcd;
		String avq;
		String avqrcd;
		String mxq;
		String mxqrcd;
		Date mxqdt;
		String mnq;
		String mnqrcd;
		Date mnqdt;
		String rw;
		String rm;
		String rd;
		String nt;
		BigDecimal bdl = null;
		BigDecimal brw = null;
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
			hyYrqF=new HyYrqF();
			year = cellTypeUtil.cellTypeYear(fileName, file, r, 1);
			avq = cellTypeUtil.cellTypecommon(fileName, file, r, 2);
			avqrcd =cellTypeUtil.cellTypecommon(fileName, file, r, 3);
			mxq = cellTypeUtil.cellTypecommon(fileName, file, r, 4);
			mxqrcd =  cellTypeUtil.cellTypecommon(fileName, file, r, 5);
			mxqdt = cellDateUtil.cellTypeFromFromat(fileName, file,year, r, 6);
			mnq =  cellTypeUtil.cellTypecommon(fileName, file, r, 7);
			mnqrcd = cellTypeUtil.cellTypecommon(fileName, file, r,8);
			mnqdt = cellDateUtil.cellTypeFromFromat(fileName, file,year, r, 9);
			rw =cellTypeUtil.cellTypecommon(fileName, file, r, 10);
			rm =cellTypeUtil.cellTypecommon(fileName, file, r, 11);
			rd =cellTypeUtil.cellTypecommon(fileName, file, r, 12);
			
			nt = cellTypeUtil.cellTypecommon(fileName, file,r, 13);
			
			hyYrqF.setStcd(stcd);
			hyYrqF.setYear(year);
			hyYrqF.setAvq(avq);
			hyYrqF.setAvqrcd(avqrcd);
			hyYrqF.setMxq(mxq);
			hyYrqF.setMxqrcd(mxqrcd);
			hyYrqF.setMxqdt(mxqdt);
			if (mnq == null || "".equals(mnq)) {hyYrqF.setMnq(null);
			}else{
				bdl = new BigDecimal(mnq);
				hyYrqF.setMnq(bdl);}
			
			hyYrqF.setMnqrcd(mnqrcd);
			hyYrqF.setMnqdt(mnqdt);
			if (rw == null || "".equals(rw)) {hyYrqF.setRw(null);
			}else{
				brw = new BigDecimal(rw);
				hyYrqF.setRw(brw);}
			hyYrqF.setRm(rm);
			hyYrqF.setRd(rd);
			hyYrqFList.add(hyYrqF);
			
			hyDaexI.setStcd(stcd);
			hyDaexI.setTbid("HY_DQ_C");
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
		
		for(HyYrqF hyYrqFResord : hyYrqFList){
			count = hyYrqFMapper.selectstcd(hyYrqFResord);
			if(count >0){
				hyYrqFMapper.delete(hyYrqFResord);
			}
			hyYrqFMapper.add(hyYrqFResord);
		}
		return notNull;
	}

}
