package org.collaboration.cloudmapping.model.mapping;


public class Instance {

	private Appliance appliance;

	private ComputeService computeResource;


	/**
	 * @param appliance
	 * @param computeResource
	 */
	public Instance(Appliance appliance, ComputeService computeResource) {
		super();
		this.appliance = appliance;
		this.computeResource = computeResource;
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
	 * @return the computeResource
	 */
	public ComputeService getComputeResource() {
		return computeResource;
	}



	/**
	 * @param computeResource the computeResource to set
	 */
	public void setComputeResource(ComputeService computeResource) {
		this.computeResource = computeResource;
	}



	public String toString() {

		return "Appliance: " + this.appliance.getName() + "\n" + "Resource: "
				+ this.computeResource.getName() + "\n \n";
	}
}
