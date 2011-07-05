package org.collaboration.cloudmapping.model.ahp.configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.collaboration.cloudmapping.model.ahp.values.AlternativeImportance;
import org.collaboration.cloudmapping.model.ahp.values.CriterionImportance;

/**
 * 
 * @author menzel A Criterion is a node in an AHP hierarchy and also an aspect
 *         to evaluate an Alternative upon.
 * 
 * 
 */

public class Criterion {

	protected String name;

	protected String description;
	
	protected String url;

	protected CriterionType type;

	protected double weight = 1D;

	private Criterion parent;

	protected Set<CriterionImportance> importanceChildren = new HashSet<CriterionImportance>();

	protected Set<AlternativeImportance> importanceAlternatives = new HashSet<AlternativeImportance>();

	protected Set<AlternativeValue> valuesAlternatives = new HashSet<AlternativeValue>();

	protected List<Criterion> children = new ArrayList<Criterion>();

	public Criterion() {
		super();
	}

	public Criterion(String name) {
		super();
		this.name = name;
	}

	public void addChild(Criterion c) {
		getChildren().add(c);
		c.setParent(this);
	}

	public int countCriteria() {
		Iterator<Criterion> iti = children.iterator();
		int help = 0;
		while (iti.hasNext()) {
			iti.next();
			help++;
		}
		return help;
	}

	public String deleteCriterion(String criterionName) {
		Iterator<Criterion> iti = this.getChildren().iterator();
		Criterion help = null;
		while (iti.hasNext()) {
			help = iti.next();
			if (criterionName.equals(help.getName())) {
				iti.remove();
				return "removed";
			} else {
				if ((help.deleteCriterion(criterionName)) != null) {
					return "removed";
				}
			}
		}

		return null;
	}

	public ArrayList<Criterion> getAllDescendants() {
		Iterator<Criterion> iti = this.getChildren().iterator();
		ArrayList<Criterion> allDescendants = new ArrayList<Criterion>();
		Criterion help = null;
		while (iti.hasNext()) {
			help = iti.next();
			allDescendants.add(help);
			if (help.hasChildren()) {
				allDescendants.addAll(help.getAllDescendants());
			}
		}
		return allDescendants;
	}

	public List<Criterion> getChildren() {
		return children;
	}

	/**
	 * @author frauen
	 * @return
	 * 
	 *         This methods returns every level of the criteria tree as a
	 *         separate list as a whole list.
	 * 
	 */
	public List<List<Criterion>> getCriteriaByLevels() {
		List<List<Criterion>> criteriaByLevelList = new ArrayList<List<Criterion>>();

		if (getChildren() != null && getChildren().size() > 0) {
			criteriaByLevelList.add(this.getChildren());

			List<Criterion> childrenCriteria = new ArrayList<Criterion>();
			childrenCriteria.addAll(this.getChildren());

			List<Criterion> newChildrenCriteria = new ArrayList<Criterion>();
			newChildrenCriteria.addAll(childrenCriteria);

			boolean isLeafLeft = true;

			while (isLeafLeft) {
				isLeafLeft = false;
				childrenCriteria.clear();
				childrenCriteria.addAll(newChildrenCriteria);
				newChildrenCriteria.clear();
				for (Criterion criterion : childrenCriteria) {
					if (!criterion.isLeaf()) {
						criteriaByLevelList.add(criterion.getChildren());
						newChildrenCriteria.addAll(criterion.getChildren());
						for (Criterion criterion2 : criterion.getChildren()) {
							if (!criterion2.isLeaf()) {
								isLeafLeft = true;
							}
						}
					}
				}
			}

		}
		return criteriaByLevelList;
	}

	public Criterion getCriterion(String criterionName) {
		Iterator<Criterion> iti = this.getChildren().iterator();
		Criterion help = null;
		Criterion help2 = null;
		while (iti.hasNext()) {
			help = iti.next();
			if (criterionName.equals(help.getName()))
				return help;
			else if ((help2 = help.getCriterion(criterionName)) != null)
				return help2;
		}

		return null;
	}
	
	public Criterion getParentCriterion(String criterionName) {
		Iterator<Criterion> iti = this.getChildren().iterator();
		Criterion help = null;
		Criterion help2 = null;
		while (iti.hasNext()) {
			help = iti.next();
			if (criterionName.equals(help.getName()))
				return this;
			else if ((help2 = help.getParentCriterion(criterionName)) != null)
				return help2;
		}

		return null;
	}

