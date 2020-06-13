package com.poi.testpoi.pojo;

import java.util.Date;

/**
 * 逐日降水量—日时段最大降水量表
 * @author ASUS
 *
 */
public class HyDmxpF {
	private String stcd;
	private String year;
	private Date bgdt;//起始日期
	private String mxpdr;//最大降水量时段长
	private String mxp;//最大降水量
	private String mxprc;
	
	public String getMxprc() {
		return mxprc;
	}
	public void setMxprc(String mxprc) {
		this.mxprc = mxprc;
	}
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
	public Date getBgdt() {
		return bgdt;
	}
	public void setBgdt(Date bgdt) {
		this.bgdt = bgdt;
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
}
