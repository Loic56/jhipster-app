package Elements;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ingredient {

	String nom;
	String quantite;
	int id;

	public String getNom() {
		return nom;
	}

	@XmlElement
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getQuantite() {
		return quantite;
	}

	@XmlElement
	public void setQuantite(String quantite) {
		this.quantite = quantite;
	}

	@XmlAttribute
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
