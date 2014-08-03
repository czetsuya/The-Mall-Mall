package com.themallmall.rest.api;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.themallmall.exception.BusinessException;
import com.themallmall.model.crm.Order;
import com.themallmall.model.crm.PromoCode;
import com.themallmall.model.crm.User;
import com.themallmall.rest.RestApiException;
import com.themallmall.rest.dto.PromoCodeListDto;
import com.themallmall.service.OrderService;
import com.themallmall.service.PromoCodeService;
import com.themallmall.service.UserService;

/**
 * @author Edward P. Legaspi
 **/
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PromoCodeApi {

	@Inject
	private UserService userService;

	@Inject
	private PromoCodeService promoCodeService;

	@Inject
	private EntityManager em;

	@Inject
	private OrderService orderService;

	public PromoCodeListDto getAds(Date date) {
		PromoCodeListDto result = new PromoCodeListDto();
		List<PromoCode> promoCodes = promoCodeService.getAds(em, date);
		result.setPromoCodes(promoCodes);
		return result;
	}

	public void validate(Long userId, String code) throws RestApiException {
		User user = userService.findById(userId);
		if (user == null) {
			throw new RestApiException("User with id=" + userId
					+ " does not exists.");
		}

		PromoCode promoCode = promoCodeService.findByCode(em, code);
		if (promoCode == null) {
			throw new RestApiException("Promo with code=" + code
					+ " does not exists.");
		} else {
			if (promoCode.getAuditable().getUpdated() != null) {
				throw new RestApiException("Promo with code=" + promoCode
						+ " is already used.");
			} else {
				promoCode.getAuditable().setUpdated(new Date());
			}
		}

		// apply the promo code
		Order order = new Order();
		order.setUser(user);
		order.setPromoCode(promoCode);
		order.setDiscount(promoCode.getPercent());
		order.setAmount(new BigDecimal(1000));

		try {
			orderService.create(em, order);
		} catch (BusinessException e) {
			throw new RestApiException(e.getMessage());
		}
	}
}
