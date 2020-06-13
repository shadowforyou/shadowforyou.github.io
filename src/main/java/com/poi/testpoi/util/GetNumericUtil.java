package com.poi.testpoi.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GetNumericUtil{
	/**
	 * 获取数字
	 * @return
	 * @throws Exception 
	 */
	
	public String GetNumeric(String result) throws Exception {
		String tempData = null;
		if(result == null || "".equals(result)) {
			return result;
		}else{
			// 控制正则表达式的匹配行为的参数(小数)
			Pattern pa = Pattern.compile("(\\d+\\.\\d+)");
			// Matcher类的构造方法也是私有的,不能随意创建,只能通过Pattern.matcher(CharSequence input)方法得到该类的实例.
			Matcher m = pa.matcher(result);
			// m.find用来判断该字符串中是否含有与"(\\d+\\.\\d+)"相匹配的子串
			if (m.find()) {
				// 如果有相匹配的,则判断是否为null操作
				// group()中的参数：0表示匹配整个正则，1表示匹配第一个括号的正则,2表示匹配第二个正则,在这只有一个括号,即1和0是一样的
				tempData = m.group(1) == null ? "" : m.group(1);
			} else {
				// 如果匹配不到小数，就进行整数匹配
				pa = Pattern.compile("(\\d+)");
				m = pa.matcher(result);
				if (m.find()) {
					// 如果有整数相匹配
					tempData = m.group(1) == null ? "" : m.group(1);
				} else {
					// 如果没有小数和整数相匹配,即字符串中没有整数和小数，就设为空
					tempData = null;
				}
			}
			if(tempData == null || "".equals(tempData)) {
				result = null;
			}else {
				String temp[] = result.split(tempData);
				if (temp.length - 1 < 0) {
				}else if(temp[0].contains("-")){
					result = "-"+tempData;
					return result;
				}
			}
		}
		return tempData;
	}
	
	
	/**
	 * 获取数字符号
	 * @return
	 * @throws Exception 
	 */
	public String GetNumericSymbol(String result) throws Exception {
		String tempData = null;
		String tempDataString = null;
		if(result == null || "".equals(result)) {
			return result;
		}else{
			// 控制正则表达式的匹配行为的参数(小数)
			Pattern pa = Pattern.compile("(\\d+\\.\\d+)");
			// Matcher类的构造方法也是私有的,不能随意创建,只能通过Pattern.matcher(CharSequence input)方法得到该类的实例.
			Matcher m = pa.matcher(result);
			// m.find用来判断该字符串中是否含有与"(\\d+\\.\\d+)"相匹配的子串
			if (m.find()) {
				// 如果有相匹配的,则判断是否为null操作
				// group()中的参数：0表示匹配整个正则，1表示匹配第一个括号的正则,2表示匹配第二个正则,在这只有一个括号,即1和0是一样的
				tempData = m.group(1) == null ? "" : m.group(1);
			} else {
				// 如果匹配不到小数，就进行整数匹配
				pa = Pattern.compile("(\\d+)");
				m = pa.matcher(result);
				if (m.find()) {
					// 如果有整数相匹配
					tempData = m.group(1) == null ? "" : m.group(1);
				} else {
					// 如果没有小数和整数相匹配,即字符串中没有整数和小数，就设为空
					tempDataString = result;
				}
			}
			if (tempDataString == null || "".equals(tempDataString)) {
				String temp[] = result.split(tempData);
				if (temp.length - 1 < 0) {
					result = null;
				}else if(temp[0].contains("-") && temp[0].length()>2){
					String tempsy[] = temp[0].split("-");
					result = tempsy[0]+temp[1];
				}else if(temp.length>1){
					String prcd = temp[0] + temp[1];
					result = prcd;
				}else if(temp.length == 1 && !(temp[0].contains("-"))) {
					result = temp[0];
				}else {
					result = null;
				}
			} else {
				result = tempDataString;
			}
		}
		return result;
	}
	
	
}
