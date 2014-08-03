package com.themallmall.model.crm;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.themallmall.model.AuditableEntity;

/**
 * @author Edward P. Legaspi
 **/
@Entity
@Table(name = "CRM_PROMO_CODE")
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "CRM_PROMO_CODE_SEQ")
public class PromoCode extends AuditableEntity {

	private static final long serialVersionUID = -6164507298015067068L;

	@Column(name = "CODE")
	private String code;

	@Column(name = "PERCENT")
	private BigDecimal percent;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_ACTIVE")
	private Date dateActive;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_EXPIRY")
	private Date dateExpiry;

	@Column(name = "DESCRIPTION", length = 250)
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}

	public Date getDateExpiry() {
		return dateExpiry;
	}

	public void setDateExpiry(Date dateExpiry) {
		this.dateExpiry = dateExpiry;
	}

	public Date getDateActive() {
		return dateActive;
	}

	public void setDateActive(Date dateActive) {
		this.dateActive = dateActive;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
