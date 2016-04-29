package com.api.rssaggregator.helpers;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public final class MorphiaHelper {
	private Morphia morphia;
	private Datastore datastore;

	/** Holder */
	private static class SingletonHolder {
		private final static MorphiaHelper instance = new MorphiaHelper();
	}

	private static MorphiaHelper getInstance() {
		return SingletonHolder.instance;
	}

	private MorphiaHelper() {
		MongoClient mongoClient = new MongoClient();
		this.morphia = new Morphia();
		String databaseName = "rss_feed_aggregator";
		this.datastore = morphia.createDatastore(mongoClient, databaseName);
	}

	public static Morphia getMorphia() {
		return getInstance().morphia;
	}

	public static Datastore getDatastore() {
		return getInstance().datastore;
	}

}