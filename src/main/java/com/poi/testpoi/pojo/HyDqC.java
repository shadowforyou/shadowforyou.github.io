package com.poi.testpoi.pojo;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 逐日平均流量表-日平均流量表
 * @author ASUS
 *
 */
public class HyDqC {
	private String stcd;
	private Date dt;
	private BigDecimal avq;
	private String avqrcd;
	public String getStcd() {
		return stcd;
	}
	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public BigDecimal getAvq() {
		return avq;
	}
	public void setAvq(BigDecimal avq) {
		this.avq = avq;
	}
	public String getAvqrcd() {
		return avqrcd;
	}
	public void setAvqrcd(String avqrcd) {
		this.avqrcd = avqrcd;
	}
}
