//时间
var date = new Date();
var seperator1 = "-";
var seperator2 = ":";
var month = date.getMonth() + 1;
var strDate = date.getDate();
if (month >= 1 && month <= 9) {
    month = "0" + month;
}
if (strDate >= 0 && strDate <= 9) {
    strDate = "0" + strDate;
}
var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
$("#date").html(currentdate);
//动态时间
var timer=null;
function displayClock(num){//num是传入的startClock中的动态值
    if(num<10){
        return "0"+num;
    }
    else{
        return num;
    }
}
//停止计时
function stopClock(){
    clearTimeout(timer);
}
//开始计时
function startClock(){
    var time =new Date();
    var hours=displayClock(time.getHours())+":";
    var minutes=displayClock(time.getMinutes())+":";
    var seconds=displayClock(time.getSeconds());
    //显示时间
    show.innerHTML=hours+minutes+seconds;//在id为show的块区域显示
    timer=setTimeout("startClock()",1000);//延时器
}
//登录验证
function checkUsername() {
    //1.获取用户名值
    var username = $("#r_username").val();
    //2.定义正则
    var reg_username = /^\w{4,20}$/;

    //3.判断，给出提示信息
    var flag = reg_username.test(username);
    if (flag) {
        //用户名合法
        $("#r_username").css("border", "");
        $("#r_u_msg").html("");
    } else {
        //用户名非法,加一个红色边框
        $("#r_username").css("border", "1px solid red");
        $("#r_u_msg").html("输入的用户名只能为英文且在4~20位");
    }

    return flag;
}

//校验密码
function checkPassword() {
    //1.获取密码值
    var password = $("#r_password").val();
    //2.定义正则
    var reg_password = /^\w{8,20}$/;

    //3.判断，给出提示信息
    var flag = reg_password.test(password);
    if (flag) {
        //密码合法
        $("#r_password").css("border", "");
        $("#r_p_msg").html("");
    } else {
        //密码非法,加一个红色边框
        $("#r_password").css("border", "1px solid red");
        $("#r_p_msg").html("密码长度在6~15位");
    }

    return flag;
}

//校验邮箱
function checkEmail() {
    //1.获取邮箱
    var email = $("#r_email").val();
    //2.定义正则		itcast@163.com
    var reg_email = /^\w+@\w+\.\w+$/;

    //3.判断
    var flag = reg_email.test(email);
    if (flag) {
        $("#r_email").css("border", "");
        $("#r_e_msg").html("");
    } else {
        $("#r_email").css("border", "1px solid red");
        $("#r_e_msg").html("请输入正确的邮箱格式");
    }

    return flag;
}

//主函数体
$(function () {
    $('.message a').click(function () {
        $('form').animate({
            height: 'toggle',
            opacity: 'toggle'
        }, 'slow');
    });
    //校验登录！！！！！
    //次函数中的方法按顺序执行
    //绑定点击事件
    $("#login").click(function () {
        var username = $("#username").val();
        var password = md5($("#password").val());
        $.post("/user/login", {
            username:username,
            password:password
        }, function (data) {
            var flag = data.flag;
            if (flag) {
                location.href = "/main";
            } else {
                $("#login_form").removeClass('shake_effect');
                setTimeout(function () {
                    $("#login_form").addClass('shake_effect')
                }, 1);
                $("#login_errorMsg").html(data.errorMsg);
                return false;
            }
        });
        return false;
    });
    //当表单提交时，调用所有的校验方法：注册的方法
    $("#create").click(function () {
        //1.发送数据到服务器
        if (checkUsername() && checkPassword() && checkEmail()) {
            var username = $("#r_username").val();
            var password = md5($("#r_password").val());
            var email = $("#r_email").val();
            $.ajax({
                url: "/user/regist",
                type: "post",
                data: {
                    username: username,
                    //前段需要MD5的加密
                    password: password,
                    email: email
                },
                dataType: "json",
                success: function (data) {
                    if (data.flag) {
                        alert("请在您的邮箱激活您的账号");
                        location.href = "/sign";
                    } else {
                        $("#login_form").removeClass('shake_effect');
                        setTimeout(function () {
                            $("#login_form").addClass('shake_effect')
                        }, 1);
                        $("#regist_errorMsg").html(data.errorMsg);
                        return false;
                    }
                }
            });
        } else {
            $("#login_form").removeClass('shake_effect');
            setTimeout(function () {
                $("#login_form").addClass('shake_effect')
            }, 1);
            return false;
        }
        //如果这个方法没有返回值，或者返回为true，则表单提交，如果返回为false，则表单不提交
    });
    //登录的方法
    //当某一个组件失去焦点是，调用对应的校验方法
    $("#r_username").blur(checkUsername);
    $("#r_password").blur(checkPassword);
    $("#r_email").blur(checkEmail);

});