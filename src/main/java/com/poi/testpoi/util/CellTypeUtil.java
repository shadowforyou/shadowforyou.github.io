package com.poi.testpoi.util;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.multipart.MultipartFile;

/*
 * 单元格内容类型判定
 */
public class CellTypeUtil {
	SheetUtil sheetUtil = new SheetUtil();
	
	/**
	 * 一般情况下的cell判定，数字型可以直接转化成String类型
	 * @param r
	 * @param c
	 * @return
	 * @throws Exception 
	 */
	public String cellTypecommon(String fileName, MultipartFile files,int r,int c) throws Exception{
		Sheet sheet = sheetUtil.batchImport(fileName,files);
		String result = null;
		if(sheet.getRow(r).getCell(c) == null) {
			result = null;
		}else {
			int type = sheet.getRow(r).getCell(c).getCellType();
			if(type == Cell.CELL_TYPE_NUMERIC) {
				result = String.valueOf(sheet.getRow(r).getCell(c).getNumericCellValue());
			}else if(type == Cell.CELL_TYPE_BLANK){
				result = null;
			}else if(type == Cell.CELL_TYPE_BOOLEAN){
				result = String.valueOf(sheet.getRow(r).getCell(c).getBooleanCellValue());
			}else {
				result = sheet.getRow(r).getCell(c).getStringCellValue();
				Pattern p = Pattern.compile("\\s*|\t|\r|\n");
				Matcher m = p.matcher(result);
				result = m.replaceAll("");
			}
		}
		
		return result;
	}
	
	/**
	 * 有的单元格用valueOf识别有问题，所以用fromat来判定
	 * @throws Exception 
	 */
	public String cellTypeSpecile(String fileName, MultipartFile files,int r,int c) throws Exception {
		Sheet sheet = sheetUtil.batchImport(fileName,files);
		String result = null;
		DecimalFormat df = new DecimalFormat("0");
		if(sheet.getRow(r).getCell(c) == null) {
			result = null;
		}else {
			int type = sheet.getRow(r).getCell(c).getCellType();
			if(type == Cell.CELL_TYPE_NUMERIC) {
				result = df.format(sheet.getRow(r).getCell(c).getNumericCellValue());
			}else if(type == Cell.CELL_TYPE_BLANK){
				result = null;
			}else {
				result = sheet.getRow(r).getCell(c).getStringCellValue();
				Pattern p = Pattern.compile("\\s*|\t|\r|\n");
				Matcher m = p.matcher(result);
				result = m.replaceAll("");
			}
		}
		
		return result;
	}
	
	/**
	 * 站码用valueOf识别有问题，所以用fromat来判定
	 * @throws Exception 
	 */
	public String cellTypeStcd(String fileName, MultipartFile files,int r,int c) throws Exception {
		Sheet sheet = sheetUtil.batchImport(fileName,files);
		String result = null;
		DecimalFormat df = new DecimalFormat("0");
		
		if(sheet.getRow(r).getCell(c) == null) {
			result = null;
		}else {
			int type = sheet.getRow(r).getCell(c).getCellType();
			if(type == Cell.CELL_TYPE_NUMERIC) {
				result = df.format(sheet.getRow(r).getCell(c).getNumericCellValue());
			}else if(type == Cell.CELL_TYPE_BLANK){
				result = null;
			}else {
				result = sheet.getRow(r).getCell(c).getStringCellValue();
			}
		}
		
		return result;
	}
	
	/**
	 * 年份
	 * @throws Exception 
	 */
	public String cellTypeYear(String fileName, MultipartFile files,int r,int c) throws Exception {
		Sheet sheet = sheetUtil.batchImport(fileName,files);
		String tempyear = null;
		int type = sheet.getRow(r).getCell(c).getCellType();
		if(type == Cell.CELL_TYPE_NUMERIC) {
			tempyear = ((Double)sheet.getRow(r).getCell(c).getNumericCellValue()).toString();
		}else {
			tempyear =sheet.getRow(r).getCell(c).getStringCellValue();
		}
		String tempYear[] = tempyear.split("\\.");
		String year = tempYear[0];		
		return year;
	}
	
}
