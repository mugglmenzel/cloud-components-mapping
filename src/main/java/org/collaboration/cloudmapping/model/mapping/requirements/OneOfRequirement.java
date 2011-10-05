/**
 * 
 */
package org.collaboration.cloudmapping.model.mapping.requirements;

import java.util.SortedSet;

import org.collaboration.cloudmapping.model.mapping.EAttribute;

/**
 * @author mugglmenzel
 * @param <T>
 *
 */
public class OneOfRequirement<T> extends Requirement<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -281977232271734055L;

	public OneOfRequirement(String name,
			EAttribute attribute, SortedSet<IRequirementItem<T>> values) {
		super(name, RequirementType.ONEOUTOF, attribute, values);
	}

	@Override
	public boolean checkValue(T item) {
		return getValues().contains(item);
	}

}
