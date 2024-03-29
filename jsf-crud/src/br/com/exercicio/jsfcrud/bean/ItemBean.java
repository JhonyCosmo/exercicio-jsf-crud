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
import br.com.exercicio.jsfcrud.enumerator.Items;
import br.com.exercicio.jsfcrud.enumerator.Usuarios;
import br.com.exercicio.jsfcrud.vo.Endereco;
import br.com.exercicio.jsfcrud.vo.Item;
import br.com.exercicio.jsfcrud.vo.Usuario;

@ManagedBean(name = "itemBean")
@SessionScoped
public class ItemBean {

	private Item item;

	public ItemBean() {
		item = new Item();
	}

	@PostConstruct
	public void initSexo() {
		
	}

	public String prepararCadastro() {
		item = new Item();
		return "cadastroItem";
	}

	public String prepararList() {
		return "";
	}

	public String adicionarItem() {
		//Validando o descricao
		Item itemNome = Items.INSTANCE.getByName(item.getDescricao());
		
		if(itemNome!=null) {
			
			if(itemNome.getId().equals(item.getId())==false) {
				
				adicionarMensagemErro("O Item j� existente");
				
				return "";
			}
			
		}
		
		Item itemPersistido=Items.INSTANCE.get(item.id);
		
		if(itemPersistido!=null) {
			
			itemPersistido=item;
			
		}else {
			
			Items.INSTANCE.add(item);
			
		}		

		
		return "listarItems";
	}

	public List<Item> getListItem() {
		return Items.INSTANCE.all();
	}

	public String removerItem(Item item) {		
		Items.INSTANCE.remove(item);
		System.out.println("Removendo Item");
		return "listarItems";
	}
	
	public String editarItem(Item item) {
		this.item = item;
		return "cadastroItem";
	}
	
	public String carregarDetalhes(Item item) {
		this.item = item;
		return "detalhesItem";
	}
	
	public void carregarDetalhes2(Item item) {
		this.item = item;
	}

	public Item getItem() {
		return item;
	}

	public void setUsuario(Item item) {
		this.item = item;
	}	

	private void adicionarMensagemErro(String mensagem) {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem,
				mensagem);
		fc.addMessage(null, fm);
		
	}
}
