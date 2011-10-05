/**
 * 
 */
package org.collaboration.cloudmapping.model.mapping;

import java.io.Serializable;

/**
 * @author mugglmenzel
 * 
 */
public class Attribute<T> implements Serializable, Cloneable,
		Comparable<Attribute<T>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1423409082864357874L;

	private EAttribute name;

	private Comparable<T> value;

	/**
	 * @param name
	 * @param value
	 */
	public Attribute(EAttribute name, Comparable<T> value) {
		super();
		this.name = name;
		this.value = value;
	}

	public EAttribute getName() {
		return name;
	}

	public void setName(EAttribute name) {
		this.name = name;
	}

	public Comparable<T> getValue() {
		return value;
	}

	public void setValue(Comparable<T> value) {
		this.value = value;
	}

	@Override
	public int compareTo(Attribute<T> o) {
		return getValue().equals(o.getValue()) ? 0 : getValue().toString()
				.compareTo(o.getValue().toString());
	}

}
