package com.rso.common.dao;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "STORAGE_INFO", catalog = "capacityplanning")
public class StorageInfo implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer Id;
	private String serverName;
	private double currentVal;
	private double totalVal;
	private String mount;
	private int monthNum;
	
	@Column(name = "servername", unique = true, nullable = false, length = 12)
	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	
	@Column(name = "mount", nullable = true, length = 20)
	public String getMount() {
		return mount;
	}

	public void setMount (String mount) {
		this.mount = mount;
	}
	
	@Column(name = "currentVal", nullable = true,  columnDefinition="Decimal(5,2)")
	public double getCurrentVal() {
		return currentVal;
	}

	public void setCurrentVal (double currentVal) {
		this.currentVal = currentVal;
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
