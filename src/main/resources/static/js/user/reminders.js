layui.use(['form', 'element','layer'], function () {
    let form = layui.form;
    let element = layui.element;
    let layer = layui.layer;
});


$(function () {

    //点击催单
    $(".reminders_btn").click(function () {

        let that = $(this);

        layer.confirm('确认催单?', {
            btn: ['确认', '取消'] //按钮
        }, function () {
            let repairId = that.val();

            remindersByRepairId(repairId);

            setTimeout(function () {

                // 关闭所有 layer选项框
                parent.layer.closeAll();
            }, 1000)

        });

    });

});

//ajax删除用户
function remindersByRepairId(repairId) {
    $.ajax({
        async: false,
        type: "post",
        url: "/user/reminders",
        dataType: "json",
        data: {repairId: repairId},
        success: function (data) {
            if (data.success){
                layer.msg("催单成功", {icon: 1, time: 1500});

                // 添加成功后跳转页面
                // setTimeout(function () {
                //     window.location.href=data.data['url'];
                // }, 1500)
            }else {
                layer.msg(data.message, {icon: 2});
            }
        },
        error: function (data) {
            alert(data.result);
        }
    });
}