import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

public class Launcher {

	public static void main(String[] args) throws IOException {

		List<FluxRss> listeRSS = Utils.chargerFluxRss();

		long debut = new Date().getTime();

		// liste des paramètres
		for (FluxRss flux : listeRSS) {

			// création du dossier de reception
			Utils.createRepository(Utils.path + "\\" + flux.getTitre());

			// création d'un objet RSSFeedParser à partir de l'url du flux rss
			ParseurDeFluxRss parser = new ParseurDeFluxRss(flux.getUrl());

			// objet Feed contient une liste d'url de fichiers mp3
			Lien lienRss = parser.lireFluxXml();

			// compteur
			int count = 0;

			System.out.println(lienRss.getTitle());
			for (MessageLien message : lienRss.getMessages()) {

				// nom du fichier a telecharger
				String[] result = message.getGuid().split("/");
				String filename = result[result.length - 1];

				// traitement que si le fichier n'existe pas d�j�
				File f = new File(Utils.path + flux.getTitre() + "\\" + filename);
				if (!f.exists()) {

					System.out.println(filename);
					URLConnection conn = new URL(message.getGuid()).openConnection();
					InputStream is = conn.getInputStream();

					OutputStream outstream = new FileOutputStream(
							new File(Utils.path + "\\" + flux.getTitre() + "\\" + filename));

					byte[] buffer = new byte[4096];
					int len;
					while ((len = is.read(buffer)) > 0) {
						outstream.write(buffer, 0, len);
					}
					outstream.close();
					count++;
				}
			}

			System.out.println("==> Nb de fichier telecharges : " + count);

		}

		long fin = new Date().getTime();
		long timeDifference = (fin - debut) / 1000;
		int h = (int) (timeDifference / (3600));
		int m = (int) ((timeDifference - (h * 3600)) / 60);
		int s = (int) (timeDifference - (h * 3600) - m * 60);

		System.out.println("");
		System.out.println("Fin du download");
		System.out.println("Durée de la recherche : " + String.format("%02d:%02d:%02d", h, m, s));

	}
}
