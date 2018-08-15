package br.com.cadastroprodutocliente.dao;

import java.util.List;

import br.com.cadastroprodutocliente.model.Categoria;

public interface ICategoriaDao {

	public void incluirCategoria(Categoria categoria);
	
	public void alterarCategoria(Categoria categoria);
	
	public void deletarCategoria(Categoria categoria);
	
	public List<Categoria> listarCategoria();
	
}
