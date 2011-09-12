package org.collaboration.cloudmapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.collaboration.cloudmapping.model.AMI;
import org.collaboration.cloudmapping.model.DeploymentManager;
import org.collaboration.cloudmapping.model.EC2Resource;
import org.collaboration.cloudmapping.model.Instance;
import org.collaboration.cloudmapping.model.ValueComparator;
import org.collaboration.cloudmapping.model.ahp.configuration.Alternative;
import org.collaboration.cloudmapping.model.ahp.configuration.Criterion;
import org.collaboration.cloudmapping.model.ahp.configuration.Decision;
import org.collaboration.cloudmapping.model.ahp.configuration.Goal;
import org.collaboration.cloudmapping.model.ahp.configuration.GoalType;
import org.collaboration.cloudmapping.logic.ahp.AnalyticHierarchyProcess;
import org.collaboration.cloudmapping.model.ahp.values.Evaluation;
import org.collaboration.cloudmapping.model.jama.Matrix;

public class SimpleAHPtest {

	/**
	 * @param args
	 */
	final static List<EC2Resource> resources = new ArrayList<EC2Resource>();
	final static List<AMI> amis = new ArrayList<AMI>();
	private static List<Instance> instances = new ArrayList<Instance>();
	
	
	final static String acceskey = "";
	final static String secretkey = "";
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// add your amis and hardware components
		amis.add(new AMI("ami-27fb3f4e"));
		amis.add(new AMI("AMI_Name_234"));
		resources.add(new EC2Resource("m1.small", 5D, 30D, 50D));
		resources.add(new EC2Resource("m1.large", 10D, 12D, 18D));
		resources.add(new EC2Resource("m1.xlarge", 25D, 32D, 25D));
		


		
		//TODO: change to "amis.size()"
		for (int i = 0; i < 1; i++) {
		
			
			/*
			 * At first we need to define the decision we want to make.After
			 * that we need do define goals we want to reach. This is important
			 * because different aspects need different consideration e.g. price
			 * and performance
			 */
			
			
			
			
			Decision decision = new Decision();
			decision.setName("Optimal cloud component mapping");

			
			
			
			
			
			
			/*
			 * Mapping AMIs and ec2 resources to get possible alternatives
			 */	
			Alternative[] alternatives = new Alternative[resources.size()];

				for (int j = 0; j < resources.size(); j++) {

					alternatives[j] = new Alternative();

					alternatives[j].setInstance(new Instance(amis.get(i),
							resources.get(j)));
					alternatives[j].setDescription("AMI: "
							+ amis.get(i).getName() + "EC2_Resource: "
							+ resources.get(j).getName());
					alternatives[j].setName(amis.get(i).getName() + "###"
							+ resources.get(j).getName());
					
					decision.addAlternative(alternatives[j]);
					System.out.println(alternatives[j].getName());

				}
			
				
				
				
				
			// adding goals
			// one for performance
			Goal goal_1 = new Goal();
			goal_1.setName("Find the most powerfull mapping");
			goal_1.setWeight(1);
			goal_1.setGoalType(GoalType.POSITIVE);
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
			goal_2.setGoalType(GoalType.NEGATIVE);
			decision.addGoal(goal_2);

			// costs per hour
			Criterion g2c1 = new Criterion("costsPerHour");

			goal_2.addChild(g2c1);
			 
			
			
			
			// start with AHP

			AnalyticHierarchyProcess ahp = new AnalyticHierarchyProcess(decision);

			System.out.println("\n weights of criteria");

			//TODO: Following in a UI -> ask user for his preferences
			
			// First we have weight our different criteria
			// in this moment via hardcode
			double[][] criteriaG1 = { { 1D, 1D}, { 1D , 1D} };
			Matrix cgoal_1 = new Matrix(criteriaG1);
			ahp.setChildrenCriteriaWeights(goal_1, cgoal_1, 4);

			// since we have only one criteria regarding goal 2 our matrix is a
			// bit degenerated
			
			
			double[][] criteriaG2 = { { 1 } };
			Matrix cgoal_2 = new Matrix(criteriaG2);
			ahp.setChildrenCriteriaWeights(goal_2, cgoal_2, 4);
			
			
			
			// Evaluation...

			// ...1.) of performance

			// for (int i = 0; i < 1; i++) {

			List <Evaluation> evaluations = new ArrayList<Evaluation>();
			Evaluation evG1 = new Evaluation();
			evG1.getEvaluations().add(createBench1Matrix(alternatives, i));
			evG1.getEvaluations().add(createBench2Matrix(alternatives, i));
			evaluations.add(evG1);
			
			
			Evaluation evG2 = new Evaluation();
			evG2.getEvaluations().add(createCostMatrix(alternatives, i));
			evaluations.add(evG2);
			
			
			try {
				System.out.println("\n ----results--- \n");
				System.out.println(decision.getGoals().iterator().next()
						.getLeafCriteria());
				Map<Alternative, Double> results = ahp.evaluate(evaluations);
				ValueComparator vcp = new ValueComparator(results);
				TreeMap<Alternative, Double> sortedResults = new TreeMap<Alternative, Double>(vcp);
				sortedResults.putAll(results);
				System.out.println("\n" + results);
				System.out.println("\n" + sortedResults + "\n");
			
				//TODO: result festhalten, richtige resource wählen!!!
				instances.add(sortedResults.firstKey().getInstance());
				System.out.println("The best choice for your needs is: \n " + sortedResults.firstEntry().getKey().toString() + "\n" + "with an absolut value of: \n" + sortedResults.firstEntry().getValue());
				System.out.println("\n ##### END OF PROCESS ##### \n");
				} catch (Exception e) {
					e.printStackTrace();
				}
			

			

		}
		
		System.out.println("Start deploying... \n");
		DeploymentManager dpm = new DeploymentManager(instances.get(0), acceskey, secretkey);
		dpm.deployInstance("test_01");
	}

	private static Matrix createBench1Matrix(Alternative[] alt, int set) {
		double[][] critEv = new double[resources.size()][resources.size()];
		double c;

		for (int a = 0; a < resources.size(); a++) {
			c = alt[a].getInstance().getBenchmark1();
			for (int b = 0; b < resources.size(); b++) {
				
				critEv[a][b] = c / alt[b].getInstance().getBenchmark1();
				System.out.println("[" + critEv[a][b] + "]");
			}
			System.out.println("\n");
		}
		Matrix bench1Evalue = new Matrix(critEv);
		
		return bench1Evalue;
	}

	private static Matrix createBench2Matrix(Alternative[] alt, int set) {
		double[][] critEv = new double[resources.size()][resources.size()];
		double c;
		for (int a = 0; a < resources.size(); a++) {
			c = alt[a].getInstance().getBenchmark2();
			for (int b = 0; b < resources.size(); b++) {
				critEv[a][b] = c / alt[b].getInstance().getBenchmark2();
			}

		}

		Matrix bench2Evalue = new Matrix(critEv);
		return bench2Evalue;
	}

	private static Matrix createCostMatrix(Alternative[] alt, int set) {
		double[][] critEv = new double[resources.size()][resources.size()];
		double c;

		for (int a = 0; a < resources.size(); a++) {
			c = alt[a].getInstance().getCostPerHour();
			for (int b = 0; b < resources.size(); b++) {
				critEv[a][b] = 1 / (c / alt[b].getInstance().getCostPerHour());
			}

		}
		Matrix costEvalue = new Matrix(critEv);
		return costEvalue;
	}

}
