$("#reset-email").on('click',function () {
    //获取输入邮箱，正则表达筛选
    var email = $("#reEmail").val();
    if(checkEmail()) {
        sendtemp(email);
    }
});

function checkEmail() {
    //1.获取邮箱
    var email = $("#reEmail").val();
    //2.定义正则		itcast@163.com
    var reg_email = /^\w+@\w+\.\w+$/;

    //3.判断
    var flag = reg_email.test(email);
    if (!flag) {
        alert("输入格式不正确");
    }
    return flag;
}

function sendtemp(email) {
    $.post("/user/sendEmail",{
        email:email,
    },function (data) {
    });
}