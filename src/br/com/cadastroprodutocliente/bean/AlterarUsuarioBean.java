package br.com.cadastroprodutocliente.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.cadastroprodutocliente.dao.IUsuarioDao;
import br.com.cadastroprodutocliente.dao.UsuarioDao;
import br.com.cadastroprodutocliente.model.Usuario;
import br.com.cadastroprodutocliente.util.FacesMessageUtil;
import br.com.cadastroprodutocliente.util.Mensagens;

@ManagedBean
@ViewScoped
public class AlterarUsuarioBean {

	private Usuario usuario;
	private String novaSenha;
	private String confirmarSenha;
	private IUsuarioDao usuarioDao;
	
	@PostConstruct
	public void inicializar() {
		usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		usuarioDao = new UsuarioDao();
	}

	public void alterarSenha() {
		Usuario usuarioConsultado = usuarioDao.consultarUsuario(usuario);
		if (usuarioConsultado.getSenha().equals(usuario.getSenha())) {
			usuario.setSenha(novaSenha);
			if (usuarioDao.alterarSenha(usuario)) {
				FacesMessageUtil.addMenssage(Mensagens.SENHA_ALTERADA_COM_SUCESSO);
			} else {
				FacesMessageUtil.addMenssage(Mensagens.ERRO_ACESSO_BASE_DADOS);
			}
		} else {
			FacesMessageUtil.addMenssage(Mensagens.SENHA_INFORMADA_INCORRETA);
		}
		
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("message");
			
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public IUsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(IUsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
}
