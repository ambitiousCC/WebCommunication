$("#resetPasswordBtn").on('click',function () {
    //1. 验证两次密码是否一致
    var newPassword = $("#re-password").val();
    var confirmPassword = $("#re-confirmPassword").val();
    var flag = false;
    if(newPassword==="" || confirmPassword==="") {
        alert("未输入完全");
    }
    else if (newPassword!==confirmPassword) {
        alert("两次输入的密码不一致");
    } else {
        //2. 更新密码
        changePassword(newPassword);
    }
});

//修改密码
function changePassword(password) {
    password = md5(password);
    $.post("/user/save/password.do", {
        password:password
    }, function (data) {
        if (data) {
            alert("修改成功");
            location.href="/sign";
        } else {
            alert("网络异常，修改失败");
        }
    });
}