package com.poi.testpoi.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 逐日平均流量表-年流量表
 * @author ASUS
 *
 */
public class HyYrqF {
	private String stcd;
	private String year;
	private String avq;
	private String avqrcd;//AVQRCD;
	public String getAvqrcd() {
		return avqrcd;
	}
	public void setAvqrcd(String avqrcd) {
		this.avqrcd = avqrcd;
	}
	private String mxq;
	private String mxqrcd;//MXQRCD;
	private Date mxqdt;
	private BigDecimal mnq;
	private String mnqrcd;//MNQRCD;
	private Date mnqdt;
	private BigDecimal rw;
	private String rwrcd;//RWRCD;
	private String rm;
	private String rd;
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
	public String getRwrcd() {
		return rwrcd;
	}
	public void setRwrcd(String rwrcd) {
		this.rwrcd = rwrcd;
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
	public String getAvq() {
		return avq;
	}
	public void setAvq(String avq) {
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
	public BigDecimal getRw() {
		return rw;
	}
	public void setRw(BigDecimal rw) {
		this.rw = rw;
	}
	public String getRm() {
		return rm;
	}
	public void setRm(String rm) {
		this.rm = rm;
	}
	public String getRd() {
		return rd;
	}
	public void setRd(String rd) {
		this.rd = rd;
	}
}
