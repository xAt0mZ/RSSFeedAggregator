package com.api.rssaggregator.entities;

import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

@Entity
public class Feed {
	
	@Id
	public String id = ObjectId.get().toString();
	public String url;
	@Transient
	public String title;
	@Transient
	public String description;
	@Transient
	public String language;
	@Transient
	public String copyright;
	@Transient
	public String pubDate;

	@Embedded
	public List<FeedMessage> messages = Lists.newArrayList();

	public Feed() {

	}

	public Feed(String title, String link, String description, String language,
			String copyright, String pubDate) {
		this.title = title;
		this.url = link;
		this.description = description;
		this.language = language;
		this.copyright = copyright;
		this.pubDate = pubDate;
	}

}
