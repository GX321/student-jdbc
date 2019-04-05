package com.jit.edu.service;

import com.jit.edu.dao.StudentDao;
import com.jit.edu.enumeration.SEX;
import com.jit.edu.model.Student;
import com.jit.edu.utils.ConnectionClose;
import com.jit.edu.utils.MyConfiguration;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * student增删改查实现类
 *
 * @author:guxiang
 * @version:2019/3/3
 */
public class StudentService implements StudentDao {
    private static final Logger logger = Logger.getLogger(StudentService.class);

    private static Connection conn;
    private static Map<String, String> sql;
    private PreparedStatement psmt;
    private ResultSet rs;
    private Student student;
    private List<Student> list;

    /**
     * 获取数据库连接，sql语句
     */
    public StudentService() {
        MyConfiguration configuration = new MyConfiguration();
        conn = configuration.getConnection("datasource.xml");
        sql = configuration.getSql("sql.xml");

    }


    /**
     * 查询所有学生信息
     *
     * @return
     */
    @Override
    public List<Student> getList() {
        try {
            psmt = conn.prepareStatement(sql.get("selectList"));
            rs = psmt.executeQuery();
            list = new ArrayList<Student>();
            //遍历结果集
            while (rs.next()) {
                student = new Student();
                student.setId(rs.getString(1));
                student.setName(rs.getString(2));
                student.setAge(rs.getString(3));
                student.setSex(rs.getString(4).equals("0") ?
                        SEX.BOY.getValue() : SEX.GIRL.getValue());
                list.add(student);

            }
        } catch (SQLException e) {
            logger.error("查询失败！" + e.getMessage());
        }
        return list;
    }

    /**
     * 根据id查询学生信息
     *
     * @param id
     * @return
     */
    @Override
    public Student getById(String id) {
        try {
            psmt = conn.prepareStatement(sql.get("selectOneById"));
            psmt.setInt(1, Integer.parseInt(id));
            rs = psmt.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getString(1));
                student.setName(rs.getString(2));
                student.setAge(rs.getString(3));
                student.setSex(rs.getString(4).equals("0") ?
                        SEX.BOY.getValue() : SEX.GIRL.getValue());
            }
        } catch (SQLException e) {
            logger.error("查询失败！" + e.getMessage());
        }
        return student;
    }

    /**
     * 根据姓名查询学生信息
     *
     * @param name
     * @return
     */
    @Override
    public List<Student> getByName(String name) {
        try {
            psmt = conn.prepareStatement(sql.get("selectOneByName"));
            psmt.setString(1, name);
            rs = psmt.executeQuery();
            list = new ArrayList<Student>();
            //遍历结果集
            while (rs.next()) {
                student = new Student();
                student.setId(rs.getString(1));
                student.setName(rs.getString(2));
                student.setAge(rs.getString(3));
                student.setSex(rs.getString(4).equals("0") ?
                        SEX.BOY.getValue() : SEX.GIRL.getValue());
                list.add(student);

            }
        } catch (SQLException e) {
            logger.error("查询失败！" + e.getMessage());
        }
        return list;
    }

    /**
     * 新增学生信息
     *
     * @param student
     */
    @Override
    public void insert(Student student) {
        try {
            psmt = conn.prepareStatement(sql.get("insertOne"));
            psmt.setString(1, student.getName());
            psmt.setString(2, student.getAge());
            psmt.setString(3, student.getSex());
            psmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("新增失败！" + e.getMessage());
        }

    }

    /**
     * 更新学生信息
     *
     * @param student
     */
    @Override
    public void updateById(Student student) {
        try {
            psmt = conn.prepareStatement(sql.get("updateOne"));
            psmt.setString(1, student.getName());
            psmt.setString(2, student.getAge());
            psmt.setString(3, student.getSex());
            psmt.setInt(4, Integer.parseInt(student.getId()));
            psmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("修改失败！" + e.getMessage());
        }
    }

    /**
     * 删除学生信息
     *
     * @param id
     */
    @Override
    public void deleteById(String id) {
        try {
            psmt = conn.prepareStatement(sql.get("deleteOne"));
            psmt.setInt(1, Integer.parseInt(id));
            psmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("删除失败！" + e.getMessage());
        }
    }
}
