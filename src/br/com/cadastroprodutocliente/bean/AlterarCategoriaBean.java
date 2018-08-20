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
import br.com.cadastroprodutocliente.util.Paginas;

@ManagedBean
@ViewScoped
public class AlterarCategoriaBean {

	private ICategoriaDao categoriaDao;
	private Categoria categoria;

	@PostConstruct
	public void inicializar() {
		categoriaDao = new CategoriaDao();
		categoria = (Categoria) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("selecionado");
	}
	
	public String confirmar() {
		categoria.setUsuarioManutencao(obterNomeUsuarioSessao());
		categoria.setDataHoraManutencao(Calendar.getInstance());
		if (categoriaDao.alterarCategoria(categoria)) {
			FacesMessageUtil.addMenssage(Mensagens.ALTERADO_COM_SUCESSO);
		} else {
			FacesMessageUtil.addMenssage(Mensagens.ERRO_ACESSO_BASE_DADOS);
		}
		categoria = new Categoria();
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("message");
		return Paginas.MANTER_CATEGORIA;
	}
	
	public Usuario obterNomeUsuarioSessao() {
		Usuario usuarioSessao = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuario");
		return usuarioSessao;
	}
	
	public String voltar() {
		return Paginas.MANTER_CATEGORIA;
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
