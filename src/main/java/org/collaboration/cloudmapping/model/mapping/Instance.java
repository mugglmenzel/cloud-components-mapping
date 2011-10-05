package org.collaboration.cloudmapping.model.mapping;


public class Instance {

	private Appliance appliance;

	private ComputeService computeService;


	/**
	 * @param appliance
	 * @param computeResource
	 */
	public Instance(Appliance appliance, ComputeService computeResource) {
		super();
		this.appliance = appliance;
		this.computeService = computeResource;
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
	public ComputeService getComputeService() {
		return computeService;
	}



	/**
	 * @param computeResource the computeResource to set
	 */
	public void setComputeService(ComputeService computeResource) {
		this.computeService = computeResource;
	}



	public String toString() {

		return "Appliance: " + this.appliance.getName() + "\n" + "Resource: "
				+ this.computeService.getName() + "\n \n";
	}
}
