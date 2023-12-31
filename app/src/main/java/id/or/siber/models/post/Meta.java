package id.or.siber.models.post;

import com.google.gson.annotations.SerializedName;

public class Meta{

	@SerializedName("footnotes")
	private String footnotes;

	public String getFootnotes(){
		return footnotes;
	}
}