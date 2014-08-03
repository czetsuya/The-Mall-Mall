package com.themallmall.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Edward P. Legaspi
 **/
@Embeddable
public class Name implements Serializable {

	private static final long serialVersionUID = -2364827625017604705L;

	@NotNull
	@Size(min = 1, max = 70)
	@Column(name = "FIRST_NAME", length = 100)
	private String firstName;

	@NotNull
	@Size(min = 1, max = 70)
	@Column(name = "LAST_NAME", length = 100)
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

}
