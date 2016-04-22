package com.api.rssaggregator.entities;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class User {

	@Id
	public String id = ObjectId.get().toString();
	public String firstName;
	public String lastName;
	public Date birthDate;
	public String address;
	public String login;
	public String password;
	public String email;
	public List<String> urls;
	public List<String> favorites;

	public User() {
	}

}
