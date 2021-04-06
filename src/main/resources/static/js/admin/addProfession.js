layui.use(['form','element','layer'], function () {
    let form = layui.form;
    let element = layui.element;
    let layer = layui.layer;

    form.on('submit(btn_addBookCategory)', function (data) {
        addProfession();
        return false;
    });
});

$(document).ready(function () {

    //检查能否再点击上一页，下一页
    let lab1 = $("#lab1").html().trim();//获取当前页码
    let lab2 = $("#lab2").html().trim();//获取总页码

    $("#prePage").click(function () {
        if (lab1 == 1) {
            layer.msg("已经是最后一页了!", {icon: 7});
            return false;
        }
        return true;
    });

    $("#nextPage").click(function () {
        if (lab1 == lab2) {
            layer.msg("已经是最后一页了!", {icon: 7});
            return false;
        }
        return true;
    });

    //点击删除按钮后删除一行
    $(".btn_deleteCategory").click(function () {

        // 获取要删除选项对应的 id;

        let that = $(this);
        
        layer.confirm('确认删除?', {
            btn: ['确认', '取消'] //按钮
        }, function () {
            let professionId = that.val();

            deleteBookCategoryById(professionId);

            that.parent().parent().remove();
            layer.msg("删除成功", {icon: 1, time: 1000});

            setTimeout(function () {

                // 关闭所有 layer选项框
                parent.layer.closeAll();
            }, 1000)

        });

    });
});


//ajax添加种类
function addProfession() {
    $.ajax({
        async: false,
        type: "post",
        url: "/profession/add",
        dataType: "json",
        data: $("#addBookCategoryForm").serialize(),
        success: function (data) {

            if (data.success) {
                layer.msg("添加成功!", {icon: 1, time: 1500});

                // 1500ms后 重新加载页面 , 将更改后的内容重新加载到页面
                setTimeout(function () {
                    location.reload();
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
function deleteBookCategoryById(professionId) {
    $.ajax({
        async: false,
        type: "post",
        url: "/profession/delete",
        dataType: "json",
        data: {professionId: professionId},
        success: function (data) {
            if (data.success){
                layer.msg("删除成功", {icon: 1, time: 1500});

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
}

