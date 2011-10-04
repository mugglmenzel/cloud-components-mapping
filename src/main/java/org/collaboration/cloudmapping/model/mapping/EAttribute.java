/**
 * 
 */
package org.collaboration.cloudmapping.model.mapping;

/**
 * @author mugglmenzel
 *
 */
public enum EAttribute {

	COSTPERHOUR("costperhour"), BENCHMARK("benchmark");
	
	private String name;
	
	private EAttribute(String name) {
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
