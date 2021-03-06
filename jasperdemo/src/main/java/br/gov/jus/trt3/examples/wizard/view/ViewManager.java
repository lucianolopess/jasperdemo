package br.gov.jus.trt3.examples.wizard.view;

import java.io.Serializable;
import java.util.List;

public class ViewManager implements Serializable {

	/**
	 * ViewManager
	 */
	private static final long serialVersionUID = -1063178756596362494L;

	private static final int START_INDEX = -1;
	private static final int FIRST_INDEX = 0;

	private List<View> views;
	private int currentIndex, lastIndex;

	public void setViews(List<View> views) {
		this.views = views;

		currentIndex = START_INDEX;
		lastIndex = views.size() - 1;
	}

	public List<View> getViews() {
		return views;
	}

	public View getViewAt(int index) {
		currentIndex = index;

		return views.get(index);
	}

	public View getNextView() {
		return views.get(++currentIndex);
	}

	public View getPreviousView() {
		return views.get(--currentIndex);
	}

	public boolean isFirst() {
		return currentIndex == FIRST_INDEX;
	}

	public boolean isLast() {
		return currentIndex == lastIndex;
	}

}
