package com.api.rssaggregator.filters;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import com.api.rssaggregator.annotations.Authenticated;

@Authenticated
@Provider
public class AuthenticatedFilter implements ContainerRequestFilter {
	@Context
	HttpServletRequest request;

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		HttpSession session = request.getSession();
		String path = request.getRequestURI().substring(
				request.getContextPath().length());
		if (!path.endsWith("/login") && !path.endsWith("/register")) {
			if (session.getAttribute("user") == null)
				requestContext.abortWith(Response.status(
						Response.Status.UNAUTHORIZED).build());
		}
	}
}