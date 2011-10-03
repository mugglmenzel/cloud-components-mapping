package org.collaboration.cloudmapping.model.mapping;

import java.util.HashSet;
import java.util.Set;

public class Component {

	private String name;
	
	private Set<Component> requiredComponents;
	
	public Set<Attribute> attributes;
	

	public Component(String name) {
		this(name, new HashSet<Component>());
	}
	
	/**
	 * @param name
	 */
	public Component(String name, Set<Component> required) {
		super();
		this.name = name;
		this.setRequiredComponents(required);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getName();
	}

	/**
	 * @param requiredComponents the requiredComponents to set
	 */
	public void setRequiredComponents(Set<Component> requiredComponents) {
		this.requiredComponents = requiredComponents;
	}

	/**
	 * @return the requiredComponents
	 */
	public Set<Component> getRequiredComponents() {
		return requiredComponents != null ? requiredComponents : new HashSet<Component>();
	}
	
	
	
}
