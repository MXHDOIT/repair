function selectIdentity(o)
{
    if(o==0)
    {
        $("#myForm").attr("action","/user/login");
        $("#submit").text("用户登录");
    }
    else if(o==1)
    {
        $("#myForm").attr("action","/technician/login");
        $("#submit").text("维修人员登录");
    }else if(o==2){
        $("#myForm").attr("action","/admin/login");
        $("#submit").text("管理员登录");
    }

    $("#submit").css("display","block");
}

function hideErrorMsg() {
    $("#errorMsg").css("display","none")
}

function login() {
    var id = $("#form-id").val()
    var password = $("#form-password").val()
    if (!check(id)  || !check(password)){
        $("#errorMsg").text("账号和密码不能为空");
        $("#errorMsg").css("display","block");
        return false;
    }

    $.ajax({
        type: "POST",
        url: $("#myForm").attr("action"),
        data: {
            'id':id,
            'password':password
        },
        success: function (data) {
            if (data.success){
                window.location.href=data.data["url"]
            }else{
                $("#errorMsg").text(data.message);
                $("#errorMsg").css("display","block");
            }
        }
    })
}

// 校验表单中用户名 与 密码是否输入,  如果有值 -> 返回 true , 如果未输入 返回 false;
function check(val) {
    val = val.toString().trim();
    return !(val == '');
}