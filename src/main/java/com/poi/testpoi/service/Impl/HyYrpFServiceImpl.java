package com.poi.testpoi.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.poi.testpoi.mapper.HyYrpFMapper;
import com.poi.testpoi.pojo.HyYrpF;
import com.poi.testpoi.service.HyYrpFService;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyYrpFServiceImpl implements HyYrpFService {

	@Autowired
	private HyYrpFMapper hyYrpFMapper;
//	@Autowired
//	private HyStscAMapper hyStscAMapper;
//	@Autowired
//	private HyDaexIMapper hyDaexIMapper;// 附录
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		List<HyYrpF> hyYrpFList = new ArrayList<>();
		SheetUtil sheetUtil = new SheetUtil();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		HyYrpF hyYrpF;
//		HyDaexI hyDaexI = new HyDaexI();
		String year;
		String stcd;
		String p;
		String prcd;
		String pdynum;
		String pdynuumrcd;
//		String nt;
//		Integer count = null;
		
		for (int r = 2; r <= sheet.getLastRowNum(); r++) {// r = 2表示从第三行开始循环
			stcd = cellTypeUtil.cellTypeStcd(fileName, file, r, 0);
			if (stcd == null || "".equals(stcd)) {
				break;
			}
			year = cellTypeUtil.cellTypeYear(fileName, file, r, 1);
			int i=hyYrpFMapper.selectstcd(stcd,year);
			if(i>0){
				continue;
			}
			Row row = sheet.getRow(r);// 通过sheet表单对象得到 行对象
			if (row == null) {
				continue;
			}
			System.out.println(year+"年"+stcd);
			hyYrpF=new HyYrpF();
			
			p = cellTypeUtil.cellTypecommon(fileName, file, r, 2);
			prcd =cellTypeUtil.cellTypecommon(fileName, file, r, 3);
			pdynum = cellTypeUtil.cellTypecommon(fileName, file, r, 4);
			pdynuumrcd = cellTypeUtil.cellTypecommon(fileName, file, r, 5);
			
//			nt = cellTypeUtil.cellTypecommon(fileName, file,r, 7);
			
			hyYrpF.setStcd(stcd);
			hyYrpF.setYear(year);
			hyYrpF.setP(p);
			hyYrpF.setPrcd(prcd);
			hyYrpF.setPdynum(pdynum);
			hyYrpF.setPdynuumrcd(pdynuumrcd);
			hyYrpFList.add(hyYrpF);
			
//			hyDaexI.setStcd(stcd);
//			hyDaexI.setTbid("HY_DP_C");
//			hyDaexI.setYear(year);
//			if (nt == null || "".equals(nt)) {
//				count = hyDaexIMapper.selectstcd(hyDaexI);
//				if (count > 0) {
//					hyDaexIMapper.delete(hyDaexI);
//				}
//			} else {
//				hyDaexI.setNt(nt);
//				count = hyDaexIMapper.selectstcd(hyDaexI);
//				if (count > 0) {
//					hyDaexIMapper.delete(hyDaexI);
//				}
//				hyDaexIMapper.add(hyDaexI);
//			}
		}
		
		for(HyYrpF hyYrpFResord : hyYrpFList){
//			count = hyYrpFMapper.selectstcd(hyYrpFResord);
//			if(count >0){
//				hyYrpFMapper.delete(hyYrpFResord);
//			}
			hyYrpFMapper.add(hyYrpFResord);
		}
		return notNull;
	}

}

