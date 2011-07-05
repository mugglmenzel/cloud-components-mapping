package org.collaboration.cloudmapping.model.ahp.configuration;



public class AlternativeValue {

	private Criterion criterion;

	private int alt;

	private Double value;

	/**
	 * 
	 */
	public AlternativeValue() {
		super();
	}

	/**
	 * @param alt
	 * @param value
	 */
	public AlternativeValue(int alt, Double value) {
		super();
		this.alt = alt;
		this.value = value;
	}


	/**
	 * @return the criterion
	 */
	public Criterion getCriterion() {
		return criterion;
	}

	/**
	 * @param criterion
	 *            the criterion to set
	 */
	public void setCriterion(Criterion criterion) {
		this.criterion = criterion;
	}

	/**
	 * @return the alt
	 */
	public int getAlt() {
		return alt;
	}

	/**
	 * @param alt
	 *            the alt to set
	 */
	public void setAlt(int alt) {
		this.alt = alt;
	}

	/**
	 * @return the value
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Double value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + alt;
		result = prime * result
				+ ((criterion == null) ? 0 : criterion.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AlternativeValue other = (AlternativeValue) obj;
		if (alt != other.alt) {
			return false;
		}
		if (criterion == null) {
			if (other.criterion != null) {
				return false;
			}
		} else if (!criterion.equals(other.criterion)) {
			return false;
		}
		return true;
	}

	public AlternativeValue clone() {
		return new AlternativeValue(getAlt(), getValue());
	}

}
