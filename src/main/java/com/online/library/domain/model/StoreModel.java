package com.online.library.domain.model;


import com.online.library.domain.utils.JSONFormatter;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class StoreModel {
	
	/**
	 * Returns a JSON string corresponding to object state
	 *
	 * @return JSON representation
	 */
	public String toJSON() {
		return JSONFormatter.toJSON(this);
	}

	@Override
	public String toString() {
		return toJSON();
	}
}
