var user = getUserInfo();

if(!user){
    window.confirm("没有登录, 不允许进来");
}

var vue = new Vue({
    el:"#app",
    data:{
        owner:{},
        visitors:[],
        totalView:0,
        todayView:0
    },
    methods:{

    },
    filters:{

    },
    mounted:function () {
        var param = getParams();
        ajaxGet("/users/get", {id:param.ownerId}, function (data) {
            vue.owner = data.data;
        })

        // ajaxGet("/visitor/queryVisitorNumber",{ownerId:param.ownerId},function (data){
        //     vue.todayView = data.data.todayVisitorNumber;
        //     vue.totalView = data.data.totalVisitorNumber;
        //     console.log(data.data)
        // })

        ajaxGet("/visitor/query",{ownerId:param.ownerId},function (data){
            vue.visitors = data.data.visitors;
            console.log(vue.visitors)
            vue.todayView = data.data.visitorNum.todayVisitorNumber;
            vue.totalView = data.data.visitorNum.totalVisitorNumber;
        })


    }
});

