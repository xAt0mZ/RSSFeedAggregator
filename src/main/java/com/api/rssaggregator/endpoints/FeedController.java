package com.api.rssaggregator.endpoints;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import jersey.repackaged.com.google.common.collect.Lists;

import com.api.rssaggregator.annotations.Authenticated;
import com.api.rssaggregator.entities.Feed;
import com.api.rssaggregator.entities.FeedMessage;
import com.api.rssaggregator.entities.Folder;
import com.api.rssaggregator.entities.User;
import com.api.rssaggregator.filters.AuthenticatedFilter;
import com.api.rssaggregator.helpers.DAOHelper;
import com.api.rssaggregator.helpers.RSSFeedParser;

@Path("feeds")
public class FeedController {
	@Context
	HttpServletRequest request;

	private FeedMessage findByUrl(List<FeedMessage> list, String url) {
		for (FeedMessage message : list) {
			if (message.url.equals(url))
				return message;
		}
		return null;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticated
	public User get() {
		String token = request.getParameter("JSESSIONID");
		HttpSession session;
		if (token == null || token.isEmpty()) {
			session = request.getSession();
		} else
			session = AuthenticatedFilter.sessions.get(token);
		String email = (String) session.getAttribute("user");
		User user = DAOHelper.userDAO.createQuery().filter("email =", email)
				.get();
		for (Folder folder : user.folders) {
			List<Feed> newList = Lists.newArrayList();
			for (Feed oldFeed : folder.feeds) {
				RSSFeedParser parser = new RSSFeedParser(oldFeed.url);
				Feed newFeed = parser.readFeed();
				for (FeedMessage newMessage : newFeed.messages) {
					FeedMessage oldMessage = findByUrl(oldFeed.messages,
							newMessage.url);
					if (oldMessage != null)
						newMessage.isRead = oldMessage.isRead;
				}
				newList.add(newFeed);
			}
			folder.feeds = newList;
		}
		DAOHelper.userDAO.save(user);
		return user;
	}
}
