package br.com.cadastroprodutocliente.dao;

import java.util.List;

import br.com.cadastroprodutocliente.model.Categoria;

public interface ICategoriaDao {

	public boolean incluirCategoria(Categoria categoria);
	
	public boolean alterarCategoria(Categoria categoria);
	
	public boolean deletarCategoria(Categoria categoria);
	
	public List<Categoria> listarCategoria();
	
}
