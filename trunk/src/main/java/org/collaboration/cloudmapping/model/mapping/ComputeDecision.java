/**
 * 
 */
package org.collaboration.cloudmapping.model.mapping;

import java.util.HashSet;
import java.util.Set;

import org.collaboration.cloudmapping.model.ahp.configuration.Decision;

/**
 * @author mugglmenzel
 * 
 */
public class ComputeDecision extends Decision {

	Appliance appliance;
	private Set<Requirement> fctRequirements;
	// Set<Goal> nonFctGoals;
	// Set<GoalImportance> goalImportance;
	// Set<ApplianceComputeMappingAlternative> applianceCompMapAlt;

	public boolean validMapping = false;

	public ComputeDecision() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	public Appliance getAppliance() {
		return appliance;
	}





	public void setAppliance(Appliance appliance) {
		this.appliance = appliance;
	}





	public Set<Requirement> getFctRequirements() {
		return fctRequirements;
	}





	public void setFctRequirements(Set<Requirement> fctRequirements) {
		this.fctRequirements = fctRequirements;
	}





	public boolean isValidMapping() {
		return validMapping;
	}


	/*
	 * Die Methode reqCheck mapped die Attribute auf die Requirements und prüft
	 * diese so auf Erfüllung.
	 */
	// TODO: später als universelle Klasse/Methode einarbeiten, da
	// ApplianceDecision ebenfalls Att. auf Requ. mapped
	public void reqCheck() {

		Set<Requirement> satisfied = new HashSet<Requirement>();
		Set<Requirement> unsatisfied = new HashSet<Requirement>();
		for (Requirement r : fctRequirements) {
			if (this.appliance.getHashedAttr().containsKey(r.getName())) {

				switch (r.getReqType()) {

				case EQUALS:
					if (this.appliance.getHashedAttr().get(r.getName())
							.equals(r.getValue())) {
						satisfied.add(r);

					} else
						unsatisfied.add(r);

					/*
					 * case MINIMUM:
					 * 
					 * 
					 * 
					 * case MAXIMUM:
					 * 
					 * 
					 * 
					 * case ONEOUTOF:
					 */
				}

			} else {
				throw new NullPointerException();
			}
		}
		if (satisfied.size() == fctRequirements.size()) {
			validMapping = true;
		}
	}

}
