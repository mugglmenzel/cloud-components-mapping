/**
 * 
 */
package org.collaboration.cloudmapping.model.ahp.values;

import java.io.Serializable;

import org.collaboration.cloudmapping.model.ahp.configuration.Decision;

/**
 * @author mugglmenzel
 * 
 *         Author: Michael Menzel (mugglmenzel)
 * 
 *         Last Change:
 *           
 *           By Author: $Author: mugglmenzel $ 
 *         
 *           Revision: $Revision: 166 $ 
 *         
 *           Date: $Date: 2011-08-05 15:49:44 +0200 (Fr, 05 Aug 2011) $
 * 
 *         License:
 *         
 *         Copyright 2011 Forschungszentrum Informatik FZI / Karlsruhe Institute
 *         of Technology
 * 
 *         Licensed under the Apache License, Version 2.0 (the "License"); you
 *         may not use this file except in compliance with the License. You may
 *         obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 *         Unless required by applicable law or agreed to in writing, software
 *         distributed under the License is distributed on an "AS IS" BASIS,
 *         WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *         implied. See the License for the specific language governing
 *         permissions and limitations under the License.
 * 
 *         
 *         SVN URL: 
 *         $HeadURL: https://aotearoadecisions.googlecode.com/svn/trunk/src/main/java/de/fzi/aotearoa/shared/model/ahp/values/GoalImportance.java $
 *
 */

public class GoalImportance implements Serializable, Importance {

	private Decision decision;

	private int critA;

	private int critB;

	private Double comparisonAToB;

	private String comment;
	
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
	public GoalImportance(int critA, int critB, Double comparisonAToB, String comment) {
		super();
		this.critA = critA;
		this.critB = critB;
		this.comparisonAToB = comparisonAToB;
		this.comment = comment;
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
		return "GoalImportance [comparisonAToB=" + comparisonAToB
				+ ", critA=" + critA + ", critB=" + critB + "]";
	}

	public GoalImportance clone() {
		return new GoalImportance(getCritA(), getCritB(),
				getComparisonAToB(), getComment());
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
}
