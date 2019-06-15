package br.com.exercicio.jsfcrud.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.exercicio.jsfcrud.enumerator.Enderecos;
import br.com.exercicio.jsfcrud.enumerator.Usuarios;
import br.com.exercicio.jsfcrud.vo.Endereco;
import br.com.exercicio.jsfcrud.vo.Usuario;

@ManagedBean(name = "enderecoBean")
@SessionScoped
public class EnderecoBean {

	private Endereco endereco;	
	private List<SelectItem> listEstado;
	
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@PostConstruct
	public void initEstados() {
		listEstado = new ArrayList<>();
		listEstado.add(new SelectItem("PB", "Paraiba"));
		listEstado.add(new SelectItem("PE", "Pernambuco"));
	}
	
	public String prepararCadastro() {
		endereco = new Endereco();
		return "cadastroEndereco";
	}
			
	public EnderecoBean() {
		endereco = new Endereco();
	}
	
	public String prepararList() {
		return "";
	}
	
	public String adicionarEndereco() {
		
		Endereco enderecoPersistido=Enderecos.INSTANCE.get(endereco.id);
		
		if(enderecoPersistido!=null) {
			enderecoPersistido=endereco;
		}else {
			Enderecos.INSTANCE.add(endereco);
		}
		
		return "listarEnderecos";
	}
	
	public String removerEndereco(Endereco endereco) {		
		Enderecos.INSTANCE.remove(endereco);
		System.out.println("Removendo endereco");
		return "listarEnderecos";
	}
	
	public String editarEndereco(Endereco endereco) {
		this.endereco = endereco;
		return "cadastroEndereco";
	}
	
	public List<Endereco> getListEndereco() {
		return Enderecos.INSTANCE.all();
	}
	
	public String carregarDetalhes(Endereco endereco) {
		this.endereco = endereco;
		return "detalhesEndereco";
	}
	
	public void carregarDetalhes2(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public List<SelectItem> getListEstados() {
		return listEstado;
	}

	public void setListSexo(List<SelectItem> listEstado) {
		this.listEstado = listEstado;
	}
}
