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

        });

    });

});

//ajax删除用户
function deleteUserById(userId) {
    $.ajax({
        async: false,
        type: "post",
        url: "/admin/deleteUser",
        dataType: "json",
        data: {userId: userId},
        success: function (data) {
            if (data.success == true){
                layer.msg("删除成功", {icon: 1, time: 1500});

                setTimeout(function () {
                    location.reload();
                }, 1500)
            }else if (data.success == false){
                layer.msg(data.message, {icon: 2});
            }
        },
        error: function (data) {
            alert(data.result);
        }
    });
}