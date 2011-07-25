/**
 * 
 */
package org.collaboration.cloudmapping.model.ahp.configuration;

/**
 * @author mugglmenzel
 *
 */
public enum GoalType {

	POSITIVE(1), NEGATIVE(-1);
	
	
	private int factor;
	
	GoalType(int i) {
		this.setFactor(i);
	}

	/**
	 * @param factor the factor to set
	 */
	public void setFactor(int factor) {
		this.factor = factor;
	}

	/**
	 * @return the factor
	 */
	public int getFactor() {
		return factor;
	}
}
