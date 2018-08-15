package br.com.cadastroprodutocliente.util;

import javax.faces.application.FacesMessage;


public enum Mensagens {

	ERRO_ACESSO_BASE_DADOS (FacesMessage.SEVERITY_FATAL , "Erro!", "Erro no acesso a base de dados"),
	EMAIL_JA_CADASTRADO (FacesMessage.SEVERITY_ERROR , "Erro!", "Email ja possui Cadastrado!"),
	INCLUIDO_COM_SUCESSO (FacesMessage.SEVERITY_INFO , "Info!", "Cadastrado com Sucesso!"),
	EMAIL_SENHA_INCORRETOS (FacesMessage.SEVERITY_WARN , "Warn!", "Email ou Senha incorretos!"),
	USUARIO_DELETADO_COM_SUCESSO(FacesMessage.SEVERITY_INFO , "Info!", "Usuario deletado com Sucesso!"),
	NENHUM_REGISTRO_SELECIONADO(FacesMessage.SEVERITY_WARN , "Warn!", "Nenhum registro selecionado!"),
	SENHA_RESETADA_COM_SUCESSO(FacesMessage.SEVERITY_INFO , "Info!", "Senha Resetada com Sucesso!"),
	SENHA_ALTERADA_COM_SUCESSO(FacesMessage.SEVERITY_INFO , "Info!", "Senha Alterada com Sucesso!"),
	SENHA_INFORMADA_INCORRETA(FacesMessage.SEVERITY_WARN , "Warn!", "Senha informada incorreta!");
	
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
