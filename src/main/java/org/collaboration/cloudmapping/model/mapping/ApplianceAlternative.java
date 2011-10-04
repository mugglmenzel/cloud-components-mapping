/**
 * 
 */
package org.collaboration.cloudmapping.model.mapping;

import org.collaboration.cloudmapping.model.ahp.configuration.Alternative;

/**
 * @author mugglmenzel
 *
 */
public class ApplianceAlternative extends Alternative {

	private Appliance appl;
	
	public ApplianceAlternative(Appliance appl, String name) {
		this.setAppl(appl);
		setName(name);
	}

	/**
	 * @param appl the appl to set
	 */
	public void setAppl(Appliance appl) {
		this.appl = appl;
	}

	/**
	 * @return the appl
	 */
	public Appliance getAppl() {
		return appl;
	}
}
