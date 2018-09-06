package br.com.cadastroprodutocliente.bean;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.cadastroprodutocliente.dao.ProdutoDao;
import br.com.cadastroprodutocliente.model.Categoria;
import br.com.cadastroprodutocliente.model.Produto;
import br.com.cadastroprodutocliente.util.FacesMessageUtil;
import br.com.cadastroprodutocliente.util.Mensagens;
import br.com.cadastroprodutocliente.util.Paginas;
import br.com.cadastroprodutocliente.util.SessaoUtil;
import br.com.cadastroprodutocliente.util.SiteUtil;

@ManagedBean
@ViewScoped
public class NovoProdutoBean {

	private Produto produto;
	private ProdutoDao produtoDao;

	@PostConstruct
	public void inicializar() {
		inicializarProduto();
		produtoDao = new ProdutoDao();
	}

	public void inicializarProduto() {
		produto = (Produto) SessaoUtil.consultarAreaFlash("produto");
		if (SiteUtil.emptyOrNull(produto)) {
			produto = new Produto();
			produto.setCategoria(new Categoria());
			produto.setValor(BigDecimal.ZERO);
		}
	}

	public String consultarCategoria() {
		SessaoUtil
		.gravarAreaSessionMap(new SessaoUtil("Novo Produto", null, null));
		SessaoUtil.gravarAreaFlash("paginaanterior", "novoproduto");
		SessaoUtil.gravarAreaFlash("produto", produto);
		return Paginas.SELECIONAR_CATEGORIA;
	}

	public void confirmar() {
		if (produtoValido()) {
			produto.setUsuarioInclusao(SessaoUtil.obterUsuarioSessao());
			produto.setDataHoraInclusao(Calendar.getInstance());
			if (produtoDao.incluirProduto(produto)) {
				FacesMessageUtil.addMenssage(Mensagens.CADASTRADO_COM_SUCESSO);
				produto = new Produto();
				produto.setCategoria(new Categoria());
				produto.setValor(BigDecimal.ZERO);
			} else {
				FacesMessageUtil.addMenssage(Mensagens.ERRO_ACESSO_BASE_DADOS);
			}
		}
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("message");
	}

	public boolean produtoValido() {
		boolean valido = true;
		if (SiteUtil.emptyOrNull(produto.getDescricao())) {
			FacesMessageUtil.addMenssage(Mensagens.DESCRICAO_PRODUTO_OBRIGATORIO);
			valido = false;
		}
		if (produto.getCategoria().getCodigo() == 0) {
			FacesMessageUtil.addMenssage(Mensagens.CATEGORIA_PRODUTO_OBRIGATORIO);
			valido = false;
		}
		if (SiteUtil.bigDecimalZeroOrNull(produto.getValor())) {
			FacesMessageUtil.addMenssage(Mensagens.PRECO_PRODUTO_INVALIDO);
			valido = false;
		}
		if (produto.getEstoque() == 0) {
			FacesMessageUtil.addMenssage(Mensagens.QUANTIDADE_PRODUTO_INVALIDO);
			valido = false;
		}
		return valido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ProdutoDao getProdutoDao() {
		return produtoDao;
	}

	public void setProdutoDao(ProdutoDao produtoDao) {
		this.produtoDao = produtoDao;
	}

}
