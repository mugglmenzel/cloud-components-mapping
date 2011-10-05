/**
 * 
 */
package org.collaboration.cloudmapping.model.mapping.requirements;

import java.util.Map;

import org.collaboration.cloudmapping.model.mapping.EAttribute;

/**
 * @author mugglmenzel
 * @param <T>
 *
 */
public interface IRequirementItem<T> extends Comparable<T> {

	public Map<EAttribute, Object> getAttributes();
	
}
