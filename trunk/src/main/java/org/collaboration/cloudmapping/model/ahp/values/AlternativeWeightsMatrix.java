package org.collaboration.cloudmapping.model.ahp.values;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.collaboration.cloudmapping.model.jama.Matrix;

/**
 * 
 * @author mugglmenzel
 *
 *         Author: Michael Menzel (mugglmenzel)
 * 
 *         Last Change:
 *           
 *           By Author: $Author: mugglmenzel $ 
 *         
 *           Revision: $Revision: 166 $ 
 *         
 *           Date: $Date: 2011-08-05 15:49:44 +0200 (Fr, 05 Aug 2011) $
 * 
 *         License:
 *         
 *         Copyright 2011 Forschungszentrum Informatik FZI / Karlsruhe Institute
 *         of Technology
 * 
 *         Licensed under the Apache License, Version 2.0 (the "License"); you
 *         may not use this file except in compliance with the License. You may
 *         obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 *         Unless required by applicable law or agreed to in writing, software
 *         distributed under the License is distributed on an "AS IS" BASIS,
 *         WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *         implied. See the License for the specific language governing
 *         permissions and limitations under the License.
 * 
 *         
 *         SVN URL: 
 *         $HeadURL: https://aotearoadecisions.googlecode.com/svn/trunk/src/main/java/de/fzi/aotearoa/shared/model/ahp/values/AlternativeWeightsMatrix.java $
 *
 */

public class AlternativeWeightsMatrix implements Serializable {

	// the order of this list must not be changed
	private int numberOfAlternatives = 1;

	private final Set<AlternativeImportance> values = new HashSet<AlternativeImportance>();

	private Matrix matrix = Matrix.identity(1, 1);

	private final HashMap<Double, Double> comparison = new HashMap<Double, Double>();

	/**
	 * @param criteriaOrder
	 */
	public AlternativeWeightsMatrix(Set<AlternativeImportance> weights, int alternatives) {
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
				numberOfAlternatives = alternatives;
				matrix = Matrix.identity(numberOfAlternatives, numberOfAlternatives);
				setMatrixWeights();
			}
		}
	}

	public Matrix getMatrix() {
		return matrix;
	}

	private void setMatrixWeights() {

		for (AlternativeImportance value : values) {
			Double val = value.getComparisonAToB();
			if (comparison.containsKey(value.getComparisonAToB())) {
				val = comparison.get(value.getComparisonAToB());
			}
			matrix.set(value.getAltA(), value.getAltB(), val);
			if (val.doubleValue() != 0D)
				matrix.set(value.getAltB(), value.getAltA(), 1D / val);
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
