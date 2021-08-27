package Elements;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Recette {

	int id;
	String nom;
	Integer note;
	String description;
	Ingredients ingredients;

	public Ingredients getIngredients() {
		return ingredients;
	}

	@XmlElement
	public void setIngredients(Ingredients ingedients) {
		this.ingredients = ingedients;
	}

	@Override
	public String toString() {
		String ret = "Recette [nom=" + nom + "\n description=" + description + "\n";
		List<Ingredient> list = ingredients.getListIngredients();
		for (Ingredient ingredient : list) {
			ret += ingredient.getNom() + ingredient.getQuantite() + "\n";
		}
		ret += "]";
		return ret;
	}

	public String getNom() {
		return nom;
	}

	@XmlElement
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}

	@XmlAttribute
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getNote() {
		return note;
	}

	@XmlElement
	public void setNote(Integer note) {
		this.note = note;
	}
}
