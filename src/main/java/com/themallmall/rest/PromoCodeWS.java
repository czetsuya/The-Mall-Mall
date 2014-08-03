package com.themallmall.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.themallmall.rest.api.PromoCodeApi;
import com.themallmall.rest.dto.GetAdsDto;
import com.themallmall.rest.dto.PromoCodeListDto;
import com.themallmall.rest.dto.ValidatePromoDto;
import com.themallmall.rest.logging.LoggingInterceptor;

/**
 * @author Edward P. Legaspi
 **/
@Path("/promo")
@RequestScoped
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Interceptors({ LoggingInterceptor.class })
public class PromoCodeWS extends BaseWS {

	@Inject
	private PromoCodeApi promoCodeApi;

	@POST
	@Path("/ads")
	public PromoCodeListDto getAds(GetAdsDto getAdsDto) {
		return promoCodeApi.getAds(getAdsDto.getDate());
	}

	@POST
	@Path("/validate")
	public ActionResult validate(ValidatePromoDto validatePromoDto) {
		ActionResult result = new ActionResult();

		try {
			promoCodeApi.validate(validatePromoDto.getUserId(),
					validatePromoDto.getCode());
		} catch (RestApiException e) {
			result.setStatus(ActionResultStatus.FAIL);
			result.setMessage(e.getMessage());
		}

		return result;
	}

}
