package br.edu.ifpb.project.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class Application {

	/*
	 * Adiciona mensagens ao contexto JSF
	 */
	public static void addMessage(String message, FacesMessage.Severity severity) {
		FacesMessage fm = new FacesMessage(message);
		FacesContext fc = FacesContext.getCurrentInstance();
		fm.setSeverity(severity);
		fc.getExternalContext().getFlash().setKeepMessages(true);

		fc.addMessage(null, fm);
	}
}
