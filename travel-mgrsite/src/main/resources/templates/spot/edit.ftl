<html lang="en">
<head>
    <title>门票</title>
    <#include "../common/header.ftl"/>

    <link type="text/css" rel="stylesheet" href="/js/plugins/uploadifive/uploadifive.css" />
    <script type="text/javascript" src="/js/plugins/uploadifive/jquery.uploadifive.min.js"></script>

    <script src="/js/ckeditor/ckeditor.js"></script>
    <script>

        //表单提交验证
        $(function () {
            var ck = CKEDITOR.replace( 'content',{
                filebrowserBrowseUrl: '/strategy/info',
                filebrowserUploadUrl: '/uploadImg_ck'
            });
            //图片上传
            $('.imgBtn').uploadifive({
                'auto' : true,  //自动发起图片上传请求
                'uploadScript' : '/common/uploadFile',   //处理上传文件的请求路径
                buttonClass:"btn-link",
                'fileObjName' : 'file',   //上传文件参数名
                'buttonText' : '浏览...',
                'fileType' : 'image',
                'multi' : false,
                'fileSizeLimit'   : 5242880,
                'removeCompleted' : true, //取消上传完成提示
                'uploadLimit' : 1,
                //'queueSizeLimit'  : 10,
                'overrideEvents': ['onDialogClose', 'onError'],    //onDialogClose 取消自带的错误提示
                'onUploadComplete' : function(file, data) {
                    $("#imgUrl").attr("src" ,(JSON.parse(data)).msg);  //data约定是json格式 图片地址
                    $("#coverUrl").val((JSON.parse(data)).msg);

                },
                onFallback : function() {
                    $.messager.alert("温馨提示","该浏览器无法使用!");
                }
            });

            //保存
            $("#btn_submit").click(function () {
                var user = $.cookie('user');
                var token = $.cookie('token');
                if (!token) {
                    alert("未登录")
                    location.href = '/login.html'
                }
                user = JSON.parse(user)

                var name = $("#name").val()
                var image = $("#coverUrl").val()
                var address = $("#address").val()
                var price = $("#price").val()
                var summary = $("#summary").val()
                console.log(summary)
                var number = $("#number").val()
                var id = $("#id").val()
                console.log(typeof ck.getData())
                $.ajax({
                    type: 'POST',
                    url: '/spot/saveOrUpdate',
                    dataType: 'json',
                    data: {
                        id,
                        name,
                        image,
                        address,
                        price,
                        summary,
                        number,
                        uid: user.id,
                        introduction: ck.getData()
                    },
                    beforeSend:function(xhr){
                        xhr.setRequestHeader("admin_token",token);
                    },
                    success:function (data) {
                        console.log(data);
                        if(data.code == 200){
                            window.location.href = "/spot/list?uid=" + user.id;
                        }else{
                            $.messager.alert("温馨提示", data.msg);
                        }
                    },
                    error:function () {
                        popup("网络不通，请联系管理员~");
                    }
                })
                //异步提交时， 富文本框可能出问题

            })

        })

    </script>
</head>
<body>
<!--设置菜单回显-->
<#assign currentMenu = 'spot'>
<div class="container-fluid " style="margin-top: 20px">
    <#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-2">
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-10">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">门票新增</h1>
                </div>
            </div>
            <div class="row col-sm-10">
                <form class="form-horizontal" action="/hotel/saveOrUpdate" method="post" id="editForm">
                    <input type="hidden"  class="form-control" id="id"  name="id" value="${spot.id!}" >
                    <input type="hidden"  class="form-control" id="uid"  name="uid" value="${uid}" >

                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">景点名称：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="name" name="name" value="${spot.name}" placeholder="请输入景点名称">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">景点封面：</label>
                        <div class="col-sm-8">
                            <input type="hidden"  class="form-control" id="coverUrl"  name="coverUrl" value="${spot.image!}" >
                            <img src="${spot.image!}" width="100px" id="imgUrl" >
                            <button type="button" class="imgBtn">浏览</button>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">景点地址：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="address" name="address" value='${spot.address!}' placeholder="请输入地址">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">门票价格：</label>
                        <div class="col-sm-8">
                            <input type="number" class="form-control" id="price" name="price" value='${spot.price!}' placeholder="请输入最低价格">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">门票数量：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="number" name="number" value='${spot.number!}' placeholder="请输入数量">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">摘要：</label>
                        <div class="col-sm-8">
                            <textarea  class="form-control" name="summary" id="summary">${spot.summary!}</textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <textarea id="content" name="content.content" class="ckeditor">${spot.introduction!}</textarea>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-8">
                            <button id="btn_submit" class="btn btn-primary" type="button">保存</button>
                            <button type="reset" class="btn btn-danger">重置</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
