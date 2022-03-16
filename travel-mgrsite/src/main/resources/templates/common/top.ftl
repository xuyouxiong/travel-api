<style>
     .lg2 {
        position: absolute;
        top: 26px;
        left: 150px;
    }
</style>
<div class="navbar cm-navbar">
    <span class="pageTitle">&nbsp;</span>
    <ul class="nav navbar-nav navbar-right cm-navbar-nav ">
        <li>
           <p class="navbar-text text-info"></p>
        </li>
        <li><a href="javascript:;" id="logout-admin">安全退出</a></li>
    </ul>
</div>

<script>
    $(function () {
        var user = localStorage.getItem("user")
        var token = localStorage.getItem("token")
        if (token != "null" && token) {
            var userJson = JSON.parse(user)
            $(".navbar-text").text(userJson.name)
        } else {
            alert("未登录1")
            location.href = "/login.html"
        }
    })

    $("#logout-admin").click(function(res) {
        localStorage.clear()
        location.reload()
    })
</script>
