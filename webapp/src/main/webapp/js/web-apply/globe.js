$(function () {
    //查询所有公开的文章
    //由于设计的不同：cid应该由当前页面下的绑定时间确定
    //页面加载完成以后，调用load方法
    //就是按照cid分类的工作需要补充
        var tid = getParameter("tid");
        var pid = getParameter("pid");
        var title = getParameter("title");
    if (null == pid) {
        pid = 1;
    }
    if (title) {
        title = window.decodeURIComponent(title);
    }
        load(tid,pid, null, title);
    return false;
});
function load(tid,pid,currentPage,title) {
    //请求数据
    $.get("/globe/article",
        {
        "tid":tid,
        "pid":pid,
        "currentPage":currentPage,
        "title":title
         },
        function (data) {
            this.title = title;
            this.tid = tid;
            this.pid = pid;
            //解析数据
            //分页展示
            var list = "";
            var firstPage;
            var prePage;
            if (data.currentPage === 1) {
                firstPage = '<a class="disabled" href="javascript:load('+tid+','+pid+',1,\''+title+'\')">首页</a>';
                prePage = '<a class="disabled" href="javascript:load('+tid+','+pid+',1,\''+title+'\')">上一页</a>';
            } else {
                firstPage = '<a href="javascript:load('+tid+','+pid+',1,\''+title+'\')">首页</a>';
                prePage = '<a href="javascript:load('+tid+','+pid+','+(data.currentPage-1)+',\''+title+'\')">上一页</a>';
            }
            list += firstPage;
            list += prePage;

            //凭借页面：八个：前四后三
            var begin;
            var end;
            if (data.totalPage<8) {
                //不够
                begin=1;
                end = data.totalPage;
            } else {
                begin = data.currentPage-4;
                end = data.currentPage+3;

                //前面不够,后面不够？统统补齐
                if (begin < 1) {
                    begin = 1;
                    end = begin + 7;
                }
                if (end > data.totalPage) {
                    end = data.totalPage;
                    begin = end - 7;
                }
            }
            for (var i = begin; i <= end; i++) {
                //判断样式:拼接样式
                if (data.currentPage === i) {
                    list += '<a class="current" href="javascript:load('+tid+','+pid+','+i+',\''+title+'\')">'+i+'</a>';
                } else {
                    list += '<a href="javascript:load('+tid+','+pid+','+i+',\''+title+'\')">'+i+'</a>';
                }
            }

            if (data.currentPage === data.totalPage) {
                var nextPage = '<a class="disabled" href="javascript:load('+tid+','+pid+','+(data.currentPage+1)+',\''+title+'\')">下一页</a>'
                var lastPage = '<a class="disabled" href="javascript:load('+tid+','+pid+','+data.totalPage+',\''+title+'\')">尾页</a>'
            } else {
                var nextPage = '<a href="javascript:load('+tid+','+pid+','+(data.currentPage+1)+',\''+title+'\')">下一页</a>'
                var lastPage = '<a href="javascript:load('+tid+','+pid+','+data.totalPage+',\''+title+'\')">尾页</a>'
            }
            list += nextPage;
            list += lastPage;
            list += '<span>共计'+data.totalPage+'页</span>';
            list += '<span>共计'+data.totalCount+'篇</span>';
            $("#pageNums").html(list);
            //列表数据展示
            var article_li = "";
            for (var i = 0; i < data.list.length; i++) {
                var article = data.list[i];
                //时间格转变！！
                article.create_time = timestampToTime(article.create_time);
                var list = '<div class="row globe-row">\n'+
                    '<div class="news-list">\n'+
                    '<div class="carousel-inner" role="listbox">\n'+
                    '<div class="item active article">\n' +
                    '<a href="/content?article_id='+article.article_id+'" target="_blank"><img src="'+article.article_img+'"/></a>\n' +
                    '<div class="carousel-caption" style="float: left;" ><a class="article_title" href="/content?article_id='+article.article_id+'"> '+article.title+'</a><span class="article_author" title="'+article.article_author+'" rel="author" style="float: right;">'+article.article_author+'</span>\n' +
                    '</div>\n' +
                    '<span class="carousel-bg"></span>\n' +
                    '</div>\n' +
                    '<div class="article-msg">\n' +
                    '<a class="article-content" title="查看详情" href="/content?article_id='+article.article_id+'">\n' +
                    '<P class="ac1">\n' +
                    ''+article.des+'\n' +
                    '</P>\n' +
                    '</a>\n' +
                    '<span>\n' +
                    '<span href="" target="_blank" class="article-tags">\n' +
                    '<a><span class="tags">【哲学】</span></a>\n' +
                    '<a><span class="title">【动漫】</span></a>\n' +
                    '</span>\n' +
                    '<span class="article-info">\n' +
                    '<span class="identity"></span> <span class="time">'+article.create_time+'</span>\n' +
                    '<span class="look"> 共 <strong>n</strong> 人围观</span>\n' +
                    '</span>\n' +
                    '</span>\n' +
                    '</div>' +
                    '</div>\n' +
                    '</div>\n' +
                    '</div>\n';
                article_li += list;
            }
            $("#article").attr("name", title);
            $("#article").html(article_li);
            window.scrollTo(0,0);
        });
}