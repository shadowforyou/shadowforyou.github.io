package com.poi.testpoi.service.Impl;

import java.math.BigDecimal;
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

import com.poi.testpoi.mapper.HyMtqEMapper;
import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.pojo.HyMtqE;
import com.poi.testpoi.service.HyMtqEService;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyMtqEServiceImpl implements HyMtqEService {

	@Autowired
	private HyMtqEMapper hyMtqEMapper;
	@Autowired
	private HyStscAMapper hyStscAMapper;
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		List<HyMtqE> hyMtqEList = new ArrayList<>();
		SheetUtil sheetUtil = new SheetUtil();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		HyMtqE hyMtqE;
		String stcd;
		String mth;
		String mth0;
		String avq;
		String avqrcd;
		String mxq;
		String mxqrcd;
		String mxqdt0;
		String mxqdt1;
		String date0;
		Date mxqdt;
		String mnq;
		String mnqrcd;
		String mnqdt0;
		String mnqdt1;
		String date1;
		Date mnqdt;
		BigDecimal bdl = null;
		BigDecimal mnw = null;
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
			hyMtqE=new HyMtqE();
			year = cellTypeUtil.cellTypeYear(fileName, file, r, 1);
			mth0 = cellTypeUtil.cellTypecommon(fileName, file, r, 2);
			String tempMth[] = mth0.split("\\.");
			mth = tempMth[0];
			avq =cellTypeUtil.cellTypecommon(fileName, file, r, 3);
			avqrcd = cellTypeUtil.cellTypecommon(fileName, file, r, 4);
			mxq =  cellTypeUtil.cellTypecommon(fileName, file, r, 5);
			mxqrcd = cellTypeUtil.cellTypecommon(fileName, file, r, 6);
			mxqdt0 = cellTypeUtil.cellTypecommon(fileName, file, r, 7);
			String tempmxq[] = mxqdt0.split("\\.");
			mxqdt1 = tempmxq[0];
			date0 = year + "-" + mth + "-" + mxqdt1;
			mxqdt =new SimpleDateFormat("yyyy-MM-dd").parse(date0);
			
			mnq =  cellTypeUtil.cellTypecommon(fileName, file, r, 8);
			mnqrcd = cellTypeUtil.cellTypecommon(fileName, file, r, 9);
			mnqdt0 = cellTypeUtil.cellTypecommon(fileName, file, r, 10);
			String tempmnq[] = mnqdt0.split("\\.");
			mnqdt1 = tempmnq[0];
			date1 = year + "-" + mth + "-" + mnqdt1;
			mnqdt =new SimpleDateFormat("yyyy-MM-dd").parse(date1);
			
			hyMtqE.setMth(Integer.parseInt(mth));
			hyMtqE.setStcd(stcd);
			hyMtqE.setYear(year);
			if (avq == null || "".equals(avq)) {hyMtqE.setAvq(null);
			}else{
				bdl = new BigDecimal(avq);
				hyMtqE.setAvq(bdl);}
			hyMtqE.setAvqrcd(avqrcd);
			hyMtqE.setMxq(mxq);
			hyMtqE.setMxqrcd(mxqrcd);
			hyMtqE.setMxqdt(mxqdt);
			if (mnq == null || "".equals(mnq)) {hyMtqE.setMnq(null);
			}else{
				mnw = new BigDecimal(mnq);
				hyMtqE.setMnq(mnw);}
			hyMtqE.setMnqrcd(mnqrcd);
			hyMtqE.setMnqdt(mnqdt);
			hyMtqEList.add(hyMtqE);
		}
		Integer count = null;
		for(HyMtqE hyMtqEResord : hyMtqEList){
			count = hyMtqEMapper.selectstcd(hyMtqEResord);
			if(count >0){
				hyMtqEMapper.delete(hyMtqEResord);
			}
			hyMtqEMapper.add(hyMtqEResord);
		}
		return notNull;
	}

}

