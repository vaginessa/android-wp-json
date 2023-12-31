package id.or.siber.models.media;

import java.util.List;

public class ImageMeta{
	private String aperture;
	private String copyright;
	private String orientation;
	private String focalLength;
	private String iso;
	private List<Object> keywords;
	private String caption;
	private String createdTimestamp;
	private String credit;
	private String camera;
	private String title;
	private String shutterSpeed;

	public String getAperture(){
		return aperture;
	}

	public String getCopyright(){
		return copyright;
	}

	public String getOrientation(){
		return orientation;
	}

	public String getFocalLength(){
		return focalLength;
	}

	public String getIso(){
		return iso;
	}

	public List<Object> getKeywords(){
		return keywords;
	}

	public String getCaption(){
		return caption;
	}

	public String getCreatedTimestamp(){
		return createdTimestamp;
	}

	public String getCredit(){
		return credit;
	}

	public String getCamera(){
		return camera;
	}

	public String getTitle(){
		return title;
	}

	public String getShutterSpeed(){
		return shutterSpeed;
	}
}