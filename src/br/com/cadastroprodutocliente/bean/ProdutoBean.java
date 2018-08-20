package br.com.cadastroprodutocliente.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.cadastroprodutocliente.model.NivelAcesso;
import br.com.cadastroprodutocliente.model.Usuario;
import br.com.cadastroprodutocliente.util.Paginas;

@ManagedBean
@ViewScoped
public class ProdutoBean {

	private boolean administrador;

	@PostConstruct
	public void inicializar() {
		administrador = verificaAdiministrador();
	}

	public boolean verificaAdiministrador() {
		Usuario usuarioSessao = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("usuario");
		if (usuarioSessao.getNivelAcesso().getId() == NivelAcesso.ADMINISTRADOR.getId()) {
			return true;
		} else {
			return false;
		}
	}
	
	public String navegarManterProduto() {
		return Paginas.MANTER_PRODUTO;
	}
	
	public String navegarNovoProduto() {
		return Paginas.NOVO_PRODUTO;
	}
	
	public String navegarManterCategoria() {
		return Paginas.MANTER_CATEGORIA;
	}
	
	public String navegarNovaCategoria() {
		return Paginas.NOVA_CATEGORIA;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
}
