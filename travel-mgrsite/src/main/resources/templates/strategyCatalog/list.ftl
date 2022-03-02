<html lang="en">
<head>
    <title>内容推荐管理</title>
    <#include "../common/header.ftl"/>

    <link type="text/css" rel="stylesheet" href="/js/plugins/uploadifive/uploadifive.css" />
    <script type="text/javascript" src="/js/plugins/uploadifive/jquery.uploadifive.min.js"></script>

    <script src="/js/ckeditor/ckeditor.js"></script>
    <script>

        //表单提交验证
        $(function () {
            //保存
            $("#btn_submit").click(function () {
                var user = $.cookie('user');
                var token = $.cookie('token');
                if (!token) {
                    alert("未登录")
                    location.href = '/login.html'
                }
                user = JSON.parse(user)
                let id = $('input:radio:checked').val();
                //异步提交时， 富文本框可能出问题
                $.ajax({
                    type: 'POST',
                    url: '/strategyCatalog/saveOrUpdate',
                    dataType: 'json',
                    data: {
                        id
                    },
                    beforeSend:function(xhr){
                        xhr.setRequestHeader("admin_token",token);
                    },
                    success:function (data) {
                        console.log(data);
                        if(data.code == 200){
                            window.location.reload()
                        }else{
                            $.messager.alert("温馨提示", data.msg);
                        }
                    },
                    error:function () {
                        popup("网络不通，请联系管理员~");
                    }
                })
            })

        })


    </script>
</head>
<body>
<!--设置菜单回显-->
<#assign currentMenu = 'strategyCatalog'>
<div class="container-fluid " style="margin-top: 20px">
    <#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-2">
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-10">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">内容推荐管理</h1>
                </div>
            </div>
            <div class="row col-sm-10">
                <form class="form-horizontal" action="" method="post" id="editForm">

                    <div class="form-group">
                        <div>
                            <label for="name" class="col-sm-2 control-label">Tag：</label>
                            <div>
                                <#list themes as theme>
                                    <input type="radio" name="optionsRadios" id="optionsRadios1" value="${theme.id}" <#if theme.id == tag.themeId>checked</#if>>
                                    ${theme.name}
                                </#list>
                            </div>
                        </div>
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
