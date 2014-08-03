package com.themallmall.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.themallmall.bean.QueryBuilder;
import com.themallmall.exception.BusinessException;
import com.themallmall.model.crm.User;

/**
 * @author Edward P. Legaspi
 **/
@Stateless
public class UserService extends PersistenceService<User> {

	public User findUser(EntityManager em, String username, String password)
			throws BusinessException {
		QueryBuilder qb = new QueryBuilder(User.class, "u");
		qb.addCriterion("username", "=", username, true);
		qb.addCriterion("password", "=", password, true);

		try {
			return (User) qb.getQuery(em).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
