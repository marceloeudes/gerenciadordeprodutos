package br.com.cadastroprodutocliente.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cadastroprodutocliente.dao.CategoriaDao;
import br.com.cadastroprodutocliente.dao.ICategoriaDao;
import br.com.cadastroprodutocliente.model.Categoria;
import br.com.cadastroprodutocliente.model.Produto;
import br.com.cadastroprodutocliente.util.SessaoUtil;
import br.com.cadastroprodutocliente.util.FacesMessageUtil;
import br.com.cadastroprodutocliente.util.Mensagens;
import br.com.cadastroprodutocliente.util.SiteUtil;

@ManagedBean
@ViewScoped
public class SelecionarCategoriaBean {

	private List<Categoria> categorias;
	private ICategoriaDao categoriaDao;
	private Categoria selecionado;
	private Produto produto;
	private String paginaAnterior;
	private String caminhoDePao;

	@PostConstruct
	public void inicializar() {
		categoriaDao = new CategoriaDao();
		categorias = categoriaDao.listarCategoria();
		selecionado = new Categoria();
		produto = (Produto) SessaoUtil.consultarAreaFlash("produto");
		paginaAnterior = (String) SessaoUtil.consultarAreaFlash("paginaanterior");
		caminhoDePao = SessaoUtil.obterCaminhoDePao();
	}
	
	public String confirmarSelecao() {
		if (selecionadoValido()) {
			produto.setCategoria(selecionado);
			SessaoUtil.gravarAreaFlash("produto", produto);
			return paginaAnterior;
		}
		return null;
	}
	
	public String voltar() {
		SessaoUtil.gravarAreaFlash("produto", produto);
		return paginaAnterior;
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

	public void setSelecionado(Categoria selecionado) {
		this.selecionado = selecionado;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto novoProduto) {
		this.produto = novoProduto;
	}

	public String getPaginaAnterior() {
		return paginaAnterior;
	}

	public void setPaginaAnterior(String paginaAnterior) {
		this.paginaAnterior = paginaAnterior;
	}

	public String getCaminhoDePao() {
		return caminhoDePao;
	}

	public void setCaminhoDePao(String caminhoDePao) {
		this.caminhoDePao = caminhoDePao;
	}
	
}
