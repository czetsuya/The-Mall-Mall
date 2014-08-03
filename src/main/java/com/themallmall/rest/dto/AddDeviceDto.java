package com.themallmall.rest.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AddDeviceDto")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddDeviceDto {
	private String deviceToken;
	private String userId;

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AddDeviceDto [deviceToken=" + deviceToken + ", userId="
				+ userId + "]";
	}

}
