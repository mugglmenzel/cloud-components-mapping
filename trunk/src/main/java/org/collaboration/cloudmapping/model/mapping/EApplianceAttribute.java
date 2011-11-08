/**
 * 
 */
package org.collaboration.cloudmapping.model.mapping;

/**
 * @author mugglmenzel
 *
 */
public enum EApplianceAttribute implements IEAttribute {

	COSTPERHOUR("costperhour"), POPULARITY("popularity");
	
	private String name;
	
	private EApplianceAttribute(String name) {
		this.setName(name);
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
}
