package org.collaboration.cloudmapping.model;

public class Instance {
	
	final AMI ami;
	final EC2Resource ec2;
	final Double costPerHour; //e.g. 0.12 US-Dollar 
	final Double performance;
	
	
	
	
	public Instance(AMI ami, EC2Resource ec2) {
		super();
		this.ami = ami;
		this.ec2 = ec2;
		//a very simple way to evaluate performance
		this.costPerHour = this.ec2.getCostPerHour();
		this.performance = this.ec2.getPerformance();
	}

	
	public Double getCostPerHour() {
		return costPerHour;
	}


	public Double getPerformance() {
		return performance;
	}


	
	
	public AMI getAmi() {
		return ami;
	}

	public EC2Resource getEc2() {
		return ec2;
	}
	
	public String toString () {
		
		return 	"Ami: " + this.ami.getName() + "\n" + "Resource: " 
				+ this.ec2.getName() + "\n" + "Benchmark: " + this.costPerHour / this.performance + "\n \n";
	}
}
