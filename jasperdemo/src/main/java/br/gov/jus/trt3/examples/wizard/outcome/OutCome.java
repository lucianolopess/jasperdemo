package br.gov.jus.trt3.examples.wizard.outcome;

public class OutCome {

	private static final String WIZARD = "/views/registeruser/";
	private static final String REDIRECT = "?faces-redirect=true";
	public static final String FIRST_STEP = WIZARD + "firststep" + REDIRECT;
	public static final String SECOND_STEP = WIZARD + "secondstep" + REDIRECT;
	public static final String THIRD_STEP = WIZARD + "thirdstep" + REDIRECT;
	public static final String SUMMARY = WIZARD + "summary" + REDIRECT;
	public static final String WIZARD_FINISH = WIZARD + "wizardfinish"
			+ REDIRECT;
	public static final String WIZARD_CANCEL = WIZARD + "wizardcancel"
			+ REDIRECT;

}
