package com.api.rssaggregator.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.api.rssaggregator.annotations.Authenticated;

@Authenticated
@Provider
public class AuthenticatedFilter implements ContainerRequestFilter {
	@Context
	HttpServletRequest request;

	public static Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		String token = request.getParameter("JSESSIONID");
		HttpSession session;
		if (token == null || token.isEmpty()) {
			session = request.getSession();
			sessions.put(session.getId(), session);
		} else
			session = sessions.get(token);
		String path = request.getRequestURI().substring(
				request.getContextPath().length());
		if (!path.endsWith("/login") && !path.endsWith("/register")) {
			if (session.getAttribute("user") == null) {
				sessions.remove(session.getId());
				requestContext.abortWith(Response.status(
						Response.Status.UNAUTHORIZED).build());
			}
		}
	}
}