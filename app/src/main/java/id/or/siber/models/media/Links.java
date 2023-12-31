package id.or.siber.models.media;

import java.util.List;

public class Links{
	private List<RepliesItem> replies;
	private List<AboutItem> about;
	private List<SelfItem> self;
	private List<CollectionItem> collection;

	public List<RepliesItem> getReplies(){
		return replies;
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