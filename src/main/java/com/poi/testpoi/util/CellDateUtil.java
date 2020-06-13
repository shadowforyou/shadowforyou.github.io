package com.poi.testpoi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.multipart.MultipartFile;

public class CellDateUtil{
	SheetUtil sheetUtil = new SheetUtil();
	CellTypeUtil cellTypeUtil = new CellTypeUtil();
	/**
	 *直接从单元格获取时间
	 * @throws Exception 
	 */
	public Date cellTypeFromCell(String fileName, MultipartFile files,int r,int c) throws Exception {
		Sheet sheet = sheetUtil.batchImport(fileName,files);
		Date date = null;
		int type = sheet.getRow(r).getCell(c).getCellType();
		if(sheet.getRow(r).getCell(c) == null) {
			date = null;
		}else {
			if(type == Cell.CELL_TYPE_NUMERIC) {
				Date tempd = HSSFDateUtil.getJavaDate((Double)(sheet.getRow(r).getCell(c).getNumericCellValue()));
				String tempSd = new SimpleDateFormat("yyyy-MM-dd").format(tempd);
				date = new SimpleDateFormat("yyyy-MM-dd").parse(tempSd);
			}else if(type == Cell.CELL_TYPE_BLANK){
				date = null;
			}else {
				String temp = sheet.getRow(r).getCell(c).getStringCellValue();
				date = new SimpleDateFormat("yyyy-MM-dd").parse(temp);
			}
		}
		return date;
	}
	
	/**
	 * 实测类、率定表的日期转换问题
	 * @throws Exception 
	 */
	public Date cellTypeFromMeasure(String fileName, MultipartFile files,String year,String M,String D,int r,int c) throws Exception {
		Sheet sheet = sheetUtil.batchImport(fileName,files);
		Date date = null;
		if(sheet.getRow(r).getCell(c) == null) {
			date = null;
		}else {
			int type = sheet.getRow(r).getCell(c).getCellType();
			if(type == Cell.CELL_TYPE_NUMERIC) {
				Date tempd = HSSFDateUtil.getJavaDate((Double)(sheet.getRow(r).getCell(c).getNumericCellValue()));
				String tempSd = new SimpleDateFormat(year+"-"+M+"-"+D+" "+"HH:mm").format(tempd);
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(tempSd);
			}else if(type == Cell.CELL_TYPE_BLANK){
				date = null;
			}else {
				String temp = sheet.getRow(r).getCell(c).getStringCellValue();
				String temp0 = null;
				String temp1 = null;
				if("24:00".equals(temp) || "24：00".equals(temp)) {
					int d = Integer.parseInt(D)+1;
					D = String.valueOf(d);
					year = "9999";
				}
				if(temp.contains("：")) {
					String tempArrDate[] = temp.split("\\：");
					temp0 = tempArrDate[0].trim();
					temp1 = tempArrDate[1].trim();
				}else if(temp.contains(":")) {
					String tempArrDate[] = temp.split("\\:");
					temp0 = tempArrDate[0].trim();
					temp1 = tempArrDate[1].trim();
				}
				String tempStringDateHourAndMin = temp0+":"+temp1;
				Date tempdateHourAndMin = new SimpleDateFormat("HH:mm").parse(tempStringDateHourAndMin);
				String tempStringDate = new SimpleDateFormat(year+"-"+M+"-"+D+" "+"HH:mm").format(tempdateHourAndMin);
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(tempStringDate);
			}
		}
		if(date == null) {
			String tempStringDate = "9999-00-00 00:00";
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(tempStringDate);
		}
		return date;
	}
	
