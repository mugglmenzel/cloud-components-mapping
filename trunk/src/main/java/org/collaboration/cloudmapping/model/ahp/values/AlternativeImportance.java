package org.collaboration.cloudmapping.model.ahp.values;

import org.collaboration.cloudmapping.model.ahp.configuration.Criterion;

public class AlternativeImportance {

	private Criterion criterion;

	private int altA;

	private int altB;

	private Double comparisonAToB;

	/**
	 * 
	 */
	public AlternativeImportance() {
		super();
	}

	/**
	 * 
	 * @param altA
	 * @param altB
	 * @param comparisonAToB
	 */
	public AlternativeImportance(int altA, int altB, Double comparisonAToB) {
		super();
		this.altA = altA;
		this.altB = altB;
		this.comparisonAToB = comparisonAToB;
	}


	/**
	 * 
	 * @return the Alternative A
	 */
	public int getAltA() {
		return altA;
	}

	/**
	 * 
	 * @param altA
	 *            the Alternative A to set
	 */
	public void setAltA(int altA) {
		this.altA = altA;
	}

	/**
	 * 
	 * @return the Alternative B
	 */
	public int getAltB() {
		return altB;
	}

	/**
	 * 
	 * @param altB
	 *            the Alternative B to set
	 */
	public void setAltB(int altB) {
		this.altB = altB;
	}

	/**
	 * 
	 * @return the comparison value between alternative A and B
	 */
	public Double getComparisonAToB() {
		return comparisonAToB;
	}

	/**
	 * 
	 * @param comparisonAToB
	 *            the comparison value between alternative A and B to set
	 */
	public void setComparisonAToB(Double comparisonAToB) {
		this.comparisonAToB = comparisonAToB;
	}

	/**
	 * @param criterion
	 *            the criterion to set
	 */
	public void setCriterion(Criterion criterion) {
		this.criterion = criterion;
	}

	/**
	 * @return the criterion
	 */
	public Criterion getCriterion() {
		return criterion;
	}

	public AlternativeImportance clone() {
		return new AlternativeImportance(getAltA(), getAltB(),
				getComparisonAToB());
	}

}
