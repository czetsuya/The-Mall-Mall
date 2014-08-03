package com.themallmall.model.crm;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.themallmall.model.BaseEntity;

/**
 * @author Edward P. Legaspi
 **/
@Entity
@Table(name = "CRM_ORDER")
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "CRM_ORDER_SEQ")
public class Order extends BaseEntity {

	private static final long serialVersionUID = -3907497586083835080L;

	@Column(name = "AMOUNT_WITH_TAX", precision = NB_PRECISION, scale = NB_DECIMALS)
	private BigDecimal amount;

	@Column(name = "DISCOUNT", precision = NB_PRECISION, scale = NB_DECIMALS)
	private BigDecimal discount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROMO_CODE_ID")
	private PromoCode promoCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TRANSACTION_DATE")
	private Date transactionDate;

	public BigDecimal getTotalAmount() {
		if (promoCode == null) {
			return amount;
		} else {
			return amount.subtract(amount.multiply(discount));
		}
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PromoCode getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(PromoCode promoCode) {
		this.promoCode = promoCode;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
}
