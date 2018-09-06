package br.com.cadastroprodutocliente.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.cadastroprodutocliente.dao.IProdutoDao;
import br.com.cadastroprodutocliente.dao.ProdutoDao;
import br.com.cadastroprodutocliente.model.Categoria;
import br.com.cadastroprodutocliente.model.Produto;
import br.com.cadastroprodutocliente.util.SessaoUtil;
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
		inicializarFiltro();
		inicializarPesquisa();
		produtos = produtoDao.listaDinamica(pesquisa);
		selecionado = new Produto();
	}

	private void inicializarFiltro() {
		filtro = (Produto) SessaoUtil.consultarAreaFlash("produto");
		if (SiteUtil.emptyOrNull(filtro)) {
			filtro = new Produto();
			filtro.setCategoria(new Categoria());
		}
	}

	private void inicializarPesquisa() {
		SessaoUtil sessaoUtil = SessaoUtil.consultarAreaSessionMap();
		if (SiteUtil.emptyOrNull(sessaoUtil)) {
			pesquisa = new Produto();
			pesquisa.setCategoria(new Categoria());
		} else if (sessaoUtil.getIdentificador() == "manterproduto.pesquisa") {
			pesquisa = (Produto) sessaoUtil.getObjetoTemporario();
			SessaoUtil.limparAreaSessionMap();
			if (sessaoUtil.getPaginasComunicadas().size() > 1) {
				filtro.setDescricao(pesquisa.getDescricao());
				filtro.setCategoria(pesquisa.getCategoria());
			}
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
		pesquisa.setCategoria(new Categoria());
		produtos = produtoDao.listarProduto();
	}

	public String navegarAlterar() {
		if (selecionadoValido()) {
			SessaoUtil.gravarAreaSessionMap(new SessaoUtil("Manter Produto", "manterproduto.pesquisa", pesquisa));
			SessaoUtil.gravarAreaFlash("selecionado", selecionado);
			return Paginas.ALTERAR_PRODUTO;
		} else {
			return null;
		}

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

	public String navegarDetalhar() {
		if (selecionadoValido()) {
			SessaoUtil.gravarAreaFlash("selecionado", selecionado);
			return Paginas.DETALHAR_PRODUTO;
		} else {
			return null;
		}
	}

	private boolean selecionadoValido() {
		if (SiteUtil.emptyOrNull(selecionado)) {
			FacesMessageUtil.addMenssage(Mensagens.NENHUM_REGISTRO_SELECIONADO);
			return false;
		}
		return true;
	}

	public String consultarCategoria() {
		SessaoUtil.gravarAreaSessionMap(new SessaoUtil("Manter Produto", "manterproduto.pesquisa", pesquisa));
		SessaoUtil.gravarAreaFlash("produto", filtro);
		SessaoUtil.gravarAreaFlash("paginaanterior", Paginas.MANTER_PRODUTO);
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
