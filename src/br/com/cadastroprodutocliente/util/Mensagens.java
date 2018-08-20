package br.com.cadastroprodutocliente.util;

import javax.faces.application.FacesMessage;


public enum Mensagens {

	ERRO_ACESSO_BASE_DADOS (FacesMessage.SEVERITY_FATAL , "Erro!", "Erro no acesso a base de dados"),
	EMAIL_JA_CADASTRADO (FacesMessage.SEVERITY_ERROR , "Erro!", "Email ja possui Cadastrado!"),
	CADASTRADO_COM_SUCESSO (FacesMessage.SEVERITY_INFO , "Info!", "Cadastrado com Sucesso!"),
	ALTERADO_COM_SUCESSO (FacesMessage.SEVERITY_INFO , "Info!", "Alterado com Sucesso!"),
	EMAIL_SENHA_INCORRETOS (FacesMessage.SEVERITY_WARN , "Warn!", "Email ou Senha incorretos!"),
	DELETADO_COM_SUCESSO(FacesMessage.SEVERITY_INFO , "Info!", "Deletado com Sucesso!"),
	NENHUM_REGISTRO_SELECIONADO(FacesMessage.SEVERITY_WARN , "Warn!", "Nenhum registro selecionado!"),
	SENHA_RESETADA_COM_SUCESSO(FacesMessage.SEVERITY_INFO , "Info!", "Senha Resetada com Sucesso!"),
	SENHA_ALTERADA_COM_SUCESSO(FacesMessage.SEVERITY_INFO , "Info!", "Senha Alterada com Sucesso!"),
	SENHA_INFORMADA_INCORRETA(FacesMessage.SEVERITY_WARN , "Warn!", "Senha informada incorreta!"),
	PRECO_PRODUTO_INVALIDO(FacesMessage.SEVERITY_WARN , "Warn!", "Preço do Produto deve ser maior que zero"),
	QUANTIDADE_PRODUTO_INVALIDO(FacesMessage.SEVERITY_WARN , "Warn!", "Quantidade do Produto deve ser maior que zero"),
	CATEGORIA_PRODUTO_OBRIGATORIO(FacesMessage.SEVERITY_WARN , "Warn!", "Categoria do Produto obrigatório"),
	DESCRICAO_PRODUTO_OBRIGATORIO(FacesMessage.SEVERITY_WARN , "Warn!", "Descrição do Produto obrigatório");
	
	private FacesMessage.Severity severity;
	private String summary;
	private String detail;
	
	private Mensagens(FacesMessage.Severity severity, String summary, String detail) {
		this.severity = severity;
		this.summary = summary;
		this.detail = detail;
	}

	public FacesMessage.Severity getSeverity() {
		return severity;
	}

	public String getSummary() {
		return summary;
	}

	public String getDetail() {
		return detail;
	}
}
