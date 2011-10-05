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

	/**
	 * 
	 */
	private static final long serialVersionUID = 6431007114719302435L;
	
	private ComputeService computeService;

	public ComputeServiceAlternative(ComputeService cs, String name) {
		setComputeService(cs);
		setName(name);
	}

	/**
	 * @return the computeService
	 */
	public ComputeService getComputeService() {
		return computeService;
	}

	/**
	 * @param computeService
	 *            the computeService to set
	 */
	public void setComputeService(ComputeService computeService) {
		this.computeService = computeService;
	}

}
