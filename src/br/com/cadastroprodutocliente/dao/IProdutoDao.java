package br.com.cadastroprodutocliente.dao;

import java.util.List;

import br.com.cadastroprodutocliente.model.Produto;

public interface IProdutoDao {

	public boolean incluirProduto(Produto produto);

	public boolean alterarProduto(Produto produto);

	public boolean deletarProduto(Produto produto);
	
	public List<Produto> listaDinamica(Produto filtro);

	public List<Produto> listarProduto();

	public List<Produto> listarProduto(int categoria);

	public List<Produto> listarProduto(String descricao);

	public List<Produto> listarProduto(int categoria, String descricao);
	
}
