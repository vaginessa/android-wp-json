package id.or.siber.models.post;

import com.google.gson.annotations.SerializedName;

public class WpFeaturedmediaItem{

	@SerializedName("href")
	private String href;

	@SerializedName("embeddable")
	private boolean embeddable;

	public String getHref(){
		return href;
	}

	public boolean isEmbeddable(){
		return embeddable;
	}
}