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

@ManagedBean(name = "enderecoBean")
@SessionScoped
public class EnderecoBean {

	private Endereco endereco;
	private List<SelectItem> listEstado;
	private List<SelectItem> listaCidades;

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@PostConstruct
	public void initEstados() {
		listEstado = new ArrayList<>();
		listEstado.add(new SelectItem("AC", "Acre"));
		listEstado.add(new SelectItem("AL", "Alagoas"));
		listEstado.add(new SelectItem("AP", "Amapá"));
		listEstado.add(new SelectItem("AM", "Amazonas"));
		listEstado.add(new SelectItem("BA", "Bahia"));
		listEstado.add(new SelectItem("CE", "Ceará"));
		listEstado.add(new SelectItem("DF", "Distrito Federal"));
		listEstado.add(new SelectItem("ES", "Espírito Santo"));
		listEstado.add(new SelectItem("GO", "Goiás"));
		listEstado.add(new SelectItem("MA", "Maranhão"));
		listEstado.add(new SelectItem("MT", "Mato Grosso"));
		listEstado.add(new SelectItem("MS", "Mato Grosso do Sul"));
		listEstado.add(new SelectItem("MG", "Minas Gerais"));
		listEstado.add(new SelectItem("PB", "Paraíba"));
		listEstado.add(new SelectItem("PR", "Paraná"));
		listEstado.add(new SelectItem("PE", "Pernambuco"));
		listEstado.add(new SelectItem("RJ", "Rio de Janeiro"));
		listEstado.add(new SelectItem("RN", "Rio Grande do Norte"));
		listEstado.add(new SelectItem("RO", "Rondônia"));
		listEstado.add(new SelectItem("RR", "Roraima"));
		listEstado.add(new SelectItem("SC", "Santa Catarina"));
		listEstado.add(new SelectItem("SP", "São Paulo"));
		listEstado.add(new SelectItem("SE", "Sergipe"));
		listEstado.add(new SelectItem("TO", "Tocantins"));

	}

	private void updateCidades() {
		try {
			String fileName = "estadosCidades.json";
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			File file = new File(classLoader.getResource(fileName).getFile());
			System.out.println("Arquivo encontrado : " + file.exists());
			if (file.exists()) {
				InputStream is = new FileInputStream(file);
				String jsonTxt = IOUtils.toString(is, "UTF-8");
				JSONObject json = new JSONObject(jsonTxt);

				for (Object objEstado : json.getJSONArray("estados")) {
					JSONObject estado = new JSONObject(objEstado.toString());
					// Buscando estado selecionado
					String uf = "PB";
					if (this.endereco.getEstado() != null) {
						uf = this.endereco.getEstado();
					}

					if (estado.get("sigla").equals(uf.toUpperCase())) {
						for (Object objCidade : estado.getJSONArray("cidades")) {
							System.out.println(objCidade.toString());
							listaCidades.add(new SelectItem(objCidade.toString(), objCidade.toString()));
						}
					}
				}
			}

		} catch (Exception ex) {
			System.out.println("Erro" + ex.getMessage());
		}
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

	public List<SelectItem> getListCidades() {
		this.updateCidades();
		return listaCidades;
	}

	public void setListSexo(List<SelectItem> listEstado) {
		this.listEstado = listEstado;
	}
}
