package org.collaboration.cloudmapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		// add your amis and hardware components
		amis.add(new AMI("AMI_Name_123"));
		amis.add(new AMI("AMI_Name_234"));
		resources.add(new EC2Resource("m1.small", 5D, 10D));
		resources.add(new EC2Resource("m1.large", 10D, 12D));
		resources.add(new EC2Resource("m1.xlarge", 12D, 32D));

		// merge amis and ec2-resozrces to get all combinations (which make
		// sense)
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

		List<Instance> sortedInst = new ArrayList<Instance>();
		StringBuilder result = new StringBuilder();

		Instance bestInstance;

		// sort instances by @param perfValue
		// no efficient algorithm! We should discuss better solutions
		for (AMI ami : amis) {
			bestInstance = amiInstances.get(ami).get(0);

			for (int i = 0; i < amiInstances.get(ami).size(); i++)
				if (bestInstance.getPerfValue() < amiInstances.get(ami).get(i)
						.getPerfValue())
					bestInstance = amiInstances.get(ami).get(i);

			sortedInst.add(bestInstance);
			// thats just to get the output in the right order (best on first
			// position)
			result.insert(0, bestInstance.toString());
		}

		System.out.println(result);

	}

}
