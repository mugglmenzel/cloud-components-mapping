/**
 * 
 */
package org.collaboration.cloudmapping.model.mapping;

/**
 * @author mugglmenzel
 *
 */
public class CombinationValue implements Comparable<CombinationValue> {

	private Appliance appliance;
	
	private ComputeService service;
	
	private Double applianceValue = 0D;
	
	private Double serviceValue = 0D;

	public CombinationValue(Appliance appliance, ComputeService service,
			Double applianceValue, Double serviceValue) {
		super();
		this.appliance = appliance;
		this.service = service;
		this.applianceValue = applianceValue;
		this.serviceValue = serviceValue;
	}

	/**
	 * @return the appliance
	 */
	public Appliance getAppliance() {
		return appliance;
	}

	/**
	 * @param appliance the appliance to set
	 */
	public void setAppliance(Appliance appliance) {
		this.appliance = appliance;
	}

	/**
	 * @return the service
	 */
	public ComputeService getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(ComputeService service) {
		this.service = service;
	}

	/**
	 * @return the applianceValue
	 */
	public Double getApplianceValue() {
		return applianceValue;
	}

	/**
	 * @param applianceValue the applianceValue to set
	 */
	public void setApplianceValue(Double applianceValue) {
		this.applianceValue = applianceValue;
	}

	/**
	 * @return the serviceValue
	 */
	public Double getServiceValue() {
		return serviceValue;
	}

	/**
	 * @param serviceValue the serviceValue to set
	 */
	public void setServiceValue(Double serviceValue) {
		this.serviceValue = serviceValue;
	}

	
	public Double getOverallValue() {
		return 0.5*(getApplianceValue() == null ? 0 : getApplianceValue()) + 0.5*(getServiceValue() == null ? 0 : getServiceValue());
	}
	
	@Override
	public int compareTo(CombinationValue o) {
		return o == null ? (this == null ? 0 : 1) : getOverallValue().compareTo(o.getOverallValue());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[(" + getAppliance().getName() + ", " + getService().getName() + "), " + getOverallValue() + "]";
	}

	
	
}
