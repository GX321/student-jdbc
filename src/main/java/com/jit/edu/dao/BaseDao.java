package com.jit.edu.dao;

import java.util.List;

/**
 * @author:guxiang
 * @version:2019/3/3
 */
public interface BaseDao<T> {

    public List<T> getList();

    public T getById(String id);

    public void insert(T t);

    public void updateById(T t);

    public void deleteById(String id);
}
