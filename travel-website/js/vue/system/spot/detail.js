var vue = new Vue({
    el:"#app",
    data:{
        spot:{}
    },
    methods:{
    },
    mounted:function () {
        var param = getParams();
        var id = param.id;
        ajaxGet("/attractions/detail",{"id":id},function (result){
            console.log(result.data)
            vue.spot = result.data;
        })
        //景点明细

    }
});

