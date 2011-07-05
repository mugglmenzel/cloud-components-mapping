package org.collaboration.cloudmapping.model.ahp.values;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.collaboration.cloudmapping.model.jama.Matrix;

public class CriterionWeightsMatrix {

	// the order of this list must not be changed
	private int numberOfCriteria = 1;

	private final Set<CriterionImportance> values = new HashSet<CriterionImportance>();

	private Matrix matrix = Matrix.identity(1, 1);

	private final HashMap<Double, Double> comparison = new HashMap<Double, Double>();

	/**
	 * @param criteriaOrder
	 */
	public CriterionWeightsMatrix(Set<CriterionImportance> weights) {
		super();
		comparison.put(-9D, 1D / 10D);
		comparison.put(-8D, 1D / 9D);
		comparison.put(-7D, 1D / 8D);
		comparison.put(-6D, 1D / 7D);
		comparison.put(-5D, 1D / 6D);
		comparison.put(-4D, 1D / 5D);
		comparison.put(-3D, 1D / 4D);
		comparison.put(-2D, 1D / 3D);
		comparison.put(-1D, 1D / 2D);
		comparison.put(0D, 1D);
		comparison.put(1D, 2D);
		comparison.put(2D, 3D);
		comparison.put(3D, 4D);
		comparison.put(4D, 5D);
		comparison.put(5D, 6D);
		comparison.put(6D, 7D);
		comparison.put(7D, 8D);
		comparison.put(8D, 9D);
		comparison.put(9D, 10D);

		if (weights != null) {
			values.addAll(weights);
			if (values.size() > 0) {
				numberOfCriteria = values.iterator().next().getParent()
						.getChildren().size();
				matrix = Matrix.identity(numberOfCriteria, numberOfCriteria);
				setMatrixWeights();
			}
		}
	}

	public Matrix getMatrix() {
		return matrix;
	}

	private void setMatrixWeights() {

		for (CriterionImportance value : values) {
			Double val = value.getComparisonAToB();
			if (comparison.containsKey(value.getComparisonAToB())) {
				val = comparison.get(value.getComparisonAToB());
			}
			matrix.set(value.getCritA(), value.getCritB(), val);
			if (val.doubleValue() != 0D)
				matrix.set(value.getCritB(), value.getCritA(), 1D / val);
		}

	}

	public String checkMatrix() {

		// check matrix size
		int size = numberOfCriteria;
		if (matrix.getColumnDimension() != size) {
			return "false column dimension";
		}

		if (matrix.getRowDimension() != size) {
			return "false row dimension";
		}

		// check diogonal in matrix (must be 1)
		for (int i = 0; i < size; i++) {
			if (matrix.get(i, i) != 1) {
				return "diagonal not 1";
			}
		}

		// check inverse
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (matrix.get(i, j) != (1 / matrix.get(j, i))) {
					return "false inverse";
				}
			}
		}

		return "matrix ok";
	}

}
