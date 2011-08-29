package org.collaboration.cloudmapping.model.ahp.values;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.collaboration.cloudmapping.model.ahp.configuration.AlternativeValue;
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
 *         $HeadURL: https://aotearoadecisions.googlecode.com/svn/trunk/src/main/java/de/fzi/aotearoa/shared/model/ahp/values/AlternativeValuesMatrix.java $
 *
 *
 */

public class AlternativeValuesMatrix implements Serializable {

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
