layui.use(['form', 'element','layer'], function () {
    let form = layui.form;
    let element = layui.element;
    let layer = layui.layer;
});


//点击完成维修按钮后
 $("#print").on('click',function () {
     window.open("/technician/printCompleteMaintenance?startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val())
 });
