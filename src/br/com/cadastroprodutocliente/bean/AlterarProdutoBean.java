package br.com.cadastroprodutocliente.bean;

import java.math.BigDecimal;
import java.util.Calendar;

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
import br.com.cadastroprodutocliente.util.SessaoUtil;
import br.com.cadastroprodutocliente.util.SiteUtil;

@ManagedBean
@ViewScoped
public class AlterarProdutoBean {

	private Produto produto;
	private IProdutoDao produtoDao;
	
	@PostConstruct
	public void inicializar() {
		produto = consultarMemoriaFlash();
		produtoDao = new ProdutoDao();
		atualizarCaminhodePao();
	}
	
	public Produto consultarMemoriaFlash() {
		Produto produtoAlteracao = (Produto) SessaoUtil.consultarAreaFlash("produto");
		if (SiteUtil.emptyOrNull(produtoAlteracao)) {
			produtoAlteracao = (Produto) SessaoUtil.consultarAreaFlash("selecionado");
		}
		return produtoAlteracao;
	}
	
	public String confirmar() {
		if (produtoValido()) {
			produto.setUsuarioManutencao(SessaoUtil.obterUsuarioSessao());
			produto.setDataHoraManutencao(Calendar.getInstance());
			if (produtoDao.alterarProduto(produto)) {
				FacesMessageUtil.addMenssage(Mensagens.ALTERADO_COM_SUCESSO);
				produto = new Produto();
				produto.setCategoria(new Categoria());
				produto.setValor(BigDecimal.ZERO);
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
	
}
