package org.manolete.apuestas.euromillones;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "claveValor")
public class ClaveValor {
	
	@XmlElement
	private String clave;
	@XmlElement
	private String valor;
	
	public String getClave() {
		return clave;
	}
	public String getValor() {
		return valor;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
}
