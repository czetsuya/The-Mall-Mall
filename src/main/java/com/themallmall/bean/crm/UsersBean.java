package com.themallmall.bean.crm;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.themallmall.bean.BaseListBean;
import com.themallmall.model.crm.User;
import com.themallmall.service.UserService;

/**
 * @author Edward P. Legaspi
 **/
@ConversationScoped
@Named
public class UsersBean extends BaseListBean<User, UserService> {

	private static final long serialVersionUID = -7503016960842165364L;

	@Inject
	private UserService userService;

	@Override
	public UserService getPersistenceService() {
		return userService;
	}
}
