<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../../common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>人员管理</title>
    <link href="${ctx}/static/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="${ctx}/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/static/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/style.min.css?v=4.0.0" rel="stylesheet">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated">
    <div class="row">
        <div class="col-sm-12">
            <form action="${ctx}/person.do" class="form-inline" method="post" role="form">
                <%--        原数据 ${requestScope.currentSize}   搜索后默认跳到第一页    --%>
                <input type="hidden" name="currentSize" value="1">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>人员管理</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <!--表格数据搜索 start-->
                    <div class="ibox-content" id="ibox-content-search">
                        <div class="row">
                            <div class="form-group">
                                <label class="font-noraml">姓名：</label>
                                <input class="form-control" name="name" placeholder="请输入姓名" type="text"
                                       value="${requestScope.params.name}">
                            </div>
                            <div class="form-group">
                                <label class="font-noraml">身份证号：</label>
                                <input class="form-control" name="code" placeholder="请输入身份证号" type="text"
                                       value="${requestScope.params.code}">
                            </div>
                            <div class="form-group">
                                <label class="font-noraml">状态：</label>
                                <label>
                                    <select class="form-control" name="state">
                                        <option value="0">请选择</option>
                                        <option value="1" ${requestScope.params.state==1?"selected":''}>离休</option>
                                        <option value="2" ${requestScope.params.state==2?"selected":''}>退休</option>
                                    </select>
                                </label>
                            </div>
                            <div class="form-group">
                                <label class="font-noraml">职级：</label>
                                <label>
                                    <select class="form-control" name="grade">
                                        <option value="0">请选择</option>
                                        <c:forEach items="${applicationScope.grade}" var="map">
                                            <option value="${map.key}" ${map.key==requestScope.params.grade?"selected":''} >${map.value}</option>
                                        </c:forEach>
                                    </select>
                                </label>
                            </div>
                            <div class="form-group">
                                <label class="font-noraml">项目：</label>
                                <div class="checkbox checkbox-inline">
                                    <input id="checkbox1" type="checkbox" name="estate"
                                           value="1" ${requestScope.params.estate==1?'checked':''}><label
                                        for="checkbox1">供暖</label>
                                </div>
                                <div class="checkbox checkbox-inline">
                                    <input id="checkbox2" type="checkbox" name="heating"
                                           value="1" ${requestScope.params.heating==1?'checked':''}><label
                                        for="checkbox2">物业</label>
                                </div>
                            </div>
                            <button class="btn btn-success" type="submit">搜索</button>
                            <button class="btn btn-warning" type="reset">重置</button>
                            <button class="btn btn-primary" id="add" type="button">添加</button>
                            <button class="btn btn-danger" type="button" id="batch">批删</button>
                        </div>
                    </div>
                    <!--表格数据搜索 end-->
                </div>
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>人员列表</h5>
                    </div>
                    <!--表格数据展示 start-->
                    <div class="ibox-content">
                        <table class="table table-bordered table-striped table-hover">
                            <thead>
                            <tr>
                                <th><input type="checkbox" id="checkAll">
                                    <button class="btn btn-danger" type="button" id="inverse">反选</button>
                                </th>
                                <th>#</th>
                                <th>姓名</th>
                                <th>证件</th>
                                <th>单位</th>
                                <th>职级</th>
                                <th>状态</th>
                                <th>补贴项</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.list}" var="entity" varStatus="n">
                                <tr>
                                    <td><input type="checkbox" name="ids" value="${entity.id}"></td>
                                    <td>${n.count}</td>
                                    <td>${entity.name}</td>
                                    <td>${entity.code}</td>
                                    <td>${entity.unit}</td>
                                    <td>
                                            <%--       监听器 ServletContext应用作用域  应用作用域.key[map中的key](Map对象)        --%>
                                            ${applicationScope.grade[entity.grade]}

                                    </td>
                                    <td>${entity.state==1?"离休":"退休"}</td>
                                    <td>
                                        <c:if test="${entity.estate==1}">
                                            <span class="badge">供暖</span>
                                        </c:if>
                                        <c:if test="${entity.heating==1}">
                                            <span class="badge">物业</span>
                                        </c:if>
                                    </td>
                                    <td>
                                        <a action="update" class="btn btn-info btn-rounded btn-sm"
                                           href="${ctx}/person.do?id=${entity.id}&tag=load">编辑</a>
                                        <a action="remove" class="btn btn-danger btn-rounded btn-sm"
                                           href="${ctx}/person.do?id=${entity.id}&tag=remove">删除</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!--表格数据展示 end-->
                    <!--表格数据分页 start-->
                    <div class="ibox-content">
                        <div class="btn-group">
                            <c:if test="${requestScope.currentSize!=1}">
                                <button class="btn btn-white" type="button">首页</button>
                                <button class="btn btn-white" type="button">上页</button>
                            </c:if>
                            <c:if test="${requestScope.currentSize!=requestScope.maxSize&&requestScope.totalSize>0}">
                                <button class="btn btn-white" type="button">下页</button>
                                <button class="btn btn-white" type="button">末页</button>
                            </c:if>
                            <button class="btn btn-danger" type="button"
                                    disabled>${requestScope.currentSize}/${requestScope.maxSize}/${requestScope.totalSize}</button>
                        </div>
                        <select name="pageSize" class="form-control">

                            <option value="7">每页7条</option>
                            <option value="8" ${requestScope.pageSize==8?"selected":""}>每页8条</option>
                            <option value="9" ${requestScope.pageSize==9?"selected":""}>每页9条</option>
                            <option value="10" ${requestScope.pageSize==10?"selected":""}>每页10条</option>
                            <option value="20" ${requestScope.pageSize==20?"selected":""}>每页20条</option>
                            <option value="30" ${requestScope.pageSize==30?"selected":""}>每页30条</option>
                            <option value="40" ${requestScope.pageSize==40?"selected":""}>每页40条</option>
                            <option value="50" ${requestScope.pageSize==50?"selected":""}>每页50条</option>
                        </select>
                        <input class="form-control" id="goPage" type="number" placeholder="请输入跳转页码">
                    </div>
                    <!--表格数据分页 end-->
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${ctx}/static/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx}/static/js/bootstrap.min.js?v=3.3.5"></script>
<script src="${ctx}/static/js/content.min.js?v=1.0.0"></script>
<script src="${ctx}/static/js/sweetalert.min.js"></script>
<script>
    $(document).ready(function () {

        let currentSize = '${requestScope.currentSize}';
        let maxSize = '${requestScope.maxSize}';

        $(".btn-group>button").on("click", function () {
            let text = $(this).text();
            // swal("刷新提示");
            console.log(text);
            if (text === "首页") {
                currentSize = 1;
            } else if (text === "上页") {
                currentSize--;
            } else if (text === "下页") {
                currentSize++;
            } else if (text === "末页") {
                currentSize = maxSize;
            }
            $('[name=currentSize]').val(currentSize);

            $('form:first').submit();
        });
        $('[name=pageSize]').on('change', function () {
            console.log($(this).val());
            $('[name=currentSize]').val(1);
            $('form:first').submit();
        });


        $(window).keydown(function (event) {
            if (event.keyCode === 13) {
                let current;
                let goPage = $('#goPage').val();
                if (goPage < 1) {
                    current = 1;
                } else if (goPage > parseInt(maxSize)) {
                    current = maxSize;
                } else {
                    current = goPage;
                }
                $('[name=currentSize]').val(current);
                $('form:first').submit();
            }
        });

        $('#checkAll').click(function () {
            $('[name=ids]').prop('checked', this.checked);
        });
        $('[name=ids]').change(function () {
            $.checkes();
        });

        $('#inverse').click(function () {
            let total = $('[name=ids]');
            let checkedS = $('[name=ids]:checked');
            $(total).not(checkedS).prop('checked', true);
            $(checkedS).prop('checked', false);
            $.checkes();
        })

        $.extend({
            checkes: function () {
                console.log("ids_change");
                let total = $('[name=ids]').length;
                let checkedS = $('[name=ids]:checked').length;
                $('#checkedAll').prop('checked', total === checkedS);
            }
        })

        $('#batch').click(function () {
            let total = $('[name=ids]:checked').length;
            if (total === 0) {
                swal("批删至少选择一条记录！");
                return false;
            }
            swal({
                title: "你确定要删除吗?",
                text: "一旦删除，您将无法恢复这条记录！",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            })
                .then((willDelete) => {
                    if (willDelete) {
                        $('form:first').attr('action', '${ctx}/person.do?tag=batch').submit();
                        swal("噗，文件已成功删除", {
                            icon: "success",
                        });
                    } else {
                        swal("您的数据还在，安全的!");
                    }
                });
        });
        //绑定页面添加按钮功能
        $('#add').click(function () {
            window.location.href = '${ctx}/view/person/add.jsp';
        });
    });
</script>
</body>
</html>