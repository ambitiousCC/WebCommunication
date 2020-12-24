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
            $("#main-pages-nums").html(list);
            //列表数据展示
            var article_li = "";
            for (var i = 0; i < data.list.length; i++) {
                var article = data.list[i];
                //时间格转变！！
                article.create_time = timestampToTime(article.create_time);
                var list = "<div class=\"news-list\">\n" +
                    "                        <div class=\"news-img col-xs-5 col-sm-5 col-md-4\"><a href=\"/content?article_id="+article.article_id+"\">"+
                    "                            <img src=\""+article.article_img+"\" alt=\"文章图片\"> </a></div>\n" +
                    "                        <div class=\"news-info col-xs-7 col-sm-7 col-md-8\">\n" +
                    "                            <dl>\n" +
                    "                                <dt><a href=\"/content?article_id="+article.article_id+"\" target=\"_blank\">"+article.title+"</a></dt>\n" +
                    "                                <dd><span class=\"name\"><a href=\"\" title=\"作者信息\" rel=\"author\">"+article.article_author+"</a></span> <span\n" +
                    "                                        class=\"identity\"></span> <span class=\"time\">"+article.create_time+"</span></dd>\n" +
                    "                                <dd class=\"text\">"+article.des+"</dd>\n" +
                    "                            </dl>\n" +
                    "                        </div>\n" +
                    "                    </div>";
                article_li += list;
            }
            $("#article-list").attr("name", title);
            $("#article-list").html(article_li);
            window.scrollTo(0,0);
        });
}

setInterval(function () {
    checkImgSize();
},1000);

function checkImgSize() {
    $('.article-img').each(function() {
        var maxWidth = $('.article-list-box-inner').width();
        var maxWidth = 400; // 图片最大宽度
        var maxHeight = 200;    // 图片最大高度
        var ratio = 0;  // 缩放比例
        var width = $(this).width();    // 图片实际宽度
        var height = $(this).height();  // 图片实际高度

        // 检查图片是否超宽
        if(width > maxWidth){
            ratio = maxWidth / width;   // 计算缩放比例
            $(this).css("width", maxWidth); // 设定实际显示宽度
            // height = height * ratio;    // 计算等比例缩放后的高度
            // $(this).css("height", height);  // 设定等比例缩放后的高度
        }

        // 检查图片是否超高
        if(height > maxHeight){
            ratio = maxHeight / height; // 计算缩放比例
            $(this).css("height", maxHeight);   // 设定实际显示高度
            // width = width * ratio;    // 计算等比例缩放后的高度
            // $(this).css("width", width * ratio);    // 设定等比例缩放后的高度
        }
    });
}