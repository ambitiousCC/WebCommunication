//创建全局对象
var editor = null;
//编辑器部分
//验证登录才能写文
$(function () {
    $('input').labelauty();
    loadUse();
});
function loadUse() {
    $.get("/user/findUser.do", {}, function (data) {
        if (data.user_id === null||undefined===data.user_id) {
            //未登录提醒并隐藏编辑器
            $("#text-container").css("display","none");
            $("#text-tips").css("display","none");
            $("#text-un-tips").css("display","block");
        } else {
            //登录以后才会有编辑器
            //并隐藏token值
            $("#text-un-tips").css("display","none");
            $("#text-container").css("display","block");
            $("#text-tips").css("display","block");
            loadKey(editor);
            checkLength();
            var k;
            loadBtns();
            var des = $("#text-des-input").val();
            if (des === "") {
                k = setInterval(function () {layer.tips('这里是写摘要的地方哦！', "#art-des-btn");},10000);
            } else {
                clearInterval(k);
            }
        }
    });
}

function loadKey(editor){
    $.get("/content/art/editor.do",{},function (data) {
        $("#text-user").val(data);
        _init_editor(editor);
    });
}

function checkLength() {
    var i,j;
    $("#text-title").focus(function () {
        i = setInterval(function () {
            var titleLength = $("#text-title").val().length;
            $("#title-count").html(titleLength);
        });
    });
    $("#text-title").blur(function () {
        clearInterval(i);
    });
    $(document).on('focus',"#text-des",function () {
        j = setInterval(function () {
            var titleLength = $("#text-des").val().length;
            $("#tip-count").html(titleLength);
        });
    });
    $(document).on('blur',"#text-des",function () {
        clearInterval(j);
    });
}
function loadBtns() {
    //弹出一个页面层
    $('#art-des-btn').on('click', function(){
        $("#art-post-btn").removeAttr("disabled");
        if (document.body.clientWidth > 767) {
            layer.open({
                type: 1,
                area: ['620px', '200px'],
                shadeClose: true, //点击遮罩关闭
                content: '\<\p class="tips-title" id="tips-title"></p>' +
                    '<div id="text-des-box" style="">' +
                    '<input type="text" id="text-des" maxlength="100" placeholder="文章内容摘要(选填)" />' +
                    '<span class="tip-max-length"><span id="tip-count">0</span>/100</span>' +
                    '\<\/div>',
                btn:["确定","关闭"],
                yes:function(){
                    layer.msg('保存成功', {icon: 1});
                    var text = $("#text-des").val().replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&#039;");
                    var filterers = filterXSS(text);
                    parent.$("#text-des-input").val(filterers);
                }
            });
        } else {
            layer.open({
                type: 1,
                area: ['100%', '100%'],
                shadeClose: true, //点击遮罩关闭
                content: '\<\p class="tips-title" id="tips-title"></p>' +
                    '<div id="text-des-box" style="">' +
                    '<input type="text" id="text-des" maxlength="100" placeholder="文章内容摘要(选填)" />' +
                    '<span class="tip-max-length"><span id="tip-count">0</span>/100</span>' +
                    '\<\/div>',
                btn:["确定","关闭"],
                yes:function(){
                    layer.msg('保存成功', {icon: 1});
                    var text = $("#text-des").val().replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&#039;");
                    var filterers = filterXSS(text);
                    parent.$("#text-des-input").val(filterers);
                }
            });
        }
        var des = $("#text-des-input").val();
        if (des === "") {
            $("#tips-title").html("填写摘要：注意哦，关闭了不会保存草稿哦，可以先复制粘贴到自家记事本上φ(>ω<*) ");
        } else {
            $("#tips-title").html("俺没有呼你，您的摘要|ू･ω･` )给~");
            $("#text-des").val(des);
        }
    });
}

/**
 .replace(/&/g, "&amp;")
 .replace(/</g, "&lt;")
 .replace(/>/g, "&gt;")
 .replace(/"/g, "&quot;")
 .replace(/'/g, "&#039;")
 */
