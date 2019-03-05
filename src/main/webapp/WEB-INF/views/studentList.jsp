<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>


<html>
<!-- 引入bootstrap -->
<link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>

<body>
<div class="container" id="content">
    <div class="row">
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 class="col-md-5">学生信息显示</h1>
                        <form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;" method="get" action="/getStudentByName">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="请输入学生姓名" name="inputName" id="inputName">
                                <span class="input-group-addon btn" ><button type="submit" id="select" disabled="disabled">查询</button></span>
                                <a href="/insertStudent"><span class="btn btn-danger">新增记录</span></a>
                            </div>
                        </form>



                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th nowrap="nowrap">序号</th>
                        <th nowrap="nowrap">姓名</th>
                        <th nowrap="nowrap">年龄</th>
                        <th nowrap="nowrap">性别</th>
                        <th nowrap="nowrap">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="student" items="${list}">

                         <tr>
                             <td>${student.id}</td>
                             <td>${student.name}</td>
                             <td>${student.age}</td>
                             <td>${student.sex}</td>

                             <td><a href="/updateStudent?id=${student.id}">
                                 <button class="btn btn-info" onclick="return confirmUpdate()">
                             <span class="glyphicon glyphicon-pencil" aria-hidden="true">
                             </span>
                                     修改
                                 </button></a>
                             <a href="/deleteStudent?id=${student.id}">
                                 <button class="btn btn-danger" onclick="return confirmDelete()" >
                             <span class="glyphicon glyphicon-trash" aria-hidden="true">
                             </span>
                                     删除
                                 </button></a></td>
                         </tr>
                     </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function confirmDelete() {
        return confirm("确定删除该记录吗");
    }

    function confirmUpdate() {
        return confirm("确定修改该记录吗");
    }
    $(function () {
        $("#inputName").change(function() {
            var a=$("#inputName").val().trim();
            if(null == a || a.length == 0){
                $("#select").attr('disabled','disabled');

            }else{
                $("#select").removeAttr("disabled");
            }

        });
    });


</script>
</html>
