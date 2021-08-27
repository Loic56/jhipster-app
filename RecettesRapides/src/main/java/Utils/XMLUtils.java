package Utils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLUtils {

	public static void EcrireListeRecetteEnXML(ArrayList<ArrayList<String>> listeRecettes) {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;

		try {
			docBuilder = docFactory.newDocumentBuilder();

			// root element
			Document doc = docBuilder.newDocument();
			Element recettesNode = doc.createElement("recettes");
			doc.appendChild(recettesNode);

			int compteurRecette = 1;
			for (ArrayList<String> recette : listeRecettes) {

				Element recetteNode = doc.createElement("recette");
				Attr attr = doc.createAttribute("id");
				attr.setValue(String.valueOf(compteurRecette));
				recetteNode.setAttributeNode(attr);
				compteurRecette++;
				recettesNode.appendChild(recetteNode);

				// nom
				Element nom = doc.createElement("nom");
				nom.appendChild(doc.createTextNode(recette.get(0)));
				recetteNode.appendChild(nom);

				Element description = doc.createElement("description");
				description.appendChild(doc.createTextNode(recette.get(1)));
				recetteNode.appendChild(description);
				
				// ingredients elements
				Element ingredientsNode = doc.createElement("ingredients");
				recetteNode.appendChild(ingredientsNode);

				int compteurIngredient = 1;
				for (int i = 2; i < recette.size(); i += 2) {

					Element ingredientNode = doc.createElement("ingredient");
					Attr ingredientId = doc.createAttribute("id");
					ingredientId.setValue(String.valueOf(compteurIngredient));
					ingredientNode.setAttributeNode(ingredientId);
					compteurIngredient++;

					// nom
					Element nomIngredient = doc.createElement("nom");
					nomIngredient.appendChild(doc.createTextNode(recette.get(i)));
					ingredientNode.appendChild(nomIngredient);

					// quantite
					Element quantiteIngredient = doc.createElement("quantite");
					quantiteIngredient.appendChild(doc.createTextNode(recette.get(i + 1)));
					ingredientNode.appendChild(quantiteIngredient);

					ingredientsNode.appendChild(ingredientNode);
				}
				compteurIngredient = 1;

			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			File newList = new File("C:\\liste.xml");
			
			StreamResult result = new StreamResult(newList);
			transformer.transform(source, result);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} 

	}

	public static int nombreDeRecettes() {
		int NBRecettes = 0;
		try {
			File fXmlFile = new File("C:\\Users\\crussonl\\Documents\\Bouffe\\fileJAXB.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("recette");
			NBRecettes = nList.getLength();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("NB recettes : " + NBRecettes);
		return NBRecettes;
	}

	// public static void AjouterALaListeExistante(Element nouvelleRecette) {
	// DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	// DocumentBuilder db = null;
	// try {
	// db = dbf.newDocumentBuilder();
	// Document doc = db.parse("C:\\file.xml");
	// NodeList recettes = doc.getElementsByTagName("recettes");
	// recettes.item(0).appendChild(nouvelleRecette);
	// System.out.println("Done");
	// } catch (SAXException e) {
	// // handle SAXException
	// } catch (IOException e) {
	// // handle IOException
	// } catch (ParserConfigurationException e1) {
	// // handle ParserConfigurationException
	// }
	// }

	public static ArrayList<ArrayList<String>> FichierXMLenList() {
		
		// save old
		File newList = new File("C:\\liste.xml");
		File oldList = new File("C:\\liste_old.xml");

		try {
			Files.copy(newList.toPath(), oldList.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
					
					
		File fXmlFile = newList;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		ArrayList<ArrayList<String>> listoflist = new ArrayList<ArrayList<String>>();
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("recettes");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				// pour chaque recettes
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					NodeList nList2 = eElement.getElementsByTagName("recette");
					for (int n = 0; n < nList2.getLength(); n++) {
						// recette
						Node nNode2 = nList2.item(n);
						if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
							// ingredients
							Element eElement2 = (Element) nNode2;
							ArrayList<String> list = new ArrayList<>();
							// nom
							list.add(eElement2.getElementsByTagName("nom").item(0).getTextContent());
							list.add(eElement2.getElementsByTagName("description").item(0).getTextContent());
							NodeList nList3 = eElement2.getElementsByTagName("ingredients");
							for (int m = 0; m < nList3.getLength(); m++) {
								// ingredient
								Node nNode3 = nList3.item(m);
								if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
									Element eElement3 = (Element) nNode3;

									NodeList nList4 = eElement3.getElementsByTagName("ingredient");
									for (int o = 0; o < nList4.getLength(); o++) {
										Node nNode4 = nList4.item(o);
										if (nNode4.getNodeType() == Node.ELEMENT_NODE) {
											Element eElement4 = (Element) nNode4;

											list.add(eElement4.getElementsByTagName("nom").item(0).getTextContent());
											list.add(eElement4.getElementsByTagName("quantite").item(0)
													.getTextContent());

										}
									}
									listoflist.add(list);
								}
							}
						}
					}
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return listoflist;
	}
}
