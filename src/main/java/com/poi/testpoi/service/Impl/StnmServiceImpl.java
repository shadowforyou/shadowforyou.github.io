package com.poi.testpoi.service.Impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.poi.testpoi.pojo.Stnm;
import com.poi.testpoi.service.StnmService;
import com.poi.testpoi.util.SheetUtil;

@Service
public class StnmServiceImpl implements StnmService{

	@Override
	public String batchImport(String fileName, MultipartFile file) throws Exception {
		SheetUtil sheetUtil = new SheetUtil();
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		Stnm sTnm = new Stnm();
		if(sheet.getRow(0).getCell(3) == null || sheet.getRow(0).getCell(3).getCellType() == Cell.CELL_TYPE_BLANK){//单元格没有值(等于null)时，getCell方法获取不到单元格,要用createCell方法。
			sTnm.setStnm(sheet.getRow(0).getCell(2).getStringCellValue()) ;
		}else {//单元格有值时，getCell方法获获取到单元格。
			sTnm.setStnm(sheet.getRow(0).getCell(3).getStringCellValue()) ;
		}
		return sTnm.getStnm();
	}

}
