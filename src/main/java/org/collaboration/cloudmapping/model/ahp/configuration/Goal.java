package org.collaboration.cloudmapping.model.ahp.configuration;

import org.collaboration.cloudmapping.model.ahp.values.AlternativeImportance;
import org.collaboration.cloudmapping.model.ahp.values.CriterionImportance;

/**
 * 
 * @author menzel While a Decision can be made under consideration of multiple
 *         Goals, the fitness of an Alternative regarding one Goal is determined
 *         by evaluating an Alternative under consideration of many aspects, the
 *         Criteria.
 * 
 */

public class Goal extends Criterion {

	private Decision decision;

	/**
	 * @param decision
	 *            the decision to set
	 */
	public void setDecision(Decision decision) {
		this.decision = decision;
	}

	/**
	 * @return the decision
	 */
	public Decision getDecision() {
		return decision;
	}

	public Goal() {
		super();
	};

	public Goal(String name) {
		super(name);
	}

	public Goal clone() {
		Goal g = new Goal(getName());
		g.setDescription(getDescription());
		g.setType(getType());
		g.setWeight(getWeight());
		for (Criterion ch : getChildren())
			g.addChild(ch.clone());
		for (CriterionImportance ci : getImportanceChildren())
			g.getImportanceChildren().add(ci.clone());
		for (AlternativeImportance ai : getImportanceAlternatives())
			g.getImportanceAlternatives().add(ai.clone());
		for (AlternativeValue av : getValuesAlternatives())
			g.getValuesAlternatives().add(av.clone());

		return g;
	}

}
