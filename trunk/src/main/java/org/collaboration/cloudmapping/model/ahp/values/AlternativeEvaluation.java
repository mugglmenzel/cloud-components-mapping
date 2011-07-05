package org.collaboration.cloudmapping.model.ahp.values;

import org.collaboration.cloudmapping.model.ahp.configuration.Alternative;
import org.collaboration.cloudmapping.model.ahp.configuration.Criterion;

public class AlternativeEvaluation {

	private Criterion criterion;
	
	private Alternative alternative;
	
	private Double value;

	/**
	 * @param criterion
	 * @param alternative
	 * @param value
	 */
	public AlternativeEvaluation(Criterion criterion, Alternative alternative,
			Double value) {
		super();
		this.criterion = criterion;
		this.alternative = alternative;
		this.value = value;
	}

	/**
	 * @return the criterion
	 */
	public Criterion getCriterion() {
		return criterion;
	}

	/**
	 * @param criterion the criterion to set
	 */
	public void setCriterion(Criterion criterion) {
		this.criterion = criterion;
	}

	/**
	 * @return the alternative
	 */
	public Alternative getAlternative() {
		return alternative;
	}

	/**
	 * @param alternative the alternative to set
	 */
	public void setAlternative(Alternative alternative) {
		this.alternative = alternative;
	}

	/**
	 * @return the value
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Double value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getAlternative().toString() + "/" + getCriterion().toString() + ":=" + value.toString();
	}
	
	
	
}
