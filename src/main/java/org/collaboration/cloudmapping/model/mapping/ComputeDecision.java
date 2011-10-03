/**
 * 
 */
package org.collaboration.cloudmapping.model.mapping;

import java.util.Set;

import org.collaboration.cloudmapping.model.ahp.configuration.Decision;

/**
 * @author mugglmenzel
 *
 */
public class ComputeDecision extends Decision {
	
	Appliance appliance;
	private Set<Requirement> fctRequirements;
	//Set<Goal> nonFctGoals;
	//Set<GoalImportance> goalImportance;
	//Set<ApplianceComputeMappingAlternative> applianceCompMapAlt;
	
	public boolean validMapping = false;
	public ComputeDecision() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	/*
	 * Die Methode reqCheck mapped die Attribute auf die Reqirements und prüft diese so auf Erfüllung. 
	 * 
	 * 
	 */
	//TODO: später als universelle Klasse/Methode einarbeiten, da ApplianceDecision ebenfalls Att. auf Requ. mapped
	public void reqCheck (Set<Requirement> fctRequirements, Set<Attribute> attributes) {
		
	}
	
	
	
}
