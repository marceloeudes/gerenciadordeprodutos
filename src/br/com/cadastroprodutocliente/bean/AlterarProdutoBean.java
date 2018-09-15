package br.com.cadastroprodutocliente.bean;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import br.com.cadastroprodutocliente.dao.IProdutoDao;
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
public class AlterarProdutoBean {

	private Produto produto;
	private IProdutoDao produtoDao;
	private Produto produtoAlteracao;
	private boolean renderizaPercentual;
	private boolean desabilitarPrecoVenda;

	@PostConstruct
	public void inicializar() {
		produtoDao = new ProdutoDao();
		produto = consultarMemoriaFlash();
		produtoAlteracao = produto;
		ajustarBaseVendaSelecionado();
		atualizarCaminhodePao();
	}

	public Produto consultarMemoriaFlash() {
		Produto produtoAlteracao = (Produto) SessaoUtil.consultarAreaFlash("produto");
		if (SiteUtil.emptyOrNull(produtoAlteracao)) {
			produtoAlteracao = (Produto) SessaoUtil.consultarAreaFlash("selecionado");
		}
		return produtoAlteracao;
	}

	public void atualizarBaseVenda(ValueChangeEvent event) {
		produtoAlteracao.setBaseValorVenda(Integer.valueOf(event.getNewValue().toString()));
		ajustarBaseVendaSelecionado();
	}

	public void atualizarPrecoVendaPercentual(ValueChangeEvent event) {
		produtoAlteracao.setPercentualVenda(Double.parseDouble(event.getNewValue().toString()));
		BigDecimal percentual = BigDecimal.valueOf(produtoAlteracao.getPercentualVenda())
				.multiply(BigDecimal.valueOf(0.01d));
		BigDecimal valorCusto = produtoAlteracao.getValorCusto();
		produtoAlteracao.setValorVenda((valorCusto.multiply(percentual)).add(valorCusto));
	}

	public void atualizarPrecoVendaCusto(ValueChangeEvent event) {
		produtoAlteracao.setValorCusto(BigDecimal.valueOf(Double.parseDouble(event.getNewValue().toString())));
		BigDecimal percentual = BigDecimal.valueOf(produtoAlteracao.getPercentualVenda())
				.multiply(BigDecimal.valueOf(0.01d));
		if (produtoAlteracao.getBaseValorVenda() == 1) {
			produtoAlteracao.setValorVenda(
					(produtoAlteracao.getValorCusto().multiply(percentual)).add(produtoAlteracao.getValorCusto()));
		}
	}

	public void ajustarBaseVendaSelecionado() {
		if (produtoAlteracao.getBaseValorVenda() == 1) {
			renderizaPercentual = true;
			desabilitarPrecoVenda = true;
			produtoAlteracao.setPercentualVenda(null);
		} else {
			renderizaPercentual = false;
			desabilitarPrecoVenda = false;
			produtoAlteracao.setValorVenda(produto.getValorVenda());
		}
	}

	public String confirmar() {
		if (produtoValido()) {
			produto.setUsuarioManutencao(SessaoUtil.obterUsuarioSessao());
			produto.setDataHoraManutencao(Calendar.getInstance());
			if (produtoDao.alterarProduto(produto)) {
				FacesMessageUtil.addMenssage(Mensagens.ALTERADO_COM_SUCESSO);
				produto = new Produto();
				produto.setCategoria(new Categoria());
				produto.setValorVenda(BigDecimal.ZERO);
			} else {
				FacesMessageUtil.addMenssage(Mensagens.ERRO_ACESSO_BASE_DADOS);
			}
		}
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("message");
		return Paginas.MANTER_PRODUTO;
	}

	public String voltar() {
		return Paginas.MANTER_PRODUTO;
	}

	public String consultarCategoria() {
		SessaoUtil.gravarAreaFlash("paginaanterior", "alterarproduto");
		SessaoUtil.gravarAreaFlash("produto", produto);
		return Paginas.SELECIONAR_CATEGORIA;
	}

	public boolean produtoValido() {
		boolean valido = true;
		if (SiteUtil.emptyOrNull(produtoAlteracao.getDescricao())) {
			FacesMessageUtil.addMenssage(Mensagens.DESCRICAO_PRODUTO_OBRIGATORIO);
			valido = false;
		}
		if (produtoAlteracao.getCategoria().getCodigo() == 0) {
			FacesMessageUtil.addMenssage(Mensagens.CATEGORIA_PRODUTO_OBRIGATORIO);
			valido = false;
		}
		if (SiteUtil.bigDecimalZeroOrNull(produtoAlteracao.getValorVenda())) {
			FacesMessageUtil.addMenssage(Mensagens.PRECO_PRODUTO_INVALIDO);
			valido = false;
		}
		if (SiteUtil.doubleZeroOrNull(produtoAlteracao.getPercentualVenda())
				&& produtoAlteracao.getBaseValorVenda() == 1) {
			FacesMessageUtil.addMenssage(Mensagens.PERCENTUAL_VENDA_OBRIGATORIO);
			valido = false;
		}
		if (produtoAlteracao.getEstoque() == 0) {
			FacesMessageUtil.addMenssage(Mensagens.QUANTIDADE_PRODUTO_INVALIDO);
			valido = false;
		}
		return valido;
	}

	public void atualizarCaminhodePao() {
		SessaoUtil sessaoUtil = SessaoUtil.consultarAreaSessionMap();
		if (SiteUtil.emptyOrNull(sessaoUtil)) {
			SessaoUtil.gravarAreaSessionMap(new SessaoUtil("Alterar Produto"));
		} else {
			if (!sessaoUtil.getPaginasComunicadas().contains("Alterar Produto")) {
				sessaoUtil.getPaginasComunicadas().add("Alterar Produto");
				SessaoUtil.gravarAreaSessionMap(sessaoUtil);
			}
		}
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public IProdutoDao getProdutoDao() {
		return produtoDao;
	}

	public void setProdutoDao(IProdutoDao produtoDao) {
		this.produtoDao = produtoDao;
	}

	public Produto getProdutoAlteracao() {
		return produtoAlteracao;
	}

	public void setProdutoAlteracao(Produto produtoAlteracao) {
		this.produtoAlteracao = produtoAlteracao;
	}

	public boolean isRenderizaPercentual() {
		return renderizaPercentual;
	}

	public void setRenderizaPercentual(boolean renderizaPercentual) {
		this.renderizaPercentual = renderizaPercentual;
	}

	public boolean isDesabilitarPrecoVenda() {
		return desabilitarPrecoVenda;
	}

	public void setDesabilitarPrecoVenda(boolean desabilitarPrecoVenda) {
		this.desabilitarPrecoVenda = desabilitarPrecoVenda;
	}

}
