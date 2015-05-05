package br.gov.jus.trt3.examples.wizard.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.gov.jus.trt3.examples.wizard.model.User;
import br.gov.jus.trt3.examples.wizard.outcome.OutCome;
import br.gov.jus.trt3.examples.wizard.service.UserService;
import br.gov.jus.trt3.examples.wizard.view.View;

@Named
public class UserWizard extends AbstractWizard {

	/**
	 * UserWizard
	 */
	private static final long serialVersionUID = 5154761309115144696L;

	@Inject
	private UserService userService;

	private User user = new User();

	@Override
	protected List<View> prepareViews() {
		List<View> views = new ArrayList<View>();
	      views.add(new View("First step", OutCome.FIRST_STEP));
	      views.add(new View("Second step", OutCome.SECOND_STEP));
	      views.add(new View("Third step", OutCome.THIRD_STEP));
	      views.add(new View("Summary", OutCome.SUMMARY));
	 
	      return views;
	}
	      
	@Override
	protected String complete() {
		userService.processUserData(user);
		return OutCome.WIZARD_FINISH;
	}

	@Override
	protected String clean() {
		return OutCome.WIZARD_CANCEL;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
