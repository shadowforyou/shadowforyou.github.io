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

import com.poi.testpoi.mapper.HyMmxpFMapper;
import com.poi.testpoi.pojo.HyMmxpF;
import com.poi.testpoi.service.HyMmxpFService;
import com.poi.testpoi.util.CellDateUtil;
import com.poi.testpoi.util.CellTypeUtil;
import com.poi.testpoi.util.GetNumericUtil;
import com.poi.testpoi.util.SheetUtil;

@Service
public class HyMmxpFServiceImpl implements HyMmxpFService {

	@Autowired
	private HyMmxpFMapper hyMmxpFMapper;

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public boolean batchImport(String fileName, MultipartFile file) throws Exception {

		List<HyMmxpF> hyMmxpFList = new ArrayList<>();
		CellTypeUtil cellTypeUtil = new CellTypeUtil();
		CellDateUtil cellDateUtil = new CellDateUtil();
		GetNumericUtil getNumericUtil = new GetNumericUtil();
		SheetUtil sheetUtil = new SheetUtil();
		boolean notNull = false;
		Sheet sheet = sheetUtil.batchImport(fileName, file);
		if (sheet != null) {
			notNull = true;
		}
		System.out.println(fileName);
		String year = cellTypeUtil.cellTypeYear(fileName, file, 0, 0);

		for (int r = 3; r <= sheet.getLastRowNum(); r += 2) {
			Row row = sheet.getRow(r);// 通过sheet表单对象得到 行对象
			if (row == null) {
				continue;
			}
			int d = r + 1;
			String result = null;
			String date = null;
			Date bgtm = null;
			// String stcd = cellTypeUtil.cellTypeStcd(fileName, file, r, 2);
			String name = cellTypeUtil.cellTypecommon(fileName, file, r, 1);
			String stcd = year + "-" + name +"-降水蒸发站";// 临时站码
			if (stcd == null || "".equals(stcd)) {
				continue;
			}

			for (int c = 3; c <= 15; c++) {
				result = cellTypeUtil.cellTypecommon(fileName, file, r, c);
				date = cellTypeUtil.cellTypecommon(fileName, file, d, c);
				HyMmxpF hyMmxpF1 = new HyMmxpF();
				if (c == 3) {
					if (result == null || "".equals(result)) {
						continue;
					} else {
						String tempresult = getNumericUtil.GetNumeric(result);
						if (tempresult == null || "".equals(tempresult)) {
							hyMmxpF1.setMxp(null);
							hyMmxpF1.setMxprc(result);
						} else {
							hyMmxpF1.setMxp(tempresult);
							hyMmxpF1.setMxprc(getNumericUtil.GetNumericSymbol(result));
						}
					}
					if (date == null || "".equals(date)) {
						bgtm = null;
					} else {
						bgtm = cellDateUtil.cellTypeFromFromat(fileName, file, year, d, c);
						hyMmxpF1.setBgtm(bgtm);
					}

					hyMmxpF1.setStcd(stcd);
					hyMmxpF1.setYear(year);
					hyMmxpF1.setMxpdr("10");
					hyMmxpFList.add(hyMmxpF1);
				}
				HyMmxpF hyMmxpF2 = new HyMmxpF();
				if (c == 4) {
					if (result == null || "".equals(result)) {
						continue;
					} else {
						String tempresult = getNumericUtil.GetNumeric(result);
						if (tempresult == null || "".equals(tempresult)) {
							hyMmxpF2.setMxp(null);
							hyMmxpF2.setMxprc(result);
						} else {
							hyMmxpF2.setMxp(tempresult);
							hyMmxpF2.setMxprc(getNumericUtil.GetNumericSymbol(result));
						}
					}
					if (date == null || "".equals(date)) {
						bgtm = null;
					} else {
						bgtm = cellDateUtil.cellTypeFromFromat(fileName, file, year, d, c);
						hyMmxpF2.setBgtm(bgtm);
					}
					hyMmxpF2.setStcd(stcd);
					hyMmxpF2.setYear(year);
					hyMmxpF2.setMxpdr("20");
					hyMmxpFList.add(hyMmxpF2);
				}
				HyMmxpF hyMmxpF3 = new HyMmxpF();
				if (c == 5) {
					if (result == null || "".equals(result)) {
						continue;
					} else {
						String tempresult = getNumericUtil.GetNumeric(result);
						if (tempresult == null || "".equals(tempresult)) {
							hyMmxpF3.setMxp(null);
							hyMmxpF3.setMxprc(result);
						} else {
							hyMmxpF3.setMxp(tempresult);
							hyMmxpF3.setMxprc(getNumericUtil.GetNumericSymbol(result));
						}
					}
					if (date == null || "".equals(date)) {
						bgtm = null;
					} else {
						bgtm = cellDateUtil.cellTypeFromFromat(fileName, file, year, d, c);
						hyMmxpF3.setBgtm(bgtm);
					}
					hyMmxpF3.setStcd(stcd);
					hyMmxpF3.setYear(year);
					hyMmxpF3.setMxpdr("30");
					hyMmxpFList.add(hyMmxpF3);
				}
				HyMmxpF hyMmxpF4 = new HyMmxpF();
				if (c == 6) {
					if (result == null || "".equals(result)) {
						continue;
					} else {
						String tempresult = getNumericUtil.GetNumeric(result);
						if (tempresult == null || "".equals(tempresult)) {
							hyMmxpF4.setMxp(null);
							hyMmxpF4.setMxprc(result);
						} else {
							hyMmxpF4.setMxp(tempresult);
							hyMmxpF4.setMxprc(getNumericUtil.GetNumericSymbol(result));
						}
					}
					if (date == null || "".equals(date)) {
						bgtm = null;
					} else {
						bgtm = cellDateUtil.cellTypeFromFromat(fileName, file, year, d, c);
						hyMmxpF4.setBgtm(bgtm);
					}
					hyMmxpF4.setStcd(stcd);
					hyMmxpF4.setYear(year);
					hyMmxpF4.setMxpdr("45");
					hyMmxpFList.add(hyMmxpF4);
				}
				HyMmxpF hyMmxpF5 = new HyMmxpF();
				if (c == 7) {
					if (result == null || "".equals(result)) {
						continue;
					} else {
						String tempresult = getNumericUtil.GetNumeric(result);
						if (tempresult == null || "".equals(tempresult)) {
							hyMmxpF5.setMxp(null);
							hyMmxpF5.setMxprc(result);
						} else {
							hyMmxpF5.setMxp(tempresult);
							hyMmxpF5.setMxprc(getNumericUtil.GetNumericSymbol(result));
						}
					}
					if (date == null || "".equals(date)) {
						bgtm = null;
					} else {
						bgtm = cellDateUtil.cellTypeFromFromat(fileName, file, year, d, c);
						hyMmxpF5.setBgtm(bgtm);
					}
					hyMmxpF5.setStcd(stcd);
					hyMmxpF5.setYear(year);
					hyMmxpF5.setMxpdr("60");
					hyMmxpFList.add(hyMmxpF5);
				}
				HyMmxpF hyMmxpF6 = new HyMmxpF();
				if (c == 8) {
					if (result == null || "".equals(result)) {
						continue;
					} else {
						String tempresult = getNumericUtil.GetNumeric(result);
						if (tempresult == null || "".equals(tempresult)) {
							hyMmxpF6.setMxp(null);
							hyMmxpF6.setMxprc(result);
						} else {
							hyMmxpF6.setMxp(tempresult);
							hyMmxpF6.setMxprc(getNumericUtil.GetNumericSymbol(result));
						}
					}
					if (date == null || "".equals(date)) {
						bgtm = null;
					} else {
						bgtm = cellDateUtil.cellTypeFromFromat(fileName, file, year, d, c);
						hyMmxpF6.setBgtm(bgtm);
					}
					hyMmxpF6.setStcd(stcd);
					hyMmxpF6.setYear(year);
					hyMmxpF6.setMxpdr("90");
					hyMmxpFList.add(hyMmxpF6);
				}
				HyMmxpF hyMmxpF7 = new HyMmxpF();
				if (c == 9) {
					if (result == null || "".equals(result)) {
						continue;
					} else {
						String tempresult = getNumericUtil.GetNumeric(result);
						if (tempresult == null || "".equals(tempresult)) {
							hyMmxpF7.setMxp(null);
							hyMmxpF7.setMxprc(result);
						} else {
							hyMmxpF7.setMxp(tempresult);
							hyMmxpF7.setMxprc(getNumericUtil.GetNumericSymbol(result));
						}
					}
					if (date == null || "".equals(date)) {
						bgtm = null;
					} else {
						bgtm = cellDateUtil.cellTypeFromFromat(fileName, file, year, d, c);
						hyMmxpF7.setBgtm(bgtm);
					}
					hyMmxpF7.setStcd(stcd);
					hyMmxpF7.setYear(year);
					hyMmxpF7.setMxpdr("120");
					hyMmxpFList.add(hyMmxpF7);
				}
				HyMmxpF hyMmxpF8 = new HyMmxpF();
				if (c == 10) {
					if (result == null || "".equals(result)) {
						continue;
					} else {
						String tempresult = getNumericUtil.GetNumeric(result);
						if (tempresult == null || "".equals(tempresult)) {
							hyMmxpF8.setMxp(null);
							hyMmxpF8.setMxprc(result);
						} else {
							hyMmxpF8.setMxp(tempresult);
							hyMmxpF8.setMxprc(getNumericUtil.GetNumericSymbol(result));
						}
					}
					if (date == null || "".equals(date)) {
						bgtm = null;
					} else {
						bgtm = cellDateUtil.cellTypeFromFromat(fileName, file, year, d, c);
						hyMmxpF8.setBgtm(bgtm);
					}
					hyMmxpF8.setStcd(stcd);
					hyMmxpF8.setYear(year);
					hyMmxpF8.setMxpdr("180");
					hyMmxpFList.add(hyMmxpF8);
				}
				HyMmxpF hyMmxpF9 = new HyMmxpF();
				if (c == 11) {
					if (result == null || "".equals(result)) {
						continue;
					} else {
						String tempresult = getNumericUtil.GetNumeric(result);
						if (tempresult == null || "".equals(tempresult)) {
							hyMmxpF9.setMxp(null);
							hyMmxpF9.setMxprc(result);
						} else {
							hyMmxpF9.setMxp(tempresult);
							hyMmxpF9.setMxprc(getNumericUtil.GetNumericSymbol(result));
						}
					}
					if (date == null || "".equals(date)) {
						bgtm = null;
					} else {
						bgtm = cellDateUtil.cellTypeFromFromat(fileName, file, year, d, c);
						hyMmxpF9.setBgtm(bgtm);
					}
					hyMmxpF9.setStcd(stcd);
					hyMmxpF9.setYear(year);
					hyMmxpF9.setMxpdr("240");
					hyMmxpFList.add(hyMmxpF9);
				}
				HyMmxpF hyMmxpF10 = new HyMmxpF();
				if (c == 12) {
					if (result == null || "".equals(result)) {
						continue;
					} else {
						String tempresult = getNumericUtil.GetNumeric(result);
						if (tempresult == null || "".equals(tempresult)) {
							hyMmxpF10.setMxp(null);
							hyMmxpF10.setMxprc(result);
						} else {
							hyMmxpF10.setMxp(tempresult);
							hyMmxpF10.setMxprc(getNumericUtil.GetNumericSymbol(result));
						}
					}
					if (date == null || "".equals(date)) {
						bgtm = null;
					} else {
						bgtm = cellDateUtil.cellTypeFromFromat(fileName, file, year, d, c);
						hyMmxpF10.setBgtm(bgtm);
					}
					hyMmxpF10.setStcd(stcd);
					hyMmxpF10.setYear(year);
					hyMmxpF10.setMxpdr("360");
					hyMmxpFList.add(hyMmxpF10);
				}
				HyMmxpF hyMmxpF11 = new HyMmxpF();
				if (c == 13) {
					if (result == null || "".equals(result)) {
						continue;
					} else {
						String tempresult = getNumericUtil.GetNumeric(result);
						if (tempresult == null || "".equals(tempresult)) {
							hyMmxpF11.setMxp(null);
							hyMmxpF11.setMxprc(result);
						} else {
							hyMmxpF11.setMxp(tempresult);
							hyMmxpF11.setMxprc(getNumericUtil.GetNumericSymbol(result));
						}
					}
					if (date == null || "".equals(date)) {
						bgtm = null;
					} else {
						bgtm = cellDateUtil.cellTypeFromFromat(fileName, file, year, d, c);
						hyMmxpF11.setBgtm(bgtm);
					}
					hyMmxpF11.setStcd(stcd);
					hyMmxpF11.setYear(year);
					hyMmxpF11.setMxpdr("540");
					hyMmxpFList.add(hyMmxpF11);
				}
				HyMmxpF hyMmxpF12 = new HyMmxpF();
				if (c == 14) {
					if (result == null || "".equals(result)) {
						continue;
					} else {
						String tempresult = getNumericUtil.GetNumeric(result);
						if (tempresult == null || "".equals(tempresult)) {
							hyMmxpF12.setMxp(null);
							hyMmxpF12.setMxprc(result);
						} else {
							hyMmxpF12.setMxp(tempresult);
							hyMmxpF12.setMxprc(getNumericUtil.GetNumericSymbol(result));
						}
					}
					if (date == null || "".equals(date)) {
						bgtm = null;
					} else {
						bgtm = cellDateUtil.cellTypeFromFromat(fileName, file, year, d, c);
						hyMmxpF12.setBgtm(bgtm);
					}
					hyMmxpF12.setStcd(stcd);
					hyMmxpF12.setYear(year);
					hyMmxpF12.setMxpdr("720");
					hyMmxpFList.add(hyMmxpF12);
				}
				HyMmxpF hyMmxpF13 = new HyMmxpF();
				if (c == 15) {
					if (result == null || "".equals(result)) {
						continue;
					} else {
						String tempresult = getNumericUtil.GetNumeric(result);
						if (tempresult == null || "".equals(tempresult)) {
							hyMmxpF13.setMxp(null);
							hyMmxpF13.setMxprc(result);
						} else {
							hyMmxpF13.setMxp(tempresult);
							hyMmxpF13.setMxprc(getNumericUtil.GetNumericSymbol(result));
						}
					}
					if (date == null || "".equals(date)) {
						bgtm = null;
					} else {
						bgtm = cellDateUtil.cellTypeFromFromat(fileName, file, year, d, c);
						hyMmxpF13.setBgtm(bgtm);
					}
					hyMmxpF13.setStcd(stcd);
					hyMmxpF13.setYear(year);
					hyMmxpF13.setMxpdr("1440");
					hyMmxpFList.add(hyMmxpF13);
				}
			}
		}

		for (HyMmxpF hyMmxpFResord : hyMmxpFList) {
			Integer count = hyMmxpFMapper.selectstcd(hyMmxpFResord);
			if (count > 0) {
				hyMmxpFMapper.delete(hyMmxpFResord);
			}
			hyMmxpFMapper.add(hyMmxpFResord);
		}
		return notNull;
	}

}
