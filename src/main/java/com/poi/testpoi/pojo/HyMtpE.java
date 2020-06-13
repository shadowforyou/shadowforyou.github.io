package com.poi.testpoi.pojo;

import java.util.Date;

/**
 * 逐日降水量-月降水量表
 * @author ASUS
 *
 */
public class HyMtpE {
	private String stcd;
	private String year;
	private int mth;
	private String p;//降水量
	private String prcd;
	private String pdynum;//降水日数
	private String pdynumrcd;
	private String mxdyp;//最大降水量
	private String mxdyprcd;
	private Date mxdypodt;//出现日期
	private Date beginTime;
	private Date endTime;
	public String getPrcd() {
		return prcd;
	}
	public void setPrcd(String prcd) {
		this.prcd = prcd;
	}
	public String getPdynumrcd() {
		return pdynumrcd;
	}
	public void setPdynumrcd(String pdynumrcd) {
		this.pdynumrcd = pdynumrcd;
	}
	public String getMxdyprcd() {
		return mxdyprcd;
	}
	public void setMxdyprcd(String mxdyprcd) {
		this.mxdyprcd = mxdyprcd;
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
	public int getMth() {
		return mth;
	}
	public void setMth(int mth) {
		this.mth = mth;
	}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	public String getPdynum() {
		return pdynum;
	}
	public void setPdynum(String pdynum) {
		this.pdynum = pdynum;
	}
	public String getMxdyp() {
		return mxdyp;
	}
	public void setMxdyp(String mxdyp) {
		this.mxdyp = mxdyp;
	}
	public Date getMxdypodt() {
		return mxdypodt;
	}
	public void setMxdypodt(Date mxdypodt) {
		this.mxdypodt = mxdypodt;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
