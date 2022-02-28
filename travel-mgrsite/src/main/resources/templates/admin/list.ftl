<html lang="en">
<head>
    <title>管理员管理</title>
    <#include "../common/header.ftl">
    <script type="text/javascript">
        $(function () {
            var token = $.cookie('token')
            $.ajax({
                type: 'GET',
                url: '/travel/getContentById',
                dataType: 'json',
                data: {
                    'id': 1
                },
                beforeSend:function(xhr){
                    xhr.setRequestHeader("admin_token",token);
                },
                success:function (data) {

                },
                error:function () {
                    popup("网络不通，请联系管理员~");
                }
            })
            //编辑/添加
            $(".inputBtn").click(function () {
                //弹出模态框
                var id = $(this).data('index')
                var status = $(this).data('status')

                $.ajax({
                    type: 'GET',
                    url: '/admin/check',
                    dataType: 'json',
                    data: {
                        'id': id,
                        'status': status
                    },
                    beforeSend:function(xhr){
                        xhr.setRequestHeader("admin_token",token);
                    },
                    success:function (data) {
                        console.log(data)
                        if(data.code == 200) {
                            location.reload()
                        } else {
                            $.messager.alert("温馨提示", data.msg)
                        }
                    },
                    error:function () {
                        popup("网络不通，请联系管理员~");
                    }
                })
            })

            $(".submitBtn").click(function () {
                //模态框表单提交
                $("#editForm").ajaxSubmit(function (data) {
                    if (data.success) {
                        window.location.reload();
                    } else {
                        $.messager.alert("温馨提示", data.msg)
                    }
                })
            })

            //查询
            $(".clickBtn").click(function () {
                var parentId = $(this).data("parentid");
                $("#parentId").val(parentId);
                $("#currentPage").val(1);
                $("#searchForm").submit();
            })

            //修改热门
            $(".hotBtn").click(function () {
                var hot = $(this).data("hot");
                var id = $(this).data("id");
                $.get("/destination/changeHotValue", {hot: hot, id: id}, function (data) {
                    if (data.success) {
                        window.location.reload();
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                })
            })

            $(".downloadBtn").click(function () {
                var url = $(this).data("url");
                location.href = url

                // $.get(url, function (data) {
                //     console.log(data)
                //     if (data.code != 200) {
                //         $.messager.alert("温馨提示", data.msg);
                //     } else {
                //         location.href = url
                //     }
                // })
            })
        })
    </script>
</head>
<body>
<!--左侧菜单回显变量设置-->
<#assign currentMenu="admin">

<div class="container-fluid " style="margin-top: 20px">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-2">
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-10">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">商家管理</h1>
                </div>
            </div>
            <#setting number_format="#">
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/destination/list" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <input type="hidden" name="parentId" id="parentId" value="${qo.parentId!}">
                <h4>
                    当前位置: <a href="javascript:;" data-parentid="" class="clickBtn">用户管理</a>
                </h4>

            </form>

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>用户名</th>
                    <th>密码</th>
                    <th>法人</th>
                    <th>手机号码</th>
                    <th>证书</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <#list page.records as entity>
                    <tr>
                        <td>${entity_index+1}</td>
                        <td><a href="/destination/list?parentId=${entity.id}">${entity.name!}</a></td>
                        <td>${entity.pass!}</td>
                        <td>${(entity.faren)!}</td>
                        <td>${(entity.phone)!}</td>
                        <td>${(entity.cert)!}</td>
                        <td>${(entity.status)!}</td>

                        <td>
                            <a class="btn btn-info btn-xs inputBtn" href="javascript:;"
                               data-index='${entity.id}' data-status="1">
                                <span class="glyphicon glyphicon-edit"></span> 审核通过
                            </a>
                            <a class="btn btn-info btn-xs downloadBtn" href="javascript:;"
                               data-url="/admin/downloadCert?id=${entity.id}"
                            >
                                <span class="glyphicon glyphicon-download"></span> 下载证书
                            </a>


                            <a href="javascript:;" class="btn btn-danger btn-xs inputBtn"
                               data-index='${entity.id}' data-status="-1">
                                <span class="glyphicon glyphicon-trash"></span> 审核不通过
                            </a>
                        </td>
                    </tr>
                </#list>
            </table>
            <#--分页-->
            <#include "../common/page.ftl"/>
        </div>
    </div>
</div>

<#--目的地编辑模态框-->
<div class="modal fade" id="editModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">目的地简介</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/destination/updateInfo" method="post" id="editForm">
                    <input type="hidden" value="" name="id">

                    <div class="form-group">
                        <div class="col-sm-12">
                            <textarea rows="10" cols="60" name="info" style="border: none;resize: none"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary submitBtn">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
