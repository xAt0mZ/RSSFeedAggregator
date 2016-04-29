package com.api.rssaggregator.endpoints;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.api.rssaggregator.annotations.Authenticated;
import com.api.rssaggregator.entities.User;
import com.api.rssaggregator.helpers.DAOHelper;

@Path("user")
public class UserController {
	@Context
	HttpServletRequest request;

	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
			"^[a-zA-Z0-9._+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	private static boolean validateEmail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("login")
	@Authenticated
	public User get(User u) {
		User user = DAOHelper.userDAO.createQuery().filter("email =", u.email)
				.filter("password =", u.password).get();
		if (user == null)
			throw new WebApplicationException(Response
					.status(Response.Status.BAD_REQUEST).build());
		request.getSession().setAttribute("user", user.email);
		return user;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("register")
	@Authenticated
	public User create(User user) {
		if (user.email == null || user.email.isEmpty()
				|| !validateEmail(user.email) || user.password == null
				|| user.password.isEmpty())
			throw new WebApplicationException(Response
					.status(Response.Status.BAD_REQUEST).build());
		if (DAOHelper.userDAO.createQuery().filter("email =", user.email).get() != null)
			throw new WebApplicationException(Response
					.status(Response.Status.BAD_REQUEST).build());
		DAOHelper.userDAO.save(user);
		request.getSession().setAttribute("user", user.email);
		return user;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticated
	public User update(User user) {
		DAOHelper.userDAO.save(user);
		return user;
	}
}
