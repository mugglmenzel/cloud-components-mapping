/**
 * 
 */
package org.collaboration.cloudmapping.model.mapping.requirements;

import org.collaboration.cloudmapping.model.mapping.EAttribute;

/**
 * @author mugglmenzel
 * 
 */
public class MinRequirement<T> extends Requirement<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7375902195281171750L;

	public MinRequirement(String name, EAttribute attribute,
			IRequirementItem<T> value) {
		super(name, RequirementType.MINIMUM, attribute, value);
	}

	@Override
	public boolean checkValue(T item) {
		return getValue().compareTo(item) < 0
				|| getValue().compareTo(item) == 0;
	}

}
