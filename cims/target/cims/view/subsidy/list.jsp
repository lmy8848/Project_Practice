<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../../common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>补贴展示</title>
    <link href="${ctx}/static/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="${ctx}/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/static/css/style.min.css?v=4.0.0" rel="stylesheet">
    <link href="${ctx}/static/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>补贴列表</h5>
                </div>
                <div class="ibox-content">
                    <form action="${ctx}/subsidy.do?type=${requestScope.type}" class="form-inline" method="post"
                          role="form">
                        <input type="hidden" name="currentSize" value="1">
                        <div class="form-group">
                            <label class="font-noraml">人员名称</label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input class="form-control" name="name" placeholder="请输入用户名" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="font-noraml">身份证号</label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-credit-card"></i></span>
                                <input class="form-control" name="code" placeholder="请输入身份证号" type="text">
                            </div>
                        </div>
                        <div class="form-group" id="data">
                            <label class="font-noraml">补贴月份</label>
                            <div class="input-group date">
                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                <input class="form-control" name="month" type="text" value="">
                            </div>
                        </div>
                        <button class="btn btn-success" type="submit">查询</button>
                        <button class="btn btn-primary" id="save" type="button">添加</button>
                        <button class="btn btn-danger" type="button">批删</button>
                    </form>
                </div>
                <div class="ibox-content">
                    <table class="table table-bordered table-striped table-hover">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th><input type="checkbox"></th>
                            <th>月份</th>
                            <th>单位</th>
                            <th>姓名</th>
                            <th>身份证号</th>
                            <th>职级</th>
                            <th>金额</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.list}" var="subsidy" varStatus="n">

                            <tr>
                                <td>${n.count}</td>
                                <td><input type="checkbox"></td>
                                <td><f:formatDate value="${subsidy.month}" pattern="yyyy年MM月"/></td>
                                <td>${subsidy.person.unit}</td>
                                <td>${subsidy.person.name}</td>
                                <td>${subsidy.person.code}</td>
                                <td>${applicationScope.grade[subsidy.person.grade]}</td>
                                <td>${subsidy.money}</td>
                                <td>
                                    <a class="btn btn-info btn-rounded btn-sm" href="">编辑</a>
                                    <a class="btn btn-danger btn-rounded btn-sm" href="">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="ibox-content">
                    <div class="btn-group">

                        <c:if test="${requestScope.currentSize!=1}">
                            <button class="btn btn-white" type="button">首页</button>
                            <button class="btn btn-white" type="button">上一页</button>
                        </c:if>
                        <c:if test="${requestScope.currentSize!=requestScope.maxSize&&requestScope.totalSize>0}">
                        <button class="btn btn-white" type="button">下一页</button>
                        <button class="btn btn-white" type="button">末页</button>
                        </c:if>
                        <button class="btn btn-danger" type="button"
                                disabled>${requestScope.currentSize}/${requestScope.maxSize}/${requestScope.totalSize}</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/static/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx}/static/js/bootstrap.min.js?v=3.3.5"></script>
<script src="${ctx}/static/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script type="application/javascript">
    $(document).ready(function () {
        $('#save').click(function () {
            window.location.href = './add.html';
        });

        let currentSize = '${requestScope.currentSize}';
        let maxSize = '${requestScope.maxSize}'
        $('.btn-group>button').on("click", function () {
            let text = $(this).text();
            if ('首页' === text) {
                currentSize = 1;
            } else if ('上一页' === text) {
                currentSize--;
            } else if ('下一页' === text) {
                currentSize++;
            } else {
                currentSize = maxSize;
            }
            $('[name=currentSize]').val(currentSize);
            $('form:first').submit();

        });
        $("#data .input-group.date").datepicker(
            {
                minViewMode: 1,
                keyboardNavigation: !1,
                forceParse: !1,
                autoclose: !0,
                todayHighlight: !0,
                language: 'zh-CN',
                format: 'yyyy-mm'
            });
    });
</script>
</body>
</html>