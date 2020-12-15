//页面加载
$('body').show();
$('.version').text(NProgress.version);
NProgress.start();
setTimeout(function() { NProgress.done(); $('.fade').removeClass('out'); }, 1000);
//从会话中获取信息
$(function () {
	$.get("/user/findUser.do",{},function (data) {
		var div = '';
		div = '<div id="search" class="sidebar-block search" role="search">\n' +
			'<h2 class="title"><strong>搜索</strong></h2>\n' +
			'<!-- 请注意这里是输入关键字进行模糊查询 -->\n' +
			'<form class="navbar-form" action="" method="post">\n' +
			'<div class="input-group">\n' +
			'<input type="text" class="form-control" size="35" placeholder="请输入关键字" id="input-search">\n' +
			'<span class="input-group-btn" id="btn-search">\n' +
			'<button class="btn btn-default btn-search" type="submit" onclick="searching()">搜索</button>\n' +
			'</span>\n' +
			'</div>\n' +
			'</form>\n' +
			'</div>';
		setInterval(function (){$("#right-nav-time").html(nowTime("ymdw"));},1000);
		if(null===data.user_id||undefined===data.user_id){
			$(".user-about").css("display","none");
			$("#user").css("display","none");
			$("#unuser").css("display","block");
			$("#search-box").html(div);
			$("#search").smartFloat();
		} else {
			$(".user-about").css("display","block");
			$("#user").css("display","block");
			$("#unuser").css("display","none");
			$("#user-png").attr("src",data.user_ico);
			$("#user-name-label").attr("title",data.user_des);
			$("#a_username").html(data.nickname);
			$("#a_user_des").html(data.user_des);
			$("#search-box").html(div);
			$("#search").smartFloat();
		}
	});
});

//时间展示函数
function nowTime(flag) {
	var d = new Date();
	var y = d.getFullYear();
	var m = d.getMonth()+1;
	var ds = d.getDate();
	if (ds <= 9) {
		ds = '0' + ds;
	}
	var h = d.getHours();
	if (h <= 9) {
		h = '0' + h;
	}
	var f = d.getMinutes();
	if (f <= 9) {
		f = '0' + f;
	}
	var s = d.getSeconds();
	if (s <= 9) {
		s = '0' + s;
	}
	var days = d.getDay();
	switch(days){
		case 1:
			days='星期一';
			break;
		case 2:
			days='星期二';
			break;
		case 3:
			days='星期三';
			break;
		case 4:
			days='星期四';
			break;
		case 5:
			days='星期五';
			break;
		case 6:
			days='星期六';
			break;
		case 0:
			days='星期日';
			break;
	}
	switch (flag) {
		case "ymd":
			return y+'年'+m+'月'+ds+'日 '+h+':'+f+':'+s;
			break;
		case "ymdhf":
			return y+'年'+m+'月'+ds+'日 '+h+':'+f+':'+s+' '+days;
			break;
		case "ymdhfs":
			return y+'年'+m+'月'+ds+'日 '+h+':'+f+':'+s+' '+days;
			break;
		case "ymdw":
			return y+'年'+m+'月'+ds+'日 '+days;
			break;
		case "mdhfs":
			return m+'月'+ds+'日 '+h+':'+f+':'+s;
			break;
		case "mdhfsw":
			return m+'月'+ds+'日 '+h+':'+f+':'+s+' '+days;
			break;
		default:
			return m+'月'+ds+'日';
	}
}
//调用时钟元素
$('#o-clock').on('click', function(){
	layer.open({
		type: 2,
		title: '噢克洛克',
		maxmin: true,
		shadeClose: true, //点击遮罩关闭层
		area : ['100%' , '100%'],
		content: 'clock.html'
	});
});
//返回顶部按钮
$(function(){
	$(window).scroll(function(){
		if($(window).scrollTop()>100){
			$(".gotop").fadeIn();	
		}
		else{
			$(".gotop").hide();
		}
	});
	$(".gotop").click(function(){
		$('html,body').animate({'scrollTop':0},500);
	});
});
//提示插件启用
$(function () {
  $('[data-toggle="popover"]').popover();
});
$(function () {
	$('[data-toggle="tooltip"]').tooltip();
});
//鼠标滑过显示 滑离隐藏
$(function(){
	$(".carousel").hover(function(){
		$(this).find(".carousel-control").show();
	},function(){
		$(this).find(".carousel-control").hide();
	});
});
$(function(){
	$(".hot-content ul li").hover(function(){
		$(this).find("h3").show();
	},function(){
		$(this).find("h3").hide();
	});
});
//页面元素智能定位
$.fn.smartFloat = function() {
	var position = function(element) {
		var top = element.position().top; //当前元素对象element距离浏览器上边缘的距离
		var pos = element.css("position"); //当前元素距离页面document顶部的距离
		$(window).scroll(function() { //侦听滚动时
			var scrolls = $(this).scrollTop();
			if (scrolls > top) { //如果滚动到页面超出了当前元素element的相对页面顶部的高度
				if (window.XMLHttpRequest) { //如果不是ie6
					element.css({ //设置css
						top: 0,//距离页面顶部为0
						position: "fixed"//固定定位,即不再跟随滚动
					}).addClass("shadow"); //加上阴影样式.shadow
				} else { //如果是ie6
					element.css({
						top: scrolls  //与页面顶部距离
					});
				}
			}else {
				element.css({ //如果当前元素element未滚动到浏览器上边缘，则使用默认样式
					position: pos,
					top: top
				}).removeClass("shadow");//移除阴影样式.shadow
			}
		});
	};
	return $(this).each(function() {
		position($(this));
	});
};
//异步加载更多内容这里的代码时点击查看更多而触发的时间
//所以并没有直接做交互的翻页的代码，因为只要后端响应就可以完成
jQuery("#pagination a").on("click", function ()
{
    var _url = jQuery(this).attr("href");
    var _text = jQuery(this).text();
    jQuery(this).attr("href", "javascript:viod(0);");
    jQuery.ajax(
    {
        type : "POST",
        url : _url,
        success : function (data)
        {
            //将返回的数据进行处理，挑选出class是news-list的内容块
            result = jQuery(data).find(".row .news-list");
            //newHref获取返回的内容中的下一页的链接地址
            nextHref = jQuery(data).find("#pagination a").attr("href");
            jQuery(this).attr("href", _url);
            if (nextHref != undefined)
            {
                jQuery("#pagination a").attr("href", nextHref);
            }
			else
            {
                jQuery("#pagination a").html("下一页没有了").removeAttr("href")
            }
            // 渐显新内容
            jQuery(function ()
            {
                jQuery(".row").append(result.fadeIn(300));
                jQuery("img").lazyload(
                {
                    effect : "fadeIn"
                });
            });
        }
    });
    return false;
});