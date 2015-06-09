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
@Table(name = "Servers", catalog = "capacityplanning", uniqueConstraints = {
		@UniqueConstraint(columnNames = "SERVER_ID"),
		@UniqueConstraint(columnNames = "SERVER_NAME") })

public class ServerDao implements Serializable  {
	
	private Integer Id;
	private String serverName;
	private String ServerId;
	private String groupName;
	
	@Column(name = "SERVER_NAME", unique = true, nullable = false, length = 12)
	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	@Column(name = "SERVER_ID", unique = true, nullable = false, length = 12)
	public String getServerId() {
		return ServerId;
	}


	public void setServerId(String serverId) {
		ServerId = serverId;
	}
	
	@Column(name = "GROUP_NAME", nullable = true, length = 20)
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
