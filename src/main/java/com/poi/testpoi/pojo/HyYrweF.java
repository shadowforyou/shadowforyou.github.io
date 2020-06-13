package com.poi.testpoi.pojo;

import java.util.Date;

/**
    * 年水面蒸发量表
 * @author Administrator
 *
 */

public class HyYrweF {
	private String stcd;
	private String eetp;//蒸发器型式
	private String year;
	private String wsfe;//水面蒸发量
	private String wsfercd;//水面蒸发量注解码
	private String mxdye;//最大水面蒸发量
	private String mxdyercd;//最大水面蒸发量注解码
	private Date mxdyeodt;//最大水面蒸发量出现日期
	private String mndye;//最小水面蒸发量
	private String mndyercd;//最小水面蒸发量注解码
	private Date mndyeodt;//最小水面蒸发量出现日期
	private Date idsdt;//终冰日期
	private Date icapd;//初冰日期
	private String eslcch;//蒸发场位置特征
	private String nt;//附注
	
	
	public String getMxdyercd() {
		return mxdyercd;
	}
	public void setMxdyercd(String mxdyercd) {
		this.mxdyercd = mxdyercd;
	}
	public String getMndyercd() {
		return mndyercd;
	}
	public void setMndyercd(String mndyercd) {
		this.mndyercd = mndyercd;
	}
	public String getWsfercd() {
		return wsfercd;
	}
	public void setWsfercd(String wsfercd) {
		this.wsfercd = wsfercd;
	}
	public String getNt() {
		return nt;
	}
	public void setNt(String nt) {
		this.nt = nt;
	}
	public String getStcd() {
		return stcd;
	}
	public Date getMxdyeodt() {
		return mxdyeodt;
	}
	public void setMxdyeodt(Date mxdyeodt) {
		this.mxdyeodt = mxdyeodt;
	}
	public Date getMndyeodt() {
		return mndyeodt;
	}
	public void setMndyeodt(Date mndyeodt) {
		this.mndyeodt = mndyeodt;
	}
	public Date getIdsdt() {
		return idsdt;
	}
	public void setIdsdt(Date idsdt) {
		this.idsdt = idsdt;
	}
	public Date getIcapd() {
		return icapd;
	}
	public void setIcapd(Date icapd) {
		this.icapd = icapd;
	}
	public String getEslcch() {
		return eslcch;
	}
	public void setEslcch(String eslcch) {
		this.eslcch = eslcch;
	}
	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	public String getEetp() {
		return eetp;
	}
	public void setEetp(String eetp) {
		this.eetp = eetp;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getWsfe() {
		return wsfe;
	}
	public void setWsfe(String wsfe) {
		this.wsfe = wsfe;
	}
	public String getMxdye() {
		return mxdye;
	}
	public void setMxdye(String mxdye) {
		this.mxdye = mxdye;
	}
	public String getMndye() {
		return mndye;
	}
	public void setMndye(String mndye) {
		this.mndye = mndye;
	}
	
}
