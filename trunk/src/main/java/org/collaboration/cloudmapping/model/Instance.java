package org.collaboration.cloudmapping.model;

public class Instance {
	
	final AMI ami;
	final EC2Resource ec2;
	final Double costPerHour; //e.g. 0.12 US-Dollar 
	final Double benchmark1;
	final Double benchmark2;
	
	
	
	
	public Instance(AMI ami, EC2Resource ec2) {
		super();
		this.ami = ami;
		this.ec2 = ec2;
		//a very simple way to evaluate performance
		this.costPerHour = this.ec2.getCostPerHour();
		this.benchmark1 = this.ec2.getBenchmark1();
		this.benchmark2 = this.ec2.getBenchmark2();
	}

	
	public Double getCostPerHour() {
		return costPerHour;
	}


	public Double getBenchmark1() {
		return benchmark1;
	}

	public Double getBenchmark2() {
		return benchmark2;
	}
	
	
	public AMI getAmi() {
		return ami;
	}

	public EC2Resource getEc2() {
		return ec2;
	}
	
	public String toString () {
		
		return 	"Ami: " + this.ami.getName() + "\n" + "Resource: " 
				+ this.ec2.getName() + "\n" + "Benchmark: " + this.costPerHour / this.benchmark1 + "\n \n";
	}
}
