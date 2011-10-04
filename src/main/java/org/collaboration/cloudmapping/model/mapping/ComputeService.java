package org.collaboration.cloudmapping.model.mapping;

import java.util.HashMap;
import java.util.Map;

public class ComputeService {

	private String name;
	
	private Map<EAttribute, Object> attributes = new HashMap<EAttribute, Object>();
	
	/**
	 * @param name
	 */
	public ComputeService(String name) {
		super();
		this.name = name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(Map<EAttribute, Object> attributes) {
		this.attributes = attributes;
	}

	/**
	 * @return the attributes
	 */
	public Map<EAttribute, Object> getAttributes() {
		return attributes;
	}
	
	
}
