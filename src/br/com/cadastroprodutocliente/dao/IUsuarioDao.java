package br.com.cadastroprodutocliente.dao;

import java.util.List;

import br.com.cadastroprodutocliente.model.Usuario;

public interface IUsuarioDao {

	public boolean incluirUsuario(Usuario usuario);
	
	public boolean resetarSenha(Usuario usuario);
	
	public boolean alterarSenha(Usuario usuario);
	
	public Usuario consultarUsuario(Usuario usuario);
	
	public boolean deletarUsuario(Usuario usuario);
	
	public Usuario validarUsuario(Usuario usuario);
	
	public List<Usuario> listaDinamica(Usuario usuario);
	
	public List<Usuario> listarUsuario();
	
}
