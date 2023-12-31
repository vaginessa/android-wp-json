package id.or.siber.models.post;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ModelPostItem{

	@SerializedName("date")
	private String date;

	@SerializedName("template")
	private String template;

	@SerializedName("_links")
	private Links links;

	@SerializedName("link")
	private String link;

	@SerializedName("type")
	private String type;

	@SerializedName("title")
	private Title title;

	@SerializedName("content")
	private Content content;

	@SerializedName("featured_media")
	private int featuredMedia;

	@SerializedName("modified")
	private String modified;

	@SerializedName("id")
	private int id;

	@SerializedName("categories")
	private List<Integer> categories;

	@SerializedName("date_gmt")
	private String dateGmt;

	@SerializedName("slug")
	private String slug;

	@SerializedName("modified_gmt")
	private String modifiedGmt;

	@SerializedName("newstopic")
	private List<Object> newstopic;

	@SerializedName("author")
	private int author;

	@SerializedName("format")
	private String format;

	@SerializedName("comment_status")
	private String commentStatus;

	@SerializedName("tags")
	private List<Object> tags;

	@SerializedName("ping_status")
	private String pingStatus;

	@SerializedName("meta")
	private Meta meta;

	@SerializedName("sticky")
	private boolean sticky;

	@SerializedName("guid")
	private Guid guid;

	@SerializedName("excerpt")
	private Excerpt excerpt;

	@SerializedName("status")
	private String status;

	public String getDate(){
		return date;
	}

	public String getTemplate(){
		return template;
	}

	public Links getLinks(){
		return links;
	}

	public String getLink(){
		return link;
	}

	public String getType(){
		return type;
	}

	public Title getTitle(){
		return title;
	}

	public Content getContent(){
		return content;
	}

	public int getFeaturedMedia(){
		return featuredMedia;
	}

	public String getModified(){
		return modified;
	}

	public int getId(){
		return id;
	}

	public List<Integer> getCategories(){
		return categories;
	}

	public String getDateGmt(){
		return dateGmt;
	}

	public String getSlug(){
		return slug;
	}

	public String getModifiedGmt(){
		return modifiedGmt;
	}

	public List<Object> getNewstopic(){
		return newstopic;
	}

	public int getAuthor(){
		return author;
	}

	public String getFormat(){
		return format;
	}

	public String getCommentStatus(){
		return commentStatus;
	}

	public List<Object> getTags(){
		return tags;
	}

	public String getPingStatus(){
		return pingStatus;
	}

	public Meta getMeta(){
		return meta;
	}

	public boolean isSticky(){
		return sticky;
	}

	public Guid getGuid(){
		return guid;
	}

	public Excerpt getExcerpt(){
		return excerpt;
	}

	public String getStatus(){
		return status;
	}
}