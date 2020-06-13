package com.poi.testpoi.pojo;

import java.util.Date;

/**
 * 日平均含沙量表
 * @author Administrator
 *
 */
public class HyDcsC {
	private String stcd;
	private Date dt;
	private String avcs;//平均含沙量
	private String avcsrcd;
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
	public String getAvcs() {
		return avcs;
	}
	public void setAvcs(String avcs) {
		this.avcs = avcs;
	}
	public String getAvcsrcd() {
		return avcsrcd;
	}
	public void setAvcsrcd(String avcsrcd) {
		this.avcsrcd = avcsrcd;
	}
	
	
}
