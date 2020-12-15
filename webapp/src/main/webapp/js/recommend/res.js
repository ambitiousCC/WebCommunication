var article_id = parseInt(getParameter("article_id"));
var CMid = parseInt(getParameter("CMid"));
//根据传递过来的参数name获取对应的值
function getParameter(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = location.search.substr(1).match(reg);
    if (r != null) return (r[2]);
    return null;
}

function looseEqual(a, b) {
    if (a === b) {
        return true
    }
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
            return a === b
        } else {
            return String(a) === String(b)
        }

    } else {
        return false;
    }
}

function timestampToTimeInUse(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-';
    M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    D = date.getDate();
    h = date.getHours();
    if (h - 8 <= 0) {
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
    return Y + M + D + h + m;
}

$(function () {
    //先默认是查询所有吧,cid=0
    //由于设计的不同：cid应该由当前页面下的绑定时间确定
    //页面加载完成以后，调用load方法
    //就是按照cid分类的工作需要补充
    loadUse();
});

function loadUse() {
    $.get("/user/findUser.do", {}, function (data) {
        if (null===data.user_id||undefined===data.user_id) {
            $("#comment-btn").attr("value", "请登录");
            $("#comment-btn").attr("disabled", "disabled");
        } else {
            $("#comment-btn").attr("value", "回复");
            $("#comment-btn").removeAttr("disabled");
        }
        load(CMid, data);
    });
}

function load(CMid, user) {
    $.get("/content/com",
        {
            "CMid": CMid
        },
        function (data) {
            var userIco = '<img src="' + data.user_ico + '" alt="">';
            var CMtime = timestampToTimeInUse(data.comment_main_time);
            $("#comment-user-ico").html(userIco);
            $("#comment-username").html(data.nickname);
            $("#comment-text").html(data.comment);
            $("#comment-time").html(CMtime);
            if (data.like_keep.like&&(data.like-1>0)) {
                $("#commentsCM-Zan").attr("onclick",'removeCommentMainLike('+data.id+')');
                $("#commentsCM-Zan").html("已赞同"+data.like);
                $("#comment-zanNums").html("我和"+data.like +"个人觉得很赞");
            } else if (data.like_keep.like) {
                $("#commentsCM-Zan").attr("onclick",'removeCommentMainLike('+data.id+')');
                $("#commentsCM-Zan").html("已赞同"+data.like);
                $("#comment-zanNums").html("我jio得很赞");
            } else if (data.like > 0) {
                $("#commentsCM-Zan").attr("onclick", 'addCommentMainLike(' + data.id + ')');
                $("#commentsCM-Zan").html("赞" + data.like);
                $("#comment-zanNums").html(data.like + "个人觉得很赞");
            } else {
                $("#commentsCM-Zan").attr("onclick", 'addCommentMainLike(' + data.id + ')');
                $("#commentsCM-Zan").html("赞");
                $("#comment-zanNums").html("暂时还没有人点赞");
            }
            $("#comment-zanNums").attr("total", data.like);
            var lists = '';
            for (var i = 0; i < data.commentList.length; i++) {
                var Ctime = timestampToTimeInUse(data.commentList[i].comment_time);
                if (null == data.commentList[i].like) {
                    data.commentList[i].like = 0;
                }
                lists += '<div class="comment" user="self">\n' +
                    '<div class="comment-left">\n' +
                    '<img src="' + data.commentList[i].user_ico + '" alt="用户头像">\n' +
                    '</div>\n' +
                    '<div class="comment-right">\n' +
                    '<div class="comment-text"><span class="user name comment-name" user="' + data.commentList[i].from_user + '">' + data.commentList[i].from_nickname + '</span>\n';
                var juStr = '';
                //注意：为了防止异常，评论对象为空时，user_id为0(绝无此人)
                //这里就快乐的解决了如果对象是空就直接弄的对象是主评论人，更有逻辑一些
                if (data.commentList[i].to_nickname == null || data.commentList[i].to_user === 0) {
                    juStr += '<p class="comment-text">' + data.commentList[i].comment + '</p>\n';
                } else {
                    juStr += '<p class="comment-text">@' + data.commentList[i].to_nickname + ':' + data.commentList[i].comment + '</p>\n';
                }
                lists += juStr;
                lists += '</div>\n' +
                    '<div class="comment-date">\n' +
                    '<span class="comment-time" style="float:left;" user="' + data.commentList[i].id + '">' + Ctime + '</span>\n' +
                    '<span class="comment-fun" style="float:right">\n';
                //这里的my值暂时未处理，需要完成动态交互以后完
                var myLike;
                var action;
                if (user !== null) {
                    var userIdA = user.user_id + '';
                    var userIdB = data.commentList[i].from_user + '';
                    userIdA = parseInt(userIdA);
                    userIdB = parseInt(userIdB);
                    if (looseEqual(userIdA, userIdB)) {
                        action = '删除';
                    } else {
                        action = '回复';
                    }
                } else {
                    action = '';
                }
                var a = '';
                if (data.commentList[i].like_keep.like) {
                    a+='<a class="comment-zan" href="javascript:;" total="' + data.commentList[i].like + '" my="1" onclick="removeCommentLike('+data.commentList[i].id+')">已赞同(' + data.commentList[i].like + ')</a>\n';
                } else {
                    a+='<a class="comment-zan" href="javascript:;" total="' + data.commentList[i].like + '" my="0" onclick="addCommentLike('+data.commentList[i].id+')">赞(' + data.commentList[i].like + ')</a>\n';
                }
                lists += a;
                lists +=
                    '<span>&nbsp;&nbsp;|&nbsp;&nbsp;</span>\n' +
                    '<a class="comment-dele" href="javascript:;">' + action + '</a>\n' +
                    '</span>\n' +
                    '</div>\n' +
                    '</div>\n' +
                    '</div>';
            }
            $("#comments").html(lists);
        });
}

if ($('.hf-text').val().length == 0) {
    $('.hf-text').css("height", "20px");
}
$(document).ready(function () {
    $(".hf-text").keydown(function (e) {
        var curKey = e.which;
        if (curKey == 13) {
            $("#textCount").html("不要回车");
            return false;
        }
    });
});
$('.hf-text').keyup(
    function () {
        var count = $(this).val().length;
        var height = 22;
        if (count <= 18) {
            $('.hf-text').css("border", "1px #008CBA solid");
            $('.hf-text').css("height", height + "px");
            $("#textCount").html("");
        } else if (count > 18 && count <= 36) {
            $('.hf-text').css("border", "1px #008CBA solid");
            $('.hf-text').css("height", 2 * height + "px");
            $("#textCount").html("");
        } else if (count > 36 && count <= 54) {
            $('.hf-text').css("border", "1px #008CBA solid");
            $('.hf-text').css("height", 3 * height + "px");
            $("#textCount").html("");
        } else if (count > 54 && count <= 72) {
            $('.hf-text').css("border", "1px #008CBA solid");
            $('.hf-text').css("height", 4 * height + "px");
            $("#textCount").html("");
        } else if (count > 90 && count <= 100) {
            $('.hf-text').css("border", "1px #008CBA solid");
            $('.hf-text').css("height", 5 * height + "px");
            $("#textCount").html("");
        } else if (count > 100 || keyCode == 13) {
            $('.hf-text').css("border", "2px red solid");
            $("#textCount").html("已超过限定字数");
        }
    }
);

function addloadEvent(func) {
    var oldonload = window.onload;
    if (typeof window.onload != "function") {
        window.onload = func;
    } else {
        window.onload = function () {
            if (oldonload) {
                oldonload();
            }
            func();
        }
    }
}

//主评论点赞

//主评论！！！！！
//点击点赞事件
function addCommentMainLike(CMid) {
    $.get("/user/findUser.do",{},function (user) {
        if (user) {
            $.get("/content/com/addCMLike.do",{article_id:article_id,comment_main_id:CMid},function (data) {
                if (data) {
                    //成功
                    location.reload();
                } else {
                    //失败
                    alert("网络异常");
                    location.reload();
                }
            });
        } else {
            parent.layer.msg('请先登录！', {shade: 0.3});
        }
    })
}
//点击取消点赞事件
function removeCommentMainLike(CMid) {
    $.get("/content/com/removeCMLike.do",{CMid:CMid},function (data) {
        if (data) {
            //成功
            location.reload();
        } else {
            //失败
            alert("网络异常");
            location.reload();
        }
    });
}
//副评论点赞
function addCommentLike(Cid) {
    $.get("/user/findUser.do",{},function (user) {
        if (user) {
            $.get("/content/com/addCLike.do",{article_id:article_id,comment_id:Cid},function (data) {
                if (data) {
                    //成功
                    location.reload();
                } else {
                    //失败
                    alert("网络异常");
                    location.reload();
                }
            });
        } else {
            parent.layer.msg('请先登录！', {shade: 0.3});
        }
    })
}
function removeCommentLike(Cid) {
    $.get("/content/com/removeCLike.do",{comment_id:Cid},function (data) {
        if (data) {
            //成功
            location.reload();
        } else {
            //失败
            alert("网络异常");
            location.reload();
        }
    });
}

addloadEvent(b);

function b() {
    var pn = document.getElementById("pn");
    var lists = pn.children;

    //删除当前节点
    function remove(node) {
        node.parentNode.removeChild(node);
    }

    //上面的点赞
    function praisebox(box, el) {
        //获取赞数量容器
        var praise = box.getElementsByClassName("people")[0];
        //获取容器当前total值
        var total = parseInt(praise.getAttribute("total"));
        //获取点击的innerHTML
        var txt = el.innerHTML;
        //创建一个新的total存储用
        var newtotal;
        //判断点击的文字内容
        if (txt == "赞") {
            //total值+1 因为我还没点击赞，所以要点击的时候就多了一个人 total+1
            newtotal = total + 1;
            //判断赞数量 把相应文字放到赞容器里
            praise.innerHTML = newtotal == 1 ? "我觉得很赞" : "我和" + total + "个人觉得很赞";
            el.innerHTML = "取消赞";
        } else {
            //反之total值-1
            newtotal = total - 1;
            praise.innerHTML = newtotal == 0 ? "" : newtotal + "个人觉得很赞";
            el.innerHTML = "赞";
        }
        //更新total值
        praise.setAttribute("total", newtotal);
        //如果没有人点赞容器隐藏
        praise.style.display = (newtotal == 0) ? "none" : "block";
    }

    //回复评论
    // ************************************************************************************************************************************
    function replyForUser(box) {
        $.get("/user/findUser.do", {}, function (data) {
            reply(box,article_id, CMid, data);
        });
    }

    function reply(box, article_id,CMid, user) {
        //不用传入当前评论的时间，直接在后端处理：抑制高并发操作
        //和文章不同，只用向后端传入用户信息和评论信息，不需要格式
        //获取评论框
        var text = box.getElementsByTagName("textarea")[0];
        var commentContent = text.value;
        //获取包含所有评论的容器
        var comment = box.getElementsByClassName("comment-list")[0];
        //创建新的评论div
        var div = document.createElement("div");
        //赋类名
        div.className = "comment";
        //设置属性
        div.setAttribute("user", "self");
        //获取每条评论的innerHTML结构，每次只替换textarea的输入内容和 当前发送时间
        var html = '<div class="comment-left">' +
            '<img src="' + user.user_ico + '" alt=""/>' +
            '</div>' +
            '<div class="comment-right">' +
            '<div class="comment-text">' +
            '<span class="user name comment-name">' + user.nickname + '</span>' +
            '<p class="comment-text">' +
            text.value +
            '</p>' +
            '</div>' +
            '<div class="comment-date">' +
            '<span class="comment-time" style="float:left;">' + getTime() + '</span>' +
            '<span class="comment-fun" style="float:right">' +
            '<a class="comment-zan" href="javascript:;" total="0" my="0">赞</a>' +
            '<span>&nbsp;&nbsp;|&nbsp;&nbsp;</span>' +
            '<a class="comment-dele" href="javascript:;">删除</a>' +
            '</span>' +
            '</div>';
        //插入到新建的评论div
        div.innerHTML = html;
        //把新评论插入到评论列表
        comment.appendChild(div);
        //评论后初始化textarea输入框
        text.value = "";
        text.parentNode.className = "hf";
        var to_userStr = box.getElementsByClassName("user")[0].getAttribute("user");
        var to_nickname = box.getElementsByClassName("user")[0].innerHTML;
        var to_user = parseInt(to_userStr);
        if (isNaN(to_user)) {
            to_user = 0;
        }
        //向后端保存数据:其中时间后端设置，以及赞数是0
        $.ajax({
            url: "/content/com/save.do",
            type: "post",
            data: {
                comment: commentContent,
                article_id:article_id,
                from_user: user.user_id,
                to_user: to_user,
                CMid:CMid,
                user_ico: user.user_ico,
                from_nickname: user.nickname,
                to_nickname: to_nickname
            },
            dataType: "json",
            success: function (data) {
                //成就？经验？I don't know
                //防止跳转
                if (data.flag === true) {
                    parent.layer.msg('评论成功', {shade: 0.3});
                }
                location.reload();
            },
            error: function () {
                parent.layer.msg('服务器异常,评论失败！', {shade: 0.3});
                return false;
            }
        });
    }

    //删除自己的评论
    // **************************************************************************************************
    function removeForUser(box, Cid) {
        //删除缓存
        remove(box);
        var comment_id = parseInt(Cid);
        // 调用删除
        removeComment(comment_id);
    }

    function removeComment(comment_id) {
        //向后端保存数据:其中时间后端设置，以及赞数是0
        $.ajax({
            url: "/content/com/del.do",
            type: "post",
            data: {
                comment_id: comment_id
            },
            dataType: "json",
            success: function (data) {
                //成就？经验？I don't know
                //防止跳转
                if (data.flag === true) {
                    parent.layer.msg('删除成功', {shade: 0.3});
                }
                location.reload();
            },
            error: function () {
                parent.layer.msg('服务器异常，删除失败！', {shade: 0.3})
                return false;
            }
        });
    }

    //获取当前时间回复评论时调用
    function getTime() {
        var t = new Date();
        var y = t.getFullYear();
        var m = t.getMonth() + 1;
        var d = t.getDate();
        var h = t.getHours();
        var mi = t.getMinutes();
        m = m < 10 ? "0" + m : m;
        d = d < 10 ? "0" + d : d;
        h = h < 10 ? "0" + h : h;
        mi = mi < 10 ? "0" + mi : mi;
        return y + "-" + m + "-" + d + "" + h + ":" + mi;
    }

    //回复里点赞
    function praiseReply(el) {
        //获取当前total值 也就是所有点赞数量
        var total = parseInt(el.getAttribute("total"));
        //获取当前my值 我的点赞
        var my = parseInt(el.getAttribute("my"));
        //创建新的total
        var newtotal;
        //判断my=0就是我准备要点赞
        if (my === 0) {
            //我点了赞总数量就要+1
            newtotal = total + 1;
            //更新total值
            el.setAttribute("total", newtotal);
            //更新my值
            el.setAttribute("my", 1);
            //更新文字 就是我点了后 文字就是取消赞了
            el.innerHTML = " 取消赞" + "(" + newtotal + ")";
        } else {
            //反之-1
            newtotal = total - 1;
            el.setAttribute("total", newtotal);
            el.setAttribute("my", 0);
            el.innerHTML = (newtotal === 0) ? " 赞" : " 赞" + "(" + newtotal + ")";
        }
        //在这里向后端传送赞的数据，以及更新的评论区文本
    }

    //操作回复
    function operateReply(el) {
        //每条评论
        var comment = el.parentNode.parentNode.parentNode;
        //整个状态
        var box = comment.parentNode.parentNode.parentNode;
        //评论框
        var text = box.getElementsByTagName("textarea")[0];
        //名字
        var user = comment.getElementsByClassName("user")[0];
        //点击的innerHTML
        var Cid = comment.getElementsByClassName("comment-time")[0].getAttribute("user");
        var txt = el.innerHTML;
        //判断当前点击的是否为回复
        if (txt == "回复") {
            //评论框触发焦点事件 也就是变高
            text.onfocus();
            //内容变为回复+当前人的名字
            text.value = "@" + user.innerHTML + ":";
            //调用键盘事件
            text.onkeyup();
        } else {
            //否则就是删除节点
            comment = comment.parentNode;
            removeForUser(comment, Cid);
        }
    }

    //遍历所有状态消息
    for (var i = 0; i < lists.length; i++) {
        //全部事件代理
        lists[i].onclick = function (e) {
            //获取当前点击事件
            var e = e || window.event;
            var el = e.srcElement;
            if (!el) {
                el = e.target; //兼容火狐
            }
            //判断点击的类名
            switch (el.className) {
                case "dzan":
                    praisebox(el.parentNode.parentNode.parentNode, el);
                    break;
                //回复评论
                case "hf-btn hf-btn-on":
                    replyForUser(el.parentNode.parentNode.parentNode);
                    break;
                //每条评论中点赞
                case "comment-zan":
                    praiseReply(el);
                    break;
                case "comment-dele":
                    operateReply(el);
                    break;
            }
        }
        var text = lists[i].getElementsByClassName("hf-text")[0];
        //焦点事件
        text.onfocus = function () {
            this.parentNode.className = 'hf hf-on';
            this.value = this.value == '评论…' ? '' : this.value;
        }
        //失焦事件
        text.onblur = function () {
            if (this.value == '') {
                this.parentNode.className = 'hf';
                this.value = '评论…';
            }
        }
        //键盘事件
        text.onkeyup = function () {
            var len = this.value.length;
            var textParentNode = this.parentNode;
            var textBtn = textParentNode.children[1];
            var textNub = textParentNode.children[2];
            if (len == 0 || len > 100) {
                textBtn.className = "hf-btn";
            } else {
                textBtn.className = "hf-btn hf-btn-on";
                /*this.style.color="#333"; */
            }
        }
    }
    //遍历结束
}