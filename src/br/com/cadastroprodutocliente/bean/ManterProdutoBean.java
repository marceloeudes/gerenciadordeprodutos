package br.com.cadastroprodutocliente.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.cadastroprodutocliente.dao.CategoriaDao;
import br.com.cadastroprodutocliente.dao.IProdutoDao;
import br.com.cadastroprodutocliente.dao.ProdutoDao;
import br.com.cadastroprodutocliente.model.Categoria;
import br.com.cadastroprodutocliente.model.Produto;
import br.com.cadastroprodutocliente.util.Paginas;

@ManagedBean
@ApplicationScoped
public class ManterProdutoBean{

	private List<Produto> produtos;
	private List<Categoria> categorias;
	private CategoriaDao categoriaDao;
	private IProdutoDao produtoDao;
	private Produto filtro;
	private Produto selecionado;
	
	@PostConstruct
	public void inicializar() {
		produtoDao = new ProdutoDao();
		categoriaDao = new CategoriaDao();
		produtos = produtoDao.listarProduto();
		categorias = categoriaDao.listarCategoria();
		filtro = new Produto();
		filtro.setCategoria(new Categoria());
		selecionado = new Produto();
		selecionado.setCategoria(new Categoria());
	}

	public void pesquisar() {
		produtos = produtoDao.listaDinamica(filtro);
	}

	public void limparFiltros() {
		filtro = new Produto();
		filtro.setCategoria(new Categoria());
		produtos = produtoDao.listarProduto();
	}
	
	public String alterar() {
		selecionado.getCodigo();
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selecionado", selecionado);
		return Paginas.ALTERAR_PRODUTO;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public CategoriaDao getCategoriaDao() {
		return categoriaDao;
	}

	public void setCategoriaDao(CategoriaDao categoriaDao) {
		this.categoriaDao = categoriaDao;
	}

	public IProdutoDao getProdutoDao() {
		return produtoDao;
	}

	public void setProdutoDao(ProdutoDao produtoDao) {
		this.produtoDao = produtoDao;
	}

	public Produto getFiltro() {
		return filtro;
	}

	public void setFiltro(Produto filtro) {
		this.filtro = filtro;
	}

	public Produto getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Produto selecionado) {
		this.selecionado = selecionado;
	}

}
