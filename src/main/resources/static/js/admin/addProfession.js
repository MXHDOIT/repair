layui.use(['form','element','layer'], function () {
    let form = layui.form;
    let element = layui.element;
    let layer = layui.layer;
});

$(document).ready(function () {

    //添加
    $("#btn_addProfession").click(function () {
        addProfession();
    });

    //点击删除按钮后删除一行
    $(".btn_deleteProfession").click(function () {

        // 获取要删除选项对应的 id;

        let that = $(this);
        
        layer.confirm('确认删除?', {
            btn: ['确认', '取消'] //按钮
        }, function () {
            let professionId = that.val();

            deleteProfessionById(professionId);
        });

    });

});


//ajax添加种类
function addProfession() {
    $.ajax({
        async: false,
        type: "post",
        url: "/admin/addProfession",
        dataType: "json",
        data: $("#addProfessionForm").serialize(),
        success: function (data) {

            if (data.success) {
                layer.msg("添加成功!", {icon: 1, time: 1500});

                // 1500ms后 重新加载页面 , 将更改后的内容重新加载到页面
                setTimeout(function () {
                    window.location.href=data.data['url'];
                }, 1500);
            } else {
                layer.msg(data.message, {icon: 2, time: 1500});
            }
        },
        error: function (data) {
            layer.msg("添加失败!", {icon: 2, time: 1500});
        }
    });
};

//ajax删除种类
function deleteProfessionById(professionId) {
    $.ajax({
        async: false,
        type: "post",
        url: "/admin/deleteProfession",
        dataType: "json",
        data: {professionId: professionId},
        success: function (data) {
            if (data.success){
                layer.msg("删除成功", {icon: 1, time: 1500});

                // 添加成功后跳转页面
                setTimeout(function () {
                    location.reload();
                }, 1500)
            }else {
                layer.msg(data.message, {icon: 2});
            }
        },
        error: function (data) {
            alert(data.result);
        }
    });
};

