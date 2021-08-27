package Utils;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import Elements.Liste;
import Elements.Recette;

public class JAXBUtils {

	public JAXBUtils() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return
	 */
	public static Liste XMLToObject() {
		Liste liste = null;
		try {
			
			File file = new File("C:\\Users\\crussonl\\Documents\\Bouffe\\fileJAXB.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Liste.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			liste = (Liste) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return liste;
	}

	/**
	 * 
	 */
	public static void AfficherLesRecettes() {
		Liste list = XMLToObject();
		List<Recette> recettes = list.getListRecettes();
		int index = 0;
		for (Recette rec : recettes) {
			System.out.println(++index + ") " + rec.getNom());
		}
	}

	public static Recette rechercherRecetteParId(Integer id) {
		Liste list = XMLToObject();
		List<Recette> recettes = list.getListRecettes();
		for (Recette rec : recettes) {
			if (id.equals(rec.getId())) {
				return rec;
			}
		}
		return null;
	}
	
	/**
	 * @param liste
	 */
	public static void ObjectToXML(Liste liste) {
		try {
			File file = new File("C:\\Users\\crussonl\\Documents\\Bouffe\\fileJAXB.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Liste.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(liste, file);
			// jaxbMarshaller.marshal(liste, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 */
	public static int nombreDeRecettes() {
		Liste list = XMLToObject();
		List<Recette> recettes = list.getListRecettes();
		return recettes.size();
	}
}
