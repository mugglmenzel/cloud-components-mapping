package org.collaboration.cloudmapping.model.ahp.configuration;

import java.io.Serializable;

import org.collaboration.cloudmapping.model.Instance;

/**
 * @author mugglmenzel This class of the data model represents an alternative
 *         solution one can decide for in a deciscion. A Value that is
 *         determined for a criterion is depending on an alternative.
 * 
 *         Author: Michael Menzel (mugglmenzel)
 * 
 *         Last Change:
 *           
 *           By Author: $Author: mugglmenzel $ 
 *         
 *           Revision: $Revision: 165 $ 
 *         
 *           Date: $Date: 2011-08-05 15:45:22 +0200 (Fr, 05 Aug 2011) $
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
 *         $HeadURL: https://aotearoadecisions.googlecode.com/svn/trunk/src/main/java/de/fzi/aotearoa/shared/model/ahp/configuration/Alternative.java $
 *         
 */

public class Alternative implements Serializable {

	private String name;

	private Instance instance;
	
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

	/**
	 * @param instance the instance to set
	 */
	public void setInstance(Instance instance) {
		this.instance = instance;
	}

	/**
	 * @return the instance
	 */
	public Instance getInstance() {
		return instance;
	}

}
