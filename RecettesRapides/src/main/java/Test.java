import java.io.FileNotFoundException;
import java.util.ArrayList;

import Elements.Ingredient;
import Elements.Ingredients;
import Elements.Liste;
import Elements.Recette;
import Utils.JAXBUtils;
import Utils.XMLUtils;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// testAjouterALaListeExistante();
		// testXMLenList();
		// testnombreDeRecettes();
		// testObjectToXML();
		// testXMLToObject();
		testAfficherLesRecettes();
	}
	
	
	private static void testObjectToXML() throws FileNotFoundException {
		ArrayList<Ingredient> list = new ArrayList<Ingredient>();
		Ingredient i = new Ingredient();
		i.setNom("Patates");
		i.setQuantite("10");
		list.add(i);
		i = new Ingredient();
		i.setNom("Tomates");
		i.setQuantite("4");
		list.add(i);
		Recette rec = new Recette();
		rec.setNom("Nom de la recette");
		rec.setDescription("description de la recette");
		Ingredients ingredients = new Ingredients();
		ingredients.setListIngredients(list);
		Recette recette = new Recette();
		recette.setDescription("Decsription");
		recette.setId(1);
		recette.setNom("Rcette test");
		recette.setIngredients(ingredients);
		
		ArrayList<Ingredient> list2 = new ArrayList<Ingredient>();
		Ingredient i2 = new Ingredient();
		i2.setNom("carotte");
		i2.setQuantite("10");
		list2.add(i2);
		i2 = new Ingredient();
		i2.setNom("cailloux");
		i2.setQuantite("4");
		list2.add(i2);
		Recette rec2 = new Recette();
		rec2.setNom("Rcette 2");
		rec2.setDescription("description de la recette 2");
		Ingredients ingredients2 = new Ingredients();
		ingredients2.setListIngredients(list2);
		Recette recette2 = new Recette();
		recette2.setDescription("Decsription");
		recette2.setId(1);
		recette2.setNom("Rcette test");
		recette2.setIngredients(ingredients2);
		
		
		ArrayList<Recette> listRecettes = new ArrayList<Recette>();
		listRecettes.add(recette);
		listRecettes.add(recette2);
		
		Liste liste = new Liste();
		liste.setListRecettes(listRecettes);

		JAXBUtils.ObjectToXML(liste);
	}

	private static void testXMLenList() {
		ArrayList<ArrayList<String>>  listoflist = XMLUtils.FichierXMLenList();
		for(ArrayList<String> list : listoflist){
			for(String element : list){
				System.out.println(element);
			}
			System.out.println("----------");
		}
	}

	
	public static void testnombreDeRecettes(){
		int i = JAXBUtils.nombreDeRecettes();
		System.out.println("nb de recettes : " + i);
	}
	
	
	public static void testAfficherLesRecettes(){
		JAXBUtils.AfficherLesRecettes();
	}
	
	
	public static void testRecetteEnXML(){
		ArrayList<ArrayList<String>> listoflist = new ArrayList<ArrayList<String>>();
		ArrayList<String> list = new ArrayList<>();
		list.add("Caf�");
		list.add("10kg");
		listoflist.add(list);
		list = new ArrayList<>();
		list.add("Camenbert");
		list.add("25kg");
		listoflist.add(list);
		list = new ArrayList<>();
		list.add("Pommes");
		list.add("2");
		listoflist.add(list);
		XMLUtils.EcrireListeRecetteEnXML(listoflist);
	}
	
	public static void testAjouterALaListeExistante(){

		ArrayList<ArrayList<String>>  listoflist = XMLUtils.FichierXMLenList();
		ArrayList<String> list = new ArrayList<>();
		list.add("Test?");
		list.add("Pruneaux");
		list.add("1kg");
		list.add("Chips");
		list.add("1 tonne");
		listoflist.add(list);
		
		XMLUtils.EcrireListeRecetteEnXML(listoflist);

		
	}
	
	
	public static void testXMLToObject(){
		Liste liste = JAXBUtils.XMLToObject();
		for(Recette recette : liste.getListRecettes()){
			System.out.println(recette);
		}
	}

}
