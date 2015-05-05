package br.gov.jus.trt3.examples.wizard.view;

public class View {

	private String name, outcome;
	private boolean available = false;

	public View(String name, String outcome) {
		this.name = name;
		this.outcome = outcome;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
}
