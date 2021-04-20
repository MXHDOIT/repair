layui.use(['form', 'element','layer'], function () {
    let form = layui.form;
    let element = layui.element;
    let layer = layui.layer;
});

$(function () {
    $("#submits").click(function () {
        addOrUpdate();
    });
});

function addOrUpdate() {
    var form = new FormData(document.getElementById("form-repair"));

        $.ajax({
            async: false,
            type: "post",
            url: "/user/addOrUpdateRepair",
            data: form,
            processData:false,
            contentType:false,
            success: function (data) {
                if (data.success == true) {
                    layer.msg(data.message, {icon: 1, time: 1500});

                    setTimeout(function () {
                        window.location.href = data.data['url'];
                    }, 1500)
                } else if (data.success == false) {
                    layer.msg(data.message, {icon: 2});
                }
            },
            error: function (data) {
                alert(data.result);
            }
        });
}