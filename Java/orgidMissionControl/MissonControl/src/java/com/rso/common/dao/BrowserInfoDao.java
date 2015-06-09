package com.rso.common.dao;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "BroswerInfo", catalog = "capacityplanning")
public class BrowserInfoDao implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer Id;
	private String broswerName;
	private int year;
	private double value_;
	private String month;
	private int monthNum;
	
	@Column(name = "broswername",  length = 20)
	public String getBrowserName() {
		return broswerName;
	}

	public void setBrowserName(String broswerName) {
		this.broswerName = broswerName;
	}
	
	
	@Column(name = "value_", nullable = true,  columnDefinition="Decimal(5,2)")
	public double getValue() {
		return value_;
	}

	public void setValue (double value_) {
		this.value_ = value_;
	}
	
	@Column(name = "month_num", nullable = true,  columnDefinition="Decimal(10,0)")
	public int getMonthNum() {
		return monthNum;
	}
	public void setMonthNum(int monthNum) {
		this.monthNum = monthNum;
	}
	
	@Column(name = "year", nullable = true,  columnDefinition="Decimal(10,0)")
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	@Column(name = "month", nullable = true, length = 20)
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.Id;
	}
	
	public void setId(Integer Id) 
	{
		this.Id = Id;
	}

}
