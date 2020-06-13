package com.poi.testpoi.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class IsRowNull {
	/**
	 * 判断行是否为空
	 * 
	 * @param row
	 * @return
	 */
	public Boolean rowIsNull(Row row) {
		for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 最后一行是否含有附注
	 * 
	 * @param row
	 * @return
	 */
	public String rowHasNt(Row row) {
		for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);

			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK && (c==0 || c==1 || c== 2)) {
				String result = null;
				if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					result = String.valueOf(row.getCell(c).getNumericCellValue());
				} else {
					result = row.getCell(c).getStringCellValue();
				}

				if(result.contains("附注") || result.contains("注") || result.contains("说明") ) {

				if (result.contains("附注") || result.contains("注") || 
					result.contains("说明") ||
					result.length()>6) {
					return result;
				}
				}
			}
		}
		return null;
	}

}
