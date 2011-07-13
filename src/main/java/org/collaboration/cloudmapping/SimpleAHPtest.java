package org.collaboration.cloudmapping;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.collaboration.cloudmapping.model.AMI;
import org.collaboration.cloudmapping.model.EC2Resource;
import org.collaboration.cloudmapping.model.Instance;
import org.collaboration.cloudmapping.model.ahp.configuration.Alternative;
import org.collaboration.cloudmapping.model.ahp.configuration.Criterion;
import org.collaboration.cloudmapping.model.ahp.configuration.Decision;
import org.collaboration.cloudmapping.model.ahp.configuration.Goal;
import org.collaboration.cloudmapping.logic.ahp.AnalyticHierarchyProcess;
import org.collaboration.cloudmapping.model.ahp.values.Evaluation;
import org.collaboration.cloudmapping.model.jama.Matrix;



public class SimpleAHPtest {

	/**
	 * @param args
	 */
	final static List<EC2Resource> resources = new ArrayList<EC2Resource>();
	final static List<AMI> amis = new ArrayList<AMI>();
	final static List<Instance> allAMIinstances = new ArrayList<Instance>();
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
			allAMIinstances.addAll(instances);
		}

		
		
		
		//Erstellen des Goals, der Decision, etc
		Decision  decision = new Decision();
		decision.setName("Cloud Computing Instance Selection");
		Goal goal = new Goal();
		goal.setName("Find the best Components for your needs");
		goal.setWeight(1);
		
		//Kriterien
		Criterion c1 = new Criterion("Performanz");
		Criterion c2 = new Criterion("Kosten");
		
		goal.addChild(c1);
		goal.addChild(c2);
		
		/*
		 * Erstellen der Alternativen und Hinzufügen zu der Decision
		 */
		for (int i = 0; i < allAMIinstances.size(); i++) {
			Alternative alt = new Alternative();
			alt.setName(allAMIinstances.get(i).getAmi().getName() + "###" + allAMIinstances.get(i).getEc2().getName());
			alt.setInstance(allAMIinstances.get(i));
			decision.addAlternative(alt);
		}
		
		
		
		
		//Beginn des AHP-Prozesses
		
		AnalyticHierarchyProcess ahp = new AnalyticHierarchyProcess(decision);
		Evaluation ev = new Evaluation();	
		
		System.out.println("\n Gewichtung der Kriterien");
		//Performanz ist 8 mal so wichtig wie die Kosten
		double[][] criteria ={
							{1, 		8}, 
							{1D / 8D, 	1}
							};
				
		Matrix cgoal = new Matrix(criteria);	
		
		ahp.setChildrenCriteriaWeights(goal,cgoal, 15 );
	}

}
