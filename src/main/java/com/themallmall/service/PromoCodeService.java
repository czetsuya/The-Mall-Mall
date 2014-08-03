package com.themallmall.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.themallmall.bean.QueryBuilder;
import com.themallmall.model.crm.PromoCode;

/**
 * @author Edward P. Legaspi
 **/
@Stateless
public class PromoCodeService extends PersistenceService<PromoCode> {

	public PromoCode findByCode(EntityManager em, String code) {
		QueryBuilder qb = new QueryBuilder(PromoCode.class, "p");

		qb.addCriterion("code", "=", code, true);
		try {
			return (PromoCode) qb.getQuery(em).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PromoCode> getAds(EntityManager em, Date date) {
		// QueryBuilder qb = new QueryBuilder(PromoCode.class, "p");
		// qb.addCriterionDateRangeFromTruncatedToDay("dateActive", date);
		// qb.addCriterionDateRangeToTruncatedToDay("dateActive", date);
		// qb.addCriterion("updated", " ", " is null", false);
		StringBuilder sb = new StringBuilder();
		sb.append("FROM " + PromoCode.class.getName() + " p");
		sb.append(" WHERE p.auditable.updated=NULL");
		sb.append(" AND p.dateActive=:date");

		try {
			return em.createQuery(sb.toString()).setParameter("date", date)
					.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
