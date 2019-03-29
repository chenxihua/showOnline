$(function () {

    layui.use(['table','util'], function(){
        var table = layui.table;
        var util = layui.util;
        //页面初始化
        table.render({
            elem: '#list'
            ,url: '/verify/online_list'
            ,cols: [[ //表头
                // {field: 'id', title: '序号', fixed: 'left'}
                {field: 'username', title: '用户名'}
                ,{field: 'ip', title: 'IP'}
                ,{field: 'createTime', title: '登录时间', templet:function(d){ return util.toDateString(d.createTime, 'yyyy-MM-dd HH:mm:ss'); }}

            ]]
            ,parseData: function(res){ //res 即为原始返回的数据

                return {
                    "code": res.code, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.count, //解析数据长度
                    "data": res.data //解析数据列表
                };
            }
            ,page: {
                limit: 15
                ,limits:[15]
            } //开启分页

        });
        //绑点查询点击事件
        $('.searchbtn').click(function (e) {
            table.reload('list', {
                where: { //设定异步数据接口的额外参数，任意设
                    username: $(".usernameinput").val()
                    ,ip: $(".ipinput").val()
                }
                ,page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        })

        //绑点enter键入
        $(".usernameinput").keypress(function (e) {
            if (e.which == 13) {
                table.reload('list', {
                    where: { //设定异步数据接口的额外参数，任意设
                        username: $(".usernameinput").val()
                        ,ip: $(".ipinput").val()
                    }
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            }
        });

        $(".searchinput").keypress(function (e) {
            if (e.which == 13) {
                table.reload('list', {
                    where: { //设定异步数据接口的额外参数，任意设
                        username: $(".usernameinput").val()
                        ,ip: $(".ipinput").val()
                    }
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            }
        });

    });


});

