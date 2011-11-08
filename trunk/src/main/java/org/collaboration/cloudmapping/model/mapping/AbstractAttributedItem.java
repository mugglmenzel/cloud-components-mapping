/**
 * 
 */
package org.collaboration.cloudmapping.model.mapping;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author mugglmenzel
 * 
 */
public abstract class AbstractAttributedItem implements IAttributedItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2650872196579507357L;
	
	private Set<Attribute<?>> attributes = new TreeSet<Attribute<?>>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(IAttributedItem o) {
		return ((o.getAttributes() != null && o.getAttributes() != null) ? getAttributes()
				.size() - o.getAttributes().size()
				: (getAttributes() != null ? 1 : -1));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.collaboration.cloudmapping.model.mapping.IAttributedItem#getAttribute
	 * (org.collaboration.cloudmapping.model.mapping.EAttribute)
	 */
	@Override
	public Attribute<?> getAttribute(IEAttribute attributeName) {
		for (Attribute<?> a : getAttributes())
			if (a.getName().equals(attributeName))
				return a;
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.collaboration.cloudmapping.model.mapping.IAttributedItem#getAttributes
	 * ()
	 */
	@Override
	public Set<Attribute<?>> getAttributes() {
		return attributes;
	}

}
