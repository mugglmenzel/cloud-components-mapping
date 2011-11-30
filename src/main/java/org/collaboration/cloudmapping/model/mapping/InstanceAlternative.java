/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Alternative;

/**
 * @author mugglmenzel
 *
 */
public class InstanceAlternative extends Alternative {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2480586708835376560L;
	
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
