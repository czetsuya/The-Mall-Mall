package com.themallmall.bean.crm;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.SortOrder;

import com.themallmall.bean.BaseListBean;
import com.themallmall.model.crm.Order;
import com.themallmall.service.OrderService;

/**
 * @author Edward P. Legaspi
 **/
@ConversationScoped
@Named
public class OrdersBean extends BaseListBean<Order, OrderService> {

	private static final long serialVersionUID = 6888035079889091264L;

	@Inject
	private OrderService orderService;

	@Override
	public OrderService getPersistenceService() {
		return orderService;
	}

	@Override
	protected List<String> getListFieldsToFetch() {
		return Arrays.asList("user", "promoCode");
	}

	@Override
	protected String getDefaultSort() {
		return "transactionDate";
	}

	@Override
	protected SortOrder getDefaultSortOrder() {
		return SortOrder.DESCENDING;
	}
}
