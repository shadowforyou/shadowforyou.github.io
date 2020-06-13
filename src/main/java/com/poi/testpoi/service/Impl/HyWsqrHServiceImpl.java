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

import com.poi.testpoi.mapper.HyStscAMapper;
import com.poi.testpoi.mapper.HyWsqrHMapper;
import com.poi.testpoi.pojo.HyWsqrH;
import com.poi.testpoi.service.HyWsqrHService;
import com.poi.testpoi.util.CellDateUtil;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyWsqrHServiceImpl implements HyWsqrHService {

	@Autowired
	private HyWsqrHMapper hyWsqrHMapper;
	@Autowired
	private HyStscAMapper hyStscAMapper;
	
	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {

		List<HyWsqrH> HyWsqrHList = new ArrayList<>();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		CellDateUtil cellDateUtil = new CellDateUtil();
		SheetUtil sheetUtil = new SheetUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if(sheet!=null){
			notNull = true;
		}
		HyWsqrH hyWsqrH;
		String year;
		String stcd = null;
		
		for (int r = 2; r <=sheet.getLastRowNum(); r++) {
			stcd = cellTypeUtil.cellTypeStcd(fileName, file, r, 0);
			if (stcd == null || "".equals(stcd)) {
				break;
			}
			int i=hyStscAMapper.selectstcd(stcd);
			if(i<=0){
				continue;
			}
			Row row = sheet.getRow(r);//通过sheet表单对象得到 行对象
			if (row == null) {
				continue;
			}
			hyWsqrH = new HyWsqrH();
			year = cellTypeUtil.cellTypeYear(fileName, file, r, 1);
			hyWsqrH.setStcd(stcd);

			//率定次序
			hyWsqrH.setRtno(cellTypeUtil.cellTypeSpecile(fileName, file, r, 3));

			//月日
			Date Msq = cellDateUtil.cellTypeFromFromat(fileName, file, year, r, 2);
			String M = String.format("%tm", Msq);
			String D = String.format("%td", Msq);

			//起始时间
			hyWsqrH.setMsqbgtm(cellDateUtil.cellTypeFromMeasure(fileName,file, year, M, D, r, 5));

			//截止时间
			hyWsqrH.setMsqedtm(cellDateUtil.cellTypeFromMeasure(fileName, file, year, M, D, r, 6));
			
			hyWsqrH.setGtgrno("999");
			//闸上游水位
			hyWsqrH.setUpz(cellTypeUtil.cellTypecommon(fileName, file, r, 7));

			//闸下游水位
			hyWsqrH.setDwz(cellTypeUtil.cellTypecommon(fileName, file, r, 8));

			//闸上游水头
			hyWsqrH.setUphd(cellTypeUtil.cellTypecommon(fileName, file, r, 9));
			
			//闸下游水头
			hyWsqrH.setDwshd(cellTypeUtil.cellTypecommon(fileName, file, r, 10));
			
			//水位差
			hyWsqrH.setZd(cellTypeUtil.cellTypecommon(fileName, file, r, 11));
			
			//闸门开启高度
			String Ogavht  = (cellTypeUtil.cellTypecommon(fileName, file, r, 12));
			if(Ogavht.equals("提出水面")){
				Ogavht = null;
				hyWsqrH.setOgavht(Ogavht);
			}else {
				hyWsqrH.setOgavht(Ogavht);
			}
			
			//闸门开启孔数
			hyWsqrH.setOgcn(cellTypeUtil.cellTypecommon(fileName, file, r, 13));
			
			//开启总宽度或平均堰宽
			hyWsqrH.setOgttwd(cellTypeUtil.cellTypecommon(fileName, file, r, 14));
			hyWsqrH.setAvwrwd(cellTypeUtil.cellTypecommon(fileName, file, r, 14));
			
			//闸孔过水面积
			hyWsqrH.setGtxsa(cellTypeUtil.cellTypecommon(fileName, file, r, 15));
			
			//实测流量
			hyWsqrH.setObq(cellTypeUtil.cellTypecommon(fileName, file, r, 16));
			
			//流态
			hyWsqrH.setFlrg(cellTypeUtil.cellTypecommon(fileName, file, r, 17));

			
			//公式编号
			hyWsqrH.setQcfrno(cellTypeUtil.cellTypecommon(fileName, file, r, 18));
			
			//代号
			hyWsqrH.setIvnm(cellTypeUtil.cellTypecommon(fileName, file, r, 19));

			//数值
			hyWsqrH.setIvv(cellTypeUtil.cellTypecommon(fileName, file, r, 20));
			
			//系数
			hyWsqrH.setQk(cellTypeUtil.cellTypecommon(fileName, file, r, 21));
			
			//测流断面位置
			hyWsqrH.setXsqlc(cellTypeUtil.cellTypecommon(fileName, file, r, 22));

			//测验方法
			String msqmt0 = cellTypeUtil.cellTypecommon(fileName, file, r, 23);//仪器
			String num = cellTypeUtil.cellTypecommon(fileName, file, r, 24);;//数值
			String msqmt =  msqmt0+" "+num;
			if(msqmt0.equals("〃")) {
				for(int m0=0; m0<=HyWsqrHList.size(); m0++) {
					HyWsqrH hyWsqrHm0 = HyWsqrHList.get(HyWsqrHList.size() -1);
					hyWsqrH.setMsqmt0(hyWsqrHm0.getMsqmt0());
					hyWsqrH.setMsqmt(hyWsqrH.getMsqmt0()+" "+num);
					m0 = HyWsqrHList.size();
				}
			}else {
				hyWsqrH.setMsqmt0(msqmt0);
				hyWsqrH.setMsqmt(msqmt);
			}
			
			//断面面积
			hyWsqrH.setXsa(cellTypeUtil.cellTypecommon(fileName, file, r, 27));
			
			//平均流速
			hyWsqrH.setXsavv(cellTypeUtil.cellTypecommon(fileName, file, r, 28));
			
			//附注
			hyWsqrH.setObnont(cellTypeUtil.cellTypecommon(fileName, file, r, 29));
			
			HyWsqrHList.add(hyWsqrH);
		}
		for (HyWsqrH hyWsqrHResord : HyWsqrHList) {
		    Integer count =	hyWsqrHMapper.selectstcd(hyWsqrHResord);
		    if(count > 0) {
		    	hyWsqrHMapper.delete(hyWsqrHResord);
		    }
			
			hyWsqrHMapper.add(hyWsqrHResord);
		}
		return notNull;
	
	}

}
