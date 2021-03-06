package br.com.cadastroprodutocliente.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.cadastroprodutocliente.model.NivelAcesso;
import br.com.cadastroprodutocliente.model.Usuario;
import br.com.cadastroprodutocliente.util.Paginas;
import br.com.cadastroprodutocliente.util.SessaoUtil;

@ManagedBean
@ViewScoped
public class UsuarioBean {

	private Usuario usuarioLogado;
	private boolean administrador;
	
	@PostConstruct
	public void inicializar() {
		usuarioLogado = obterUsuarioSessao();
	}
	
	public Usuario obterUsuarioSessao() {
		Usuario usuarioSessao = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuario");
		if (usuarioSessao.getNivelAcesso().getId() == NivelAcesso.ADMINISTRADOR.getId()) {
			administrador = true;
		} else {
			administrador = false;
		}
		return usuarioSessao;
	}
	
	public String navegarAlterarSenha() {
		SessaoUtil.limparAreaSessionMap();
		return Paginas.ALTERAR_SENHA;
	}
	
	public String navegarMeuUsuario() {
		SessaoUtil.limparAreaSessionMap();
		return Paginas.MEU_USUARIO;
	}
	
	public String navegarNovoUsuario() {
		SessaoUtil.limparAreaSessionMap();
		return Paginas.NOVO_USUARIO;
	}
	
	public String navegarManterUsuario() {
		SessaoUtil.limparAreaSessionMap();
		return Paginas.MANTER_USUARIO;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
}
