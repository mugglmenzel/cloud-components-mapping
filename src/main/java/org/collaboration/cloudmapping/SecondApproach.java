package org.collaboration.cloudmapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.collaboration.cloudmapping.logic.ahp.AnalyticHierarchyProcess;
import org.collaboration.cloudmapping.model.AMI;
import org.collaboration.cloudmapping.model.EC2Resource;
import org.collaboration.cloudmapping.model.Instance;
import org.collaboration.cloudmapping.model.ahp.configuration.Alternative;
import org.collaboration.cloudmapping.model.ahp.configuration.Criterion;
import org.collaboration.cloudmapping.model.ahp.configuration.Decision;
import org.collaboration.cloudmapping.model.ahp.configuration.Goal;
import org.collaboration.cloudmapping.model.ahp.values.Evaluation;
import org.collaboration.cloudmapping.model.jama.Matrix;

public class SecondApproach {

	/**
	 * @param args
	 * 
	 */

	final static List<EC2Resource> resources = new ArrayList<EC2Resource>();
	final static List<AMI> amis = new ArrayList<AMI>();

	public static void main(String[] args) {

		amis.add(new AMI("AMI_Name_123"));

		resources.add(new EC2Resource("m1.small", 5D, 10D, 5D));
		resources.add(new EC2Resource("m1.large", 10D, 12D, 18D));

		Decision decision = new Decision();
		decision.setName("Optimal cloud component mapping");

		// adding goals
		// one for performance
		Goal goal_1 = new Goal();
		goal_1.setName("Find the most powerfull mapping");
		goal_1.setWeight(1);
		decision.addGoal(goal_1);
	

		// lets say there are two benchmarking values
		Criterion g1c1 = new Criterion("BenchmarkValue1");
		Criterion g1c2 = new Criterion("BenchmarkValue2");
		Criterion g1c3 = new Criterion("CostPerHour");

		goal_1.addChild(g1c1);
		goal_1.addChild(g1c2);
		goal_1.addChild(g1c3);

		
	

	
		
		
		
		
		AnalyticHierarchyProcess ahp = new AnalyticHierarchyProcess(decision);

		System.out.println("\n weights of criteria");

		// First we have weight our different criteria
		// in this moment via hardcode
		double[][] criteriaG1 = { { 1D, 1D, -1D}, { 1D, 1D, -1D }, {-1D, -1D, 1D }};
		Matrix cgoal_1 = new Matrix(criteriaG1);
		ahp.setChildrenCriteriaWeights(goal_1, cgoal_1, 15);
		
		
		

		// Alternatives
		Alternative[] alternatives = new Alternative[resources.size()];

		for (int i = 0; i < resources.size(); i++) {

			alternatives[i] = new Alternative();

			alternatives[i].setInstance(new Instance(amis.get(0), resources
					.get(i)));
			alternatives[i].setDescription("AMI: " + amis.get(0).getName()
					+ "EC2_Resource: " + resources.get(i).getName());
			alternatives[i].setName(amis.get(0).getName() + "###"
					+ resources.get(i).getName());
			decision.addAlternative(alternatives[i]);
			System.out.println(alternatives[i].getName());

		}

		Evaluation ev = new Evaluation();

		double crit1[][] = { { 1, 12D / 10D }, { 10D / 12D, 1 } };
		Matrix crit1M = new Matrix(crit1);
		ev.getEvaluations().add(crit1M);

		double crit2[][] = { { 1, 18D / 5D }, { 5D / 18D, 1 } };
		Matrix crit2M = new Matrix(crit2);
		ev.getEvaluations().add(crit2M);
		
		double crit3[][] = {{1D, 10D/5D}, {5D/10D,1D}};
		Matrix crit3M = new Matrix(crit3);
		ev.getEvaluations().add(crit3M);

		try {

			System.out.println(decision.getGoals().iterator().next()
					.getLeafCriteria());
			Map<Alternative, Double> results = ahp.evaluate(ev);

			System.out.println(results);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
