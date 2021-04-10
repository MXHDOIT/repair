layui.use(['form', 'element','layer'], function () {
    let form = layui.form;
    let element = layui.element;
    let layer = layui.layer;
});

$("#allocated").click(function() {
    $.ajax({
        async: false,
        type: "post",
        url: "/repair/allocated",
        dataType: "json",
        data: {
            repairId: $("#allocated").val(),
            technicianId:$("#technician").val()
        },
        success: function (data) {
            if (data.success){
                layer.msg("分配成功", {icon: 1, time: 1500});

                // 添加成功后跳转页面
                setTimeout(function () {
                    window.location.href=data.data['url'];
                }, 1500)
            }else {
                layer.msg(data.message, {icon: 2});
            }
        },
        error: function (data) {
            alert(data.result);
        }
    });
});