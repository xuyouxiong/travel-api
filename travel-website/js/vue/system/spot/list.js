var vue = new Vue({
    el:"#app",
    data:{
        spots:[]
    },
    methods:{
    },
    mounted:function () {
        ajaxGet("/attractions/list",{},function (result){
            vue.spots = result.data.records;
            console.log(vue.spots);
        })
    }
});

