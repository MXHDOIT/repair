layui.use(['form', 'element','layer'], function () {
    let form = layui.form;
    let element = layui.element;
    let layer = layui.layer;
});


$(function () {

    //点击删除按钮后删除一行
    $(".del_btn").click(function () {

        let that = $(this);

        layer.confirm('确认删除?', {
            btn: ['确认', '取消'] //按钮
        }, function () {
            let userId = that.val();

            deleteUserById(userId);

            that.parent().parent().remove();
            layer.msg("删除成功", {icon: 1, time: 1000});
            window.location.href=data.data["url"];
            setTimeout(function () {

                // 关闭所有 layer选项框
                parent.layer.closeAll();
            }, 1000)

        });

    });

});

//ajax删除用户
function deleteUserById(userId) {
    $.ajax({
        async: false,
        type: "post",
        url: "/user/delete",
        dataType: "json",
        data: {userId: userId},
        success: function (data) {
            if (data.success){
                layer.msg("删除成功", {icon: 1, time: 1500});

                // 添加成功后重新加载页面
                setTimeout(function () {
                    location.reload();
                }, 1500)
            }
        },
        error: function (data) {
            alert(data.result);
        }
    });
}