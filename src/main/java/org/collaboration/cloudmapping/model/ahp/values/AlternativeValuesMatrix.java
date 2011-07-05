package org.collaboration.cloudmapping.model.ahp.values;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.collaboration.cloudmapping.model.ahp.configuration.AlternativeValue;
import org.collaboration.cloudmapping.model.jama.Matrix;

public class AlternativeValuesMatrix {

	// the order of this list must not be changed
	private int numberOfAlternatives = 1;

	private final Set<AlternativeValue> values = new HashSet<AlternativeValue>();

	private Matrix matrix = Matrix.identity(1, 1);

	/**
	 * @param values
	 */
	public AlternativeValuesMatrix(Set<AlternativeValue> values, int alternatives) {
		super();

		if (values != null) {
			this.values.addAll(values);
			if (this.values.size() > 0) {
				numberOfAlternatives = alternatives;
				matrix = Matrix.identity(numberOfAlternatives,
						numberOfAlternatives);
				setMatrixWeights();
			}
		}
	}

	public Matrix getMatrix() {
		return matrix;
	}

	private void setMatrixWeights() {

		Vector<Double> vals = new Vector<Double>(numberOfAlternatives);
		for(int i=0; i < numberOfAlternatives; i++) vals.add(null);
		
		for (AlternativeValue value : values) {
			vals.set(value.getAlt(), value.getValue() == 0 ? 1D : value.getValue());
		}

		for (int i = 0; i < values.size() - 1; i++) {
			for (int j = i + 1; j < values.size(); j++) {
					matrix.set(i, j, vals.get(i)/vals.get(j));
					matrix.set(j, i, vals.get(j) / vals.get(i));
			}
		}

	}

	public String checkMatrix() {

		// check matrix size
		int size = numberOfAlternatives;
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
