package org.collaboration.cloudmapping.model.ahp.configuration;



public class DecisionTemplate extends Decision {

	private String templateName;

	public DecisionTemplate() {
		super();
	}

	public DecisionTemplate(Decision dec) {
		super();
		Decision decision = dec.clone();
		setAlternatives(decision.getAlternatives());
		setGoals(decision.getGoals());
		setName(decision.getName());
		setDescription(decision.getDescription());
		setTemplateName("New Template");

	}

	public Decision getDecision() {
		return (Decision) this;
	}

	/**
	 * @param templateName
	 *            the templateName to set
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * @return the templateName
	 */
	public String getTemplateName() {
		return templateName;
	}

}
