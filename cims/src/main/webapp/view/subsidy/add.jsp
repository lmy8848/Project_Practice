<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../../common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>补贴添加</title>
    <link href="favicon.ico" rel="shortcut icon">
    <link href="${ctx}/static/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="${ctx}/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/static/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/style.min.css?v=4.0.0" rel="stylesheet">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>补贴添加</h5>
                </div>
                <div class="ibox-content">
                    <form action="" class="form-horizontal m-t" id="subsidyForm" method="post">
                        <input type="hidden" name="type" value="${requestScope.subsidy.type}">
                        <input type="hidden" name="tag" value="add">
                        <input type="hidden" name="personId">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">姓名：</label>
                            <div class="col-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                    <input class="form-control" id="personId" readonly type="text" value="">
                                    <span>点击输入框选择补贴人员</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">身份证号：</label>
                            <div class="col-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-credit-card"></i></span>
                                    <input class="form-control" id="personCode" readonly type="text" value="">
                                    <span>根据选择的人员自动填充身份证号码</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">补贴金额：</label>
                            <div class="col-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-cny"></i></span>
                                    <input class="form-control" name="" type="number" value="">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">更改原因：</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" name=""></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-3">
                                <button class="btn btn-primary" type="submit">提交</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div aria-hidden="true" class="modal fade" data-backdrop="static" id="modal" role="dialog" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button aria-hidden="true" class="close" data-dismiss="modal" type="button">×</button>
                <h4 class="modal-title">标题</h4>
            </div>
            <div class="modal-body">
                内容
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" id="ok">确认</button>
                <button class="btn btn-danger" data-dismiss="modal" type="button">关闭</button>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/static/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx}/static/js/bootstrap.min.js?v=3.3.5"></script>
<script src="${ctx}/static/js/content.min.js?v=1.0.0"></script>
<script src="${ctx}/static/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/static/js/sweetalert.min.js"></script>
<script>
    $(document).ready(function () {
        $('#personId').click(function () {
            // 打开模态窗
            $('#modal').modal('show');

            $.get('${ctx}/subsidy.do', {
                tag: 'subsidy',
                type: '${requestScope.type}'
            }, function (result) {
                console.log(result);
                let html = '<table class="table table-bordered table-striped table-hover">';
                html += '<thead>'
                html += '<tr>'
                html += '<th>#</th>'
                html += '<th>序号</th>'
                html += '<th>姓名</th>'
                html += '<th>证件</th>'
                html += '<th>单位</th>'
                html += '</tr>'
                html += '</thead>'
                html += '<tbody>'
                $.each(result,function (i,o){
                html += '<tr>'
                html += '<td><input type="radio" name="subsidyPersonId" value="'+o.id+'"/td>'
                html += '<td>'+(i+1)+'</td>'
                html += '<td>'+o.name+'</td>'
                html += '<td>'+o.code+'</td>'
                html += '<td>'+o.unit+'</td>'
                html += '</tr>'
                })
                html += '</tbody>'
                html += '</table>'

                $('.modal-body').html(html);


            })
            // 关闭模态窗
            // $('#modal').modal('hide');
        });


        $(document).delegate('#ok','click',function (){
            let total = $('[name=subsidyPersonId]:checked');
            if (total.length===0){
                swal('请选择人员');
                return false;
            }
            let person = $(total).first();
            let id = $(person).val();
            let name = $(person).parent().nextAll().eq(1).text();
            let code = $(person).parent().nextAll().eq(2).text();

            $('[name=personId]').val(id);
            $('#personId').val(name);
            $('#personCode').val(code);

            $('#modal').modal('hide');
        });
    });
</script>
</body>
</html>