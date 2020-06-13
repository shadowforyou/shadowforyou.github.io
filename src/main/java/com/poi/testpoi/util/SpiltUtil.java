package com.poi.testpoi.util;

public class SpiltUtil {
	/**
	 * 简单的按.分割，获取点之前的数据
	 * @param data
	 * @return
	 */
	public String splitCommon(String data) {
		String temp[] = data.split("\\.");
		return temp[0];
	}
}