$("#uploadForm").on("change","input[type='file']",function() {
    var filePath=$(this).val();
    if(filePath.indexOf("jpg")!=-1 || filePath.indexOf("png")!=-1|| filePath.indexOf("jpeg")!=-1){
        var arr=filePath.split('\\');
        var fileName=arr[arr.length-1];
        $("#inputImgName").val(fileName);
    }else{
        $("#inputImgName").val("");
        return false
    }

    const file = document.getElementById('reportXML');
    const fileObj = file.files[0];
    const windowURL = window.URL || window.webkitURL;
    const img = document.getElementById('previewImg');
    if(file && fileObj) {
        const dataURl = windowURL.createObjectURL(fileObj);
        img.setAttribute('src',dataURl);
    }
});
$("#srcImg").on('click',function () {
    layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        area: '768px',
        skin: 'layui-layer-nobg', //没有背景色
        shadeClose: true,
        content: $('#photo')
    });
});
//改变图片尺寸的思路
function changeSize() {
    var maxWidth = 576; // 图片最大宽度
    var maxHeight = 324;    // 图片最大高度
    var ratio = 0;  // 缩放比例
    var width = $(this).width();    // 图片实际宽度
    var height = $(this).height();  // 图片实际高度

    // 检查图片是否超宽
    if(width > maxWidth){
        ratio = maxWidth / width;   // 计算缩放比例
        $(this).css("width", maxWidth); // 设定实际显示宽度
        height = height * ratio;    // 计算等比例缩放后的高度
        $(this).css("height", height);  // 设定等比例缩放后的高度
    }

    // 检查图片是否超高
    if(height > maxHeight){
        ratio = maxHeight / height; // 计算缩放比例
        $(this).css("height", maxHeight);   // 设定实际显示高度
        width = width * ratio;    // 计算等比例缩放后的高度
        $(this).css("width", width * ratio);    // 设定等比例缩放后的高度
    }
}
//主体部分！！！！：获得文章内容
$(document).on('click','#art-post-btn',function () {
        var title = $("#text-title").val();
        if (title === "" || title == null) {
            //没有填标题
            alert("请填写文章标题");
            return ;
        } else {
            //判断是否有内容
            var content = editor.txt.html().replace(/'/g, "&#039;");
            //虽然很强大，但是会把编辑器的所有样式弄没！！！
            var filterHtml = filterXSS(content);
            if (filterHtml === "" || filterHtml == null) {
                alert("请填写文章内容");
            } else {
                //填写了标题、文章
                //获取选择的参数
                var checkType = $("input[name='radio-class']:checked").val();
                var checkProp = $("input[name='radio-value']:checked").val();
                //关于类别
                /*
                   11 12 13 21 22 23 31 32 33代表了九种基本文章，
                    十位是文章基本类型，个位是公开不公开
                 */
                if (checkType === "" || checkType == null || checkProp === "" || checkProp == null) {
                    //没有选择
                    alert("文章必选属性不能为空");
                    return false;
                } else {
                    //完事具备
                    saveArticle(checkType,checkProp,title,content);
                }
            }
        }
});

function loadPreImg(title,des,content,tid,pid){
    //获取上传的图片信息的
        var key = $("#text-user").val();
        var photo = $("#reportXML")[0].files[0];
        var params = new FormData();
        params.append('file',photo);
        $.ajax({
            url:"/content/art/saveHeadImg.do?token="+key,
            type:"post",
            data:params,
            cache: false,
            contentType: false,
            processData: false,
            success:function (data) {
                if (data.error === 0) {
                    //获取上交成功的封面url
                    //兼容无封面的情况：只有恶意上传才返回error=1
                    var preImgURL = data.url;
                    // 上传数据
                    saveArticleAllInfo(preImgURL,title,des,content,tid,pid,key);
                } else {
                    alert("上传失败");
                }
            }
        });
}
function saveArticle(tid,pid,title,content) {
    //获取摘要，如果没有自动生成
    var desText =editor.txt.text();
    var des = $("#text-des-input").val();
    if (des===""||des===null||des===undefined) {
        //自动生成
        if (desText.length > 100) {
            des = desText.substring(0,101);
        } else {
            des = desText;
        }
    }
    loadPreImg(title,des,content,tid,pid);
}

function saveArticleAllInfo(url, title, des, content, tid, pid,key) {
    $.post("/content/art/saveArticle.do",{
        previewURL:url,
        title:title,
        des:des,
        content:content,
        tid:tid,
        pid:pid,
        key:key
    },function (data) {
        if (data) {
            alert("发表成功");
            location.href="/globe";
            return true;
        }else{
            alert("上传失败，服务器异常,请保存草稿!");
            return false;
        }
    })
}

//创建富文本编辑器
function _init_editor() {
    var E = window.wangEditor;
    editor = new E('#editor');
    //自定义标签
    editor.customConfig.menus = [
        'head',  // 标题
        'bold',  // 粗体
        'fontSize',  // 字号
        'fontName',  // 字体
        'italic',  // 斜体
        'underline',  // 下划线
        'strikeThrough',  // 删除线
        'foreColor',  // 文字颜色
        'backColor',  // 背景颜色
        'link',  // 插入链接
        'list',  // 列表
        'justify',  // 对齐方式
        'quote',  // 引用
        'emoticon',  // 表情
        'image',  // 插入图片
        'table',  // 表格
        'code',  // 插入代码
        'undo',  // 撤销
        'redo'  // 重复
    ];
    //开启debug模式！
    editor.customConfig.debug = location.href.indexOf('wangeditor_debug_mode=1') > 0;
    //用户自己使用js添加内容是不会触发onchange函数的
    editor.customConfig.onchange = function (html) {
        // html 即变化之后的内容
        console.log(html)
    }
    // 打开粘贴样式的过滤
    editor.customConfig.pasteFilterStyle =true ;
    // 忽略粘贴内容中的图片
    editor.customConfig.pasteIgnoreImg = true;
    // 自定义处理粘贴的文本内容
    editor.customConfig.pasteTextHandle = function (content) {
        // content 即粘贴过来的内容（html 或 纯文本），可进行自定义处理然后返回
        return content + '<p>-----------------------------关于粘贴文本的说明-------------------------------------------</p>' +
            '<p>已经关闭了粘贴内容的样式，注意粘贴图片请下载以后自行插入</p>'
    };
    // 表情面板可以有多个 tab ，因此要配置成一个数组。数组每个元素代表一个 tab 的配置
    editor.customConfig.emotions = [
        {
            // tab 的标题
            title: '默认',
            // type -> 'emoji' / 'image'
            type: 'image',
            // content -> 数组
            content: [
                {
                    alt: '[坏笑]',
                    src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/50/pcmoren_huaixiao_org.png'
                },
                {
                    alt: '[舔屏]',
                    src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/40/pcmoren_tian_org.png'
                }
            ]
        },
        {
            title: '拓展',
            type: 'image',
            content: [
                {
                    alt:'[喵喵]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/7b/2018new_miaomiao_org.png'
                },
                {
                    alt:'[doge]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/a1/2018new_doge02_org.png'
                },
                {
                    alt:'[爱你]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/f6/2018new_aini_org.png'
                },
                {
                    alt:'[允悲]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/83/2018new_kuxiao_org.png'
                },
                {
                    alt:'[悲伤]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/ee/2018new_beishang_org.png'
                },
                {
                    alt:'[吃惊]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/49/2018new_chijing_org.png'
                },
                {
                    alt:'[偷笑]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/71/2018new_touxiao_org.png'
                },
                {
                    alt:'[疑问]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/b8/2018new_ningwen_org.png'
                },
                {
                    alt:'[右哼哼]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c1/2018new_youhengheng_org.png'
                },
                {
                    alt:'[互粉]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/86/2018new_hufen02_org.png'
                },
                {
                    alt:'[顶]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/ae/2018new_ding_org.png'
                },
                {
                    alt:'[污]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/10/2018new_wu_org.png'
                },
                {
                    alt:'[害羞]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c1/2018new_haixiu_org.png'
                },
                {
                    alt:'[可怜]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/96/2018new_kelian_org.png'
                },
                {
                    alt:'[失望]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/aa/2018new_shiwang_org.png'
                },
                {
                    alt:'[生病]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/3b/2018new_shengbing_org.png'
                },
                {
                    alt:'[憧憬]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c9/2018new_chongjing_org.png'
                },
                {
                    alt:'[黑线]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/a3/2018new_heixian_org.png'
                },
                {
                    alt:'[感冒]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/40/2018new_kouzhao_org.png'
                },
                {
                    alt:'[亲亲]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/2c/2018new_qinqin_org.png'
                },
                {
                    alt:'[并不简单]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/aa/2018new_bingbujiandan_org.png'
                },
                {
                    alt:'[晕]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/07/2018new_yun_org.png'
                },
                {
                    alt:'[吃瓜]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/01/2018new_chigua_org.png'
                },
                {
                    alt:'[打脸]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/cb/2018new_dalian_org.png'
                },
                {
                    alt:'[可爱]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/09/2018new_keai_org.png'
                },
                {
                    alt:'[汗]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/28/2018new_han_org.png'
                },
                {
                    alt:'[笑而不语]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/2d/2018new_xiaoerbuyu_org.png'
                },
                {
                    alt:'[馋嘴]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/fa/2018new_chanzui_org.png'
                },
                {
                    alt:'[抓狂]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/17/2018new_zhuakuang_org.png'
                },
                {
                    alt:'[太开心]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/1e/2018new_taikaixin_org.png'
                },
                {
                    alt:'[坏笑]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/4d/2018new_huaixiao_org.png'
                },
                {
                    alt:'[吐]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/08/2018new_tu_org.png'
                },
                {
                    alt:'[色]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/9d/2018new_huaxin_org.png'
                },
                {
                    alt:'[微笑]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/e3/2018new_weixioa02_org.png'
                },
                {
                    alt:'[笑cry]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/4a/2018new_xiaoku_thumb.png'
                },
                {
                    alt:'[酷]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c4/2018new_ku_org.png'
                },
                {
                    alt:'[衰]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/a2/2018new_shuai_org.png'
                },
                {
                    alt:'[哼]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/7c/2018new_heng_org.png'
                },
                {
                    alt:'[思考]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/30/2018new_sikao_org.png'
                },
                {
                    alt:'[怒]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/f6/2018new_nu_org.png'
                },
                {
                    alt:'[鼓掌]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6e/2018new_guzhang_org.png'
                },
                {
                    alt:'[钱]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/a2/2018new_qian_org.png'
                },
                {
                    alt:'[困]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/3c/2018new_kun_org.png'
                },
                {
                    alt:'[舔屏]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/3e/2018new_tianping_org.png'
                },
                {
                    alt:'[拜拜]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/fd/2018new_baibai_org.png'
                },
                {
                    alt:'[嘘]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/b0/2018new_xu_org.png'
                },
                {
                    alt:'[左哼哼]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/43/2018new_zuohengheng_org.png'
                },
                {
                    alt:'[草泥马]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/3b/2018new_caonima_org.png'
                },
                {
                    alt:'[求关注]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/ac/lxhqiuguanzhu_org.gif'
                },
                {
                    alt:'[猪头]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/1c/2018new_zhutou_thumb.png'
                },
                {
                    alt:'[微风]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c7/2018new_weifeng_thumb.png'
                },
                {
                    alt:'[羞嗒嗒]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/df/lxhxiudada_org.gif'
                },
                {
                    alt:'[给力]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/36/2018new_geili_org.png'
                },
                {
                    alt:'[下雨]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/7e/2018new_yu_org.png'
                },
                {
                    alt:'[蜡烛]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/16/2018new_lazhu_org.png'
                },
                {
                    alt:'[炸鸡啤酒]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/e2/zhajibeer_org.gif'
                },
                {
                    alt:'[太阳]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/cd/2018new_taiyang_org.png'
                },
                {
                    alt:'[最右]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/be/remen_zuiyou180605_org.png'
                },
                {
                    alt:'[干杯]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/40/2018new_ganbei_org.png'
                },
                {
                    alt:'[笑哈哈]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/32/lxhwahaha_org.gif'
                },
                {
                    alt:'[好爱哦]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/74/lxhainio_org.gif'
                },
                {
                    alt:'[拳头]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/86/2018new_quantou_org.png'
                },
                {
                    alt:'[好喜欢]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/d6/lxhlike_thumb.gif'
                },
                {
                    alt:'[奥特曼]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/c6/2018new_aoteman_org.png'
                },
                {
                    alt:'[沙尘暴]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/b7/2018new_shachenbao_org.png'
                },
                {
                    alt:'[赞啊]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/00/lxhzan_thumb.gif'
                },
                {
                    alt:'[蛋糕]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/f9/2018new_dangao_org.png'
                },
                {
                    alt:'[肥皂]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/d6/2018new_feizao_thumb.png'
                },
                {
                    alt:'[飞机]',
                    src:'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/4a/2018new_feiji_org.png'
                }
            ]
        },
        {
            // tab 的标题
            title: 'emoji',
            // type -> 'emoji' / 'image'
            type: 'emoji',
            // content -> 数组
            content: ['😀', '😃', '😄', '😁', '😆','']
        }
    ];

    //失焦事件
    editor.customConfig.onblur = function (html) {
        // html 即编辑器中的内容
        //可以实现自动保存草稿的提示
        console.log('onblur', html);
    };

    //将图片存储到服务器
    editor.customConfig.uploadImgServer = '/content/art/saveImg.do';
    //配置上传功能
    // 将图片大小限制为 3M
    editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024;
    // 限制一次最多上传 5 张图片
    editor.customConfig.uploadImgMaxLength = 5;
    const key = $("#text-user").val();
    editor.customConfig.uploadImgParams = {
        // 如果版本 <=v3.1.0 ，属性值会自动进行 encode ，此处无需 encode
        // 如果版本 >=v3.1.1 ，属性值不会自动 encode ，如有需要自己手动 encode
        //也就是验证的功能，防止非法上传
        //思路是，每一次刷新页面都会从后端传入一个uuid的值保存到某一个隐藏域中，前段向后端发送请求必须一致
        token: key
    };
    //自定义上传文件的名称
    editor.customConfig.uploadFileName = 'photo';
    //将token值拼接到url链接
    editor.customConfig.uploadImgParamsWithUrl = true;
    // 将 timeout 时间改为 5s
    editor.customConfig.uploadImgTimeout = 5000;

    //监听操作
    editor.customConfig.uploadImgHooks = {
        before: function (xhr, editor, files) {
            // 图片上传之前触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，files 是选择的图片文件

            // 如果返回的结果是 {prevent: true, msg: 'xxxx'} 则表示用户放弃上传
            // return {
            //     prevent: true,
            //     msg: '放弃上传'
            // }
        },
        success: function (xhr, editor, result) {
            // 图片上传并返回结果，图片插入成功之后触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
            //上传成功时，跳转浏览？
        },
        fail: function (xhr, editor, result) {
            // 图片上传并返回结果，但图片插入错误时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
        },
        error: function (xhr, editor) {
            // 图片上传出错时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
        },
        timeout: function (xhr, editor) {
            // 图片上传超时时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
        },

        // 如果服务器端返回的不是 {errno:0, data: [...]} 这种格式，可使用该配置
        // （但是，服务器端返回的必须是一个 JSON 格式字符串！！！否则会报错）
        customInsert: function (insertImg, result, editor) {
            // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
            // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果

            // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
            var url = result.url;
            insertImg(url)

            // result 必须是一个 JSON 格式字符串！！！否则报错
        }
    };

    editor.create();

    setInterval(function () {
        // 如果未配置 editor.customConfig.onchange，则 editor.change 为 undefined
        //此处是动态配置变化
        editor.change && editor.change()
    },100);
}