package org.collaboration.cloudmapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Math;
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// add your amis and hardware components
		amis.add(new AMI("AMI_Name_123"));
		amis.add(new AMI("AMI_Name_234"));
		resources.add(new EC2Resource("m1.small", 5D, 10D, 5D));
		resources.add(new EC2Resource("m1.large", 10D, 12D, 18D));
		resources.add(new EC2Resource("m1.xlarge", 12D, 32D, 25D));


		/*
		 * Mapping AMIs and ec2 resources to get possible alternatives
		 */
		
		Alternative[][] alternatives = new Alternative[amis.size()][resources
				.size()];

		for (int i = 0; i < amis.size(); i++) { 

			for (int j = 0; j < resources.size(); j++) {

				alternatives[i][j] = new Alternative();

				alternatives[i][j].setInstance(new Instance(amis.get(i),
						resources.get(j)));
				alternatives[i][j].setDescription("AMI: "
						+ amis.get(i).getName() + "EC2_Resource: "
						+ resources.get(j).getName());
				alternatives[i][j].setName(amis.get(i).getName() + "###"
						+ resources.get(j).getName());

				System.out.println(alternatives[i][j].getName());

			}
		}
		
		
		for (int i = 0; i < amis.size(); i++) {

			/*
			 * At first we need to define the decision we want to make.After
			 * that we need do define goals we want to reach. This is important
			 * because different aspects need different consideration e.g. price
			 * and performance
			 */
			
			
			
			
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

			goal_1.addChild(g1c1);
			goal_1.addChild(g1c2);
			
			
			
			// one for costs
			Goal goal_2 = new Goal();
			goal_2.setName("Find the cheapest mapping for your needs");
			goal_2.setWeight(1);
			decision.addGoal(goal_2);

			// costs per hour
			Criterion g2c1 = new Criterion("costsPerHour");

			goal_2.addChild(g2c1);
			 
			
			
			
			// start with AHP

			AnalyticHierarchyProcess ahp = new AnalyticHierarchyProcess(decision);

			System.out.println("\n weights of criteria");

			// First we have weight our different criteria
			// in this moment via hardcode
			double[][] criteriaG1 = { { 1D, 1D }, { 1D , 1D } };
			Matrix cgoal_1 = new Matrix(criteriaG1);
			ahp.setChildrenCriteriaWeights(goal_1, cgoal_1, 4);

			// since we have only one criteria regarding goal 2 our matrix is a
			// bit degenerated
			
			double[][] criteriaG2 = { { 1 } };
			Matrix cgoal_2 = new Matrix(criteriaG2);
			ahp.setChildrenCriteriaWeights(goal_2, cgoal_2, 4);
			
			
			
			//Adding alternatives
			for (int j = 0; j < resources.size(); j++) {
				
				decision.addAlternative(alternatives[i][j]);
				
				System.out.println(alternatives[i][j].toString());
			}
			
			
			
			
			// Evaluation...

			// ...1.) of performance

			// for (int i = 0; i < 1; i++) {

			Evaluation evaluation = new Evaluation();
			
			evaluation.getEvaluations()
					.add(createBench1Matrix(alternatives, i));
			evaluation.getEvaluations()
					.add(createBench2Matrix(alternatives, i));
			
			
			
			
			try {
				
				System.out.println(decision.getGoals().iterator().next()
						.getLeafCriteria());
				Map<Alternative, Double> results = ahp.evaluate(evaluation);
				
				System.out.println(results);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// }
			/*
			 * // ...2.) der Perfomanz
			 * 
			 * double crit2[][] = { { 1, 12D / 10D, 32D / 10D }, { 10D / 12D, 1,
			 * 32D / 12D }, { 10D / 32D, 12D / 32D, 1 } };
			 * 
			 * Matrix crit2M = new Matrix(crit2);
			 * ev.getEvaluations().add(crit2M);
			 */

		}

	}

	private static Matrix createBench1Matrix(Alternative[][] alt, int set) {
		double[][] critEv = new double[resources.size()][resources.size()];
		double c;

		for (int a = 0; a < resources.size(); a++) {
			c = alt[set][a].getInstance().getBenchmark1();
			for (int b = 0; b < resources.size(); b++) {
				if (a <= b) {
					critEv[a][b] = alt[set][b].getInstance().getBenchmark1() / c;

				} else {
					critEv[a][b] = Math.pow(critEv[a][b], (double) 1);
				}

			}

		}

		Matrix bench1Evalue = new Matrix(critEv);
		System.out.println(bench1Evalue.toString());
		return bench1Evalue;
	}

	private static Matrix createBench2Matrix(Alternative[][] alt, int set) {
		double[][] critEv = new double[resources.size()][resources.size()];
		double c;

		for (int a = 0; a < resources.size(); a++) {
			c = alt[set][a].getInstance().getBenchmark2();
			for (int b = 0; b < resources.size(); b++) {
				if (a <= b) {
					critEv[a][b] = alt[set][b].getInstance().getBenchmark2() / c;
							

				} else {
					critEv[a][b] = Math.pow(critEv[a][b], (double) 1);
				}

			}

		}

		Matrix bench2Evalue = new Matrix(critEv);
		return bench2Evalue;
	}

	private static Matrix createCostMatrix(Alternative[][] alt, int set) {
		double[][] critEv = new double[resources.size()][resources.size()];
		double c;

		for (int a = 0; a < resources.size(); a++) {
			c = alt[set][a].getInstance().getCostPerHour();
			for (int b = 0; b < resources.size(); b++) {
				if (a <= b) {
					critEv[a][b] = c
							/ alt[set][b].getInstance().getCostPerHour();

				} else {
					critEv[a][b] = Math.pow(critEv[a][b], (double) 1);
				}

			}

		}
	}

}
