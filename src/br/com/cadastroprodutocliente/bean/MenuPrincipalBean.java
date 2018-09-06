package br.com.cadastroprodutocliente.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cadastroprodutocliente.model.Usuario;
import br.com.cadastroprodutocliente.util.Paginas;
import br.com.cadastroprodutocliente.util.SessaoUtil;

@ManagedBean
@ViewScoped
public class MenuPrincipalBean {
	
	private Usuario usuario;

	@PostConstruct
	public void inicializar() {
		usuario = SessaoUtil.obterUsuarioSessao();
	}
	
	public String navegarProdutos() {
		SessaoUtil.limparAreaSessionMap();
		return Paginas.MANTER_PRODUTO;
	}
	
	public String navegarUsuarios() {
		SessaoUtil.limparAreaSessionMap();
		return Paginas.MEU_USUARIO;
	}

	public String deslogar() {
		usuario = new Usuario();
		SessaoUtil.finalizarSessao();
		return Paginas.PAGINA_LOGIN;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
