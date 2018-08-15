package br.com.cadastroprodutocliente.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.cadastroprodutocliente.model.Usuario;

public class Autorizador implements PhaseListener{

	private static final long serialVersionUID = -2645651070492443080L;

	@Override
	public void afterPhase(PhaseEvent evento) {

		FacesContext context = evento.getFacesContext();
		String nomePagina = context.getViewRoot().getViewId();
		
		System.out.println(nomePagina);
		
		if ("/login.xhtml".equals(nomePagina)) {
			return;
		}
		
		Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
		
		if (!SiteUtil.emptyOrNull(usuarioLogado)) {
			return;
		}
		
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, Paginas.PAGINA_LOGIN);
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		System.out.println("FASE" + event.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
