/**
 * 
 */
package org.collaboration.cloudmapping.model.mapping;

import org.collaboration.cloudmapping.model.ahp.configuration.Alternative;

/**
 * @author mugglmenzel
 *
 */
public class InstanceAlternative extends Alternative {

	private Instance instance;
	
	public InstanceAlternative(Instance instance, String name) {
		setInstance(instance);
		setName(name);
	}

	public Instance getInstance() {
		return this.instance;
	}
	
	/**
	 * @param instance the instance to set
	 */
	public void setInstance(Instance instance) {
		this.instance = instance;
	}

}
