import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Utils {

	//static String path = "C:\\Users\\loicc\\Documents\\Podcast\\";
	static String path = "C:\\Users\\crussonl\\Documents\\Podcast\\";
	
	public Utils() {
	}

	public static List<FluxRss> chargerFluxRss() {
		ArrayList<FluxRss> listeRetour = new ArrayList<>();

		FluxRss rssExpertSante = new FluxRss("Expert forme santé",
				"http://podcast.rmc.fr/channel296/RMCInfochannel296.xml", "RMC", "Santé");
		listeRetour.add(rssExpertSante);
		FluxRss rsssLaurentGerra = new FluxRss("Laurent gerra", "http://www.rtl.fr/podcast/laurent-gerra.xml", "RTL",
				"Humour");
		listeRetour.add(rsssLaurentGerra);
		FluxRss rssPierreBelemarre = new FluxRss("Pierre Bellemare",
				"http://radiofrance-podcast.net/podcast09/rss_16316.xml", null, "Chronique Criminelle");
		listeRetour.add(rssPierreBelemarre);
		FluxRss rssLHeureDuCrime = new FluxRss("L'heure du crime", "https://www.rtl.fr/podcast/l-heure-du-crime.xml",
				"RTL", "Chronique Criminelle");
		listeRetour.add(rssLHeureDuCrime);
		FluxRss rssAuCoeurDuCrime = new FluxRss("Au coeur du crime",
				"https://www.nouvelobs.com/podcast/au-coeur-du-crime/podcast.xml", "Nouvel obs",
				"Chronique Criminelle");
		listeRetour.add(rssAuCoeurDuCrime);
		FluxRss rssLesGrdesAffairesCriminelles = new FluxRss("Les Grandes Affaires Criminelles",
				"http://radiofrance-podcast.net/podcast09/rss_13362.xml", "France bleu", "Chronique Criminelle");
		listeRetour.add(rssLesGrdesAffairesCriminelles);
		return listeRetour;
	}

	public static void createRepository(String directoryPath) {
		File directory = new File(directoryPath);
		if (!directory.exists()) {
			directory.mkdir();
		}
	}

}
