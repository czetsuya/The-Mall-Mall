package com.themallmall.rest;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import com.themallmall.rest.api.DeviceApi;
import com.themallmall.rest.dto.AddDeviceDto;
import com.themallmall.rest.logging.LoggingInterceptor;

@Path("/device")
@RequestScoped
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Interceptors({ LoggingInterceptor.class })
public class DeviceWS extends BaseWS {

	@Inject
	private DeviceApi deviceApi;

	@POST
	@Path(value = "/add")
	public ActionResult addDevice(AddDeviceDto addDeviceDto) {
		ActionResult result = new ActionResult();

		if (addDeviceDto.getUserId() != null
				&& addDeviceDto.getDeviceToken() != null) {
			deviceApi.addDevice(addDeviceDto.getDeviceToken(),
					addDeviceDto.getUserId());
		} else {
			StringBuilder sb = new StringBuilder(
					"Missing value for the following parameters ");
			List<String> missingFields = new ArrayList<String>();

			if (StringUtils.isEmpty(addDeviceDto.getUserId())) {
				missingFields.add("userId");
			}

			if (StringUtils.isEmpty(addDeviceDto.getDeviceToken())) {
				missingFields.add("deviceToken");
			}

			if (missingFields.size() > 0) {
				sb.append(org.apache.commons.lang.StringUtils.join(
						missingFields.toArray(), ", "));
			} else {
				sb.append(missingFields.get(0));
			}
			sb.append(".");

			result.setMessage(sb.toString());
		}

		return result;
	}
}
