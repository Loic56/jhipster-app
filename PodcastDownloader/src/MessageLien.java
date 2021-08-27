
public class MessageLien {

	String titre;
	String description;
	String lien;
	String auteur;
	String guid;

	public String getTitle() {
		return titre;
	}

	public void setTitle(String title) {
		this.titre = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return lien;
	}

	public void setLink(String link) {
		this.lien = link;
	}

	public String getAuthor() {
		return auteur;
	}

	public void setAuthor(String author) {
		this.auteur = author;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	@Override
	public String toString() {
		return "FeedMessage [title=" + titre + ", description=" + description + ", link=" + lien + ", author=" + auteur
				+ ", guid=" + guid + "]";
	}

}
