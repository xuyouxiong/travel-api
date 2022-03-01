<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>后台管理系统</title>
    <#include "../common/header.ftl" >
    <script>
        $(function(){
            //审核
           $(".auditBtn1").click(function () {
               var id = $(this).data('id');
               var state = $(this).data('state');
               $.get('/order/audit',{id:id,state:state},function (data) {
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
            <#assign currentMenu="order"/>
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-10">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">订单管理</h1>
                </div>
            </div>

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>订单号</th>
                    <th>类型</th>
                    <th>姓名</th>
                    <th>联系方式</th>
                    <th>身份证</th>
                    <th>商品信息</th>
                    <th>价格</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <#list page.records as entity>
                    <tr>
                        <td>${entity_index+1}</td>
						<td>${entity.orderSn!}</td>
						<td>${entity.type!}</td>
						<td>${(entity.name)!}</td>
						<td>${(entity.phone)!}</td>
						<td>${entity.idCard!}</td>
						<td>${entity.getOrderInfo()}</td>
						<td>${entity.price!}</td>
                        <td>${entity.getStatusName()}</td>
                        <td>
                            <#if entity.status == 1>
                            <a class="btn btn-default btn-xs auditBtn1" href="JavaScript:;"
                               data-id="${entity.id}" data-state="2">
                            <span class="glyphicon glyphicon-tag"></span> 取消订单
                            </#if>
                            <#if entity.status == 2>
                            <a class="btn btn-default btn-danger deleteBtn" href="JavaScript:;"
                               data-id="${entity.id}" data-state="2">
                                <span class="glyphicon glyphicon-tag"></span> 删除订单
                            </a>
                             </#if>
                        </td>
                    </tr>
                </#list>
            </table>
            <#include "../common/page.ftl"/>
        </div>
    </div>
</div>


<!-- 模态框必须作为body的子标签 -->
<div class="modal fade" id="inputModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">查看文章内容</h4>
            </div>
            <div class="modal-body" >

            </div>
        </div>
    </div>
</div>


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
