package org.collaboration.cloudmapping.logic.ahp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.collaboration.cloudmapping.model.ahp.configuration.Alternative;
import org.collaboration.cloudmapping.model.ahp.configuration.Criterion;
import org.collaboration.cloudmapping.model.ahp.configuration.CriterionType;
import org.collaboration.cloudmapping.model.ahp.configuration.Decision;
import org.collaboration.cloudmapping.model.ahp.configuration.Goal;
import org.collaboration.cloudmapping.model.ahp.values.AlternativeEvaluation;
import org.collaboration.cloudmapping.model.ahp.values.AlternativeValuesMatrix;
import org.collaboration.cloudmapping.model.ahp.values.AlternativeWeightsMatrix;
import org.collaboration.cloudmapping.model.ahp.values.CriterionWeightsMatrix;
import org.collaboration.cloudmapping.model.ahp.values.Evaluation;
import org.collaboration.cloudmapping.model.jama.Matrix;


public class AnalyticHierarchyProcess {

	private final int DEFAULT_PRECISION = 5;

	private Decision decision;

	private Set<AlternativeEvaluation> alternativeEvaluations = new HashSet<AlternativeEvaluation>();

	/**
	 * @return the alternativeEvaluations
	 */
	private Set<AlternativeEvaluation> getAlternativeEvaluations() {
		return alternativeEvaluations;
	}

	public AnalyticHierarchyProcess(Decision decision) {
		super();
		this.decision = decision;
		System.out.println("created new AHP with decision: " + decision);
	}

	public Map<Alternative, Double> evaluate(Evaluation evaluation)
			throws Exception {
		return this.evaluate(evaluation, DEFAULT_PRECISION);
	}

	// TODO: neuen Exceptiontyp einf¸hren!
	public Map<Alternative, Double> evaluate(Evaluation evaluation,
			int precision) throws Exception {
		if (!sanityCheck(evaluation))
			throw new Exception("Given decision model not complete.");

		List<Goal> goals = decision.getGoals();
		Goal goal = goals.iterator().next();

		// ChildrenWeights
		setChildrenCriteriaWeights(goal);
		for (Criterion c : goal.getAllDescendants())
			setChildrenCriteriaWeights(c);

		List<Criterion> leafCriteria = goal.getLeafCriteria();

		setAlternativeEvaluations(new HashSet<AlternativeEvaluation>());

		if (evaluation == null) {
			evaluation = new Evaluation();
			for (Criterion leaf : leafCriteria) {
				if (CriterionType.QUALITATIVE.equals(leaf.getType())) {
					AlternativeWeightsMatrix m = new AlternativeWeightsMatrix(
							leaf.getImportanceAlternatives(), decision
									.getAlternatives().size());
					evaluation.getEvaluations().add(m.getMatrix());
				} else {
					AlternativeValuesMatrix m = new AlternativeValuesMatrix(
							leaf.getValuesAlternatives(), decision
									.getAlternatives().size());
					evaluation.getEvaluations().add(m.getMatrix());
				}

			}
		}

		int i = 0;
		for (Matrix m : evaluation.getEvaluations()) {
			// System.out.print("Matrix to Eigen for "
			// + getDecision().getAlternatives().size()
			// + " alternatives, criterion " + leafCriteria.get(i) + ":");
			// m.print(2, 2);
			if (m != null) {
				Vector<Double> normalized = calculateEigenvector(m, precision);
				System.out.println(normalized.toString());
				System.out.println("------");

				for (int j = 0; j < normalized.size(); j++)
					getAlternativeEvaluations().add(
							new AlternativeEvaluation(leafCriteria.get(i),
									getDecision().getAlternatives().get(j),
									normalized.get(j)));
			}
			i++;
		}

		System.out.println("evaluations: " + getAlternativeEvaluations());

		Map<Alternative, Double> alternativeMap = new HashMap<Alternative, Double>();
		for (AlternativeEvaluation ce : getAlternativeEvaluations()) {
			alternativeMap
					.put(ce.getAlternative(),
							new Double(
									(alternativeMap.get(ce.getAlternative()) != null ? alternativeMap
											.get(ce.getAlternative())
											.doubleValue() : 0D)
											+ (ce.getValue() * ce
													.getCriterion()
													.getGlobalWeight())));
		}

		return alternativeMap;
	}

	/**
	 * @param alternativeEvaluations
	 *            the alternativeEvaluations to set
	 */
	private void setAlternativeEvaluations(
			Set<AlternativeEvaluation> alternativeEvaluations) {
		this.alternativeEvaluations = alternativeEvaluations;
	}

	// TODO: Sanity-Check implementieren
	private boolean sanityCheck(Evaluation eval) {
		boolean result = getDecision() != null;
		result &= getDecision().getAlternatives().size() > 0;
		result &= getDecision().getGoals().size() == 1;
		/*
		 * result &= eval.getEvaluations().size() ==
		 * getDecision().getGoals().get(0).getLeafCriteria().size(); for (Matrix
		 * m : eval.getEvaluations()) { result &= m.getColumnDimension() ==
		 * m.getRowDimension(); result &= m.getColumnDimension() ==
		 * getDecision().getAlternatives() .size(); }
		 */
		return result;
	}


