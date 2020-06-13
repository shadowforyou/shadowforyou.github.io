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

import com.poi.testpoi.mapper.HyMtzEMapper;
import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.pojo.HyMtzE;
import com.poi.testpoi.service.HyMtzEService;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyMtzEServiceImpl implements HyMtzEService {

	@Autowired
	private HyMtzEMapper hyMtzEMapper;
	@Autowired
	private HyStscAMapper hyStscAMapper;
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		List<HyMtzE> hyMtzEList = new ArrayList<>();
		SheetUtil sheetUtil = new SheetUtil();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		HyMtzE hyMtzE;
		String stcd;
		String mth;
		String mth0;
		String avz;
		String avzrcd;
		String htz;
		String htzrcd;
		String htzdt0;
		String htzdt1;
		String date0;
		Date htzdt;
		String mnz;
		String mnzrcd;
		String mnzdt0;
		String mnzdt1;
		String date1;
		Date mnzdt;
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
			hyMtzE=new HyMtzE();
			year = cellTypeUtil.cellTypeYear(fileName, file, r, 1);
			mth0 = cellTypeUtil.cellTypecommon(fileName, file, r, 2);
			String tempMth[] = mth0.split("\\.");
			mth = tempMth[0];
			avz =cellTypeUtil.cellTypecommon(fileName, file, r, 3);
			avzrcd = cellTypeUtil.cellTypecommon(fileName, file, r, 4);
			htz =  cellTypeUtil.cellTypecommon(fileName, file, r, 5);
			htzrcd = cellTypeUtil.cellTypecommon(fileName, file, r, 6);
			htzdt0 = cellTypeUtil.cellTypecommon(fileName, file, r, 7);
			String temphtz[] = htzdt0.split("\\.");
			htzdt1 = temphtz[0];
			date0 = year + "-" + mth + "-" + htzdt1;
			htzdt =new SimpleDateFormat("yyyy-MM-dd").parse(date0);
			
			mnz =  cellTypeUtil.cellTypecommon(fileName, file, r, 8);
			mnzrcd = cellTypeUtil.cellTypecommon(fileName, file, r, 9);
			mnzdt0 = cellTypeUtil.cellTypecommon(fileName, file, r, 10);
			String tempmnz[] = mnzdt0.split("\\.");
			mnzdt1 = tempmnz[0];
			date1 = year + "-" + mth + "-" + mnzdt1;
			mnzdt =new SimpleDateFormat("yyyy-MM-dd").parse(date1);
			
			hyMtzE.setMth(Integer.parseInt(mth));
			hyMtzE.setStcd(stcd);
			hyMtzE.setYear(year);
			hyMtzE.setAvz(avz);
			hyMtzE.setAvzrcd(avzrcd);
			hyMtzE.setHtz(htz);
			hyMtzE.setHtzrcd(htzrcd);
			hyMtzE.setHtzdt(htzdt);
			hyMtzE.setMnz(mnz);
			hyMtzE.setMnzrcd(mnzrcd);
			hyMtzE.setMnzdt(mnzdt);
			hyMtzEList.add(hyMtzE);
		}
		Integer count = null;
		for(HyMtzE hyMtzEResord : hyMtzEList){
			count = hyMtzEMapper.selectstcd(hyMtzEResord);
			if(count >0){
				hyMtzEMapper.delete(hyMtzEResord);
			}
			hyMtzEMapper.add(hyMtzEResord);
		}
		return notNull;
	}

}


