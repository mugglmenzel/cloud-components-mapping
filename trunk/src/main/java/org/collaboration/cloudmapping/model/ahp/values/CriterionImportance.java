/**
 * 
 */
package org.collaboration.cloudmapping.model.ahp.values;

import java.io.Serializable;

import org.collaboration.cloudmapping.model.ahp.configuration.Criterion;

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
 *         $HeadURL: https://aotearoadecisions.googlecode.com/svn/trunk/src/main/java/de/fzi/aotearoa/shared/model/ahp/values/CriterionImportance.java $
 *
 */

public class CriterionImportance implements Serializable, Importance {

	private Criterion parent;

	private int critA;

	private int critB;

	private Double comparisonAToB;
	
	private String comment;

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
	public CriterionImportance(int critA, int critB, Double comparisonAToB, String comment) {
		super();
		this.critA = critA;
		this.critB = critB;
		this.comparisonAToB = comparisonAToB;
		this.comment = comment;
	}

	public CriterionImportance clone() {
		return new CriterionImportance(getCritA(), getCritB(),
				getComparisonAToB(), getComment());
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
		return true;
	}

	/* (non-Javadoc)
	 * @see de.fzi.aotearoa.shared.model.ahp.values.Importance#getComment()
	 */
	@Override
	public String getComment() {
		return comment;
	}

	/* (non-Javadoc)
	 * @see de.fzi.aotearoa.shared.model.ahp.values.Importance#getComparisonAToB()
	 */
	@Override
	public Double getComparisonAToB() {
		return comparisonAToB;
	}

	/* (non-Javadoc)
	 * @see de.fzi.aotearoa.shared.model.ahp.values.Importance#getCritA()
	 */
	@Override
	public int getCritA() {
		return critA;
	}

	/* (non-Javadoc)
	 * @see de.fzi.aotearoa.shared.model.ahp.values.Importance#getCritB()
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see de.fzi.aotearoa.shared.model.ahp.values.Importance#setComment(java.lang.String)
	 */
	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}

	/* (non-Javadoc)
	 * @see de.fzi.aotearoa.shared.model.ahp.values.Importance#setComparisonAToB(java.lang.Double)
	 */
	@Override
	public void setComparisonAToB(Double comparisonAToB) {
		this.comparisonAToB = comparisonAToB;
	}


	/* (non-Javadoc)
	 * @see de.fzi.aotearoa.shared.model.ahp.values.Importance#setCritA(int)
	 */
	@Override
	public void setCritA(int critA) {
		this.critA = critA;
	}

	/* (non-Javadoc)
	 * @see de.fzi.aotearoa.shared.model.ahp.values.Importance#setCritB(int)
	 */
	@Override
	public void setCritB(int critB) {
		this.critB = critB;
	}

	/**
	 * @param parent the parent to set
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
}