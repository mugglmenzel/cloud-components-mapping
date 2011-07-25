/**
 * 
 */
package org.collaboration.cloudmapping.model.ahp.values;

import org.collaboration.cloudmapping.model.ahp.configuration.Decision;

/**
 * @author mugglmenzel
 * 
 */

public class GoalImportance {

	private Decision decision;

	private int critA;

	private int critB;

	private Double comparisonAToB;

	/**
	 * 
	 */
	public GoalImportance() {
		super();
	}

	/**
	 * @param critA
	 * @param critB
	 * @param comparisonAToB
	 */
	public GoalImportance(int critA, int critB, Double comparisonAToB) {
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
		GoalImportance other = (GoalImportance) obj;
		if (critA != other.critA) {
			return false;
		}
		if (critB != other.critB) {
			return false;
		}
		if (decision == null) {
			if (other.decision != null) {
				return false;
			}
		} else if (!decision.equals(other.decision)) {
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
	public Decision getDecision() {
		return decision;
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
		result = prime * result + ((decision == null) ? 0 : decision.hashCode());
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
	public void setDecision(Decision parent) {
		this.decision = parent;
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

	public GoalImportance clone() {
		return new GoalImportance(getCritA(), getCritB(),
				getComparisonAToB());
	}
}
