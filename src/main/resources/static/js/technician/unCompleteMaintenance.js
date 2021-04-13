layui.use(['form', 'element','layer'], function () {
    let form = layui.form;
    let element = layui.element;
    let layer = layui.layer;
});


//点击完成维修按钮后
 $(".del_btn").on('click',function () {
     let that = $(this);
    //页面层
     layer.open({
         type: 1 //Page层类型
         , skin: 'layui-layer-molv'
         , area: ['380px', '270px']
         , title: ['维修后图片上传', 'font-size:18px']
         , btn: ['保存', '取消']
         , shadeClose: true
         , shade: 0 //遮罩透明度
         , content: $("#window")
         , yes: function () {
             completeMaintenance(that.val());
         }
     });
 });

function completeMaintenance(maintenanceId) {
    $.ajax({

        async: false,
        type: 'post',
        url: '/technician/completeMaintenance',
        data: {
            maintenanceId:maintenanceId,
            file:$('#picture').val()
        },
        success: function (data) {
            layer.alert('上 传 成 功', {icon: 1}, function () {
                location.reload();
            });
        },
        error: function (data) {
            layer.alert("上 传 失 败");
        }
    });
};