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
import com.api.rssaggregator.helpers.DAOHelper;
import com.api.rssaggregator.helpers.RSSFeedParser;

@Path("feeds")
public class FeedController {
	@Context
	HttpServletRequest request;

	private FeedMessage findByUrl(List<FeedMessage> list, String url) {
		for (FeedMessage message : list) {
			if (message.url == url)
				return message;
		}
		return null;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Authenticated
	public List<Folder> get() {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Feed> tmpList;
		RSSFeedParser parser;
		Feed tmpFeed;
		FeedMessage tmpMessage;
		for (Folder folder : user.folders) {
			tmpList = Lists.newArrayList();
			for (Feed feed : folder.feeds) {
				parser = new RSSFeedParser(feed.url);
				tmpFeed = parser.readFeed();
				for (FeedMessage message : tmpFeed.messages) {
					tmpMessage = findByUrl(feed.messages, message.url);
					if (tmpMessage != null)
						message.isRead = tmpMessage.isRead;
				}
				tmpList.add(tmpFeed);
			}
			folder.feeds = tmpList;
		}
		session.setAttribute("user", user);
		DAOHelper.userDAO.save(user);
		return user.folders;
	}
}
