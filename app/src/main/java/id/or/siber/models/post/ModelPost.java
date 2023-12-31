package id.or.siber.models.post;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ModelPost{

	@SerializedName("ModelPost")
	private List<ModelPostItem> modelPost;

	public List<ModelPostItem> getModelPost(){
		return modelPost;
	}
}