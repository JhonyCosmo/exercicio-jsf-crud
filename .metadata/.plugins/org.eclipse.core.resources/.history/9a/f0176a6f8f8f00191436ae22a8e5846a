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

import br.com.exercicio.jsfcrud.enumerator.Usuarios;
import br.com.exercicio.jsfcrud.vo.Usuario;

@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioBean {

	private Usuario usuario;
	private List<SelectItem> listSexo;

	public UsuarioBean() {
		usuario = new Usuario();
	}

	@PostConstruct
	public void initSexo() {
		listSexo = new ArrayList<>();
		listSexo.add(new SelectItem("M", "Masculino"));
		listSexo.add(new SelectItem("F", "Feminino"));
	}

	public String prepararCadastro() {
		usuario = new Usuario();
		return "cadastroUsuario";
	}

	public String prepararList() {
		return "";
	}

	public String adicionarUsuario() {
		List<Usuario> listAux = getListUsuario();

		if (listAux != null) {
			for (Usuario u : listAux) {
				if (u.getUsername().equals(usuario.getUsername())) {
					FacesContext fc = FacesContext.getCurrentInstance();
					FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username já existente",
							"Username já existente");
					fc.addMessage(null, fm);
					return "";
				}
			}
		}
		Usuarios.INSTANCE.addUser(usuario);
		return "listarUsuarios";
	}

	public List<Usuario> getListUsuario() {
		return Usuarios.INSTANCE.allUsers();
	}

	public String carregarDetalhes(Usuario usuario) {
		this.usuario = usuario;
		return "detalhesUsuario";
	}
	
	public void carregarDetalhes2(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<SelectItem> getListSexo() {
		return listSexo;
	}

	public void setListSexo(List<SelectItem> listSexo) {
		this.listSexo = listSexo;
	}

}
