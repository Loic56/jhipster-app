package Elements;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Liste")
public class Liste {


	List<Recette> listRecettes;

	public List<Recette> getListRecettes() {
		return listRecettes;
	}
	

	@XmlElement (name = "Recette")
	public void setListRecettes(List<Recette> listRecettes) {
		this.listRecettes = listRecettes;
	}


}
