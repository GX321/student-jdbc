package com.jit.edu.dao;

import com.jit.edu.model.Student;

import java.util.List;

/**
 * @author:guxiang
 * @version:2019/3/3
 */
public interface StudentDao extends BaseDao<Student>{
    public List<Student> getByName(String name);

}
