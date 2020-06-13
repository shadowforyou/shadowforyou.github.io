package com.poi.testpoi.pojo;

import java.util.Date;

/**
 * 日平均输沙率表
 * @author Administrator
 *
 */
public class HyDqsC {
	private String stcd;
	private String sdtp;//泥沙类型（悬移质）
	private Date dt;
	private String avqs;
	private String avqsrcd;
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
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
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
	
}
