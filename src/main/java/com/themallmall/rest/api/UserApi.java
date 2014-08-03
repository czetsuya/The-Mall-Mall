package com.themallmall.rest.api;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.themallmall.exception.BusinessException;
import com.themallmall.model.crm.User;
import com.themallmall.rest.RestApiException;
import com.themallmall.service.UserService;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserApi {

	@Inject
	private EntityManager em;

	@Inject
	private UserService userService;

	public Long findUser(String username, String password)
			throws RestApiException {
		try {
			User user = userService.findUser(em, username, password);
			if (user != null) {
				return user.getId();
			}
		} catch (BusinessException e) {

		}

		throw new RestApiException("Invalid username or password");
	}
}
