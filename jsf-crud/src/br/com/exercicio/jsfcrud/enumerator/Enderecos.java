package br.com.exercicio.jsfcrud.enumerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.exercicio.jsfcrud.vo.Endereco;
import br.com.exercicio.jsfcrud.vo.Usuario;

public enum Enderecos {

	INSTANCE;
	
	private List<Endereco> listEnderecos;

	private Enderecos() {
		listEnderecos = new ArrayList<>();
	}

	public void add(Endereco u) {
		UUID uuid = UUID.randomUUID();
		u.id = uuid.toString();  
		listEnderecos.add(u);
	}
	
	public Endereco get(String id) {		
		if (listEnderecos != null) {
			for (Endereco u : listEnderecos) {
				if (u.getId().equals(id)) {
					return u;
				}
			}
		}		
		return null;
	}
	
	public void remove(Endereco u) {
		listEnderecos.remove(u);
	}
	
	public List<Endereco> all() {
		return listEnderecos;
	}
	
}
