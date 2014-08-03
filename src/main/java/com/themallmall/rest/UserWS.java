package com.themallmall.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.themallmall.rest.api.UserApi;
import com.themallmall.rest.dto.FindUserDto;
import com.themallmall.rest.logging.LoggingInterceptor;

@Path("/user")
@RequestScoped
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Interceptors({ LoggingInterceptor.class })
public class UserWS {

	@Inject
	private UserApi userApi;

	@POST
	@Path("/login")
	public ActionResult find(FindUserDto findUserDto) {
		ActionResult result = new ActionResult();

		try {
			result.setMessage(""
					+ userApi.findUser(findUserDto.getUsername(),
							findUserDto.getPassword()));
		} catch (RestApiException e) {
			result.setStatus(ActionResultStatus.FAIL);
			result.setMessage(e.getMessage());
		}

		return result;
	}
}
