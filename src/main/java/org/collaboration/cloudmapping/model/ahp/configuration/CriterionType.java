/**
 * 
 */
package org.collaboration.cloudmapping.model.ahp.configuration;


/**
 * @author menzel
 *
 */

public enum CriterionType {

	QUANTITATIVE("quantitative"), QUALITATIVE("qualitative"), BENCHMARK("benchmark");
	
	String typeName;
	
	private CriterionType(String type) {
		setTypeName(type);
	}

	/**
	 * @return the type
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param type the type to set
	 */
	private void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return getTypeName();
	}
	
	
	
	
}
