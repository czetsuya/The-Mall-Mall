package com.themallmall.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.themallmall.bean.PaginationConfiguration;
import com.themallmall.bean.QueryBuilder;
import com.themallmall.exception.BusinessException;
import com.themallmall.model.BaseEntity;
import com.themallmall.model.IEntity;
import com.themallmall.model.IdentifiableEnum;

/**
 * @author Edward P. Legaspi
 **/
public class PersistenceService<E extends IEntity> {

	private Logger log = LoggerFactory.getLogger(PersistenceService.class);

	protected final Class<E> entityClass;

	@Inject
	private EntityManager em;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PersistenceService() {
		Class clazz = getClass();
		while (!(clazz.getGenericSuperclass() instanceof ParameterizedType)) {
			clazz = clazz.getSuperclass();
		}
		Object o = ((ParameterizedType) clazz.getGenericSuperclass())
				.getActualTypeArguments()[0];

		if (o instanceof TypeVariable) {
			this.entityClass = (Class<E>) ((TypeVariable) o).getBounds()[0];
		} else {
			this.entityClass = (Class<E>) o;
		}
	}

	@SuppressWarnings("unchecked")
	public PersistenceService(Class<? extends IEntity> entityClass) {
		this.entityClass = (Class<E>) entityClass;
	}

	public Class<E> getEntityClass() {
		return entityClass;
	}

	public E findById(Long id) {
		return findById(id, false);
	}

	public E findById(Long id, List<String> fetchFields) {
		return findById(id, fetchFields, false);
	}

	public E findById(Long id, boolean refresh) {
		log.debug("start of find {} by id (id={}) ..", getEntityClass()
				.getSimpleName(), id);
		final Class<? extends E> productClass = getEntityClass();
		E e = getEntityManager().find(productClass, id);
		if (refresh) {
			log.debug("refreshing loaded entity");
			getEntityManager().refresh(e);
		}
		log.debug("end of find {} by id (id={}). Result found={}.",
				getEntityClass().getSimpleName(), id, e != null);
		return e;
	}

	@SuppressWarnings("unchecked")
	public E findById(Long id, List<String> fetchFields, boolean refresh) {
		log.debug("start of find {} by id (id={}) ..", getEntityClass()
				.getSimpleName(), id);
		final Class<? extends E> productClass = getEntityClass();
		StringBuilder queryString = new StringBuilder("from "
				+ productClass.getName() + " a");
		if (fetchFields != null && !fetchFields.isEmpty()) {
			for (String fetchField : fetchFields) {
				queryString.append(" left join fetch a." + fetchField);
			}
		}
		queryString.append(" where a.id = :id");
		Query query = getEntityManager().createQuery(queryString.toString());
		query.setParameter("id", id);

		E e = (E) query.getResultList().get(0);

		if (refresh) {
			log.debug("refreshing loaded entity");
			getEntityManager().refresh(e);
		}
		log.debug("end of find {} by id (id={}). Result found={}.",
				getEntityClass().getSimpleName(), id, e != null);
		return e;
	}

	public void create(E e) throws BusinessException {
		create(getEntityManager(), e);
	}

	public void create(EntityManager em, E e) throws BusinessException {
		log.debug("start of create {} entity...", e.getClass().getSimpleName());
		try {
			em.persist(e);

			log.debug("end of create {}. entity id={}.", e.getClass()
					.getSimpleName(), e.getId());
		} catch (ConstraintViolationException ex) {
			log.error("validation failed. Cause: {}.", ex.getMessage());
			throw ex;
		}
	}
	
	public void update(EntityManager em, E e) {
		log.debug("start of update {} entity (id={}) ..", e.getClass()
				.getSimpleName(), e.getId());		
		em.merge(e);
		log.debug("end of update {} entity (id={}).", e.getClass()
				.getSimpleName(), e.getId());
	}

	public long count(PaginationConfiguration config) {
		List<String> fetchFields = config.getFetchFields();
		config.setFetchFields(null);
		QueryBuilder queryBuilder = getQuery(config);
		config.setFetchFields(fetchFields);
		return queryBuilder.count(getEntityManager());
	}

