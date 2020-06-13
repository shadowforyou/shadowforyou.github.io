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
import com.poi.testpoi.mapper.HyObqGMapper;
import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.pojo.HyDaexI;
import com.poi.testpoi.pojo.HyObqG;
import com.poi.testpoi.service.HyObqGService;
import com.poi.testpoi.util.CellDateUtil;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.GetNumericUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyObqGServiceImpl implements HyObqGService {

	@Autowired
	private HyObqGMapper hyObqGMapper;
	@Autowired
	private HyDaexIMapper HyDaexIMapper;// 附录
	@Autowired
	private HyStscAMapper hyStscAMapper;

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {

		List<HyObqG> hyObqGList = new ArrayList<>();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		CellDateUtil cellDateUtil = new CellDateUtil();
		GetNumericUtil getNumericUtil = new GetNumericUtil();
		SheetUtil sheetUtil = new SheetUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		HyObqG hyObqG;
		HyDaexI hyDaexI = new HyDaexI();
		String nt = null;
		String year;
		String stcd = null;

		for (int r = 2; r <= sheet.getLastRowNum(); r++) {
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
			hyObqG = new HyObqG();
			year = cellTypeUtil.cellTypeYear(fileName, file, r,1);
			hyObqG.setStcd(stcd);
			// 施测号数
			hyObqG.setQobno(cellTypeUtil.cellTypeSpecile(fileName, file, r, 5));// 施测号数
			// System.out.println("-----------------"+hyObqG.getQobno());
			//月日
			Date Msq = cellDateUtil.cellTypeFromFromat(fileName, file, year, r, 2);
			String M = String.format("%tm", Msq);
			String D = String.format("%td", Msq);

			// 起始时间
			hyObqG.setMsqbgtm(cellDateUtil.cellTypeFromMeasure(fileName, file, year, M, D, r, 3));
			// System.out.println("****"+r+"----"+hyObqG.getMsqbgtm());
			// 结束时间
			hyObqG.setMsqedtm(cellDateUtil.cellTypeFromMeasure(fileName, file, year, M, D, r, 4));
			// System.out.println("****"+r+"----"+hyObqG.getMsqedtm());
			// 断面位置
			String xsqlc = cellTypeUtil.cellTypecommon(fileName, file, r, 6);
			hyObqG.setXsqlc(xsqlc);

			// 测验方法
			hyObqG.setMsqmt(cellTypeUtil.cellTypecommon(fileName, file, r, 7));

			// 基本水尺水位
			String rB = cellTypeUtil.cellTypecommon(fileName, file, r, 11);
			hyObqG.setBsggz(rB);

			// 流量-注解嗎
			hyObqG.setQ(getNumericUtil.GetNumeric(cellTypeUtil.cellTypecommon(fileName, file, r, 12)));
			hyObqG.setQrcd(getNumericUtil.GetNumericSymbol(cellTypeUtil.cellTypecommon(fileName, file, r, 12)));

			// 断面面积：定为断面总面积
			hyObqG.setXstta(getNumericUtil.GetNumeric(cellTypeUtil.cellTypecommon(fileName, file, r, 13)));
			String bracket =cellTypeUtil.cellTypecommon(fileName, file, r, 14);//括号既注解码
			if(bracket.contains("true")){
				hyObqG.setXsarcd("()");
			}else{
				hyObqG.setXsarcd(null);
			}

			// 平均流速
			hyObqG.setXsavv(getNumericUtil.GetNumeric(cellTypeUtil.cellTypecommon(fileName, file, r, 15)));

			// 最大流速
			hyObqG.setXsmxv(getNumericUtil.GetNumeric(cellTypeUtil.cellTypecommon(fileName, file, r, 16)));

			// 水面宽
			hyObqG.setTpwd(cellTypeUtil.cellTypecommon(fileName, file, r, 17));

			// 平均水深
			hyObqG.setXsavdp(cellTypeUtil.cellTypecommon(fileName, file, r, 18));

			// 最大水深
			hyObqG.setXsmxdp(cellTypeUtil.cellTypecommon(fileName, file, r, 19));

			// 水面比
			hyObqG.setRvsfsl(cellTypeUtil.cellTypecommon(fileName, file, r, 20));

			// 糙率
			hyObqG.setN(cellTypeUtil.cellTypecommon(fileName, file, r, 21));

			// 附注
			hyObqG.setObnont(cellTypeUtil.cellTypecommon(fileName, file, r, 22));

			hyObqGList.add(hyObqG);
			
			nt =cellTypeUtil.cellTypecommon(fileName, file, r, 22);
			hyDaexI.setStcd(stcd);
			hyDaexI.setTbid("HY_OBQ_G");
			hyDaexI.setYear(year);
		}

		for (HyObqG hyObqGResord : hyObqGList) {
			Integer count = hyObqGMapper.selectstcd(hyObqGResord);
			if (count > 0) {
				hyObqGMapper.delete(hyObqGResord);
			}
			hyObqGMapper.add(hyObqGResord);
		}

		if (nt == null || "".equals(nt)) {
			Integer count = HyDaexIMapper.selectstcd(hyDaexI);
			if (count > 0) {
				HyDaexIMapper.delete(hyDaexI);
			}
		} else {
			hyDaexI.setNt(nt);
			Integer count = HyDaexIMapper.selectstcd(hyDaexI);
			if (count > 0) {
				HyDaexIMapper.delete(hyDaexI);
			}
			HyDaexIMapper.add(hyDaexI);
		}

		return notNull;

	}

}
