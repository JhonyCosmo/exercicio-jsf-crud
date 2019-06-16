package br.com.exercicio.jsfcrud.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("cpfCnpjConverter")
public class CpfCnpjConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		String cpfCnpj = value;
		if (value != null && !value.equals(""))
			cpfCnpj = value.replaceAll("\\D", "");

		return cpfCnpj;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		String cpfCnpj = value.toString();

		if (cpfCnpj != null && cpfCnpj.length() == 11) {
			cpfCnpj = cpfCnpj.substring(0, 3) + "." 
					+ cpfCnpj.substring(3, 6) + "." 
					+ cpfCnpj.substring(6, 9) + "-" 
					+ cpfCnpj.substring(9, 11);
		}
		
		if (cpfCnpj != null && cpfCnpj.length() == 14) {
			cpfCnpj = cpfCnpj.substring(0, 2) + "." 
					+ cpfCnpj.substring(2, 5) + "." 
					+ cpfCnpj.substring(5, 8) + "/" 
					+ cpfCnpj.substring(8, 12) + "-"
					+ cpfCnpj.substring(12, 14);
		}

		return cpfCnpj;

	}
}
