/**
 * 
 */
package org.collaboration.cloudmapping.model.mapping;

import java.io.Serializable;
import java.util.Set;

/**
 * @author mugglmenzel
 *
 */
public interface IAttributedItem extends Cloneable, Serializable, Comparable<IAttributedItem> {

	public Attribute<?> getAttribute(EAttribute attributeName);
	
	public Set<Attribute<?>> getAttributes();
	
}
