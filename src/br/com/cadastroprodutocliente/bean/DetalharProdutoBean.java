package br.com.cadastroprodutocliente.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cadastroprodutocliente.model.Produto;
import br.com.cadastroprodutocliente.util.Paginas;
import br.com.cadastroprodutocliente.util.SessaoUtil;
import br.com.cadastroprodutocliente.util.SiteUtil;

@ManagedBean
@ViewScoped
public class DetalharProdutoBean {
	
	private Produto produto;
	private boolean manutencao;

	@PostConstruct
	public void inicializar() {
		produto = (Produto) SessaoUtil.consultarAreaFlash("selecionado");
		
		if (!SiteUtil.emptyOrNull(produto.getUsuarioManutencao())) {
			manutencao = true;
		}
	}
	
	public String voltar() {
		return Paginas.MANTER_PRODUTO;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public boolean isManutencao() {
		return manutencao;
	}

	public void setManutencao(boolean manutencao) {
		this.manutencao = manutencao;
	}

}
