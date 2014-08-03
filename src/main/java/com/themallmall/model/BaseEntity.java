package com.themallmall.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * @author Edward P. Legaspi
 **/
@MappedSuperclass
public abstract class BaseEntity implements IEntity, Serializable {

	private static final long serialVersionUID = 1265471300793378515L;

	public static final int NB_PRECISION = 23;
	public static final int NB_DECIMALS = 12;

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	@Column(name = "ID")
	private Long id;

	/**
	 * Version of the information stored in the record
	 */
	@Version
	@Column(name = "VERSION")
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public boolean isTransient() {
		return id == null;
	}

}
