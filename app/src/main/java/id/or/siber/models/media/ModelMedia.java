package id.or.siber.models.media;

import java.util.List;

public class ModelMedia{
	private String date;
	private String template;
	private Links links;
	private String link;
	private Description description;
	private Caption caption;
	private String type;
	private Title title;
	private MediaDetails mediaDetails;
	private String sourceUrl;
	private int post;
	private String altText;
	private String mediaType;
	private String modified;
	private int id;
	private String dateGmt;
	private String slug;
	private String modifiedGmt;
	private int author;
	private String commentStatus;
	private String pingStatus;
	private String mimeType;
	private List<Object> meta;
	private Guid guid;
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

	public Description getDescription(){
		return description;
	}

	public Caption getCaption(){
		return caption;
	}

	public String getType(){
		return type;
	}

	public Title getTitle(){
		return title;
	}

	public MediaDetails getMediaDetails(){
		return mediaDetails;
	}

	public String getSourceUrl(){
		return sourceUrl;
	}

	public int getPost(){
		return post;
	}

	public String getAltText(){
		return altText;
	}

	public String getMediaType(){
		return mediaType;
	}

	public String getModified(){
		return modified;
	}

	public int getId(){
		return id;
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

	public int getAuthor(){
		return author;
	}

	public String getCommentStatus(){
		return commentStatus;
	}

	public String getPingStatus(){
		return pingStatus;
	}

	public String getMimeType(){
		return mimeType;
	}

	public List<Object> getMeta(){
		return meta;
	}

	public Guid getGuid(){
		return guid;
	}

	public String getStatus(){
		return status;
	}
}