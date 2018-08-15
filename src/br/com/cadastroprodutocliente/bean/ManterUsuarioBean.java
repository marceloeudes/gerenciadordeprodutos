package br.com.cadastroprodutocliente.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cadastroprodutocliente.dao.IUsuarioDao;
import br.com.cadastroprodutocliente.dao.UsuarioDao;
import br.com.cadastroprodutocliente.model.NivelAcesso;
import br.com.cadastroprodutocliente.model.Usuario;
import br.com.cadastroprodutocliente.util.FacesMessageUtil;
import br.com.cadastroprodutocliente.util.Mensagens;
import br.com.cadastroprodutocliente.util.SiteUtil;

@ManagedBean
@ViewScoped
public class ManterUsuarioBean {

	private List<Usuario> usuarios;
	private IUsuarioDao usuarioDao;
	private Usuario selecionado;
	private List<NivelAcesso> niveisAcesso;
	private Usuario pesquisa;

	@PostConstruct
	public void inicializar() {
		usuarioDao = new UsuarioDao();
		usuarios = usuarioDao.listarUsuario();
		selecionado = new Usuario();
		niveisAcesso = NivelAcesso.listarNivelAcesso();
		pesquisa = new Usuario();
	}

	public void deletarUsuario() {
		if (selecionadoValido()) {
			if (usuarioDao.deletarUsuario(selecionado)) {
				FacesMessageUtil.addMenssage(Mensagens.USUARIO_DELETADO_COM_SUCESSO);
				usuarios = usuarioDao.listarUsuario();
			} else {
				FacesMessageUtil.addMenssage(Mensagens.ERRO_ACESSO_BASE_DADOS);
			}
		}
	}

	public void resetarSenha() {
		if (selecionadoValido()) {
			if (usuarioDao.resetarSenha(selecionado)) {
				FacesMessageUtil.addMenssage(Mensagens.SENHA_RESETADA_COM_SUCESSO);
			} else {
				FacesMessageUtil.addMenssage(Mensagens.ERRO_ACESSO_BASE_DADOS);
			}
		}
	}
	
	public void pesquisar() {
		usuarios = usuarioDao.listaDinamica(pesquisa);
	}
	
	public void limpar() {
		pesquisa = new Usuario();
		usuarios = usuarioDao.listarUsuario();
	}
	
	public boolean selecionadoValido() {
		if (SiteUtil.emptyOrNull(selecionado)) {
			FacesMessageUtil.addMenssage(Mensagens.NENHUM_REGISTRO_SELECIONADO);
			return false;
		}
		return true;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public IUsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(IUsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public Usuario getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Usuario selecionado) {
		this.selecionado = selecionado;
	}

	public List<NivelAcesso> getNiveisAcesso() {
		return niveisAcesso;
	}

	public void setNiveisAcesso(List<NivelAcesso> niveisAcesso) {
		this.niveisAcesso = niveisAcesso;
	}

	public Usuario getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(Usuario pesquisa) {
		this.pesquisa = pesquisa;
	}

}
