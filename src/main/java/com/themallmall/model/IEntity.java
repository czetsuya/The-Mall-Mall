package com.themallmall.model;

import java.io.Serializable;

/**
 * @author Edward P. Legaspi
 **/
public interface IEntity {
	public Serializable getId();

	public boolean isTransient();
}
