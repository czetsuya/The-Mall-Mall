package com.themallmall.rest.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.themallmall.model.crm.PromoCode;

/**
 * @author Edward P. Legaspi
 **/
@XmlRootElement(name = "PromoCodeList")
@XmlAccessorType(XmlAccessType.FIELD)
public class PromoCodeListDto {
	private List<PromoCode> promoCodes = new ArrayList<PromoCode>();

	public List<PromoCode> getPromoCodes() {
		return promoCodes;
	}

	public void setPromoCodes(List<PromoCode> promoCodes) {
		this.promoCodes = promoCodes;
	}

	@Override
	public String toString() {
		return "PromoCodeListDto [promoCodes=" + promoCodes + "]";
	}
}
