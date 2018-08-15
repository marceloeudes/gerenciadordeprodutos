package br.com.cadastroprodutocliente.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public final class FacesMessageUtil {

	public static void addMenssage(Mensagens mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(mensagem.getSeverity(), mensagem.getSummary(), mensagem.getDetail()));
	}

}
