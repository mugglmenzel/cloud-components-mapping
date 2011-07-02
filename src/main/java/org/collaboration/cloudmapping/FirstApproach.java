package org.collaboration.cloudmapping;

import java.util.ArrayList;
import java.util.List;

import org.collaboration.cloudmapping.model.AMI;
import org.collaboration.cloudmapping.model.EC2Resource;
import org.collaboration.cloudmapping.model.Instance;


public class FirstApproach {
	
	final static List<EC2Resource> resources = new ArrayList<EC2Resource>();
	final static List<AMI> amis = new ArrayList<AMI>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//add your amis and hardware components
		amis.add(new AMI("ami_Name_123"));
		resources.add(new EC2Resource("m1.small", 5D, 10D));
		resources.add(new EC2Resource("m1.large", 10D, 12D));
		
		//merge amis and ec2-resozrces to get all combinations (which make sense)
		List<Instance> instances = new ArrayList<Instance>();
		if (amis.size() == 0 || resources.size() == 0) {
			System.out.println("No Input!");
		}
			
		for (int i = 0; i < amis.size(); i++) {
			for (int a = 0; a < resources.size(); a++) {
				instances.add(new Instance(amis.get(i), resources.get(a)) );
			}
		}
		
		
		List<Instance> sortedInst = new ArrayList<Instance>();
		StringBuilder result = new StringBuilder();
		
		Instance bestInstance;
		Instance comperator;
		
		//sort instances by @param perfValue 
		while (!instances.isEmpty()){
			bestInstance = instances.get(0);
			for (int i = 1; i < sortedInst.size(); i++){
				comperator = instances.get(i);
				if (comperator.getPerfValue() > bestInstance.getPerfValue()){
					bestInstance = comperator;
				}
			}
			sortedInst.add(bestInstance);
			instances.remove(bestInstance);
			//thats just to get the output in the right order (best on first position)
			result.insert(0,bestInstance.toString());
			
		}
		
		
		
		System.out.println(result);
		

	}

	
	
	
	
	
	
}
