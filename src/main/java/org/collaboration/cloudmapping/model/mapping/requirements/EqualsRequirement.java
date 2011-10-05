/**
 * 
 */
package org.collaboration.cloudmapping.model.mapping.requirements;

import org.collaboration.cloudmapping.model.mapping.EAttribute;

/**
 * @author mugglmenzel
 * @param <T>
 * 
 */
public class EqualsRequirement<T> extends Requirement<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7161931209823985667L;

	public EqualsRequirement(String name, EAttribute attribute,
			IRequirementItem<T> value) {
		super(name, RequirementType.EQUALS, attribute, value);
	}

	@Override
	public boolean checkValue(T item) {
		return getValue().equals(item);
	}

}
