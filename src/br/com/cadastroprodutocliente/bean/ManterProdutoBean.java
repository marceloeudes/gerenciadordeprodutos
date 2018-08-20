package br.com.cadastroprodutocliente.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.cadastroprodutocliente.dao.IProdutoDao;
import br.com.cadastroprodutocliente.dao.ProdutoDao;
import br.com.cadastroprodutocliente.model.Categoria;
import br.com.cadastroprodutocliente.model.Produto;
import br.com.cadastroprodutocliente.util.FacesMessageUtil;
import br.com.cadastroprodutocliente.util.Mensagens;
import br.com.cadastroprodutocliente.util.Paginas;
import br.com.cadastroprodutocliente.util.SiteUtil;

@ManagedBean
@ViewScoped
public class ManterProdutoBean {

	private List<Produto> produtos;
	private IProdutoDao produtoDao;
	private Produto filtro;
	private Produto pesquisa;
	private Produto selecionado;

	@PostConstruct
	public void inicializar() {
		produtoDao = new ProdutoDao();
		consultarMemoriaFlash();
		pesquisa = new Produto();
		pesquisa.setCategoria(new Categoria());
		produtos = produtoDao.listaDinamica(pesquisa);
		selecionado = new Produto();
	}
	
	public void consultarMemoriaFlash() {
		filtro = (Produto) FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.get("produto");
		if (SiteUtil.emptyOrNull(filtro)) {
			filtro = new Produto();
			filtro.setCategoria(new Categoria());
		}
		
		pesquisa = (Produto) FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.get("temporario");
		if (SiteUtil.emptyOrNull(pesquisa)) {
			pesquisa = new Produto();
			pesquisa.setCategoria(new Categoria());
		}
	}

	public void pesquisar() {
		pesquisa.setDescricao(filtro.getDescricao());
		pesquisa.setCategoria(filtro.getCategoria());
		produtos = produtoDao.listaDinamica(pesquisa);
	}

	public void limpar() {
		filtro = new Produto();
		pesquisa = new Produto();
		filtro.setCategoria(new Categoria());
		produtos = produtoDao.listarProduto();
	}

	public String alterar() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selecionado", selecionado);
		return Paginas.ALTERAR_PRODUTO;
	}

	public void deletar() {
		if (selecionadoValido()) {
			if (produtoDao.deletarProduto(selecionado)) {
				FacesMessageUtil.addMenssage(Mensagens.DELETADO_COM_SUCESSO);
				produtos = produtoDao.listarProduto();
			} else {
				FacesMessageUtil.addMenssage(Mensagens.ERRO_ACESSO_BASE_DADOS);
			}
		}
	}

	public boolean selecionadoValido() {
		if (SiteUtil.emptyOrNull(selecionado)) {
			FacesMessageUtil.addMenssage(Mensagens.NENHUM_REGISTRO_SELECIONADO);
			return false;
		}
		return true;
	}
	
	public String consultarCategoria() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("paginaanterior", "manterproduto");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("produto", filtro);
		return Paginas.SELECIONAR_CATEGORIA;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
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