	/**
	 * 摘录表时间:若是出现该日24分，则将24分改为次日0分，年份为9999
	 * @throws Exception 
	 */
	public Date cellTypeFromExcerpt(String fileName, MultipartFile files,String year,String M,String D,int r,int c) throws Exception {
		Sheet sheet = sheetUtil.batchImport(fileName,files);
		Date date = null;
		String tempSd = null;
		Date tempDateF = null;
		String temp0 = null;
		String temp1 = null;
		int type = sheet.getRow(r).getCell(c).getCellType();
		if(sheet.getRow(r).getCell(c) == null) {
			date = null;
		}else {
			if(type == Cell.CELL_TYPE_NUMERIC) {
				if(sheet.getRow(r).getCell(c).getNumericCellValue() == 24) {
					int dd = Integer.parseInt(D) + 1;
					D = String.valueOf(dd);
					Date tempd = HSSFDateUtil.getJavaDate((Double)(sheet.getRow(r).getCell(c).getNumericCellValue()));
					tempSd = new SimpleDateFormat(9999+"-"+M+"-"+D+" "+"HH:mm").format(tempd);
				}else {
					Date tempd = HSSFDateUtil.getJavaDate((Double)(sheet.getRow(r).getCell(c).getNumericCellValue()));
					tempSd = new SimpleDateFormat(year+"-"+M+"-"+D+" "+"HH:mm").format(tempd);
				}
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(tempSd);
			}else if(type == Cell.CELL_TYPE_BLANK){
				date = null;
			}else {
				String temp = sheet.getRow(r).getCell(c).getStringCellValue();
				String tempStringDateP = null;
				if(temp.contains("：")) {
					String tempArrDate[] = temp.split("\\：");
					temp0 = tempArrDate[0].trim();
					if(tempArrDate.length-1<=0) {
						tempStringDateP = temp0;
					}else {
						temp1 = tempArrDate[1].trim();
						tempStringDateP = temp0+":"+temp1;
					}
					tempDateF = new SimpleDateFormat("HH:mm").parse(tempStringDateP);
					tempSd =  new SimpleDateFormat(year+"-"+M+"-"+D+" "+"HH:mm").format(tempDateF);
				}else if(temp.contains(":")) {
					String tempArrDate[] = temp.split("\\:");
					temp0 = tempArrDate[0].trim();
					if(tempArrDate.length-1<=0) {
						tempStringDateP = temp0;
					}else {
						temp1 = tempArrDate[1].trim();
						tempStringDateP = temp0+":"+temp1;
					}
					tempDateF = new SimpleDateFormat("HH:mm").parse(tempStringDateP);
					tempSd =  new SimpleDateFormat(year+"-"+M+"-"+D+" "+"HH:mm").format(tempDateF);
				}else if("24".equals(temp)){
					int dd = Integer.parseInt(D) + 1;
					D = String.valueOf(dd);
					tempDateF = new SimpleDateFormat("HH").parse(temp);
					tempSd = new SimpleDateFormat(9999+"-"+M+"-"+D+" "+"HH"+":00").format(tempDateF);
				}else {
					tempDateF = new SimpleDateFormat("HH").parse(temp);
					tempSd = new SimpleDateFormat(year+"-"+M+"-"+D+" "+"HH"+":00").format(tempDateF);
				}
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(tempSd);
			}
		}
		return date;
	}
	
	/**
	 * 时间格式为x-x或者逐日降水量表中日时段最大降水量的x月x日
	 * @throws Exception 
	 */
	public Date cellTypeFromFromat(String fileName, MultipartFile files,String year,int r, int c) throws Exception {
		Sheet sheet = sheetUtil.batchImport(fileName,files);
		Date date  = null;
		String tempbgdt = null;
		if(sheet.getRow(r).getCell(c) == null) {
			date = null;
		}else {
			int type = sheet.getRow(r).getCell(c).getCellType();
			if(type == Cell.CELL_TYPE_BLANK) {
				date = null;
			}else if(type == Cell.CELL_TYPE_NUMERIC) {
				Date data = HSSFDateUtil.getJavaDate((Double)(sheet.getRow(r).getCell(c).getNumericCellValue()));
				String tempdP = new SimpleDateFormat(year+"-MM-dd").format(data);
				date = new SimpleDateFormat("yyyy-MM-dd").parse(tempdP);
			}else{
				tempbgdt = sheet.getRow(r).getCell(c).getStringCellValue();
				String temp0 = null;
				String temp1 = null;
				String tempbd = null;

				Pattern p = Pattern.compile("\\s*|\t|\r|\n");
				Matcher m = p.matcher(tempbgdt);
				tempbgdt = m.replaceAll("");
				if(tempbgdt.equals("月日") || tempbgdt.equals("月曰")) {
					return null;
				}
				if(tempbgdt.contains("月")) {
					Date tempDateF = null;
					String tempDateP = null;
					if(tempbgdt.contains("日")) {
						tempDateF = new SimpleDateFormat("MM月dd日").parse(tempbgdt);
					}else if(tempbgdt.contains("曰")) {
						tempDateF = new SimpleDateFormat("MM月dd曰").parse(tempbgdt);
					}
					tempDateP = new SimpleDateFormat(year+"-"+"MM-dd").format(tempDateF);
					date = new SimpleDateFormat("yyyy-MM-dd").parse(tempDateP);
				}else {
					if(tempbgdt.contains("(")) {
						String temp[] = tempbgdt.split("\\(");
						String te[] = temp[1].split("\\)");
						tempbgdt = te[0];
					}else if(tempbgdt.contains("（")) {
						String temp[] = tempbgdt.split("\\（");
						String te[] = temp[1].split("\\）");
						tempbgdt = te[0];
					}
					if(tempbgdt.contains("-")) {
						String temp[] = tempbgdt.split("\\-");
						temp0 = temp[0].trim();
						temp1 = temp[1].trim();
					}else if(tempbgdt.contains("—")) {
						String temp[] = tempbgdt.split("\\—");
						temp0 = temp[0].trim();
						temp1 = temp[1].trim();
					}else if(tempbgdt.contains("~")) {
						String temp[] = tempbgdt.split("\\~");
						temp0 = temp[0].trim();
						temp1 = temp[1].trim();
					}else if(tempbgdt.contains("·")) {
						String temp[] = tempbgdt.split("\\·");
						temp0 = temp[0].trim();
						temp1 = temp[1].trim();
					}else if(tempbgdt.contains("/")) {
						String temp[] = tempbgdt.split("\\/");
						temp0 = temp[0].trim();
						temp1 = temp[1].trim();
					}
					tempbd = temp0+"-"+temp1;
					//日期
					String date0 = year+"-"+tempbd;
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
					date = sdf.parse(date0);
				}
			}
		}
		return date;
	}

