package id.or.siber.models.media;

public class MediaDetails{
	private String file;
	private Sizes sizes;
	private ImageMeta imageMeta;
	private int width;
	private int filesize;
	private int height;

	public String getFile(){
		return file;
	}

	public Sizes getSizes(){
		return sizes;
	}

	public ImageMeta getImageMeta(){
		return imageMeta;
	}

	public int getWidth(){
		return width;
	}

	public int getFilesize(){
		return filesize;
	}

	public int getHeight(){
		return height;
	}
}
