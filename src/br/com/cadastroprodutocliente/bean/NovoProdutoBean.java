package br.com.cadastroprodutocliente.bean;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.cadastroprodutocliente.dao.CategoriaDao;
import br.com.cadastroprodutocliente.dao.ProdutoDao;
import br.com.cadastroprodutocliente.model.Categoria;
import br.com.cadastroprodutocliente.model.Produto;

@ManagedBean
public class NovoProdutoBean {

	private Produto produto;
	private CategoriaDao categoriaDao;
	private ProdutoDao produtoDao;
	
	@PostConstruct
	public void inicializar() {
		produto = new Produto();
		produto.setCategoria(new Categoria());
		produto.setValor(new BigDecimal("0"));
		categoriaDao =  new CategoriaDao();
		produtoDao = new ProdutoDao();
	}
	
	public List<Categoria> getListaCategoria() {
		return categoriaDao.listarCategoria();
	}
	
	public void confirmar() {
		produtoDao.incluirProduto(produto);
		inicializar();
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public CategoriaDao getCategoriaDao() {
		return categoriaDao;
	}

	public void setCategoriaDao(CategoriaDao categoriaDao) {
		this.categoriaDao = categoriaDao;
	}

	public ProdutoDao getProdutoDao() {
		return produtoDao;
	}

	public void setProdutoDao(ProdutoDao produtoDao) {
		this.produtoDao = produtoDao;
	}
	
}
