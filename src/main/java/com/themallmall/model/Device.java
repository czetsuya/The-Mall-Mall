package com.themallmall.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.themallmall.model.crm.User;

@Entity
@Table(name = "DEVICE")
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "CRM_ORDER_SEQ")
public class Device extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "DEVICE_TOKEN", unique = true)
	private String deviceToken;

	@JoinColumn(name = "USER_ID")
	@OneToOne
	private User user;

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
