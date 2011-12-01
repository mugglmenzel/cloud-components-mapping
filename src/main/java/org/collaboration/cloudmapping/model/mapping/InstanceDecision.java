/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Alternative;
import de.eorg.cumulusgenius.shared.cloudmapping.model.ahp.configuration.Decision;
import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.requirements.Requirement;


/**
 * @author mugglmenzel
 * 
 */
public class InstanceDecision extends Decision {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7722924479698464974L;

	// private ComputeService computeService;

	private Set<Requirement<?>> fctRequirements = new HashSet<Requirement<?>>();
	// Set<Goal> nonFctGoals;
	// Set<GoalImportance> goalImportance;
	private List<InstanceAlternative> instanceAlternatives = new ArrayList<InstanceAlternative>();

	public boolean validMapping = false;

	public InstanceDecision() {
		super();
	}

	public Set<Requirement<?>> getFctRequirements() {
		return fctRequirements;
	}

	public void setFctRequirements(Set<Requirement<?>> fctRequirements) {
		this.fctRequirements = fctRequirements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.collaboration.cloudmapping.model.ahp.configuration.Decision#
	 * addAlternative
	 * (org.collaboration.cloudmapping.model.ahp.configuration.Alternative)
	 */
	@Deprecated
	public void addAlternative(Alternative alternative) {
		super.addAlternative(alternative);
	}

	public void addAlternative(InstanceAlternative alternative) {
		getInstanceAlternatives().add(alternative);
	}

	@Deprecated
	public void setAlternatives(List<Alternative> alternatives) {
		super.setAlternatives(alternatives);
	}

	@Deprecated
	public List<Alternative> getAlternatives() {
		List<Alternative> alts = new ArrayList<Alternative>();
		for (InstanceAlternative ia : getInstanceAlternatives())
			alts.add(ia);

		return alts;
	}

	/**
	 * @return the instanceAlternatives
	 */
	public List<InstanceAlternative> getInstanceAlternatives() {
		return instanceAlternatives;
	}

	/**
	 * @param instanceAlternatives
	 *            the instanceAlternatives to set
	 */
	public void setInstanceAlternatives(
			List<InstanceAlternative> instanceAlternatives) {
		this.instanceAlternatives = instanceAlternatives;
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

		for (Requirement r : fctRequirements)
			for (InstanceAlternative a : getInstanceAlternatives()) {
				System.out.print("checking " + a + "/"
						+ a.getInstance().getComputeService() + " for "
						+ r.getAttributeName());
				if (a.getInstance().getComputeService()
						.getAttribute(r.getAttributeName()) != null)
					System.out.println(", result: "
							+ r.checkValue(a.getInstance().getComputeService()
									.getAttribute(r.getAttributeName())
									.getValue()));
			}
	}
}
