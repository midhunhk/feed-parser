package com.ae.feeds.reader.model;

/**
 * Describes a FeedMessage
 * 
 * @author 380699
 * 
 */
public class FeedMessage {

	String	title;
	String	description;
	String	link;
	String	guid;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
}
