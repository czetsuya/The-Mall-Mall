package com.themallmall.model.crm;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.themallmall.model.BaseEntity;
import com.themallmall.model.Name;

/**
 * @author Edward P. Legaspi
 **/
@Entity
@Table(name = "CRM_USER")
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "CRM_USER_SEQ")
public class User extends BaseEntity {

	private static final long serialVersionUID = -5980537640679423583L;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Embedded
	private Name name = new Name();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
