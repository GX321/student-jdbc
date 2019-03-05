package com.jit.edu;

import com.jit.edu.model.Student;
import com.jit.edu.service.StudentService;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;




/**
 * @author:guxiang
 * @version:2019/3/3
 */
public class TestStudent {
    private static final Logger logger = Logger.getLogger(TestStudent.class);
    private StudentService service;

    @Before
    public void init() {
        service = new StudentService();
    }

    /**
     * 查询所有
     */
    @Test
    public void getList() {
        service.getList().forEach(xx -> {
            logger.info(xx);
        });
    }

    /**
     * 通过姓名查询
     */
    @Test
    public void getByName() {
        logger.info(service.getByName("小红"));
    }

    /**
     * 新增学生记录
     */
    @Test
    public void insert() {
        Student student = new Student("7", "小贝", "11", "0");
        service.insert(student);
    }

    /**
     * 修改记录
     */
    @Test
    public void update() {
        Student student = new Student("7", "小贝", "15", "0");
        service.updateById(student);
    }

    /**
     * 删除记录
     */
    @Test
    public void delete() {
        service.deleteById("7");
    }
}

