layui.use(['form', 'element', 'layer'], function () {
    let form = layui.form;
    let element = layui.element;
    let layer = layui.layer;

    form.on('submit(btn_addUser)', function (data) {
        addUser();
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
});


//添加用户的ajax方法
function addUser() {
    let id = $("#technicianId").val();
    let name = $("#name").val();
    let password = $("#password").val();
    let work = $("#work").val();
    let phone = $("#phone").val();
    let email = $("#email").val();
    $.ajax({
        async: false,
        type: 'post',
        url: '/technician/add',
        data: {
            'id':id,
            'name':name,
            'password':password,
            'professionId':work,
            'phone':phone,
            'email':email
        },
        success: function (data) {
            if (data.success){
                layer.msg("添加成功", {icon: 1, time: 1500});

                // 添加成功后重新加载页面
                setTimeout(function () {
                    location.reload();
                }, 1500)
            }else {
                layer.msg(data.message, {icon: 2});
            }
        },
        error: function (data) {
            layer.msg("添加失败", {icon: 2});
        }
    });
};