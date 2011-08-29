package org.collaboration.cloudmapping.model.ahp.values;

import java.io.Serializable;

import org.collaboration.cloudmapping.model.ahp.configuration.Criterion;

/**
 * 
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
 *         $HeadURL: https://aotearoadecisions.googlecode.com/svn/trunk/src/main/java/de/fzi/aotearoa/shared/model/ahp/values/AlternativeImportance.java $
 *
 */


public class AlternativeImportance implements Serializable {

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