	public String getDescription() {
		return description;
	}

	public double getGlobalWeight() {
		return hasParent() ? getParent().getGlobalWeight() * getWeight()
				: getWeight();
	}


	/**
	 * @return the importanceAlternatives
	 */
	public Set<AlternativeImportance> getImportanceAlternatives() {
		return importanceAlternatives;
	}

	/**
	 * @return the importanceChildren
	 */
	public Set<CriterionImportance> getImportanceChildren() {
		return importanceChildren;
	}

	public List<Criterion> getLeafCriteria() {
		return getLeafCriteria(null);
	}

	public List<Criterion> getLeafCriteria(CriterionType type) {
		List<Criterion> leafCriteria = new ArrayList<Criterion>();

		if (isLeaf() && (type == null || type.equals(getType()))) {
			leafCriteria.add(this);
		} else {
			Iterator<Criterion> iti = getChildren().iterator();
			while (iti.hasNext()) {
				leafCriteria.addAll(iti.next().getLeafCriteria(type));
			}
		}
		return leafCriteria;
	}

	public String getName() {
		return name;
	}

	/**
	 * @return the parent
	 */
	public Criterion getParent() {
		return parent;
	}

	public CriterionType getType() {
		return type;
	}

	/**
	 * @return the valuesAlternatives
	 */
	public Set<AlternativeValue> getValuesAlternatives() {
		return valuesAlternatives;
	}

	public double getWeight() {
		return weight;
	}

	public boolean hasChildren() {
		return !getChildren().isEmpty();
	}

	public boolean hasParent() {
		return parent != null;
	}

	public boolean isLeaf() {
		return getChildren().isEmpty();
	}

	public void setChildren(List<Criterion> children) {
		this.children = children;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @param importanceAlternatives
	 *            the importanceAlternatives to set
	 */
	public void setImportanceAlternatives(
			Set<AlternativeImportance> importanceAlternatives) {
		this.importanceAlternatives = importanceAlternatives;
	}

	/**
	 * @param importanceChildren
	 *            the importanceChildren to set
	 */
	public void setImportanceChildren(
			Set<CriterionImportance> importanceChildren) {
		this.importanceChildren = importanceChildren;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(Criterion parent) {
		this.parent = parent;
	}

	public void setType(CriterionType type) {
		this.type = type;
	}

	/**
	 * @param valuesAlternatives
	 *            the valuesAlternatives to set
	 */
	public void setValuesAlternatives(Set<AlternativeValue> valuesAlternatives) {
		this.valuesAlternatives = valuesAlternatives;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getName() + "/parent:"
				+ (hasParent() ? getParent().getName() : "<no parent>")
				+ "/children:[ " + getChildren() + "]/importance:["
				+ importanceChildren + "]";
	}

	public Double getImportanceChild(int i, int j) {
		CriterionImportance test = new CriterionImportance(i, j, null);
		test.setParent(this);
		System.out.println("looking for " + test + " in "
				+ getImportanceChildren());
		if (!getImportanceChildren().contains(test))
			return null;
		for (CriterionImportance ci : getImportanceChildren())
			if (test.equals(ci))
				return ci.getComparisonAToB();
		return null;
	}

	public Double getImportanceAlternative(int i, int j) {
		AlternativeImportance test = new AlternativeImportance(i, j, null);
		test.setCriterion(this);
		if (!getImportanceAlternatives().contains(test))
			return null;
		for (AlternativeImportance ci : getImportanceAlternatives())
			if (test.equals(ci))
				return ci.getComparisonAToB();
		return null;
	}

	public Double getValueAlternative(int i) {
		AlternativeValue test = new AlternativeValue(i, null);
		test.setCriterion(this);
		if (!getValuesAlternatives().contains(test))
			return null;
		for (AlternativeValue av : getValuesAlternatives())
			if (test.equals(av))
				return av.getValue();
		return null;
	}

	public Criterion clone() {
		Criterion c = new Criterion(getName());
		c.setDescription(getDescription());
		c.setType(getType());
		c.setWeight(getWeight());
		for (Criterion ch : getChildren())
			c.addChild(ch.clone());
		for (CriterionImportance ci : getImportanceChildren())
			c.getImportanceChildren().add(ci.clone());
		for (AlternativeImportance ai : getImportanceAlternatives())
			c.getImportanceAlternatives().add(ai.clone());
		for (AlternativeValue av : getValuesAlternatives())
			c.getValuesAlternatives().add(av.clone());

		return c;
	}

}
