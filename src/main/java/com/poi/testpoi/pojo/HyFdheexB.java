package com.poi.testpoi.pojo;

import java.util.Date;

/**
 * 洪水水文要素摘录表
 * @author ASUS
 *
 */
public class HyFdheexB {
	private String stcd;
	private Date tm;
	private String z;
	private String zrdc;
	public String getZrdc() {
		return zrdc;
	}
	public void setZrdc(String zrdc) {
		this.zrdc = zrdc;
	}
	private String q;
	private String s;
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
	public Date getTm() {
		return tm;
	}
	public void setTm(Date tm) {
		this.tm = tm;
	}
	public String getZ() {
		return z;
	}
	public void setZ(String z) {
		this.z = z;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
}
