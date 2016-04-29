package com.api.rssaggregator.entities;

import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class User {

	@Id
	public String email;
	public String password;

	@Embedded
	public List<Folder> folders = Lists.newArrayList();

	public User() {
	}

}
