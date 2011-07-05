/**
 * 
 */
package org.collaboration.cloudmapping.model.ahp.values;

import org.collaboration.cloudmapping.model.ahp.configuration.Criterion;

/**
 * @author mugglmenzel
 * 
 */

public class CriterionImportance {


	private Criterion parent;

	private int critA;

	private int critB;

	private Double comparisonAToB;

	/**
	 * 
	 */
	public CriterionImportance() {
		super();
	}

	/**
	 * @param critA
	 * @param critB
	 * @param comparisonAToB
	 */
	public CriterionImportance(int critA, int critB, Double comparisonAToB) {
		super();
		this.critA = critA;
		this.critB = critB;
		this.comparisonAToB = comparisonAToB;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CriterionImportance other = (CriterionImportance) obj;
		if (critA != other.critA) {
			return false;
		}
		if (critB != other.critB) {
			return false;
		}
		if (parent == null) {
			if (other.parent != null) {
				return false;
			}
		} else if (!parent.equals(other.parent)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the comparisonAToB
	 */
	public Double getComparisonAToB() {
		return comparisonAToB;
	}

	/**
	 * @return the critA
	 */
	public int getCritA() {
		return critA;
	}

	/**
	 * @return the critB
	 */
	public int getCritB() {
		return critB;
	}

	/**
	 * @return the parent
	 */
	public Criterion getParent() {
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + critA;
		result = prime * result + critB;
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		return result;
	}

	/**
	 * @param comparisonAToB
	 *            the comparisonAToB to set
	 */
	public void setComparisonAToB(Double comparisonAToB) {
		this.comparisonAToB = comparisonAToB;
	}

	/**
	 * @param critA
	 *            the critA to set
	 */
	public void setCritA(int critA) {
		this.critA = critA;
	}

	/**
	 * @param critB
	 *            the critB to set
	 */
	public void setCritB(int critB) {
		this.critB = critB;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(Criterion parent) {
		this.parent = parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CriterionImportance [comparisonAToB=" + comparisonAToB
				+ ", critA=" + critA + ", critB=" + critB + "]";
	}

	public CriterionImportance clone() {
		return new CriterionImportance(getCritA(), getCritB(),
				getComparisonAToB());
	}
}
