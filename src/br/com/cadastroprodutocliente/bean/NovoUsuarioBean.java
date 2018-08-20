package br.com.cadastroprodutocliente.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.cadastroprodutocliente.dao.IUsuarioDao;
import br.com.cadastroprodutocliente.dao.UsuarioDao;
import br.com.cadastroprodutocliente.model.NivelAcesso;
import br.com.cadastroprodutocliente.model.Usuario;
import br.com.cadastroprodutocliente.util.FacesMessageUtil;
import br.com.cadastroprodutocliente.util.Mensagens;
import br.com.cadastroprodutocliente.util.Paginas;
import br.com.cadastroprodutocliente.util.SiteUtil;

@ManagedBean
@ViewScoped
public class NovoUsuarioBean {

	private Usuario usuario;
	private IUsuarioDao usuarioDao;
	private List<NivelAcesso> niveisAcesso;

	@PostConstruct
	public void inicializar() {
		usuario = new Usuario();
		usuarioDao = new UsuarioDao();
		niveisAcesso = NivelAcesso.listarNivelAcesso();
	}

	public void confirmar() {
		if (!SiteUtil.emptyOrNull(usuarioDao.validarUsuario(usuario))) {
			FacesMessageUtil.addMenssage(Mensagens.EMAIL_JA_CADASTRADO);
			return;
		}
		
		if (usuarioDao.incluirUsuario(usuario)){
			FacesMessageUtil.addMenssage(Mensagens.CADASTRADO_COM_SUCESSO);
			usuario = new Usuario();
		} else {
			FacesMessageUtil.addMenssage(Mensagens.ERRO_ACESSO_BASE_DADOS);
		}
		
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("message");
			
	}
	
	public String voltar() {
		return Paginas.MANTER_USUARIO;
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

	public List<NivelAcesso> getNiveisAcesso() {
		return niveisAcesso;
	}

	public void setNiveisAcesso(List<NivelAcesso> niveisAcesso) {
		this.niveisAcesso = niveisAcesso;
	}

}
