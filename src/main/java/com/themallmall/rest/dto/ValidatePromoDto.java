package com.themallmall.rest.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Edward P. Legaspi
 **/
@XmlRootElement(name = "ValidatePromo")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValidatePromoDto {
	private Long userId;
	private String code;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "ValidatePromoDto [userId=" + userId + ", code=" + code + "]";
	}
}
