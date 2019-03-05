package com.jit.edu.servlet;

import com.jit.edu.model.Student;
import com.jit.edu.service.StudentService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 模拟restful风格处理url请求
 * @author:guxiang
 * @version:2019/3/3
 */
public class MyServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(StudentService.class);
    private StudentService service;

    /**
     * 初始化获取数据库连接，sql语句
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        service = new StudentService();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getRequestURI().contains("studentList")) {
                List<Student> list = service.getList();
                req.setAttribute("list", list);
                req.getRequestDispatcher("/WEB-INF/views/studentList.jsp").forward(req, resp);
                return;
            }
            if (req.getRequestURI().contains("getStudentByName")) {
                String name = req.getParameter("inputName");
                if (null != name && !"".equals(name.trim())) {
                    List<Student> list = service.getByName(name);
                    req.setAttribute("list", list);
                    req.getRequestDispatcher("/WEB-INF/views/studentList.jsp").forward(req, resp);
                    return;
                }
            }
            if (req.getRequestURI().contains("insertStudent")) {
                req.getRequestDispatcher("/WEB-INF/views/studentInsert.jsp").forward(req, resp);
                return;
            }
            if (req.getRequestURI().contains("deleteStudent")) {
                doDelete(req, resp);
                return;
            }
            if (req.getRequestURI().contains("updateStudent")) {
                String id = req.getParameter("id");
                Student student = service.getById(id);
                req.setAttribute("student", student);
                req.getRequestDispatcher("/WEB-INF/views/studentEdit.jsp").forward(req, resp);

            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getRequestURI().contains("updateStudent")) {
                doPut(req, resp);
                return;
            }
            if (req.getRequestURI().contains("insertStudent")) {
                Student student = new Student();
                student.setName(req.getParameter("name"));
                student.setAge(req.getParameter("age"));
                student.setSex(req.getParameter("sex"));
                service.insert(student);
                List<Student> list = service.getList();
                req.setAttribute("list", list);
                req.getRequestDispatcher("/WEB-INF/views/studentList.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = new Student();
        student.setId(req.getParameter("id"));
        student.setName(req.getParameter("name"));
        student.setAge(req.getParameter("age"));
        student.setSex(req.getParameter("sex"));
        service.updateById(student);
        List<Student> list = service.getList();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/WEB-INF/views/studentList.jsp").forward(req, resp);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            service.deleteById(id);
            List<Student> list = service.getList();
            req.setAttribute("list", list);
            req.getRequestDispatcher("/WEB-INF/views/studentList.jsp").forward(req, resp);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
