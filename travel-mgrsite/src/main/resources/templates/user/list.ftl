<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>后台管理系统</title>
    <#include "../common/header.ftl" >
    <script>
        $(function(){
            //查看文章
           $(".lookBtn").click(function () {
               var id = $(this).data('id');
               //根据id查询游记内容
               $.get('/travel/getContentById',{id:id},function (data) {
                   console.log(data.data)
                   $("#inputModal .modal-body").html(data.data.content);
                   $("#inputModal").modal('show');
               })
           })

            $(".inputBtn").click(function () {
                console.log("sdasdas")
                //数据复原
                //攻略分类回显数据
                var data = $(this).data("info");
                console.log(data)
                if(data){
                    $("#editForm input[name='id']").val(data.id);
                    $("#editForm input[name='nickname']").val(data.nickname);
                    $("#editForm input[name='phone']").val(data.phone);

                    // $("#editForm input[name='seq']").val(data.seq);
                    // $("#editForm select[name='state']").val(data.state);
                    // $("#editForm select[name='destId']").val(data.destId);

                }
                //弹出模态框
                $("#editModal").modal("show");

            })

            //审核
           $(".auditBtn").click(function () {
               var id = $(this).data('id');
               var state = $(this).data('state');
               $.get('/user/audit',{id:id,state:state},function (data) {
                   console.log(data)
                   if(data.code == 200){
                       window.location.reload();
                   }else{
                       $.messager.alert("温馨提示", "操作失败");
                   }
               })
           })
        })
    </script>

</head>
<body>

<div class="container-fluid" style="margin-top: 20px">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-2">
            <#assign currentMenu="user"/>
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-10">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">用户管理</h1>
                </div>
            </div>

            <!--高级查询--->
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>昵称</th>
                    <th>手机号</th>
                    <th>密码</th>
                    <th>城市</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <#list page.records as entity>
                    <tr>
                        <td>${entity_index+1}</td>
						<td>${entity.nickname!}</td>
						<td>${(entity.phone)!}</td>
						<td>${(entity.password)!}</td>
						<td>${(entity.city)!}</td>
                        <td>${entity.stateDisplay!}</td>
                        <td>
                            <#if entity.state == -1>
                            <a class="btn btn-default btn-xs auditBtn" href="JavaScript:;"
                               data-id="${entity.id}" data-state="0">
                            <span class="glyphicon glyphicon-tag"></span> 解除禁用
                        </a>
                            |
                            </#if>
                            <#if entity.state == 0>
                                <a class="btn btn-danger btn-xs auditBtn" href="javascript:;"
                                   data-id="${entity.id}" data-state="-1" >
                                    <span class="glyphicon glyphicon-minus-sign"></span> 禁用
                                </a>
                            </#if>
                            |
                            <a class="btn btn-info btn-xs inputBtn" href="JavaScript:;" data-info='${entity.jsonString}'>
                                <span class="glyphicon glyphicon-th"></span> 查看</a>
                            |
                            <a href="javascript:;" class="btn btn-danger btn-xs deleteBtn"
                               data-url="/user/delete?id=${entity.id}">
                                <span class="glyphicon glyphicon-trash"></span> 删除
                            </a>
                        </td>
                    </tr>
                </#list>
            </table>
            <#include "../common/page.ftl"/>
        </div>
    </div>
</div>

<div class="modal fade" id="editModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span> </button>
                <h4 class="modal-title">用户的查看</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/user/saveOrUpdate" method="post" id="editForm">
                    <input type="hidden" value="" name="id">
                    <div class="form-group">
                        <label  class="col-sm-3 control-label">名称：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="nickname" name="nickname"  placeholder="昵称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-3 control-label">手机号：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="phone" name="phone"  placeholder="手机号">
                        </div>
                    </div>

<#--                    <div class="form-group">-->
<#--                        <label  class="col-sm-3 control-label">状态：</label>-->
<#--                        <div class="col-sm-6">-->
<#--                            <select class="form-control" id="state" name="state">-->
<#--                                <option value="0">普通</option>-->
<#--                                <option value="1">禁用</option>-->
<#--                            </select>-->
<#--                        </div>-->
<#--                    </div>-->

<#--                    <div class="form-group">-->
<#--                        <label  class="col-sm-3 control-label">排序：</label>-->
<#--                        <div class="col-sm-6">-->
<#--                            <input type="text" class="form-control" id="seq" name="seq" placeholder="请输入序号">-->
<#--                        </div>-->
<#--                    </div>-->
                </form>
            </div>
            <div class="modal-footer">
<#--                <button type="button" class="btn btn-primary submitBtn" >保存</button>-->
                <button type="button" class="btn btn-default" data-dismiss="modal" >取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 模态框必须作为body的子标签 -->


<!-- 大图显示 -->
<div id="ShowImage_Form" class="modal fade" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body" >
            </div>
        </div>
    </div>
</div>


<script>
   $("table img").click(function(){
       var src = $(this).attr("src");
       $("#ShowImage_Form .modal-body").html("<img src='"+src+"' class='carousel-inner img-responsive img-rounded' />");
       $("#ShowImage_Form").modal('show');
   })
</script>


<style>
    #inputModal img{
        width: 570px !important;
    }
</style>

</body>
</html>
