package com.geektrust.backend.repositories;

import java.util.List;

public interface CRUDRepository<T,ID> {
    public T save(T entity);
    public List<T> findAll();
    boolean existsById(ID id);
    public void delete(T entity);
    public void deleteById(ID id);
    public long count();
}