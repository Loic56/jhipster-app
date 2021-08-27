package Elements;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ingredients {

	List<Ingredient> listIngredients;

	
	public List<Ingredient> getListIngredients() {
		return listIngredients;
	}

	
	@XmlElement(name = "Ingredient")
	public void setListIngredients(List<Ingredient> listIngredients) {
		this.listIngredients = listIngredients;
	}

}
