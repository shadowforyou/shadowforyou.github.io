package com.poi.testpoi.pojo;

import java.util.Date;

/**
 * 各时段最大洪水总量统计表
 * @author ASUS
 *
 */
public class HyImxfwF {
	private String stcd;
	private String year;
	private Date bgdt;
	private String mxwdr;//MXWDR
	private Float mxw;
	private String rname;
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
	public String getMxwdr() {
		return mxwdr;
	}
	public void setMxwdr(String mxwdr) {
		this.mxwdr = mxwdr;
	}
	public Float getMxw() {
		return mxw;
	}
	public void setMxw(Float mxw) {
		this.mxw = mxw;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
}
