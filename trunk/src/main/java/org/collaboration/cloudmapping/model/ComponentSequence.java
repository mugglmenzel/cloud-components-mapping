/**
 * 
 */
package org.collaboration.cloudmapping.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.collaboration.cloudmapping.model.mapping.Component;

/**
 * @author menzel
 * 
 */
public class ComponentSequence {

	private List<Component> sequence;

	private Double value = new Random().nextDouble();

	/**
	 * @param sequence
	 */
	public ComponentSequence(List<Component> sequence) {
		super();
		this.sequence = sequence;
	}

	/**
	 * @return the sequence
	 */
	public List<Component> getSequence() {
		return sequence;
	}

	public Double getValue() {
		return this.value;
	}

	public boolean isValid() {
		for (int i = 0; i < getSequence().size(); i++)
			if (getSequence().get(i).getRequiredComponents().size() > 0) {
				List<Component> previous = new ArrayList<Component>(getSequence().subList(0, i));
				previous
						.retainAll(getSequence().get(i).getRequiredComponents());
				if (previous.size() != getSequence().get(i)
						.getRequiredComponents().size())
					return false;
			}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getSequence().toString();
	}

}
