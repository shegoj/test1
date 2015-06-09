package com.rso.common.dao;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "CPU_MEMORY_INFO", catalog = "capacityplanning")
public class CpuMemoryDao  implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer Id;
	private String serverName;
	private double val;
	private double totalVal;
	private String resource_type;
	private int monthNum;
	private String month;
	private int year;
	
	@Column(name = "servername", unique = true, nullable = false, length = 12)
	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	
	@Column(name = "resource_type", nullable = true, length = 20)
	public String getResource_type() {
		return resource_type;
	}

	public void setResource_type (String resource_type) {
		this.resource_type = resource_type;
	}
	
	@Column(name = "value_", nullable = true,  columnDefinition="Decimal(5,2)")
	public double getVal() {
		return val;
	}

	public void setVal (double val) {
		this.val = val;
	}
	
	@Column(name = "TotalVal", nullable = true,  columnDefinition="Decimal(5,2)")
	public double getTotalVal() {
		return totalVal;
	}

	public void setTotalVal (double totalVal) {
		this.totalVal = totalVal;
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
