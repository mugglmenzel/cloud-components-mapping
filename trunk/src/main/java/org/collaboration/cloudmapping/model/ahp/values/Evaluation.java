package org.collaboration.cloudmapping.model.ahp.values;

import java.util.ArrayList;
import java.util.List;

import org.collaboration.cloudmapping.model.ahp.configuration.Decision;
import org.collaboration.cloudmapping.model.jama.Matrix;

public class Evaluation {

	// evaluations
	private List<Matrix> evaluations = new ArrayList<Matrix>();

	
	
	/**
	 * 
	 */
	public Evaluation() {
		super();
	}

	/**
	 * @return the evaluations
	 */
	public List<Matrix> getEvaluations() {
		return evaluations;
	}

	public void update(Decision decision) {
		if (decision.getGoals().size() > 0)
			for (int i = 0; i < decision.getGoals().get(0).getLeafCriteria()
					.size(); i++)
				if (getEvaluations().size() <= i)
					getEvaluations().add(
							new Matrix(decision.getAlternatives().size(),
									decision.getAlternatives().size()));
				else if (getEvaluations().get(i) == null)
					getEvaluations().set(
							i,
							new Matrix(decision.getAlternatives().size(),
									decision.getAlternatives().size()));
				else if (getEvaluations().get(i).getColumnDimension() != decision
						.getAlternatives().size()
						|| getEvaluations().get(i).getRowDimension() != decision
								.getAlternatives().size())
					getEvaluations().set(
							i,
							Matrix.identity(decision.getAlternatives().size(),
									decision.getAlternatives().size()));
	}

}
