package com.poi.testpoi.pojo;

import java.util.Date;

/**
 * 各时段最大降水量表（一）
 * @author Administrator
 *
 */
public class HyMmxpF {
	private String stcd;
	private String year;
	private Date bgtm;
	private String mxpdr;//最大降水量时段长
	private String mxp;//最大降水量
	private String mxprc;//最大降水量注解码
	public String getStcd() {
		return stcd;
	}
	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Date getBgtm() {
		return bgtm;
	}
	public void setBgtm(Date bgtm) {
		this.bgtm = bgtm;
	}
	public String getMxpdr() {
		return mxpdr;
	}
	public void setMxpdr(String mxpdr) {
		this.mxpdr = mxpdr;
	}
	public String getMxp() {
		return mxp;
	}
	public void setMxp(String mxp) {
		this.mxp = mxp;
	}
	public String getMxprc() {
		return mxprc;
	}
	public void setMxprc(String mxprc) {
		this.mxprc = mxprc;
	}
	
}
