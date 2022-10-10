<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../../common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>人员添加</title>
    <link href="${ctx}/static/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="${ctx}/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/static/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/style.min.css?v=4.0.0" rel="stylesheet">
</head>
<body>
<div class="ibox">
    <div class="ibox-title">
        <h5>人员添加</h5>
    </div>
    <div class="ibox-content">
        <form action="${ctx}/person.do" class="form-horizontal m-t" id="personForm" method="post">
            <input type="hidden" name="tag" value="add">
            <div class="form-group">
                <label class="col-sm-2 control-label">姓名：</label>
                <div class="col-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                        <input class="form-control" name="name" placeholder="请输入人员名称" type="text" value="">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">证件：</label>
                <div class="col-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-credit-card"></i></span>
                        <input class="form-control" name="code" placeholder="请输入身份证号" type="text" value="">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">单位：</label>
                <div class="col-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-credit-card"></i></span>
                        <input class="form-control" name="unit" placeholder="请输入人员单位" type="text" value="">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">状态：</label>
                <div class="col-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                        <select class="form-control" name="state">
                            <option value="0">请选择</option>
                            <option value="1">离休</option>
                            <option value="2">退休</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">职级：</label>
                <div class="col-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-signal"></i></span>
                        <select class="form-control" name="grade">
                            <option value="0">请选择</option>
                            <c:forEach items="${applicationScope.grade}" var="map" >
                            <option value="${map.key}">${map.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">补贴项目：</label>
                <div class="col-sm-10">
                    <div class="input-group">
                        <input id="checkbox1" name="subsidy" value="1" type="checkbox"><label for="checkbox1">供暖</label>
                        <input id="checkbox2" name="subsidy" value="2" type="checkbox"><label for="checkbox2">物业</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">更改原因：</label>
                <div class="col-sm-10">
                    <textarea class="form-control" name="reason" placeholder="请输入更改原因"></textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-4 col-sm-offset-3">
                    <button class="btn btn-primary" type="submit">提交</button>
                    <button class="btn btn-danger" type="reset">重置</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="${ctx}/static/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx}/static/js/bootstrap.min.js?v=3.3.5"></script>
<script src="${ctx}/static/js/content.min.js?v=1.0.0"></script>
<script src="${ctx}/static/js/plugins/validate/jquery.validate.min.js"></script>
<script>
    $(document).ready(function () {
        $('#personForm').validate({
            rules:{
                name:{
                    required: true
                }
                
            },
            message:{
                name:{
                    required:'人员姓名为必填项'
                }
            }
        });
    });
</script>
</body>
</html>