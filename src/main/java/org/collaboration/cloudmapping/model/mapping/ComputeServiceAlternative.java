/**
 * 
 */
package org.collaboration.cloudmapping.model.mapping;

import org.collaboration.cloudmapping.model.ahp.configuration.Alternative;

/**
 * @author mugglmenzel
 *
 */
public class ComputeServiceAlternative extends Alternative {

	private ComputeService cs;
	
	public ComputeServiceAlternative(ComputeService cs, String name) {
		this.setCs(cs);
		setName(name);
	}

	/**
	 * @param cs the cs to set
	 */
	public void setCs(ComputeService cs) {
		this.cs = cs;
	}

	/**
	 * @return the cs
	 */
	public ComputeService getCs() {
		return cs;
	}
	
}
