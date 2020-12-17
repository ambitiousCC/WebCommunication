//注意：parent 是 JS 自带的全局对象，可用于操作父页面
var index = parent.layer.getFrameIndex(window.name); //获取窗口索引

//在父层弹出一个层
$('#new').on('click', function () {
    parent.layer.msg('请您同意《服务条款》', {shade: 0.3})
});
//关闭iframe
$('#closeIframe').click(function () {
    parent.layer.msg('本站欢迎您的到来');
    parent.layer.close(index);
});