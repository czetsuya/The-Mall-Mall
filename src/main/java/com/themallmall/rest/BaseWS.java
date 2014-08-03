package com.themallmall.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author Edward P. Legaspi
 **/
public abstract class BaseWS {

	@GET
	@Path("/version")
	public ActionResult index() {
		ActionResult result = new ActionResult(ActionResultStatus.SUCCESS,
				"The Mall Mall API Rest Web Service V1.0");

		return result;
	}

}
