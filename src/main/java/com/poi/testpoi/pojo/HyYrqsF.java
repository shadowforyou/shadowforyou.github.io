package com.poi.testpoi.pojo;

import java.util.Date;

/**
 * 年输沙率表
 * @author Administrator
 *
 */
public class HyYrqsF {
	private String stcd;
	private String sdtp;//泥沙类型（悬移质）
	private String year;
	private String avqs;
	private String avqsrcd;
	private String mxdyqs;//最大日平均输沙率
	private String mxdyqsrcd;//最大日平均输沙率注解码
	private Date mxdyqsodt;//最大日平均输沙率出现日期 
	private String sw;//输沙量
	private String swrc;//输沙量注解码
	private String sm;//输沙模数
	private String nt;
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
	public String getSw() {
		return sw;
	}
	public void setSw(String sw) {
		this.sw = sw;
	}
	public String getSwrc() {
		return swrc;
	}
	public void setSwrc(String swrc) {
		this.swrc = swrc;
	}
	public String getSm() {
		return sm;
	}
	public void setSm(String sm) {
		this.sm = sm;
	}
	public String getNt() {
		return nt;
	}
	public void setNt(String nt) {
		this.nt = nt;
	}
	
}
