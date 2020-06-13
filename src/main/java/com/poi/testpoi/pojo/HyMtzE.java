package com.poi.testpoi.pojo;

import java.util.Date;

/**
 * 月水位表
 * @author Administrator
 *
 */
public class HyMtzE {
	private String stcd;
	private String year;
	private int mth;
	private String avz;//平均水位
	private String avzrcd;
	private String htz;//最高水位
	private String htzrcd;
	private Date htzdt;//最高水位日期
	private String mnz;//最低水位
	private String mnzrcd;
	private Date mnzdt;//最低水位日期
	
	
	public String getHtzrcd() {
		return htzrcd;
	}
	public void setHtzrcd(String htzrcd) {
		this.htzrcd = htzrcd;
	}
	public String getMnzrcd() {
		return mnzrcd;
	}
	public void setMnzrcd(String mnzrcd) {
		this.mnzrcd = mnzrcd;
	}
	public String getAvzrcd() {
		return avzrcd;
	}
	public void setAvzrcd(String avzrcd) {
		this.avzrcd = avzrcd;
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
	public String getAvz() {
		return avz;
	}
	public void setAvz(String avz) {
		this.avz = avz;
	}
	public String getHtz() {
		return htz;
	}
	public void setHtz(String htz) {
		this.htz = htz;
	}
	public Date getHtzdt() {
		return htzdt;
	}
	public void setHtzdt(Date htzdt) {
		this.htzdt = htzdt;
	}
	public String getMnz() {
		return mnz;
	}
	public void setMnz(String mnz) {
		this.mnz = mnz;
	}
	public Date getMnzdt() {
		return mnzdt;
	}
	public void setMnzdt(Date mnzdt) {
		this.mnzdt = mnzdt;
	}

	
}