	public long count() {
		final Class<? extends E> entityClass = getEntityClass();
		QueryBuilder queryBuilder = new QueryBuilder(entityClass, "a");
		return queryBuilder.count(getEntityManager());
	}

	@SuppressWarnings("rawtypes")
	private QueryBuilder getQuery(PaginationConfiguration config) {

		final Class<? extends E> entityClass = getEntityClass();

		QueryBuilder queryBuilder = new QueryBuilder(entityClass, "a",
				config.getFetchFields());

		Map<String, Object> filters = config.getFilters();
		if (filters != null) {
			if (!filters.isEmpty()) {
				for (String key : filters.keySet()) {
					Object filter = filters.get(key);
					if (filter != null) {
						// if ranged search (from - to fields)
						if (key.contains("fromRange-")) {
							String parsedKey = key.substring(10);
							if (filter instanceof Double) {
								BigDecimal rationalNumber = new BigDecimal(
										(Double) filter);
								queryBuilder.addCriterion("a." + parsedKey,
										" >= ", rationalNumber, true);
							} else if (filter instanceof Number) {
								queryBuilder.addCriterion("a." + parsedKey,
										" >= ", filter, true);
							} else if (filter instanceof Date) {
								queryBuilder
										.addCriterionDateRangeFromTruncatedToDay(
												"a." + parsedKey, (Date) filter);
							}
						} else if (key.contains("toRange-")) {
							String parsedKey = key.substring(8);
							if (filter instanceof Double) {
								BigDecimal rationalNumber = new BigDecimal(
										(Double) filter);
								queryBuilder.addCriterion("a." + parsedKey,
										" <= ", rationalNumber, true);
							} else if (filter instanceof Number) {
								queryBuilder.addCriterion("a." + parsedKey,
										" <= ", filter, true);
							} else if (filter instanceof Date) {
								queryBuilder
										.addCriterionDateRangeToTruncatedToDay(
												"a." + parsedKey, (Date) filter);
							}
						} else if (key.contains("list-")) {
							// if searching elements from list
							String parsedKey = key.substring(5);
							queryBuilder.addSqlCriterion(":" + parsedKey
									+ " in elements(a." + parsedKey + ")",
									parsedKey, filter);
						}
						// if not ranged search
						else {
							if (filter instanceof String) {
								// if contains dot, that means join is needed
								String filterString = (String) filter;
								queryBuilder.addCriterionWildcard("a." + key,
										filterString, true);
							} else if (filter instanceof Date) {
								queryBuilder.addCriterionDateTruncatedToDay(
										"a." + key, (Date) filter);
							} else if (filter instanceof Number) {
								if (key.startsWith("!")) {
									queryBuilder.addCriterion(
											"a." + key.substring(1), " <> ",
											filter, true);
								} else {
									queryBuilder.addCriterion("a." + key,
											" = ", filter, true);
								}
							} else if (filter instanceof Boolean) {
								queryBuilder.addCriterion("a." + key, " is ",
										filter, true);
							} else if (filter instanceof Enum) {
								if (filter instanceof IdentifiableEnum) {
									String enumIdKey = new StringBuilder(key)
											.append("Id").toString();
									queryBuilder
											.addCriterion("a." + enumIdKey,
													" = ",
													((IdentifiableEnum) filter)
															.getId(), true);
								} else {
									queryBuilder.addCriterionEnum("a." + key,
											(Enum) filter);
								}
							} else if (BaseEntity.class.isAssignableFrom(filter
									.getClass())) {
								queryBuilder.addCriterionEntity("a." + key,
										filter);
							}
						}
					}
				}
			}
		}
		queryBuilder.addPaginationConfiguration(config, "a");

		return queryBuilder;
	}

	@SuppressWarnings("unchecked")
	public List<E> list() {
		final Class<? extends E> entityClass = getEntityClass();
		QueryBuilder queryBuilder = new QueryBuilder(entityClass, "a", null);
		Query query = queryBuilder.getQuery(getEntityManager());
		return query.getResultList();
	}

	@SuppressWarnings({ "unchecked" })
	public List<E> list(PaginationConfiguration config) {
		QueryBuilder queryBuilder = getQuery(config);
		Query query = queryBuilder.getQuery(getEntityManager());
		return query.getResultList();
	}

	public EntityManager getEntityManager() {
		return em;
	}

}
