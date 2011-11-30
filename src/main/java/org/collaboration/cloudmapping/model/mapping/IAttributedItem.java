/**
 * 
 */
package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;

import java.io.Serializable;
import java.util.Set;

/**
 * @author mugglmenzel
 *
 */
public interface IAttributedItem extends Cloneable, Serializable, Comparable<IAttributedItem> {

	public Attribute<?> getAttribute(IEAttribute attributeName);
	
	public Set<Attribute<?>> getAttributes();
	
}
