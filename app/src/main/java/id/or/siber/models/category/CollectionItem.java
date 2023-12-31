package id.or.siber.models.category;

import com.google.gson.annotations.SerializedName;

public class CollectionItem{

	@SerializedName("href")
	private String href;

	public String getHref(){
		return href;
	}
}