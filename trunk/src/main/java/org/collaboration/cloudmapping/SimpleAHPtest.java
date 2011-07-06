package org.collaboration.cloudmapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.collaboration.cloudmapping.model.AMI;
import org.collaboration.cloudmapping.model.EC2Resource;
import org.collaboration.cloudmapping.model.Instance;
import org.collaboration.cloudmapping.model.ahp.configuration.Criterion;
import org.collaboration.cloudmapping.model.ahp.configuration.Decision;
import org.collaboration.cloudmapping.model.ahp.configuration.Goal;


public class SimpleAHPtest {

	/**
	 * @param args
	 */
	final static List<EC2Resource> resources = new ArrayList<EC2Resource>();
	final static List<AMI> amis = new ArrayList<AMI>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// add your amis and hardware components
		amis.add(new AMI("AMI_Name_123"));
		amis.add(new AMI("AMI_Name_234"));
		resources.add(new EC2Resource("m1.small", 5D, 10D));
		resources.add(new EC2Resource("m1.large", 10D, 12D));
		resources.add(new EC2Resource("m1.xlarge", 12D, 32D));

		
		Map<AMI, List<Instance>> amiInstances = new HashMap<AMI, List<Instance>>();

		if (amis.size() == 0 || resources.size() == 0) {
			System.out.println("No Input!");
		}

		for (int i = 0; i < amis.size(); i++) {
			List<Instance> instances = new ArrayList<Instance>();
			for (int a = 0; a < resources.size(); a++) {
				instances.add(new Instance(amis.get(i), resources.get(a)));
			}
			amiInstances.put(amis.get(i), instances);
		}

		
		
		
		
		Decision  decision = new Decision();
		decision.setName("Cloud Computing Provider Selection");
		Goal goal = new Goal();
		goal.setName("Find the best Cloud Provider");
		goal.setWeight(1);
		Criterion c1 = new Criterion("Kosten");
		goal.addChild(c1);
		
	}

}
