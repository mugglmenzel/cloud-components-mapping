package org.collaboration.cloudmapping.model.ahp.configuration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author menzel A Decision is the main class of the data model. It represents
 *         a decision itself and encapsulates multiple Goals that are pursued
 *         and multiple Alternatives that are potential solutions.
 * 
 */

public class Decision implements Cloneable {


	protected String name;

	protected String description;

	protected List<Alternative> alternatives = new ArrayList<Alternative>();

	protected List<Goal> goals = new ArrayList<Goal>();

	public Decision() {
		super();
	}

	public Decision(String decisionName) {
		super();
		this.name = decisionName;
	}

	public void addAlternative(Alternative alternative) {
		getAlternatives().add(alternative);
	}

	public void addGoal(Goal goal) {
		getGoals().add(goal);
	}

	public int countGoals() {
		return getGoals().size();
	}

	public Alternative getAlternative(String alternativeName) {
		for (Alternative a : getAlternatives())
			if (a.getName().equals(alternativeName))
				return a;
		return null;
	}

	public List<Alternative> getAlternatives() {
		return alternatives;
	}

	public Criterion getCriterion(String criterionName) {
		if (getGoal(criterionName) != null)
			return getGoal(criterionName);
		return getCriterion(getGoals().get(0), criterionName);
	}
	
	public Criterion getCriterion(String goalName, String criterionName) {
		if (goalName.equals(criterionName) && getGoal(criterionName) != null)
			return getGoal(criterionName);
		return getCriterion(getGoal(goalName), criterionName);
	}

	public Criterion getCriterion(Criterion goal, String criterionName) {
		if (goal.getName().equals(criterionName))
			return goal;
		return goal.getCriterion(criterionName);
	}
	
	public Criterion getParentCriterion(String criterionName) {
		if (getGoal(criterionName) != null)
			return null;
		return getParentCriterion(getGoals().get(0), criterionName);
	}
	
	public Criterion getParentCriterion(String goalName, String criterionName) {
		if (goalName.equals(criterionName))
			return null;
		return getParentCriterion(getGoal(goalName), criterionName);
	}
	
	public Criterion getParentCriterion(Criterion goal, String criterionName) {
		if (goal.getName().equals(criterionName))
			return null;
		return goal.getParentCriterion(criterionName);
	}

	public String getDescription() {
		return description;
	}

	public Goal getGoal(String name) {
		Iterator<Goal> iti = getGoals().iterator();
		Goal help = null;
		while (iti.hasNext()) {
			help = iti.next();
			if (help.getName() == name)
				return help;
		}
		return null;
	}

	public List<Goal> getGoals() {
		return goals;
	}

	public String getName() {
		return name;
	}


	public void setAlternatives(List<Alternative> alternatives) {
		this.alternatives = alternatives;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setGoals(List<Goal> goals) {
		this.goals = goals;
	}

	public void setName(String name) {
		this.name = name;
	};


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getName() + ", " + getAlternatives() + ", " + getGoals();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	public Decision clone() {
		Decision dec = new Decision(getName());
		dec.setDescription(getDescription());

		for (Alternative a : getAlternatives())
			dec.addAlternative(a.clone());
		for (Goal g : getGoals())
			dec.addGoal(g.clone());

		return dec;
	}

}
