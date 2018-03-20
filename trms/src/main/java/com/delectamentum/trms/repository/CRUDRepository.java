package com.delectamentum.trms.repository;

import java.util.List;

public interface CRUDRepository<T> {
	public List<T> getAll();
	public T getById(int id);
	public T save(T t);
	public T update(T t);
	public T delete(T t);
}
