/**
 * 
 */
package org.collaboration.cloudmapping.model.mapping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author mugglmenzel
 * 
 */
public abstract class Appliance {

	private String name;
	private Set<Attribute> attributes = new HashSet<Attribute>();
	private HashMap<String,Object> hashedAttr = new HashMap<String,Object> ();

	public Appliance(String name) {
		super();
		this.name = name;
	}
	
	public Appliance(String name, Set<Attribute> attributes) {
		super();
		this.name = name;
		this.attributes = attributes;
	}

	public HashMap<String, Object> getHashedAttr() {
		return hashedAttr;
	}

	public void setHashedAttr(HashMap<String, Object> hashedAttr) {
		for(Attribute a : attributes){
			hashedAttr.put(a.getName(), a.getValue());
		}
	}
	

	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the attributes
	 */
	public Set<Attribute> getAttributes() {
		return attributes;
	}

	
	
}
