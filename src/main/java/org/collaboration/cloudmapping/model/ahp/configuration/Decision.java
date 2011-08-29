package org.collaboration.cloudmapping.model.ahp.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.collaboration.cloudmapping.model.ahp.values.GoalImportance;

/**
 * @author mugglmenzel A Decision is the main class of the data model. It represents a
 *         decision itself and encapsulates multiple Goals that are pursued and
 *         multiple Alternatives that are potential solutions.
 * 
 *         Author: Michael Menzel (mugglmenzel)
 * 
 *         Last Change:
 *           
 *           By Author: $Author: mugglmenzel $ 
 *         
 *           Revision: $Revision: 169 $ 
 *         
 *           Date: $Date: 2011-08-05 16:46:17 +0200 (Fr, 05 Aug 2011) $
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
 *         $HeadURL: https://aotearoadecisions.googlecode.com/svn/trunk/src/main/java/de/fzi/aotearoa/shared/model/ahp/configuration/Decision.java $
 *
 */

public class Decision implements Serializable, Cloneable {

	protected String userId;

	protected String name;

	protected String description;

	protected List<Alternative> alternatives = new ArrayList<Alternative>();

	protected List<Goal> goals = new ArrayList<Goal>();

	protected Set<GoalImportance> importanceGoals = new HashSet<GoalImportance>();

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
		for (Goal goal : getGoals()) {
			Criterion a = getCriterion(goal, criterionName);
			if (a != null)
				return a;
		}
		return null;
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
		for (Goal goal : getGoals()) {
			Criterion a = getParentCriterion(goal, criterionName);
			if (a != null)
				return a;
		}
		return null;
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

	public List<List<Criterion>> getCriteriaByLevels() {
		List<List<Criterion>> result = new ArrayList<List<Criterion>>();
		result.add(new ArrayList<Criterion>(getGoals()));
		for (Goal g : getGoals())
			result.addAll(g.getCriteriaByLevels());

		return result;
	}

	public List<Criterion> getLeafCriteria() {
		List<Criterion> result = new ArrayList<Criterion>();
		for (Goal g : getGoals())
			result.addAll(g.getLeafCriteria());

		return result;
	}

	public List<Criterion> getLeafCriteria(CriterionType type) {
		List<Criterion> result = new ArrayList<Criterion>();
		for (Goal g : getGoals())
			result.addAll(g.getLeafCriteria(type));

		return result;
	}

	public String getDescription() {
		return description;
	}

	public Goal getGoal(String name) {
		Iterator<Goal> iti = getGoals().iterator();
		Goal help = null;
		while (iti.hasNext()) {
			help = iti.next();
			if (help.getName().equals(name))
				return help;
		}
		return null;
	}

	public String deleteGoal(String goalName) {
		Iterator<Goal> iti = this.getGoals().iterator();
		while (iti.hasNext()) {
			if (goalName.equals(iti.next().getName())) {
				iti.remove();
				return "removed";
			}
		}
		return null;
	}

	public List<Goal> getGoals() {
		return goals;
	}

	public String getName() {
		return name;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
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

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getName() + ", " + getAlternatives() + ", " + getGoals() + ", "
				+ getImportanceGoals();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	public Decision clone() {
		Decision dec = new Decision(getName());
		dec.setDescription(getDescription());
		dec.setUserId(getUserId());

		for (Alternative a : getAlternatives())
			dec.addAlternative(a.clone());
		for (Goal g : getGoals())
			dec.addGoal(g.clone());

		return dec;
	}

	/**
	 * @return the importanceGoals
	 */
	public Set<GoalImportance> getImportanceGoals() {
		return importanceGoals;
	}

	/**
	 * @param importanceGoals
	 *            the importanceGoals to set
	 */
	public void setImportanceGoals(Set<GoalImportance> importanceGoals) {
		this.importanceGoals = importanceGoals;
	}

	public GoalImportance getImportanceChild(int i, int j) {
		GoalImportance test = new GoalImportance(i, j, null, null);
		test.setDecision(this);
		System.out.println("decision looking for " + test + " in "
				+ getImportanceGoals());
		if (!getImportanceGoals().contains(test))
			return null;
		for (GoalImportance gi : getImportanceGoals())
			if (test.equals(gi))
				return gi;
		return null;
	}

}
