package id.or.siber.models.post;

import com.google.gson.annotations.SerializedName;

public class PredecessorVersionItem{

	@SerializedName("id")
	private int id;

	@SerializedName("href")
	private String href;

	public int getId(){
		return id;
	}

	public String getHref(){
		return href;
	}
}