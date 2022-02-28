<ul id="menu" class="list-group">
    <li class="list-group-item is_admin">
        <a href="#" data-toggle="collapse" data-target="#strategyMgr">
            <span>管理员管理</span>
        </a>
        <ul class="in" id="strategyMgr">
            <li class="admin"><a href="/admin/list">商家管理</a></li>
        </ul>
    </li>

    <li class="list-group-item is_admin">
        <a href="#" data-toggle="collapse" data-target="#strategyMgr">
            <span>用户管理</span>
        </a>
        <ul class="in" id="strategyMgr">
            <li class="user"><a href="/user/list">用户列表</a></li>
        </ul>
    </li>

    <li class="list-group-item">
        <a href="#" data-toggle="collapse" data-target="#strategyMgr">
            <span>商品管理</span>
        </a>
        <ul class="in" id="strategyMgr">
            <li class="hotel"><a href="/hotel/list" id="hotel_manger">酒店管理</a></li>
            <li class="order"><a href="/order/list" id="order_manger">订单管理</a></li>
        </ul>
    </li>

    <li class="list-group-item is_admin">
        <a href="#" data-toggle="collapse" data-target="#strategyMgr">
            <span>内容管理</span>
        </a>
        <ul class="in" id="strategyMgr">
            <li class="strategyTheme"><a href="/strategyTheme/list">攻略管理</a></li>
            <li class="strategyCatalog"><a href="/strategyCatalog/list">攻略管理</a></li>
            <li class="strategy"><a href="/strategy/list">攻略明细管理</a></li>
        </ul>
    </li>

    <li class="list-group-item is_admin">
        <a href="#" data-toggle="collapse" data-target="#travelMgr">
            <span>游记管理</span>
        </a>
        <ul class="in" id="travelMgr">
            <li class="travel"><a href="/travel/list">游记管理</a></li>
        </ul>
    </li>
</ul>

<!--设置菜单回显-->
<script>
    $(function () {
        var user = $.cookie('user');
        var token = $.cookie('token');
        if (!token) {
            alert("未登录")
            location.href = '/login.html'
        }
        var userJson = JSON.parse(user)
        if (userJson.role != 1) {
            $(".is_admin").hide();
        }

        $('#hotel_manger').attr("href", "/hotel/list?uid=" + userJson.id)
        $('#order_manger').attr("href", "/hotel/list?uid=" + userJson.id)
        console.log(user)
        console.log(token)
    })
    $(".in li.${currentMenu}").addClass("active");
</script>
