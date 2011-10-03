package org.collaboration.cloudmapping.model.mapping;

public class Requirement {
	
	private String name;
	private RequirementType reqType;
	private Object value;
	
	
	public Requirement(String name, RequirementType reqType, Object value) {
		super();
		this.name = name;
		this.reqType = reqType;
		this.value = value;
	}
	
	
	public Object getValue() {
		return value;
	}


	public void setValue(Object value) {
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
	
}
