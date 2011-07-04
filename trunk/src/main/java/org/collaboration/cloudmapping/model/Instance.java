package org.collaboration.cloudmapping.model;

public class Instance {
	
	final AMI ami;
	
	final EC2Resource ec2;

	final double perfValue;
	
	public Instance(AMI ami, EC2Resource ec2) {
		super();
		this.ami = ami;
		this.ec2 = ec2;
		//a very simple way to evaluate performance
		this.perfValue = this.ec2.getPerformace() / this.ec2.getCostPerHour(); 
	}

	public double getPerfValue() {
		return perfValue;
	}
	
	
	public String toString () {
		
		return 	"Ami: " + this.ami.getName() + "\n" + "Resource: " 
				+ this.ec2.getName() + "\n" + "Benchmark: " + this.perfValue + "\n \n";
	}
}