	/**
	 * Dqc里面的月数据最大时间和最小时间
	 */
	public Date cellTypeFromDqcMaxandMin(String fileName, MultipartFile files,String year,int r, int c) throws Exception {
		Sheet sheet = sheetUtil.batchImport(fileName,files);
		Date date;
		String tempD = null;
		if(sheet.getRow(r).getCell(c) == null) {
			date = null;
		}else {
			int type = sheet.getRow(r).getCell(c).getCellType();
			if(type == Cell.CELL_TYPE_NUMERIC) {
				tempD = ((Double)sheet.getRow(r).getCell(c).getNumericCellValue()).toString();
			}else if(type == Cell.CELL_TYPE_BLANK){
				date = null;
				return date;
			}else {
				tempD = sheet.getRow(r).getCell(c).getStringCellValue();
			}
			String date0 = year+"-"+c+"-"+tempD;
			date = new SimpleDateFormat("yyyy-MM-dd").parse(date0);
		}
		return date;
	}
	
	/**
	 * Dqc、Dwe里面的年数据最大时间和最小时间
	 */
	public Date cellTypeFromDqcYear(String fileName, MultipartFile files,String year,int r, int c) throws Exception {
		String con = cellTypeUtil.cellTypecommon(fileName, files, r, c);
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(con);
		con = m.replaceAll("");
		String tempDate = null;
		if(con.contains("(")) {
			String temp[] = con.split("\\(");
			String te[] = temp[1].split("\\)");
			tempDate = te[0];
		}else if(con.contains("（")) {
			String temp[] = con.split("\\（");
			String te[] = temp[1].split("\\）");
			tempDate = te[0];
		}else {
			tempDate = con;
		}
		Sheet sheet = sheetUtil.batchImport(fileName,files);
		Date date = null;
		String tempDateP = null;
		Date tempDateF = null;
		if(sheet.getRow(r).getCell(c) == null) {
			date = null;
		}else {
			int type = sheet.getRow(r).getCell(c).getCellType();
			if(type == Cell.CELL_TYPE_NUMERIC) {
				Date tempd = HSSFDateUtil.getJavaDate((Double)(sheet.getRow(r).getCell(c).getNumericCellValue()));
				tempDateP = new SimpleDateFormat(year+"-"+"MM-dd").format(tempd);
				date = new SimpleDateFormat("yyyy-MM-dd").parse(tempDateP);
			}else if(type == Cell.CELL_TYPE_BLANK){
				date = null;
			}else {
				//String tempDate = sheet.getRow(r).getCell(c).getStringCellValue();
				p = Pattern.compile("\\s*|\t|\r|\n");
				m = p.matcher(tempDate);
				tempDate = m.replaceAll("");
				if("月日".equals(tempDate)||"月曰".equals(tempDate)) {
					return null;
				}
				if(tempDate.contains("日")) {
					tempDateF = new SimpleDateFormat("MM月dd日").parse(tempDate);
				}else if(tempDate.contains("曰")){
					tempDateF = new SimpleDateFormat("MM月dd曰").parse(tempDate);
				}else {
					tempDateF = new SimpleDateFormat("MM月dd").parse(tempDate);
				}
				tempDateP = new SimpleDateFormat(year+"-"+"MM-dd").format(tempDateF);
				date = new SimpleDateFormat("yyyy-MM-dd").parse(tempDateP);
			}
		}
		return date;
	}
	
