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

    <li class="list-group-item is_not_admin">
        <a href="#" data-toggle="collapse" data-target="#strategyMgr">
            <span>商品管理</span>
        </a>
        <ul class="in" id="strategyMgr">
            <li class="hotel"><a href="/hotel/list" id="hotel_manger">酒店管理</a></li>
            <li class="spot"><a href="/ticket/list" id="ticket_manger">门票管理</a></li>
            <li class="order"><a href="/order/list" id="order_manger">订单管理</a></li>
        </ul>
    </li>

    <li class="list-group-item is_admin">
        <a href="#" data-toggle="collapse" data-target="#strategyMgr">
            <span>内容管理</span>
        </a>
        <ul class="in" id="strategyMgr">
<#--            <li class="strategyTheme"><a href="/strategyTheme/list">攻略管理</a></li>-->
            <li class="strategyCatalog"><a href="/strategyCatalog/list">内容推荐管理</a></li>
            <li class="strategy"><a href="/strategy/list">攻略管理</a></li>
        </ul>
    </li>

<#--    <li class="list-group-item is_admin">-->
<#--        <a href="#" data-toggle="collapse" data-target="#travelMgr">-->
<#--            <span>游记管理</span>-->
<#--        </a>-->
<#--        <ul class="in" id="travelMgr">-->
<#--            <li class="travel"><a href="/travel/list">游记管理</a></li>-->
<#--        </ul>-->
<#--    </li>-->
</ul>

<!--设置菜单回显-->
<script>
    $(function () {
        var user = localStorage.getItem("user")
        var token = localStorage.getItem("token")
        if (!token) {
            location.href = '/login.html'
        }
        var userJson = JSON.parse(user)
        console.log(userJson)
        if (userJson.role != 1) {
            $(".is_admin").hide();
        } else {
            $(".is_not_admin").hide()
        }

        $('#ticket_manger').attr("href", "/spot/list?uid=" + userJson.id)
        $('#hotel_manger').attr("href", "/hotel/list?uid=" + userJson.id)
        $('#order_manger').attr("href", "/order/list?uid=" + userJson.id)
    })
    $(".in li.${currentMenu}").addClass("active");
</script>
