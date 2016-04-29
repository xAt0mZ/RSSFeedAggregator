package com.api.rssaggregator.entities;

import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Id;

public class Folder {

	@Id
	public String id = ObjectId.get().toString();
	public String title;

	@Embedded
	public List<Feed> feeds = Lists.newArrayList();

	public Folder() {

	}
}
