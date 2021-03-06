package br.com.cadastroprodutocliente.bean;

import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.cadastroprodutocliente.dao.CategoriaDao;
import br.com.cadastroprodutocliente.dao.ICategoriaDao;
import br.com.cadastroprodutocliente.model.Categoria;
import br.com.cadastroprodutocliente.model.Usuario;
import br.com.cadastroprodutocliente.util.FacesMessageUtil;
import br.com.cadastroprodutocliente.util.Mensagens;

@ManagedBean
@ViewScoped
public class NovaCategoriaBean {

	private ICategoriaDao categoriaDao;
	private Categoria categoria;
	
	@PostConstruct
	public void inicializar() {
		categoriaDao = new CategoriaDao();
		categoria = new Categoria();
	}
	
	public void confirmar() {
		categoria.setusuarioInclusao(obterUsuarioSessao());
		categoria.setDataHoraInclusao(Calendar.getInstance());
		if (categoriaDao.incluirCategoria(categoria)) {
			FacesMessageUtil.addMenssage(Mensagens.CADASTRADO_COM_SUCESSO);
		} else {
			FacesMessageUtil.addMenssage(Mensagens.ERRO_ACESSO_BASE_DADOS);
		}
		categoria = new Categoria();
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("message");
	}
	
	public Usuario obterUsuarioSessao() {
		Usuario usuarioSessao = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuario");
		return usuarioSessao;
	}

	public ICategoriaDao getCategoriaDao() {
		return categoriaDao;
	}

	public void setCategoriaDao(ICategoriaDao categoriaDao) {
		this.categoriaDao = categoriaDao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
