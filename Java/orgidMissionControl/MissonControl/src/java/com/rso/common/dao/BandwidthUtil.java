package com.rso.common.dao;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "BANDWIDTHUTIL", catalog = "capacityplanning")

public class BandwidthUtil implements java.io.Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String serverGroup;
	private String serverName;
	private int year;
	private String month;
	private int monthNum;
	private double requests;
	private double bandwithUsed;
	private double avg; 
	private Integer id;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "serverGroup", nullable = true, length = 20)
	public String getServerGroup() {
		return serverGroup;
	}
	public void setServerGroup(String serverGroup) {
		this.serverGroup = serverGroup;
	}
	
	@Column(name = "servername", nullable = true, length = 20)
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		
		this.serverName = serverName;
	}
	
	@Column(name = "year", nullable = true, length = 20)
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
	
	@Column(name = "month_num", nullable = true,  columnDefinition="Decimal(10,0)")
	public int getMonthNum() {
		return monthNum;
	}
	public void setMonthNum(int monthNum) {
		this.monthNum = monthNum;
	}
	
	@Column(name = "requests", nullable = true, columnDefinition="Decimal(10,0)")
	public double getRequests() {
		return requests;
	}
	public void setRequests(double requests) {
		this.requests = requests;
	}
	
	@Column(name = "bandwithUsed", nullable = true, columnDefinition="Decimal(10,2)")
	public double getBandwithUsed() {
		return bandwithUsed;
	}
	public void setBandwithUsed(double bandwithUsed) {
		this.bandwithUsed = bandwithUsed;
	}
	
	@Column(name = "Avg", nullable = true, columnDefinition="Decimal(10,2)")
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
}
