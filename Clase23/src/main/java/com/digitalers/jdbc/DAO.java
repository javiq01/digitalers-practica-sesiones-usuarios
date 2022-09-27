package com.digitalers.jdbc;

import java.util.List;

import com.digitalers.entities.GenericEntity;

public interface DAO<E extends GenericEntity, ID> {
	E findById(ID id);

	boolean save(E e);

	boolean delete(E e);

	List<E> findAll();
}
