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
import com.poi.testpoi.mapper.HyXsmsrsGMapper;
import com.poi.testpoi.pojo.HyDaexI;
import com.poi.testpoi.pojo.HyXsmsrsG;
import com.poi.testpoi.service.HyXsmsrsGService;
import com.poi.testpoi.util.CellDateUtil;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.GetNumericUtil;
import com.poi.testpoi.util.IsRowNull;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyXsmsrsGServiceImpl implements HyXsmsrsGService {

	@Autowired
	private HyXsmsrsGMapper hyXsmsrsGMapper;

	@Autowired
	private HyDaexIMapper HyDaexIMapper;// 附录

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {
		List<HyXsmsrsG> hyXsmsrsGList = new ArrayList<>();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		CellDateUtil cellDateUtil = new CellDateUtil();
		GetNumericUtil getNumericUtil = new GetNumericUtil();
		IsRowNull isRowNull = new IsRowNull();
		SheetUtil sheetUtil = new SheetUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		HyXsmsrsG hyXsmsrsG;
		HyDaexI hyDaexI = new HyDaexI();

		String nt = null;
		// String stcd = cellTypeUtil.cellTypeStcd(fileName, file, 0, 1);
		String year = cellTypeUtil.cellTypeYear(fileName, file, 0, 0);
		Date date = cellDateUtil.cellTypeFromXsCommon(fileName, file, 0, 5);
		String name = cellTypeUtil.cellTypecommon(fileName, file, 0, 2);
		String stcd = year + "-" + name+"-水位水文站";// 临时站码
		// 测时水位
		String obdrz = getNumericUtil.GetNumeric(cellTypeUtil.cellTypecommon(fileName, file, 0, 9));

		// 测次号
		String obno = cellTypeUtil.cellTypeSpecile(fileName, file, 0, 11);

		for (int i = 0; i <= 14; i += 3) {
			if (stcd == null || "".equals(stcd)) {
				break;
			}
			for (int r = 2; r <= sheet.getLastRowNum(); r++) {
				Row row = sheet.getRow(r);// 通过sheet表单对象得到 行对象
				if (r == sheet.getLastRowNum()) {
					Boolean flag = isRowNull.rowIsNull(row);// false表示不为空
					if (flag) {// true,最后一行为空
						continue;
					} else {
						nt = isRowNull.rowHasNt(row);//附注
						if(nt == null) {
						}else {
							continue;
						}
					}
				}

				hyXsmsrsG = new HyXsmsrsG();
				hyXsmsrsG.setStcd(stcd);
				hyXsmsrsG.setObdt(date);
				hyXsmsrsG.setObno(obno);
				hyXsmsrsG.setObdrz(obdrz);
				String vtno = null;
				String di;
				String rvbdel;
				String result;
				if (i == 0) {
					for (int c = 0; c <= 2; c++) {
						result = cellTypeUtil.cellTypecommon(fileName, file, r, c);
						if (c == 0) {
							vtno = result;
							if(vtno != null && vtno.contains(".")) {
								String temp[] = vtno.split("\\.");
								vtno = temp[0];
							}
							hyXsmsrsG.setVtno(vtno);
						} else if (c == 1) {
							di = result;
							if (di == null || "".equals(di)) {
								hyXsmsrsG.setDi(null);
							} else {
								// bdl =new BigDecimal(di);
								hyXsmsrsG.setDi(di);
							}
						} else {
							if (vtno == null || "".equals(vtno)) {
								continue;
							}
							if (result == null || "".equals(result)) {
								rvbdel = result;
								hyXsmsrsG.setRvbdel(rvbdel);
							} else {
								if (result.contains(".")) {
									rvbdel = result;
									hyXsmsrsG.setRvbdel(rvbdel);
								} else {
									for (int rv = 0; rv <= hyXsmsrsGList.size(); rv++) {
										HyXsmsrsG hyXsmsrsGrv = hyXsmsrsGList.get(hyXsmsrsGList.size() - 1);
										String temprv = hyXsmsrsGrv.getRvbdel();
										String temp[] = temprv.split("\\.");
										rvbdel = temp[0] + "." + result;
										hyXsmsrsG.setRvbdel(rvbdel);
										rv = hyXsmsrsGList.size();
									}
								}
							}
						}
					}
					if (vtno == null || "".equals(vtno)) {
						continue;
					} else {
						hyXsmsrsGList.add(hyXsmsrsG);
					}
					if (r == sheet.getLastRowNum()) {
						continue;
					}
				} else if (i == 3) {
					for (int c = 3; c <= 5; c++) {
						result = cellTypeUtil.cellTypecommon(fileName, file, r, c);
						if (c == 3) {
							vtno = result;
							if(vtno != null && vtno.contains(".")) {
								String temp[] = vtno.split("\\.");
								vtno = temp[0];
							}
							hyXsmsrsG.setVtno(vtno);
						} else if (c == 4) {
							di = result;
							if (di == null || "".equals(di)) {
								hyXsmsrsG.setDi(null);
							} else {
								// bdl =new BigDecimal(di);
								hyXsmsrsG.setDi(di);
							}
						} else {
							if (vtno == null || "".equals(vtno)) {
								continue;
							}
							if (result == null || "".equals(result)) {
								rvbdel = result;
								hyXsmsrsG.setRvbdel(rvbdel);
							} else {
								if (result.contains(".")) {
									rvbdel = result;
									hyXsmsrsG.setRvbdel(rvbdel);
								} else {
									for (int rv = 0; rv <= hyXsmsrsGList.size(); rv++) {
										HyXsmsrsG hyXsmsrsGrv = hyXsmsrsGList.get(hyXsmsrsGList.size() - 1);
										String temprv = hyXsmsrsGrv.getRvbdel();
										String temp[] = temprv.split("\\.");
										rvbdel = temp[0] + "." + result;
										hyXsmsrsG.setRvbdel(rvbdel);
										rv = hyXsmsrsGList.size();
									}
								}
							}
						}
					}
					if (vtno == null || "".equals(vtno)) {
						continue;
					} else {
						hyXsmsrsGList.add(hyXsmsrsG);
					}
					if (r == sheet.getLastRowNum()) {
						continue;
					}
				} else if (i == 6) {
					for (int c = 6; c <= 8; c++) {
						result = cellTypeUtil.cellTypecommon(fileName, file, r, c);
						if (c == 6) {
							vtno = result;
							if(vtno != null && vtno.contains(".")) {
								String temp[] = vtno.split("\\.");
								vtno = temp[0];
							}
							hyXsmsrsG.setVtno(vtno);
						} else if (c == 7) {
							di = result;
							if (di == null || "".equals(di)) {
								hyXsmsrsG.setDi(null);
							} else {
								// bdl =new BigDecimal(di);
								hyXsmsrsG.setDi(di);
							}
						} else {
							if (vtno == null || "".equals(vtno)) {
								continue;
							}
							if (result == null || "".equals(result)) {
								rvbdel = result;
								hyXsmsrsG.setRvbdel(rvbdel);
							} else {
								if (result.contains(".")) {
									rvbdel = result;
									hyXsmsrsG.setRvbdel(rvbdel);
								} else {
									for (int rv = 0; rv <= hyXsmsrsGList.size(); rv++) {
										HyXsmsrsG hyXsmsrsGrv = hyXsmsrsGList.get(hyXsmsrsGList.size() - 1);
										String temprv = hyXsmsrsGrv.getRvbdel();
										String temp[] = temprv.split("\\.");
										rvbdel = temp[0] + "." + result;
										hyXsmsrsG.setRvbdel(rvbdel);
										rv = hyXsmsrsGList.size();
									}
								}
							}
						}
					}
					if (vtno == null || "".equals(vtno)) {
						continue;
					} else {
						hyXsmsrsGList.add(hyXsmsrsG);
					}
					if (r == sheet.getLastRowNum()) {
						continue;
					}
				} else if (i == 9) {
					for (int c = 9; c <= 11; c++) {
						result = cellTypeUtil.cellTypecommon(fileName, file, r, c);
						if (c == 9) {
							vtno = result;
							if(vtno != null && vtno.contains(".")) {
								String temp[] = vtno.split("\\.");
								vtno = temp[0];
							}
							hyXsmsrsG.setVtno(vtno);
						} else if (c == 10) {
							di = result;
							if (di == null || "".equals(di)) {
								hyXsmsrsG.setDi(null);
							} else {
								// System.out.println("****"+r+"-------"+"======"+di);
								// bdl =new BigDecimal(di);
								hyXsmsrsG.setDi(di);
							}
						} else {
							if (vtno == null || "".equals(vtno)) {
								continue;
							}
							if (result == null || "".equals(result)) {
								rvbdel = result;
								hyXsmsrsG.setRvbdel(rvbdel);
							} else {
								if (result.contains(".")) {
									rvbdel = result;
									hyXsmsrsG.setRvbdel(rvbdel);
								} else {
									for (int rv = 0; rv <= hyXsmsrsGList.size(); rv++) {
										HyXsmsrsG hyXsmsrsGrv = hyXsmsrsGList.get(hyXsmsrsGList.size() - 1);
										String temprv = hyXsmsrsGrv.getRvbdel();
										String temp[] = temprv.split("\\.");
										rvbdel = temp[0] + "." + result;
										hyXsmsrsG.setRvbdel(rvbdel);
										rv = hyXsmsrsGList.size();
									}
								}
							}
						}
					}
					if (vtno == null || "".equals(vtno)) {
						continue;
					} else {
						hyXsmsrsGList.add(hyXsmsrsG);
					}
					if (r == sheet.getLastRowNum()) {
						continue;
					}
				} else if (i == 12) {
					for (int c = 12; c <= 14; c++) {
						result = cellTypeUtil.cellTypecommon(fileName, file, r, c);
						if (c == 12) {
							vtno = result;
							if(vtno != null && vtno.contains(".")) {
								String temp[] = vtno.split("\\.");
								vtno = temp[0];
							}
							hyXsmsrsG.setVtno(vtno);
						} else if (c == 13) {

							di = result;
							if (di == null || di.equals("")) {
								hyXsmsrsG.setDi(null);
							} else {
								// bdl =new BigDecimal(di);
								hyXsmsrsG.setDi(di);
							}
						} else {
							if (vtno == null || "".equals(vtno)) {
								continue;
							}
							if (result == null || "".equals(result)) {
								rvbdel = result;
								hyXsmsrsG.setRvbdel(rvbdel);
							} else {
								if (result.contains(".")) {
									rvbdel = result;
									hyXsmsrsG.setRvbdel(rvbdel);
								} else {
									for (int rv = 0; rv <= hyXsmsrsGList.size(); rv++) {
										HyXsmsrsG hyXsmsrsGrv = hyXsmsrsGList.get(hyXsmsrsGList.size() - 1);
										String temprv = hyXsmsrsGrv.getRvbdel();
										String temp[] = temprv.split("\\.");
										rvbdel = temp[0] + "." + result;
										hyXsmsrsG.setRvbdel(rvbdel);
										rv = hyXsmsrsGList.size();
									}
								}
							}
						}
					}
					if (vtno == null || "".equals(vtno)) {
						continue;
					} else {
						hyXsmsrsGList.add(hyXsmsrsG);
					}
					if (r == sheet.getLastRowNum()) {
						continue;
					}
				}
			}
		}
		Integer count = null;
		for (HyXsmsrsG HyXsmsrsGResord : hyXsmsrsGList) {
			count = hyXsmsrsGMapper.selectstcd(HyXsmsrsGResord);
			if (count > 0) {
				hyXsmsrsGMapper.delete(HyXsmsrsGResord);
			}
			hyXsmsrsGMapper.add(HyXsmsrsGResord);
		}
		hyDaexI.setStcd(stcd);
		hyDaexI.setTbid("HY_XSMSRS_G");
		hyDaexI.setYear(year);
		if(nt == null || "".equals(nt)) {
			count = HyDaexIMapper.selectstcd(hyDaexI);
			if(count>0) {
				HyDaexIMapper.delete(hyDaexI);
			}
		}else {
			hyDaexI.setNt(nt);
			count = HyDaexIMapper.selectstcd(hyDaexI);
			if(count>0) {
				HyDaexIMapper.delete(hyDaexI);
			}
			HyDaexIMapper.add(hyDaexI);
		}
		
		return notNull;
	}

}
