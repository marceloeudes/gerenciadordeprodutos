package br.com.cadastroprodutocliente.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.cadastroprodutocliente.model.Usuario;
import br.com.cadastroprodutocliente.util.Paginas;

@ManagedBean
@SessionScoped
public class SessaoBean {

	private Usuario usuario;

	@PostConstruct
	public void inicializar() {
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
	}

	public String deslogar() {
		usuario = new Usuario();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
		return Paginas.PAGINA_LOGIN;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
