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
    $.get("/user/sendEmail",{
        email:email
    },function (data) {
        if(data) {
            $("#reset-email").attr("disabled","disabled");
            disableBtn();
        } else {
            alert("服务器异常，请人工申诉");
        }
    });
}

var disableTime = 60;
function disableBtn() {
    if(disableTime === 0) {
        disableTime = 60;
        $("#reset-email").removeAttr("disabled");
        $("#timeleast").html("");
        return false;
    } else {
        disableTime --;
        $("#timeleast").html(",请稍后("+disableTime+")");
    }
    setTimeout(function () {
        disableBtn();
    },1000);
    return true;
}