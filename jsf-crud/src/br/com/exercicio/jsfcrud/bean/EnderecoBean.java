package br.com.exercicio.jsfcrud.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.primefaces.json.JSONObject;
import org.primefaces.shaded.commons.io.IOUtils;

import br.com.exercicio.jsfcrud.enumerator.Enderecos;
import br.com.exercicio.jsfcrud.vo.Endereco;

import br.com.exercicio.jsfcrud.vo.UnidadeFederativa;
import br.com.exercicio.jsfcrud.vo.Usuario;


@ManagedBean(name = "enderecoBean")
@SessionScoped
public class EnderecoBean {

	private Endereco endereco;

	private List<UnidadeFederativa> listaEstados;
	private List<SelectItem> listEstado;
	private String filtro;
	private List<Endereco> listaEndereco;
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@PostConstruct
	public void initEstados() {
		
		carregarUnidadesFederativas();
		
		listEstado = new ArrayList<>();
		
		for (UnidadeFederativa uf : listaEstados) {
			listEstado.add(new SelectItem(uf.getSigla(), uf.getEstado()));
		}
		
		listaEndereco=Enderecos.INSTANCE.all();
	}
	
	public void filtrarTabela() {
		listaEndereco = new ArrayList<>();
		for (Endereco u : Enderecos.INSTANCE.all()) {
			if (u.getCep().contains(filtro)) {
				listaEndereco.add(u);
			}
		}
	}
	
	public void carregarUnidadesFederativas() {
		
		listaEstados = new ArrayList<UnidadeFederativa>();
		
		try 
		{			
			String fileName = "estados-cidades.json";
	        ClassLoader classLoader = new EnderecoBean().getClass().getClassLoader();	 
	        File file = new File(classLoader.getResource(fileName).getFile());
			
	        if (file.exists())
	        {	
			    InputStream is = new FileInputStream(file);
				String jsonTxt = IOUtils.toString(is, "UTF-8");					
				JSONObject json = new JSONObject(jsonTxt);						
				
				for (Object objEstado : json.getJSONArray("estados"))
				{						
					JSONObject estado= new JSONObject(objEstado.toString());					
					UnidadeFederativa uf= new UnidadeFederativa();
					
					uf.setSigla(estado.get("sigla").toString());
					uf.setEstado(estado.get("nome").toString());
										
					for (Object objCidade : estado.getJSONArray("cidades")) 
					{
						uf.AddCidade(objCidade.toString());															
					}	
					
					listaEstados.add(uf);
				}
							
	        }
			
		}catch(Exception ex){
			System.out.println("Erro" + ex.getMessage());
		}	
		
	}
		
	public List<SelectItem> getListCidades(){
		
		List<SelectItem> listaCidades = new ArrayList<SelectItem>();
		
		for (UnidadeFederativa uf : listaEstados) 
		{	
			
			if(uf.getSigla().equals(this.endereco.getEstado())) {
				
				for (String cidade : uf.getCidades()) {
					listaCidades.add(new SelectItem(cidade,cidade));
				}				
			}			
		}		 
		return listaCidades;				
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

		Endereco enderecoPersistido = Enderecos.INSTANCE.get(endereco.id);

		if (enderecoPersistido != null) {
			enderecoPersistido = endereco;
		} else {
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
		return listaEndereco;
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
	
	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
}
