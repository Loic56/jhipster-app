import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.chrono.JapaneseChronology;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Elements.Ingredient;
import Elements.Ingredients;
import Elements.Liste;
import Elements.Recette;
import Utils.JAXBUtils;

public class Launcher {

	static Scanner sc;

	/**
	 * Lancement
	 * 
	 * @param args
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		afficherMenu();
	}

	private static void afficherMenu() throws FileNotFoundException {
		sc = new Scanner(System.in);
		System.out.println("\r\n---------------------------------------------\r\n"
				+ "1) Enregistrer une nouvelle recette\r\n" + "2) Obtenir une liste de course\r\n"
				+ "3) Noter une recette\r\n" + "4) Choisir dans la liste");
		System.out.println("---------------------------------------------\r\n");
		String choix = sc.nextLine();
		if (choix.equals("1")) {
			creerNouvelleRecette();
		} else if (choix.equals("2")) {
			obtenirUneListe();
		} else if (choix.equals("3")) {
			noterLaRecette();
			afficherLaListeCompleteDesRecettes();
		} else if (choix.equals("4")) {
			selectionnerLesRecettes();
		} else {
			System.out.println("mauvaise saisie");
		}
	}



	/**
	 * @throws FileNotFoundException
	 */
	private static void selectionnerLesRecettes() throws FileNotFoundException {
		afficherLaListeCompleteDesRecettes();
		System.out.println("Choix ? (1,2,5,4)");
		String selection = sc.nextLine();
		String[] tab = selection.split(",");
		List<Recette> liste = new ArrayList<>();
		for(String s : tab){
			Recette recette = JAXBUtils.rechercherRecetteParId(Integer.parseInt(s));
			liste.add(recette);
		}
		genererFichier(liste);
	}

	
	/**
	 * @throws NumberFormatException
	 * @throws FileNotFoundException
	 */
	private static void noterLaRecette() throws FileNotFoundException {
		afficherLaListeCompleteDesRecettes();

		int id = sc.nextInt();
		sc.nextLine();
		Recette rec = JAXBUtils.rechercherRecetteParId(id - 1);
		System.out.println(rec.getNom());
		System.out.println("\r\nQuelle note ? (1 a 5)\r\n");
		String note = sc.nextLine();

		modifierLaNoteRecette(Integer.parseInt(note), rec.getId());

		raz();
	}
	
	
	/**
	 * @throws FileNotFoundException
	 * @throws NumberFormatException
	 * 
	 */
	private static void afficherLaListeCompleteDesRecettes() throws FileNotFoundException {
		List<Recette> listeComplete = JAXBUtils.XMLToObject().getListRecettes();
		for (Recette rec : listeComplete) {
			System.out.println(rec.getId() + " - " + rec.getNom() + " note: " + rec.getNote());
		}
	}

	/**
	 * @param liste
	 */
	private static void genererFichier(List<Recette> liste) {
		String s = "-----------------------------------\r\n";
		s += "Liste de courses\r\n";
		s += "-----------------------------------\r\n";
		for (int i = 0; i < liste.size(); i++) {
			Recette recette = liste.get(i);
			String note = "";
			for (int n = 0; n < recette.getNote(); n++) {
				note += "*";
			}
			s += recette.getNom() + "\r\n";
			List<Ingredient> l = recette.getIngredients().getListIngredients();
			for (int j = 0; j < l.size(); j++) {
				Ingredient ingredient = l.get(j);
				s += "	> " + ingredient.getQuantite() + " - " + ingredient.getNom() + "\r\n";
			}
		}

		s += "\r\n";

		for (int i = 0; i < liste.size(); i++) {
			Recette recette = liste.get(i);
			s += "\r\n	----------------\r\n";
			s += "		Jour " + (i + 1) + "\r\n";
			s += "	----------------\r\n";
			s += "Recette : " + recette.getNom() + "\r\n";
			s += "\r\n";
			List<Ingredient> l = recette.getIngredients().getListIngredients();
			s += "Ingrédients :\r\n";
			for (int j = 0; j < l.size(); j++) {
				Ingredient ingredient = l.get(j);
				s += "	> " + ingredient.getQuantite() + " - " + ingredient.getNom() + "\r\n";
			}
			s += "\r\n";
			String[] description = recette.getDescription().split("-");
			for (int d = 1; d < description.length; d++) {
				s += "- " + description[d];
			}
		}

		ecrire(s);
	}

