package com.api.rssaggregator.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.api.rssaggregator.entities.Feed;
import com.api.rssaggregator.helpers.RSSFeedParser;

@Path("tests")
public class TestController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Feed get() {
		RSSFeedParser parser = new RSSFeedParser(
				"http://www.vogella.com/article.rss");
		Feed feed = parser.readFeed();
		return feed;
	}

}
