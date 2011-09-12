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
	Set<Goal> nonFctGoals;
	Set<GoalImportance> goalImportance;
	Set<ApplianceComputeMappingAlternative> applianceCompMapAlt;
	public ComputeDecision() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	
	
	
	
}
