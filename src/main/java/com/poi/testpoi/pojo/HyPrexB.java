package com.poi.testpoi.pojo;

import java.util.Date;

/**
 * 降水量摘录表
 * @author Administrator
 *
 */
public class HyPrexB {
	private String stcd;
	private Date bgtm;
	private Date endtm;
	private String p;
	private String M;
	private String D;
	public String getM() {
		return M;
	}
	public void setM(String m) {
		M = m;
	}
	public String getD() {
		return D;
	}
	public void setD(String d) {
		D = d;
	}
	public String getStcd() {
		return stcd;
	}
	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	public Date getBgtm() {
		return bgtm;
	}
	public void setBgtm(Date bgtm) {
		this.bgtm = bgtm;
	}
	public Date getEndtm() {
		return endtm;
	}
	public void setEndtm(Date endtm) {
		this.endtm = endtm;
	}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	
}
