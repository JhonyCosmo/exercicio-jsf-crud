package br.com.exercicio.jsfcrud.vo;

import java.util.Date;

public class Usuario extends ModelBase {

	private String nome;
	private String username;
	private String password;
	private String sexo;
	private Date dataNasc;
	private String cidadeNasc;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getCidadeNasc() {
		return cidadeNasc;
	}

	public void setCidadeNasc(String cidadeNasc) {
		this.cidadeNasc = cidadeNasc;
	}

}

