package br.com.cadastroprodutocliente.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.cadastroprodutocliente.dao.IUsuarioDao;
import br.com.cadastroprodutocliente.dao.UsuarioDao;
import br.com.cadastroprodutocliente.model.Usuario;
import br.com.cadastroprodutocliente.util.FacesMessageUtil;
import br.com.cadastroprodutocliente.util.Mensagens;
import br.com.cadastroprodutocliente.util.Paginas;
import br.com.cadastroprodutocliente.util.SessaoUtil;
import br.com.cadastroprodutocliente.util.SiteUtil;

@ManagedBean
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private IUsuarioDao usuarioDao;

	@PostConstruct
	public void inicializar() {
		usuario = new Usuario();
		usuarioDao = new UsuarioDao();
	}

	public String logar() {
		Usuario usuarioValido = usuarioDao.validarUsuario(usuario);
		if (!SiteUtil.emptyOrNull(usuarioValido)) {
			SessaoUtil.gravarUsuarioSessao(usuarioValido);
			return Paginas.PAGINA_INICIAL;
		} else {
			FacesMessageUtil.addMenssage(Mensagens.EMAIL_SENHA_INCORRETOS);
			return null;
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public IUsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(IUsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

}