	/**
	 * 实测大断面类型的时间xxxx-x-x
	 */
	public Date cellTypeFromXsCommon(String fileName, MultipartFile files,int r, int c) throws Exception {
		Sheet sheet = sheetUtil.batchImport(fileName,files);
		Date date = null;
		Date tempd = null;
		String tempdP = null;
		String tempdStringP = null;
		Date tempdF = null;
		int type = sheet.getRow(r).getCell(c).getCellType();
		if(sheet.getRow(r).getCell(c) == null) {
			date = null;
		}else {
			if(type==Cell.CELL_TYPE_NUMERIC) {
				tempd = HSSFDateUtil.getJavaDate((Double)(sheet.getRow(r).getCell(c).getNumericCellValue()));
				tempdP = new SimpleDateFormat("yyyy-MM-dd").format(tempd);
				date = new SimpleDateFormat("yyyy-MM-dd").parse(tempdP);
			}else if(type == Cell.CELL_TYPE_BLANK){
				date = null;
			}else {
				tempdP = sheet.getRow(r).getCell(c).getStringCellValue();
				tempdF = new SimpleDateFormat("yyyy-MM-dd").parse(tempdP);
				tempdStringP= new SimpleDateFormat("yyyy-MM-dd").format(tempdF);
				date = new SimpleDateFormat("yyyy-MM-dd").parse(tempdStringP);
			}
		}
		return date;
	}
	
	/**
	 *各时段最大洪水总量统计x、x
	 */
	public Date cellTypeImxCommon(String fileName, MultipartFile files,String year,int r, int c) throws Exception {
		Sheet sheet = sheetUtil.batchImport(fileName,files);
		Date date = null;
		if(sheet.getRow(r).getCell(c) == null) {
			date = null;
		}else {
			String result = sheet.getRow(r).getCell(c).getStringCellValue();
			String M = null;
			String D = null;
			if(result.contains("、")) {
				String temp[] = result.split("\\、");
				M = temp[0];
				D = temp[temp.length-1];
			}else if(result.contains(", ")) {
				String temp[] = result.split("\\, ");
				M = temp[0];
				D = temp[temp.length-1];
			}else if(result.contains(". ")) {
				String temp[] = result.split("\\. ");
				M = temp[0];
				D = temp[temp.length-1];
			}
			else if(result.contains(".")) {
				String temp[] = result.split("\\.");
				M = temp[0];
				D = temp[temp.length-1];
			}
			String tempdate = year+"-"+M+"-"+D;
			date = new SimpleDateFormat("yyyy-MM-dd").parse(tempdate);
		}		
		return date;
	}
	/**
	 * 传入年份，月份，获取时间
	 * @param year
	 * @param i
	 * @return
	 * @throws ParseException
	 */
	public Date cellGetTime(String year,int i) throws ParseException {
		String tempdate = year+"-"+i+"-00";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tempdate);
		return date;
	}
	/**
	 * 传入年份，月，日，时，分，获取时间---若是时日为空，则填写 9999-M-D 00:00
	 * @param year
	 * @param M
	 * @param D
	 * @param TH
	 * @param TM
	 * @return
	 * @throws ParseException
	 */
	public Date cellGetMeasureTime(String year,String M,String D,String TH,String TM) throws ParseException {
		if(TH == null && TM == null) {
			year = "9999";
			TH = "00";
			TM = "00";
			
		}else if(TM == null) {
			TM = "00";
		}
		if(TH.contains(".")) {
			String temp[] = TH.split("\\.");
			TH = temp[0];
		}
		if(TM.contains(".")) {
			String temp[] = TM.split("\\.");
			TM = temp[0];
		}
		if("24".equals(TH)) {
			int d = Integer.parseInt(D)+1;
			D = String.valueOf(d);
			year = "9999";
		}
		String temp = year+"-"+M+"-"+D+" "+TH+":"+TM;
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(temp);
		return date;
	}
}
