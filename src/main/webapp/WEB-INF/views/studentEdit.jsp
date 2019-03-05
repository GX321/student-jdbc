<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
<body>
<div class="container" id="content">
<form class="form-horizontal col-lg-offset-4"
      action="/updateStudent?id=${student.id}" id="form" method="post">
    <h3>更新学生信息</h3>
    <div class="form-group">
        <label class="col-lg-2 control-label">姓名：</label>
        <div class="col-lg-4">
            <input type="text" class="form-control" name="name"  placeholder="请输入姓名"
                   value="${student.name}">

        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label">年龄：</label>
        <div class="col-lg-4">
            <input type="text" class="form-control" name="age"  placeholder="请输入年龄"
                   value="${student.age}">

        </div>
    </div>

    <div class="form-group">
        <label class="col-lg-2 control-label">性别：</label>
        <div class="col-lg-4">
            <select name="sex"  value="${student.sex}">
                <option value="0" <c:if test="${'1' eq student.sex}">selected</c:if> >男</option>
                <option value="1" <c:if test="${'2' eq student.sex}">selected</c:if> >女</option>
            </select>
        </div>
    </div>

    <br>
    <div class="form-group">
        <div class="col-lg-4 col-lg-offset-2">
            <input type="submit" value="提交"  class="btn btn-danger" id="button">
            &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
            <input type="reset" value="重置"name="reset" class="btn btn-danger">
        </div>
    </div>
</form>
</div>
</body>

</html>
