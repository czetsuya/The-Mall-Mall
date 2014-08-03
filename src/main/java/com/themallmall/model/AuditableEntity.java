package com.themallmall.model;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

/**
 * @author Edward P. Legaspi
 **/
@MappedSuperclass
public class AuditableEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Embedded
	private Auditable auditable;

	public AuditableEntity() {
	}

	public AuditableEntity(Auditable auditable) {
		this.auditable = auditable;
	}

	public Auditable getAuditable() {
		return auditable;
	}

	public void setAuditable(Auditable auditable) {
		this.auditable = auditable;
	}

}
