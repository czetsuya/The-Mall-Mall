package com.themallmall.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.themallmall.bean.QueryBuilder;
import com.themallmall.exception.BusinessException;
import com.themallmall.model.Device;
import com.themallmall.model.crm.User;

@Stateless
public class DeviceService extends PersistenceService<Device> {

	public Device findByToken(EntityManager em, String token) {
		QueryBuilder qb = new QueryBuilder(Device.class, "d");
		qb.addCriterion("deviceToken", "=", token, false);

		try {
			return (Device) qb.getQuery(em).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Device findByUser(EntityManager em, User user) {
		QueryBuilder qb = new QueryBuilder(Device.class, "d");
		qb.addCriterionEntity("user", user);

		try {
			return (Device) qb.getQuery(em).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void addDevice(EntityManager em, Device device)
			throws BusinessException {
		create(em, device);
	}
}
