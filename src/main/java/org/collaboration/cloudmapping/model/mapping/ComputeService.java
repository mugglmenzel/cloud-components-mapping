package de.eorg.cumulusgenius.shared.cloudmapping.model.mapping;


public abstract class ComputeService extends AbstractAttributedItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5770949839975306149L;

	private String name;

	/**
	 * @param name
	 */
	public ComputeService(String name) {
		super();
		this.name = name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return getName();
	}

}