	/**
	 * @param path
	 * @param content
	 */
	private static void ecrire(String content) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy-HHmmss");
		String date = sdf.format(new Date());
		String path = "C:\\Users\\crussonl\\Documents\\Bouffe\\course_" + date + ".txt";
		File file = new File(path);
		FileWriter fw;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(">> " + file.toString());
	}

	/**
	 * Obtenir une liste de recette pr un certain nombre de jours
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	private static void obtenirUneListe() throws FileNotFoundException {
		System.out.println("\r\nPour combien de jours : ");
		int nbJours = sc.nextInt();

		System.out.println("\r\nCombien de propositions : ");
		int nbPropositions = sc.nextInt();

		List<List<Recette>> listeProposition = genererListePropositions(nbJours, nbPropositions);

		System.out.println("\r\nChoix ? ");
		int choix = sc.nextInt();

		genererFichier(listeProposition.get(choix - 1));

		sc.nextLine();

		raz();
	}

	/**
	 * Génère plusieurs propositions de liste d'un certain nombre de jours
	 * 
	 * @param nbJours
	 * @param nbProposition
	 * @return
	 */
	private static List<List<Recette>> genererListePropositions(int nbJours, int nbProposition) {
		List<Recette> toutesLesRecettesExistantes = JAXBUtils.XMLToObject().getListRecettes();
		List<List<Recette>> listePropositions = new ArrayList<>();
		for (int i = 0; i < nbProposition; i++) {

			ArrayList<Recette> liste = new ArrayList<>();
			do {
				Integer index = new Random().nextInt(toutesLesRecettesExistantes.size());
				Recette recette = toutesLesRecettesExistantes.get(index);
				if (!listeDejaReference(listePropositions, recette) && !liste.contains(recette)) {
					liste.add(toutesLesRecettesExistantes.get(index));
				}
			} while (liste.size() < nbJours);
			listePropositions.add(liste);
		}
		afficherLeslistes(listePropositions);
		return listePropositions;
	}

	/**
	 * Vérifie qu'une recette n'est pas déjà existante ds une des listes
	 * précedemment crées
	 * 
	 * @param listeProposition
	 * @param recette
	 * @return
	 */
	private static Boolean listeDejaReference(List<List<Recette>> listeProposition, Recette recette) {
		for (List<Recette> l : listeProposition) {
			if (l.contains(recette)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * Affiche plusieurs liste de recette. Pour chaque recette affiche le nom et
	 * la note
	 * 
	 * @param listeProposition
	 */
	private static void afficherLeslistes(List<List<Recette>> listeProposition) {
		for (int i = 0; i < listeProposition.size(); i++) {
			List<Recette> liste = listeProposition.get(i);
			System.out.println(i + 1);
			for (int s = 0; s < liste.size(); s++) {
				Recette recette = liste.get(s);
				String note = "";
				for (int n = 0; n < recette.getNote(); n++) {
					note += "*";
				}
				System.out.println("  - " + recette.getNom() + " (" + note + ")");
			}
		}
	}

	/**
	 * Saisir une nouvelle recette et l'ajouter � la liste de mes recettes
	 * favorites
	 * 
	 * @throws FileNotFoundException
	 */
	private static void creerNouvelleRecette() throws FileNotFoundException {
		Recette recette = choisirUnNomEtDescription();
		recette = definirLesIngredients(recette);
		recapitulatifDeLaRecette(recette);
		sauvegarderLaRecette(recette);
		raz();
	}

	/**
	 * @throws FileNotFoundException
	 * 
	 */
	private static void raz() throws FileNotFoundException {
		System.out.println("\r\n-------------\r\n" + "1) Menu \r\n" + "2) Quitter\r\n" + "-------------\r\n");
		String choix = sc.nextLine();
		if ("1".equals(choix)) {
			afficherMenu();
		} else if ("2".equals(choix)) {
			System.out.println("\r\nAdios !");
			System.exit(0);
		} else if ("2".equals(choix)) {
			System.out.println("Mauvaise saisie");
		}
	}

	private static void modifierLaNoteRecette(Integer note, int id) throws FileNotFoundException {
		Liste liste = JAXBUtils.XMLToObject();
		List<Recette> recettes = liste.getListRecettes();
		for (Recette rec : recettes) {
			if (id == rec.getId()) {
				rec.setNote(note);
			}
		}
		JAXBUtils.ObjectToXML(liste);
	}

	private static void sauvegarderLaRecette(Recette recette) throws FileNotFoundException {
		Liste liste = JAXBUtils.XMLToObject();
		List<Recette> recettes = liste.getListRecettes();
		recettes.add(recette);
		JAXBUtils.ObjectToXML(liste);
	}

	private static void recapitulatifDeLaRecette(Recette recette) {
		System.out.println("\r\nRecap de la recette ?\r\n---------------------------------------");
		System.out.println(recette.getNom());
		System.out.println(recette.getDescription());
		for (Ingredient ingredient : recette.getIngredients().getListIngredients()) {
			System.out.println(ingredient.getNom() + " - " + ingredient.getQuantite());
		}
	}

	private static Recette definirLesIngredients(Recette recette) {
		System.out
				.println("\r\nCombien d'ingrédients ds la recette ?\r\n---------------------------------------------");
		int nb = sc.nextInt();
		sc.nextLine();
		Ingredients ingredients = new Ingredients();
		List<Ingredient> liste = new ArrayList<Ingredient>();

		for (int i = 1; i <= nb; i++) {
			System.out.println("\r\nIngrédient " + i + "\r\n---------------------------------------");
			Ingredient ingredient = new Ingredient();
			boolean ok = false;
			while (!ok) {
				System.out.println("> nom : ");
				String nom_ingredient = sc.nextLine();
				ingredient.setNom(nom_ingredient);
				ok = true;
			}
			ok = false;
			while (!ok) {
				System.out.println("> quantité : ");
				String quantite_ingredient = sc.nextLine();
				ingredient.setQuantite(quantite_ingredient);
				ok = true;
			}
			ingredient.setId(i);
			liste.add(ingredient);
		}
		ingredients.setListIngredients(liste);
		recette.setIngredients(ingredients);
		return recette;
	}

	private static Recette choisirUnNomEtDescription() {
		Recette recette = new Recette();
		Boolean ok = false;
		String re = "";
		while (!ok) {
			System.out.println("\r\nVeuillez " + re
					+ "saisir un nom de recette :\r\n--------------------------------------------------");
			String nom_recette = sc.nextLine();
			System.out.println("> Valider ? (o/n) : ");
			String valid = sc.nextLine();
			if (valid.equals("o")) {
				ok = true;
				recette.setNom(nom_recette);
			} else
				re = "re-";
		}
		ok = false;
		while (!ok) {
			System.out.println("\r\nDescription : ");
			String description = sc.nextLine();
			System.out.println("> Valider ? (o/n) : ");
			String valid = sc.nextLine();
			if (valid.equals("o")) {
				ok = true;
				recette.setDescription(description);
			} else
				re = "re-";
		}
		int index = JAXBUtils.nombreDeRecettes();
		recette.setId(index + 1);
		recette.setNote(0);
		return recette;
	}

}
