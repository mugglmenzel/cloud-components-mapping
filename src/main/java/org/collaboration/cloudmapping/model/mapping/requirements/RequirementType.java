package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.requirements;

import java.io.Serializable;

public enum RequirementType implements Serializable {
	EQUALS("equalsRequirement"), MINIMUM("minRequirement"), MAXIMUM(
			"maxRequirement"), ONEOUTOF("oneOfRequirement");

	String typeName;

	private RequirementType(String type) {
		setTypeName(type);
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String name) {
		this.typeName = name;
	}
}
