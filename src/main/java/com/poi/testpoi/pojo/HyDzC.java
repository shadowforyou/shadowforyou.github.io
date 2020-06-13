package com.poi.testpoi.pojo;

import java.util.Date;

/**
 * 日水位表
 * @author Administrator
 *
 */
public class HyDzC {
	private String stcd;
	private Date dt;
	private String avz;
	private String avzrcd;
	public String getStcd() {
		return stcd;
	}
	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	
	public String getAvz() {
		return avz;
	}
	public void setAvz(String avz) {
		this.avz = avz;
	}
	public String getAvzrcd() {
		return avzrcd;
	}
	public void setAvzrcd(String avzrcd) {
		this.avzrcd = avzrcd;
	}
	
}
