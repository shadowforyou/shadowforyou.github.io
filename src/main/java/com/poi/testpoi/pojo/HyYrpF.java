package com.poi.testpoi.pojo;

/**
 * 逐日降水量-年降水量
 * @author ASUS
 *
 */
public class HyYrpF {
	private String stcd;
	private String year;
	private String p;
	private String prcd;
	private String pdynum;//降水日数
	private String pdynuumrcd;//PDYNUMRCD
	public String getPrcd() {
		return prcd;
	}
	public void setPrcd(String prcd) {
		this.prcd = prcd;
	}
	public String getPdynuumrcd() {
		return pdynuumrcd;
	}
	public void setPdynuumrcd(String pdynuumrcd) {
		this.pdynuumrcd = pdynuumrcd;
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
}
