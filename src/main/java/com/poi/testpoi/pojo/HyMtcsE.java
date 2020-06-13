package com.poi.testpoi.pojo;

import java.util.Date;

/**
 * 月含沙量表
 * @author Administrator
 *
 */
public class HyMtcsE {
	private String stcd;
	private String year;
	private int mth;
	private String avcs;//平均含沙量
	private String avcsrcd;
	private String mxs;//最大含沙量
	private String mxsrcd;
	private Date mxsdt;//最大含沙量日期
	private String mns;//最小含沙量
	private String mnsrcd;
	private Date mnsdt;//最小含沙量日期
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
	public int getMth() {
		return mth;
	}
	public void setMth(int mth) {
		this.mth = mth;
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
	public String getMxs() {
		return mxs;
	}
	public void setMxs(String mxs) {
		this.mxs = mxs;
	}
	public String getMxsrcd() {
		return mxsrcd;
	}
	public void setMxsrcd(String mxsrcd) {
		this.mxsrcd = mxsrcd;
	}
	
	public String getMns() {
		return mns;
	}
	public void setMns(String mns) {
		this.mns = mns;
	}
	public String getMnsrcd() {
		return mnsrcd;
	}
	public void setMnsrcd(String mnsrcd) {
		this.mnsrcd = mnsrcd;
	}
	public Date getMxsdt() {
		return mxsdt;
	}
	public void setMxsdt(Date mxsdt) {
		this.mxsdt = mxsdt;
	}
	public Date getMnsdt() {
		return mnsdt;
	}
	public void setMnsdt(Date mnsdt) {
		this.mnsdt = mnsdt;
	}
	
	
}
