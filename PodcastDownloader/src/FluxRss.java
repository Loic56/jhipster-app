
public class FluxRss {

	public String url;
	public String titre;
	public String type;
	public String source;

	public FluxRss(String titre, String url, String source, String type) {
		super();
		this.url = url;
		this.titre = titre;
		this.source = source;
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
