package br.com.exercicio.jsfcrud.bean;

import java.util.ArrayList;
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
	private List<SelectItem> listTipo;

	public UsuarioBean() {
		usuario = new Usuario();
	}
	

	@PostConstruct
	public void initCamposRadio() {
		listSexo = new ArrayList<>();
		listSexo.add(new SelectItem("M", "Masculino"));
		listSexo.add(new SelectItem("F", "Feminino"));
		
		listTipo = new ArrayList<>();
		listTipo.add(new SelectItem(1, "Pessoa F�sica"));
		listTipo.add(new SelectItem(2, "Pessoa Jur�dica"));
	}

	public String prepararCadastro() {
		usuario = new Usuario();
		return "cadastroUsuario";
	}

	public String adicionarUsuario() {
		// Validando o nome
		Usuario usuarioNome = Usuarios.INSTANCE.getByName(usuario.getNome());
		
		if(usuarioNome!=null) {
			
			if(usuarioNome.getId().equals(usuario.getId())==false) {
				
				adicionarMensagemErro("Username j� existente");

				return "";
			}

		}

		Usuario usuarioPersistido = Usuarios.INSTANCE.get(usuario.id);

		if (usuarioPersistido != null) {

			usuarioPersistido = usuario;

		} else {

			Usuarios.INSTANCE.addUser(usuario);

		}

		return "listarUsuarios";
	}

	public List<Usuario> getListUsuario() {
		return Usuarios.INSTANCE.allUsers();
	}

	public String removerUsuario(Usuario usuario) {
		Usuarios.INSTANCE.remove(usuario);
		System.out.println("Removendo Usuario");
		return "listarUsuarios";
	}

	public String editarUsuario(Usuario usuario) {
		this.usuario = usuario;
		return "cadastroUsuario";
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
	
	public List<SelectItem> getListTipo() {
		return listTipo;
	}
	
	public void setListTipo(List<SelectItem> listTipo) {
		this.listTipo = listTipo;
	}
	
	public void setListSexo(List<SelectItem> listSexo) {
		this.listSexo = listSexo;
	}
	

	private void adicionarMensagemErro(String mensagem) {

		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "Username j� existente");
		fc.addMessage(null, fm);

	}
}
