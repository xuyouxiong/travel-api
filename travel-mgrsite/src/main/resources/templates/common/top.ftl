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
        <li><a href="/login.html">安全退出</a></li>
    </ul>
</div>

<script>
    $(function () {
        var user = $.cookie('user');
        var token = $.cookie('token');
        var userJson = JSON.parse(user)
        $(".navbar-text").text(userJson.name)
        console.log(user)
        console.log(token)
    })
</script>
