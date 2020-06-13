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
import com.poi.testpoi.mapper.HyYrqsFMapper;
import com.poi.testpoi.pojo.HyDaexI;
import com.poi.testpoi.pojo.HyYrqsF;
import com.poi.testpoi.service.HyYrqsFService;
import com.poi.testpoi.util.CellDateUtil;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyYrqsFServiceImpl implements HyYrqsFService {

	@Autowired
	private HyYrqsFMapper hyYrqsFMapper;
	@Autowired
	private HyStscAMapper hyStscAMapper;
	@Autowired
	private HyDaexIMapper hyDaexIMapper;// 附录
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		List<HyYrqsF> hyYrqsFList = new ArrayList<>();
		SheetUtil sheetUtil = new SheetUtil();
		CellDateUtil cellDateUtil = new CellDateUtil();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		HyYrqsF hyYrqsF;
		HyDaexI hyDaexI = new HyDaexI();
		String sdtp = "悬移质";
		String year;
		String stcd;
		String avqs;
		String mxdyqs;
		Date mxdyqsdt;
		String sw;
		String sm;
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
			hyYrqsF=new HyYrqsF();
			year = cellTypeUtil.cellTypeYear(fileName, file, r, 1);
			avqs = cellTypeUtil.cellTypecommon(fileName, file, r, 2);
			mxdyqs =cellTypeUtil.cellTypecommon(fileName, file, r, 3);
			mxdyqsdt = cellDateUtil.cellTypeFromFromat(fileName, file,year, r,4);
			sw =  cellTypeUtil.cellTypecommon(fileName, file, r, 5);
			sm = cellTypeUtil.cellTypecommon(fileName, file, r, 6);
			
			nt = cellTypeUtil.cellTypecommon(fileName, file,r, 7);
			
			hyYrqsF.setStcd(stcd);
			hyYrqsF.setSdtp(sdtp);
			hyYrqsF.setYear(year);
			hyYrqsF.setAvqs(avqs);
			hyYrqsF.setMxdyqs(mxdyqs);
			hyYrqsF.setMxdyqsodt(mxdyqsdt);
			hyYrqsF.setSw(sw);
			hyYrqsF.setSm(sm);
			hyYrqsFList.add(hyYrqsF);
			
			hyDaexI.setStcd(stcd);
			hyDaexI.setTbid("HY_DQS_C");
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
		
		for(HyYrqsF hyYrqsFResord : hyYrqsFList){
			count = hyYrqsFMapper.selectstcd(hyYrqsFResord);
			if(count >0){
				hyYrqsFMapper.delete(hyYrqsFResord);
			}
			hyYrqsFMapper.add(hyYrqsFResord);
		}
		return notNull;
	}

}
