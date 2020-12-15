//从链接获得信息
var article_id = parseInt(getParameter("article_id"));
//查询用户信息
$(function () {
    $.get("/user/findUser.do",{},function (data) {
        if(null===data.user_id||undefined===data.user_id){
            $("#comment-main-profile").css("display","none");
            $("#comment-main-login").css("display","block");
            $("#comment-main-btn").prop("disabled","disabled");
        } else {
            $("#comment-main-profile").css("display","block");
            $("#comment-main-nickname").html(data.nickname);
            $("#comment-main-login").css("display","none");
            setInterval(function (){$("#comment-main-time").html(nowTime("mdhfs"));},1000);
            $("#comment-main-btn").removeProp("disabled");
        }
        loadArt(data);
    });
});

function activeBtn(user) {
    //激活查看作者信息
    $(".user-profile").on('click',function () {
        location.href="/about?user_id="+user.user_id;
    })

}
function loadArt(user) {
    $.get("/content/art",{"article_id":article_id},function (data) {
    data.create_time = timestampToTime(data.create_time);
    $("#art-title").html(data.title);
    $("#art-des").html(data.des);
    $("#art-author-ico").attr("src",data.user.user_ico);
    $("#art-author").html(data.article_author);
    $("#art-create_time").html(data.create_time);
    $("#article-content").html(data.content.content);
    activeBtn(data);
    loadmore(user, data);
    var liStr = '';
    for (var i = 0; i < data.commentMains.length; i++) {//还没有完成查看个人资料的页面                         在这里插入个人资料的页面
        var time = data.commentMains[i].comment_main_time;
        var newTime = timestampToTime(time);
    liStr += '<li>' +
            '<span class="face"><a href="#"><img src="'+data.commentMains[i].user_ico+'" alt="头像" href="/about"></a></span>\n' +
            '<span class="users"><a class="comment-username">'+data.commentMains[i].nickname+'</a><strong class="comment-time">'+newTime+'</strong></span>\n' +
            '<span class="text">'+data.commentMains[i].comment+''+
            '<span class="comment-fun">\n';
    var a = '';
        if (data.commentMains[i].like_keep.like) {
            a+='<a class="art-comment-main-like comment-zan" onclick="removeCommentMainLike('+data.commentMains[i].id+')">已赞同\n' +
            '<span class="glyphicon glyphicon-thumbs-up" style="color: #1E9FFF;"></span>';
        } else {
            a+='<a class="art-comment-main-like comment-zan" onclick="addCommentMainLike('+data.commentMains[i].id+')">\n' +
            '<span class="glyphicon glyphicon-thumbs-up" style="color: #fff;"></span>';
        }
        liStr += a;
        liStr +=
            '<span class="comment-zan-nums">'+data.commentMains[i].like+'</span>\n' +
            '</a>\n'+
            '<a class="comment-res" href="javascript:comments('+article_id+','+data.commentMains[i].id+');">\n' +
            '<span class="glyphicon glyphicon-send"></span><span class="comment-res-content">查看回复</span>\n' +
            '</a>\n';
            var del = '';
        if (data.commentMains[i].user_id === user.user_id) {
            del += '<a class="comment-res" style="float: right;" href="javascript:delCommentMain('+data.commentMains[i].id+');">\n' +
                '<span class="glyphicon glyphicon-warning-sign"></span><span class="comment-res-content">删除</span>\n' +
                '</a>';
            liStr += del;
        }
        liStr+=
            '</span>\n' +
            '</span>\n'+
            '</li>';
    }
    $("#art-comment").html(liStr);
});
}

//ajax更新点赞值和收藏值
function loadmore(user,article) {
    $('.zam-nums').html(article.like);
    $('.keep-nums').html(article.keep);
    $.get("/content/com/like.do", {article_id: article_id}, function (data) {
        if (data.like) {
            $("#zambia-text").html("已赞");
            $("#art-a-like").attr("onclick","removeLike()");
            $("#like").addClass("favorite-already");
        } else {
            $("#zambia-text").html("点赞");
            $("#art-a-like").attr("onclick","addLike()");
            $("#like").removeClass("favorite-already");
        }
        if (data.keep) {
            $("#keep-text").html("已纳");
            $("#art-a-keep").attr("onclick","removeKeep()");
            $("#keep").addClass("favorite-already");
        } else {
            $("#keep-text").html("收藏");
            $("#art-a-keep").attr("onclick","addKeep()");
            $("#keep").removeClass("favorite-already");
        }
    });
}
//点击点赞事件
function addLike() {
    var article_id = getParameter("article_id");
    $.get("/user/findUser.do",{},function (user) {
        if (null!==user.user_id||undefined!==user_id) {
            $.get("/content/art/like.do",{article_id:article_id},function (data) {
                if (!data) {
                    alert("网络异常");
                }
                location.reload();
            });
        } else {
            parent.layer.msg('请先登录！', {shade: 0.3});
        }
    })
}

