var vue = new Vue({
    el:"#app",
    data:{
        strategyList:[]  //攻略收藏列表,不需要分页
    },
    methods:{

    },
    mounted:function () {
        //游记查询
        ajaxGet("/collections/query", {}, function (data) {
            let _this;
            vue.strategyList = data.data;
            console.log(vue.strategyList)

        })

    }
});

