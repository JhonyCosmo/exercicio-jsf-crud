package br.com.exercicio.jsfcrud.enumerator;

import java.util.ArrayList;
import java.util.List;

import br.com.exercicio.jsfcrud.vo.Usuario;


public enum Usuarios {

	INSTANCE;

	private List<Usuario> listUsuarios;

	private Usuarios() {
		listUsuarios = new ArrayList<>();
	}

	public void addUser(Usuario u) {
		listUsuarios.add(u);
	}

	public List<Usuario> allUsers() {
		return listUsuarios;
	}
}
