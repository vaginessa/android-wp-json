package id.or.siber.models.category;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ModelCategory{

	@SerializedName("ModelCategory")
	private List<ModelCategoryItem> modelCategory;

	public List<ModelCategoryItem> getModelCategory(){
		return modelCategory;
	}
}