//点击点赞收藏事件
function addKeep() {
    var article_id = getParameter("article_id");
    $.get("/user/findUser.do",{},function (user) {
        if (null!==user.user_id||undefined!==user_id) {
            $.get("/content/art/keep.do",{article_id:article_id},function (data) {
                if (!data) {
                    alert("网络异常");
                }
                location.reload();
            });
        } else {
            parent.layer.msg('请先登录！', {shade: 0.3});
        }
    })
}

//点击取消点赞事件
function removeLike() {
    var article_id = getParameter("article_id");
    $.get("/content/art/removeLike.do",{article_id:article_id},function (data) {
        if (!data) {
            alert("网络异常");
        }
        location.reload();
    });
}

//点击取消收藏事件
function removeKeep() {
    var article_id = getParameter("article_id");
    $.get("/content/art/removeKeep.do",{article_id:article_id},function (data) {
        if (!data) {
            alert("网络异常");
        }
        location.reload();
    });
}

//主评论！！！！！
//点击评论事件
$(function () {
   //给提交按钮绑定事件:获取提交的内容同时传递到后端处理刷新页面
   $("#comment-main-btn").on('click',function () {
       //增加判断用户合法性
       $.get("/user/findUser.do",{},function (data) {
           if (!(null!==user.user_id||undefined!==user_id)) {
               //未登录：非法操作，强制跳转登录
               location.href="/sign";
               return true;
           } else {
               var comment = $("#comment-main-textarea").val();
               var article_id = parseInt(getParameter("article_id"));
               $.ajax({
                   url:"/content/art/saveCommentMain.do",
                   type:"post",
                   data: {
                       comment:comment,
                       article_id:article_id
                   },
                   dataType:"json",
                   success: function (data) {
                       //成就？经验？I don't know
                       //防止跳转
                       if (data.flag === true) {
                           parent.layer.msg('评论成功', {shade: 0.3});
                       } else {
                           parent.layer.msg('网络异常', {shade: 0.3});
                       }
                       location.reload();
                   },
                   error: function () {
                       parent.layer.msg('服务器异常,评论失败！', {shade: 0.3});
                       return false;
                   }
               });
           }
       });
   }) ;
});

//点击点赞事件
function addCommentMainLike(CMid) {
    $.get("/user/findUser.do",{},function (user) {
        if (null!==user.user_id||undefined!==user_id) {
            $.get("/content/com/addCMLike.do",{article_id:article_id,comment_main_id:CMid},function (data) {
                if (!data) {
                    alert("网络异常");
                }
                location.reload();
            });
            parent.layer.msg('测试刷新父页面', {shade: 0.3});
        } else {
            parent.layer.msg('请先登录！', {shade: 0.3});
        }
    })
}

//点击删除
function delCommentMain(CMid) {
    $.get("/user/findUser.do",{},function (user) {
        if (null!==user.user_id||undefined!==user_id) {
            $.get("/content/com/delCommentMain.do",{comment_main_id:CMid},function (data) {
                if (!data) {
                    alert("网络异常");
                }
                location.reload();
            });
        } else {
            parent.layer.msg('请先登录！', {shade: 0.3});
        }
    })
}

//点击取消点赞事件
function removeCommentMainLike(CMid) {
    $.get("/content/com/removeCMLike.do",{CMid:CMid},function (data) {
        if (!data) {
            alert("网络异常");
        }
        location.reload();
    });
    parent.layer.msg('测试刷新父页面', {shade: 0.3});
}

//弹出一个iframe层
function comments(article_id,CMid) {
    if(document.body.clientWidth>=767){
        layer.open({
            type: 2,
            title: '评论与回复',
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area: ['520px', '800px'],
            content: 'res.html?CMid='+CMid+'&&article_id='+article_id
        });
    } else {
        layer.open({
            type: 2,
            title: '评论与回复',
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area: ['100%', '100%'],
            content: 'res.html?CMid='+CMid+'&&article_id='+article_id
        });
    }
}
// comment-main-profile