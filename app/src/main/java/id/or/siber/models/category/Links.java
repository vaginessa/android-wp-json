package id.or.siber.models.category;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Links{

	@SerializedName("wp:post_type")
	private List<WpPostTypeItem> wpPostType;

	@SerializedName("curies")
	private List<CuriesItem> curies;

	@SerializedName("about")
	private List<AboutItem> about;

	@SerializedName("self")
	private List<SelfItem> self;

	@SerializedName("collection")
	private List<CollectionItem> collection;

	public List<WpPostTypeItem> getWpPostType(){
		return wpPostType;
	}

	public List<CuriesItem> getCuries(){
		return curies;
	}

	public List<AboutItem> getAbout(){
		return about;
	}

	public List<SelfItem> getSelf(){
		return self;
	}

	public List<CollectionItem> getCollection(){
		return collection;
	}
}