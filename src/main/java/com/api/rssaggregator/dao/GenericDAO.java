package com.api.rssaggregator.dao;

import org.mongodb.morphia.Key;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.api.rssaggregator.helpers.MorphiaHelper;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

public abstract class GenericDAO<T, K> extends BasicDAO<T, K>{

	protected GenericDAO() {
		super(MorphiaHelper.getDatastore());
	}

	@Override
	public WriteResult delete(T entity) {
		// TODO Auto-generated method stub
		return super.delete(entity);
	}

	@Override
	public WriteResult delete(T entity, WriteConcern wc) {
		// TODO Auto-generated method stub
		return super.delete(entity, wc);
	}

	@Override
	public WriteResult deleteById(K id) {
		// TODO Auto-generated method stub
		return super.deleteById(id);
	}

	@Override
	public WriteResult deleteByQuery(Query<T> query) {
		// TODO Auto-generated method stub
		return super.deleteByQuery(query);
	}

	@Override
	public QueryResults<T> find() {
		// TODO Auto-generated method stub
		return super.find();
	}

	@Override
	public QueryResults<T> find(Query<T> query) {
		return super.find(query);
	}

	@Override
	public Key<T> save(T entity) {
		// TODO Auto-generated method stub
		return super.save(entity);
	}

	@Override
	public Key<T> save(T entity, WriteConcern wc) {
		// TODO Auto-generated method stub
		return super.save(entity, wc);
	}

	@Override
	public UpdateResults update(Query<T> query, UpdateOperations<T> ops) {
		// TODO Auto-generated method stub
		return super.update(query, ops);
	}

	@Override
	public UpdateResults updateFirst(Query<T> query, UpdateOperations<T> ops) {
		// TODO Auto-generated method stub
		return super.updateFirst(query, ops);
	}
	
}
