package br.com.exercicio.jsfcrud.enumerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.exercicio.jsfcrud.vo.Usuario;


public enum Usuarios {

	INSTANCE;

	private List<Usuario> listUsuarios;

	private Usuarios() {
		listUsuarios = new ArrayList<>();
	}

	public void addUser(Usuario u) {
		UUID uuid = UUID.randomUUID();
		u.id = uuid.toString();
		listUsuarios.add(u);
	}

	public Usuario get(String id) {		
		if (listUsuarios != null) {
			for (Usuario u : listUsuarios) {
				if (u.getId().equals(id)) {
					return u;
				}
			}
		}		
		return null;
	}
	
	public Usuario getByName(String name) {		
		if (listUsuarios != null) {
			for (Usuario u : listUsuarios) {
				if (u.getNome().equals(name)) {
					return u;
				}
			}
		}		
		return null;
	}
	public List<Usuario> allUsers() {
		return listUsuarios;
	}
	
	public void remove(Usuario u) {
		listUsuarios.remove(u);
	}
}
