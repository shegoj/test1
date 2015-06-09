package com.rso.common.dao;


import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



/**
 * 
 * @author UC186742
 *
 */
@Entity
@Table(name = "APP_STATUS", catalog = "capacityplanning")

public class ApplicationStatusDao implements Serializable  {
	
	private Integer Id;
	private String serverName;
	private String app;
	private String status;
	private String env;
	
	@Column(name = "server", unique = true, nullable = false, length = 12)
	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	@Column(name = "app", unique = true, nullable = false, length = 12)
	public String getApp() {
		return app;
	}


	public void setApp(String app) {
		this.app = app;
	}
	
	@Column(name = "status", nullable = true, length = 20)
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	public void setEnv(String env) {
		this.env = env;
	}
	
	@Column(name = "env", nullable = true, length = 20)
	public String getEnv() {
		return env;
	}



	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.Id;
	}
	
	public void setId(Integer Id) {
		this.Id = Id;
	}

}
