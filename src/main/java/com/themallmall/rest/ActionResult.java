package com.themallmall.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Edward P. Legaspi
 **/
@XmlRootElement(name = "ActionResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class ActionResult {
	private ActionResultStatus status;
	private String message;

	public ActionResult() {
		status = ActionResultStatus.SUCCESS;
	}

	public ActionResult(ActionResultStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public ActionResultStatus getStatus() {
		return status;
	}

	public void setStatus(ActionResultStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
