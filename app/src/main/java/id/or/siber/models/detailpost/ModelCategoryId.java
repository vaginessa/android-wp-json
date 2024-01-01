package id.or.siber.models.detailpost;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ModelCategoryId{

	@SerializedName("parent")
	private int parent;

	@SerializedName("_links")
	private Links links;

	@SerializedName("meta")
	private List<Object> meta;

	@SerializedName("count")
	private int count;

	@SerializedName("link")
	private String link;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private int id;

	@SerializedName("taxonomy")
	private String taxonomy;

	@SerializedName("slug")
	private String slug;

	public int getParent(){
		return parent;
	}

	public Links getLinks(){
		return links;
	}

	public List<Object> getMeta(){
		return meta;
	}

	public int getCount(){
		return count;
	}

	public String getLink(){
		return link;
	}

	public String getName(){
		return name;
	}

	public String getDescription(){
		return description;
	}

	public int getId(){
		return id;
	}

	public String getTaxonomy(){
		return taxonomy;
	}

	public String getSlug(){
		return slug;
	}
}