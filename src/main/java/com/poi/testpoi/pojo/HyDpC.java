package com.poi.testpoi.pojo;

import java.util.Date;

/**
 * 
 * 逐日降水量-日降水量表
 * @author ASUS
 *
 */
public class HyDpC {
	private String stcd;
	private Date dt;
	private String year;
	private String p;
	private String prcd;
	
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
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	public String getPrcd() {
		return prcd;
	}
	public void setPrcd(String prcd) {
		this.prcd = prcd;
	}
	
}
