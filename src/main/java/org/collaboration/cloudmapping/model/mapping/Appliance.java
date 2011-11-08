/**
 * 
 */
package org.collaboration.cloudmapping.model.mapping;

import java.util.HashSet;
import java.util.Set;

import org.collaboration.cloudmapping.model.AMI;

/**
 * @author mugglmenzel
 * 
 */
public abstract class Appliance implements IAttributedItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5718934871710445135L;

	private String name;

	private Set<Attribute<?>> attributes = new HashSet<Attribute<?>>();

	public Appliance(String name) {
		super();
		this.name = name;
	}

	public Appliance(String name, Set<Attribute<?>> attributes) {
		super();
		this.name = name;
		this.attributes = attributes;
	}

	public void setAttributes(Set<Attribute<?>> attributes) {
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
	public Set<Attribute<?>> getAttributes() {
		return attributes;
	}

	@Override
	public Attribute<?> getAttribute(IEAttribute attributeName) {
		for (Attribute<?> a : getAttributes())
			if (a.getName().equals(attributeName))
				return a;
		return null;
	}

	@Override
	public int compareTo(IAttributedItem o) {
		return ((o.getAttributes() != null && o.getAttributes() != null) ? getAttributes()
				.size() - o.getAttributes().size()
				: (getAttributes() != null ? 1 : -1));
	}

}
