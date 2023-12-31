package id.or.siber.models.post;

import com.google.gson.annotations.SerializedName;

public class Guid{

	@SerializedName("rendered")
	private String rendered;

	public String getRendered(){
		return rendered;
	}
}