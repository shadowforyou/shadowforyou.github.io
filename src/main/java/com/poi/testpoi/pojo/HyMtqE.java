package com.poi.testpoi.pojo;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 逐日平均流量表-月流量表
 * @author ASUS
 *
 */
public class HyMtqE {
	private String stcd;
	private String year;
	private int mth;
	private BigDecimal avq;
	private String avqrcd;
	private String mxq;
	private String mxqrcd;
	private Date mxqdt;
	private BigDecimal mnq;
	private String mnqrcd;
	private Date mnqdt;
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
	public BigDecimal getAvq() {
		return avq;
	}
	public void setAvq(BigDecimal avq) {
		this.avq = avq;
	}
	public String getMxq() {
		return mxq;
	}
	public void setMxq(String mxq) {
		this.mxq = mxq;
	}
	public Date getMxqdt() {
		return mxqdt;
	}
	public void setMxqdt(Date mxqdt) {
		this.mxqdt = mxqdt;
	}
	public BigDecimal getMnq() {
		return mnq;
	}
	public void setMnq(BigDecimal mnq) {
		this.mnq = mnq;
	}
	public Date getMnqdt() {
		return mnqdt;
	}
	public void setMnqdt(Date mnqdt) {
		this.mnqdt = mnqdt;
	}
	public String getMxqrcd() {
		return mxqrcd;
	}
	public void setMxqrcd(String mxqrcd) {
		this.mxqrcd = mxqrcd;
	}
	public String getMnqrcd() {
		return mnqrcd;
	}
	public void setMnqrcd(String mnqrcd) {
		this.mnqrcd = mnqrcd;
	}
	public String getAvqrcd() {
		return avqrcd;
	}
	public void setAvqrcd(String avqrcd) {
		this.avqrcd = avqrcd;
	}
}
