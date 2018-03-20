package com.pchase95.project1.dao;

import java.util.List;

import com.pchase95.project1.pojos.TrmsObject;

public interface DAO <T extends TrmsObject> {
	public List<T> getAll();

	public T getById(long id);

	public boolean add(T newItem);

	public boolean update(long id, T updatedItem);

	public boolean remove(long id);
}
