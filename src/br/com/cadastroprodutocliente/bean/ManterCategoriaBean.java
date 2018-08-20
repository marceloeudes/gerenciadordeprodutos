package br.com.cadastroprodutocliente.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.cadastroprodutocliente.dao.CategoriaDao;
import br.com.cadastroprodutocliente.dao.ICategoriaDao;
import br.com.cadastroprodutocliente.model.Categoria;
import br.com.cadastroprodutocliente.util.FacesMessageUtil;
import br.com.cadastroprodutocliente.util.Mensagens;
import br.com.cadastroprodutocliente.util.Paginas;
import br.com.cadastroprodutocliente.util.SiteUtil;

@ManagedBean
@ViewScoped
public class ManterCategoriaBean {

	private List<Categoria> categorias;
	private ICategoriaDao categoriaDao;
	private Categoria selecionado;
	private boolean deletarCategoria;

	@PostConstruct
	public void inicializar() {
		categoriaDao = new CategoriaDao();
		categorias = categoriaDao.listarCategoria();
		selecionado = new Categoria();
	}

	public String navegarAlterarCategoria() {
		if (selecionadoValido()) {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selecionado", selecionado);
			return Paginas.ALTERAR_CATEGORIA;
		}
		return null;
	}
	
	public boolean selecionadoValido() {
		if (SiteUtil.emptyOrNull(selecionado)) {
			FacesMessageUtil.addMenssage(Mensagens.NENHUM_REGISTRO_SELECIONADO);
			return false;
		}
		return true;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public ICategoriaDao getCategoriaDao() {
		return categoriaDao;
	}

	public void setCategoriaDao(ICategoriaDao categoriaDao) {
		this.categoriaDao = categoriaDao;
	}

	public Categoria getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Categoria categoria) {
		this.selecionado = categoria;
	}

	public boolean isDeletarCategoria() {
		return deletarCategoria;
	}

	public void setDeletarCategoria(boolean deletarCategoria) {
		this.deletarCategoria = deletarCategoria;
	}

}
