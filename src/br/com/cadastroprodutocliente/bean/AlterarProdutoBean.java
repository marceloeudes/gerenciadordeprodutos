package br.com.cadastroprodutocliente.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.cadastroprodutocliente.dao.CategoriaDao;
import br.com.cadastroprodutocliente.dao.ICategoriaDao;
import br.com.cadastroprodutocliente.dao.IProdutoDao;
import br.com.cadastroprodutocliente.dao.ProdutoDao;
import br.com.cadastroprodutocliente.model.Categoria;
import br.com.cadastroprodutocliente.model.Produto;
import br.com.cadastroprodutocliente.util.Paginas;

@ManagedBean
@ViewScoped
public class AlterarProdutoBean {

	private Produto produto;
	private IProdutoDao produtoDao;
	private ICategoriaDao categoriaDao;
	
	@PostConstruct
	public void inicializar() {
		produto = (Produto) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("selecionado");
		produtoDao = new ProdutoDao();
		categoriaDao = new CategoriaDao();
	}
	
	public List<Categoria> getListaCategoria() {
		return categoriaDao.listarCategoria();
	}
	
	public String confirmar() {
		produtoDao.alterarProduto(produto);
		return Paginas.MANTER_PRODUTO;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public IProdutoDao getProdutoDao() {
		return produtoDao;
	}

	public void setProdutoDao(IProdutoDao produtoDao) {
		this.produtoDao = produtoDao;
	}

	public ICategoriaDao getCategoriaDao() {
		return categoriaDao;
	}

	public void setCategoriaDao(ICategoriaDao categoriaDao) {
		this.categoriaDao = categoriaDao;
	}
	
}