	public void setChildrenCriteriaWeights(Criterion parent) {
		setChildrenCriteriaWeights(parent, DEFAULT_PRECISION);
	}

	public void setChildrenCriteriaWeights(Criterion parent, int precision) {
		if (parent.hasChildren()) {
			CriterionWeightsMatrix critM = new CriterionWeightsMatrix(
					parent.getImportanceChildren());

			setChildrenCriteriaWeights(parent, critM.getMatrix(), precision);
		}
	}

	public void setChildrenCriteriaWeights(Criterion parent, Matrix m) {
		this.setChildrenCriteriaWeights(parent, m, DEFAULT_PRECISION);
	}

	public void setChildrenCriteriaWeights(Criterion parent, Matrix m,
			int precision) {
		Vector<Double> criteriaWeights = calculateEigenvector(m, precision);

		if (criteriaWeights.size() > 0
				&& criteriaWeights.size() >= parent.getChildren().size()) {
			Iterator<Criterion> itiLeaf = parent.getChildren().iterator();
			int i = 0;
			while (itiLeaf.hasNext()) {
				Criterion leafCriterion = itiLeaf.next();
				leafCriterion.setWeight(criteriaWeights.get(i));
				System.out.println(leafCriterion.getName() + ": local = "
						+ leafCriterion.getWeight() + ", global = "
						+ leafCriterion.getGlobalWeight());
				i++;
			}
		}
	}

	private Vector<Double> calculateEigenvector(Matrix m, int preciseness) {

		// System.out.print("m: " );
		// m.print(3, 2);

		// Variablen zur späteren Berechnung des normalisierten Eigenvektors
		Matrix normalisierterEigenVektorAlt = new Matrix(m.getRowDimension(), 1);
		for (int i = 0; i < m.getRowDimension(); i++) {
			normalisierterEigenVektorAlt.set(i, 0, 0.0);
		}
		Matrix normalisierterEigenVektor = null;

		// die Matrix wird so oft quadriert bis nur noch eine geringe
		// Abweichungen
		// beim Eigenvektor auftreten (maximal 1000 mal)
		for (int a = 0; a < 1000; a++) {

			// Quadratur der Matrix
			m = m.times(m);
			// System.out.print("m²: " );
			// m.print(3, 2);

			// Variablen zur Berechnung des Eigenvektors
			int columnDimension = m.getColumnDimension();
			int rowDimension = m.getRowDimension();
			double reihenGesamtSumme = 0.0;
			Matrix eigenVektor = new Matrix(rowDimension, 1);
			normalisierterEigenVektor = new Matrix(rowDimension, 1);

			// berechnet den Eigenvektor
			for (int i = 0; i < rowDimension; i++) {
				double reihenSumme = 0.0;
				for (int j = 0; j < columnDimension; j++) {
					reihenSumme += m.get(i, j);
				}
				eigenVektor.set(i, 0, reihenSumme);
				reihenGesamtSumme += reihenSumme;
			}

			// normalisiert den Eigenvektor
			for (int i = 0; i < eigenVektor.getRowDimension(); i++) {
				double value = eigenVektor.get(i, 0) / reihenGesamtSumme;
				normalisierterEigenVektor.set(i, 0, value);
			}
			// System.out.print("eigenvektor: ");
			// eigenVektor.print(3, 2);
			// System.out.print(", normalisiert: ");
			// normalisierterEigenVektor.print(3, 2);

			// Differenz zwischen neuen und altem Eigenvektor
			Matrix eigenVektorDifferenz = normalisierterEigenVektor
					.minus(normalisierterEigenVektorAlt);

			// falls ein Wert der Eigenvektordifferenz kleiner als 0.0001 ist
			// wird die Schleife beendet
			boolean isFinished = true;
			for (int i = 0; i < rowDimension; i++) {
				if (Math.abs(eigenVektorDifferenz.get(i, 0)) >= Math.pow(10, -1
						* preciseness)) {
					isFinished = false;
				}
			}
			if (isFinished) {
				break;
			}

			normalisierterEigenVektorAlt = normalisierterEigenVektor;
		}

		Vector<Double> eigenVektor = new Vector<Double>();
		for (int i = 0; i < normalisierterEigenVektor.getRowDimension(); i++) {
			eigenVektor.add(normalisierterEigenVektor.get(i, 0));
		}

		return eigenVektor;

	}

	public Decision getDecision() {
		return decision;
	}

	public void setDecision(Decision decision) {
		this.decision = decision;
	}

	// TODO: wegen nicht möglicher JAMA lib Nutzung erstmal auskommentiert
	// public void calculateLocalCriteriaWeight(CriteriaMatrix criteriaMatrix) {
	//
	// Vector<Double> normalizedEigenVector =
	// calculateEigenvector(criteriaMatrix.getMatrix());
	// List<Criterion> criteriaList = criteriaMatrix.getCriteriaList();
	// for (int i = 0; i < criteriaList.size(); i++) {
	// criteriaList.get(i).setLocalWeight(normalizedEigenVector.get(i));
	// }
	//
	// }

}
