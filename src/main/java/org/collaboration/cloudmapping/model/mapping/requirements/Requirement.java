package org.collaboration.cloudmapping.model.mapping.requirements;

import java.io.Serializable;
import java.util.Set;

import org.collaboration.cloudmapping.model.mapping.EAttribute;

public abstract class Requirement<T> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4265980830540891007L;

	private String name;
	
	private RequirementType reqType;
	
	private EAttribute attributeName;
	
	private IRequirementItem<T> value;
	
	private Set<IRequirementItem<T>> values;
	
	
	public Requirement(String name, RequirementType reqType, EAttribute attribute, IRequirementItem<T> value) {
		super();
		this.name = name;
		this.reqType = reqType;
		this.attributeName = attribute;
		this.value = value;
	}
	
	public Requirement(String name, RequirementType reqType, EAttribute attribute, Set<IRequirementItem<T>> values) {
		super();
		this.name = name;
		this.reqType = reqType;
		this.attributeName = attribute;
		this.setValues(values);
	}
	
	
	public IRequirementItem<T> getValue() {
		return value;
	}


	public void setValue(IRequirementItem<T> value) {
		this.value = value;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RequirementType getReqType() {
		return reqType;
	}
	public void setReqType(RequirementType reqType) {
		this.reqType = reqType;
	}
	
	/**
	 * @return the attribute
	 */
	public EAttribute getAttributeName() {
		return attributeName;
	}


	/**
	 * @param attribute the attribute to set
	 */
	public void setAttributeName(EAttribute attribute) {
		this.attributeName = attribute;
	}


	/**
	 * @return the values
	 */
	public Set<IRequirementItem<T>> getValues() {
		return values;
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(Set<IRequirementItem<T>> values) {
		this.values = values;
	}

	public abstract boolean checkValue(T item);
	
}
