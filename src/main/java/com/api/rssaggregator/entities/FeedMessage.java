package com.api.rssaggregator.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

@Entity
public class FeedMessage {

	@Id
	public String id = ObjectId.get().toString();
	public String url;
	@Transient
	public String title;
	@Transient
	public String description;
	@Transient
	public String author;
	@Transient
	public String guid;
	public boolean isRead = false;

}