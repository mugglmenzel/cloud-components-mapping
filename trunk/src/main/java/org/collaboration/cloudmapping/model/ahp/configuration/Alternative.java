package org.collaboration.cloudmapping.model.ahp.configuration;


/**
 * @author menzel This class of the data model represents an alternative
 *         solution one can decide for in a deciscion. A Value that is
 *         determined for a criterion is depending on an alternative.
 * 
 */

public class Alternative {


	private String name;

	private String description;

	private Float indexResult;

	private Decision decision;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getIndexResult() {
		return indexResult;
	}

	public void setIndexResult(Float indexResult) {
		this.indexResult = indexResult;
	}

	public Decision getDecision() {
		return decision;
	}

	public void setDecision(Decision decision) {
		this.decision = decision;
	}

	public Alternative() {
		super();
	}

	public Alternative(String name) {
		super();
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getName();
	}


	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public Alternative clone() {
		Alternative alt = new Alternative(getName());
		alt.setDescription(getDescription());
		return alt;
	}

}
