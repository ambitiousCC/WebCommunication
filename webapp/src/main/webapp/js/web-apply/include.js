//公用函数
function looseEqual(a, b) {
    if (a === b) { return true }
    var isObject = function (obj) {
        return obj !== null && typeof obj === 'object'
    };
    var isObjectA = isObject(a),
        isObjectB = isObject(b);
    if (isObjectA && isObjectB) {
        try {
            var isArrayA = Array.isArray(a),
                isArrayB = Array.isArray(b);
            if (isArrayA && isArrayB) {
                return a.length === b.length && a.every(function (e, i) {
                    return looseEqual(e, b[i])
                })
            } else if (!isArrayA && !isArrayB) {
                var keysA = Object.keys(a),
                    keysB = Object.keys(b);
                return keysA.length === keysB.length && keysA.every(function (key) {
                    return looseEqual(a[key], b[key])
                })
            } else {
                return false
            }
        } catch (e) {
            return false
        }
    } else if (!isObjectA && !isObjectB) {
        if (typeof a === 'number' || typeof b === 'number') {
            return a === b;
        } else {
            return String(a) === String(b)
        }

    } else {
        return false;
    }

}
// 空文件
//拼接代码--》注意：需要的是以后功能的实现，都要注意文件引用的问题
$.get("left-nav.html",function (data) {
    $("#js-header").html(data);
});
$.get("right-nav.html",function (data) {
    $("#js-aside").html(data);
});
$.get("footer.html",function (data) {
    $("#js-footer").html(data);
});

//根据传递过来的参数name获取对应的值
function getParameter(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
    var r = location.search.substr(1).match(reg);
    if (r!=null) return (r[2]); return null;
}
//globe中的异步提交
function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    D = date.getDate();
    h = date.getHours();
    if(h-8<=0){
        h += 16;
        D -= 1;
    } else {
        h -= 8;
    }
    D += " ";
    h += ":";
    // m = date.getMinutes() + ':';
    m = date.getMinutes();
    // s = date.getSeconds();
    return Y+M+D+h+m;
}

function searching() {
    $.get("/user/findUser.do",{},function (data) {
        if(null===data.user_id||undefined===data.user_id) {
            window.location.href = "/globe";
            alert("登陆以后才具有搜索功能");
        } else {
            var title = $("#input-search").val();
            window.location.href = "/globe?title=" + title;
            alert("这些是搜索的结果");
            return false;
        }
    });
}
