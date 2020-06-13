package com.poi.testpoi.pojo;

import java.util.Date;

/**
 * 月输沙率表
 * @author Administrator
 *
 */
public class HyMtqsE {
	private String stcd;
	private String sdtp;//泥沙类型（悬移质）
	private String year;
	private int mth;
	private String avqs;
	private String avqsrcd;
	private String mxdyqs;//最大日平均输沙率
	private String mxdyqsrcd;//最大日平均输沙率注解码
	private Date mxdyqsodt;//最大日平均输沙率出现日期 
	public String getStcd() {
		return stcd;
	}
	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	public String getSdtp() {
		return sdtp;
	}
	public void setSdtp(String sdtp) {
		this.sdtp = sdtp;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getMth() {
		return mth;
	}
	public void setMth(int mth) {
		this.mth = mth;
	}
	public String getAvqs() {
		return avqs;
	}
	public void setAvqs(String avqs) {
		this.avqs = avqs;
	}
	public String getAvqsrcd() {
		return avqsrcd;
	}
	public void setAvqsrcd(String avqsrcd) {
		this.avqsrcd = avqsrcd;
	}
	public String getMxdyqs() {
		return mxdyqs;
	}
	public void setMxdyqs(String mxdyqs) {
		this.mxdyqs = mxdyqs;
	}
	public String getMxdyqsrcd() {
		return mxdyqsrcd;
	}
	public void setMxdyqsrcd(String mxdyqsrcd) {
		this.mxdyqsrcd = mxdyqsrcd;
	}
	public Date getMxdyqsodt() {
		return mxdyqsodt;
	}
	public void setMxdyqsodt(Date mxdyqsodt) {
		this.mxdyqsodt = mxdyqsodt;
	}
	
	
}
