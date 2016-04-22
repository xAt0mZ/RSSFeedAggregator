package com.api.rssaggregator.endpoints;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.api.rssaggregator.entities.User;
import com.api.rssaggregator.helpers.DAOHelper;

@Path("users")
public class UserController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> get() {
		return DAOHelper.userDAO.find().asList();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public User getById(@PathParam("id") String id) {
		return DAOHelper.userDAO.createQuery().filter("id =", id).get();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(User entity) {
		DAOHelper.userDAO.save(entity);
		return Response.status(201).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(User entity) {
		DAOHelper.userDAO.save(entity);
		return Response.status(201).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(User entity) {
		DAOHelper.userDAO.delete(entity);
		return Response.status(201).build();
	}

}
