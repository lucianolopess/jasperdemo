package br.gov.jus.trt3.examples.wizard.controller;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * A few things need to be done while creating the conversational bean:
 * 
 * * bean has to be serializable
 * * bean class must be annotated with @ConversationScope annotation which tells
 * the container that the state of this particular bean will be maintained while
 * conversation is running.
 * * bean class must have a reference to Conversation object (injected via @Inject
 * annotation) which takes the responsability of managing the conversation context
 * (beginning and ending conversation with begin and end methods respectively)
 * * additionally @Named annotation makes the bean accessible from EL (in JSF page)
 * 
 * By default the Conversation object is in transient state. Invocation of the begin
 * method marks it as long-running (when a real conversation starts). Ending conversation
 * (by invoking end method) marks the Conversation object as transient. If we try to
 * invoke begin method when Conversation object is in long-running state an IllegalStateException
 * will be thrown. Similarly, we can not invoke end method while Conversation object
 * is in transient state, otherwise we will get the same exception. 
 * 
 * @author lucianof
 *
 */

@Named
@ConversationScoped
public class WebWizard implements Serializable {

	/**
	 * WebWizard
	 */
	private static final long serialVersionUID = 5598602736245017583L;

	@Inject
	private Conversation conversation;
	
	public void beginConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}
	
	public void endConversation() {
		if (conversation.isTransient()) {
			conversation.end();
		}
	}
	
}
