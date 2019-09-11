// 按钮控制js
    $(function () {
         var ImgIndex = 0;
         var ImgMax =  $(".smallpics li").length-1;
         function changeImg(){
             $(".big").attr("src",$(".smallpics li").eq(ImgIndex).children("img").attr("src"));
             $(".smallpics li").eq(ImgIndex).children("img").addClass("img-border");
             $(".smallpics li").eq(ImgIndex).siblings().children("img").removeClass("img-border");
         }
         changeImg();
         function leftAway(){
            $(".smallpics").stop().animate({left:"+=530px"})
        }
        function rightAway(){
            $(".smallpics").stop().animate({left:"-=530px"})
        }
        $(".preOne").click(function () {
            if (ImgIndex == 0){
                changeImg();
            }else {
                ImgIndex = ImgIndex-1;
                changeImg();
                if ((ImgIndex+1)%5==0){
                    leftAway();
                }
            }
        });
        $(".nextOne").click(function () {
            if (ImgIndex == ImgMax){
                ImgIndex = 0;
                changeImg();
                $(".smallpics").stop().animate({left:0});
            }else {
                ImgIndex = ImgIndex+1;
                changeImg();
                if (ImgIndex%5==0){
                    rightAway();
                }
            }
        });
        setInterval(function(){
            $(".nextOne").click();
        },5000);

        $(".preImg").click(function () {
            $("html,body").animate({scrollTop:300});
            if (parseInt($(".pic-item").css("left"))<0){
                $(".pic-item").stop().animate({left:"+=780px"})
            }else {
                alert("读者老爷，前面没有了哦！")
            }
        });

        $(".nextImg").click(function () {
            $("html,body").animate({scrollTop:300});
            if (parseInt($(".pic-item").css("left"))>($(".pic-item li").length-1)*-780){
                $(".pic-item").stop().animate({left:"-=780px"})
            }else {
                alert("读者老爷，到底了哦！")
            }
        });
    });
//搜索功能
$(function () {
    $("#main-menu .search input").focusin(function () {
        var form = $(this).parent().parent();
        $(this).keydown(function (e) {
            if (e.keyCode==13){
                var url = rootPath+"searchComic";
                form.attr("action",url);
                form.submit();
            }
        })
    });
    $(".search-field .search input").focusin(function () {
        var form = $(this).parent().parent();
        $(this).keydown(function (e) {
            if (e.keyCode==13){
                var url = rootPath+"searchDetail";
                form.attr("action",url);
                form.submit();
            }
        })
    });
    $("input.loop-search").click(function () {
        var url = rootPath+"searchDetail";
        $(".search-field .search").attr("action",url)
        form.submit();
    });
    $(document).on("keyup", function (e) {
        var code = e.keyCode;
        //left
        if (code==37) {
            $(".preImg").click();
        }
        //right
        if (code==39){
            $(".nextImg").click();
        }
    });
});

// 分类列表选择案例
    $(function(){
        $("#book-list .list li").mouseenter(function(){
            $(this).find("div").addClass("list-select").addClass("up").parents("li").siblings().find("div").removeClass("list-select").removeClass("up")
        }).click(function () {
            $(this).find("div").addClass("list-active").parents("li").siblings().find("div").removeClass("list-active")
        });
        });

 //按钮滑动案例
    $(function(){
        $(".leftAway").click(function () {
            //fixme 字符串转数字 自动切割掉了 px
            var scope = $(this).attr("bindScope");
            var length = $(this).attr("bindLength");
            if (parseInt($(scope).css("right"))>parseInt(length)){
                $(scope).stop().animate({right:"-="+length},500);
            }else {
                $(scope).stop().animate({right:4*length},200);
            }
        });
        $(".rightAway").click(function () {
            var scope = $(this).attr("bindScope");
            var length = $(this).attr("bindLength");
            if (parseInt($(scope).css("right"))<4*parseInt(length)){
                $(scope).stop().animate({right:"+="+length},500);
            }else {
                $(scope).stop().animate({right:0},200);
            }
        });
        //fixme 定时器 每4s执行一次 点击事件
        setInterval(function(){
            $(".rightAway").click();
        },4000);

        $(".changeOnce").click(function () {
            var scope = $(this).attr("bindScope");
            var length = $(this).attr("bingHeight");
            if (parseInt($(scope).css("bottom"))<2*parseInt(length)){
            $(scope).animate({bottom:"+="+length});
            }else {
                $(scope).animate({bottom:0});
            }
        });
    });
// 分类列表选择案例
//分类查找漫画ajax
$(function () {
    $("#yanQing").click(function () {
       listYanQing(1);
    });
    function listYanQing(pn){
        $.ajax({
            url:rootPath+"listYanQing?pn="+pn,
            method:"GET",
            success:function (result) {
                if (result.code==200){
                    var bookUl = $(".book-item").empty();
                    var pageControlUl = $(".pagination").empty();
                    var list = result.data.list;
                    $.each(list,function (indext,item){
                        var $li = $("<li></li>");
                        var a = $("<a class='inline-border'></a>").attr("href",rootPath+"detail?id="+item.comicId);
                        $("<img src='' alt=''>").attr("src", "/images/comics/" + item.root + "/cover.jpg").appendTo(a);
                        var info = $("<div class=\"info\"></div>");
                        var a2 = $("<a class=\"description center\"></a>").attr("href",rootPath+"detail?id="+item.comicId);
                        $("<h4 class=\"title mytext-title\"></h4>").append(item.comicName).appendTo(a2);
                        a2.appendTo(info);
                        $("<p class=\"author center mytext-p\"></p>").append(item.author).appendTo(info);
                        $("<p class=\"type center mytext-p\"></p>").append(item.type).appendTo(info);
                        $li.append(a).append(info).appendTo(bookUl);
                    });
                    var first = $("<li></li>").append($("<a href='#'>&laquo;</a>"));
                    first.appendTo(pageControlUl);
                    first.on("click", function () {
                        listYanQing(1);
                    });
                    for (var index=0;index<result.data.pages;index++){
                        var current = index+1;
                        var li = $("<li></li>").append($("<a></a>").append(current));
                        li.on("click", function () {
                            listYanQing($(this).text());
                        });
                        li.appendTo(pageControlUl);
                    }
                    var last = $("<li></li>").append($("<a href=\"#\">&raquo;</a>"));
                    last.on("click", function () {
                        listYanQing(result.data.pages)
                    });
                    last.appendTo(pageControlUl);
                }else {
                    var bookUl = $(".book-item").empty();
                    bookUl.append($("<h1 class=\"title-large\" style=\"margin:40px 0px;\">暂无数据</h1>"))
                }
            }
        })
    }
    $("#xuanHuan").click(function () {
       listXuanHuan(1);
    });
    function listXuanHuan(pn){
        $.ajax({
            url:rootPath+"listXuanHuan?pn="+pn,
            method:"GET",
            success:function (result) {
                if (result.code==200){
                    var bookUl = $(".book-item").empty();
                    var pageControlUl = $(".pagination").empty();
                    var list = result.data.list;
                    $.each(list,function (indext,item){
                        var $li = $("<li></li>");
                        var a = $("<a class='inline-border'></a>").attr("href",rootPath+"detail?id="+item.comicId);
                        $("<img src='' alt=''>").attr("src", "/images/comics/" + item.root + "/cover.jpg").appendTo(a);
                        var info = $("<div class=\"info\"></div>");
                        var a2 = $("<a class=\"description center\"></a>").attr("href",rootPath+"detail?id="+item.comicId);
                        $("<h4 class=\"title mytext-title\"></h4>").append(item.comicName).appendTo(a2);
                        a2.appendTo(info);
                        $("<p class=\"author center mytext-p\"></p>").append(item.author).appendTo(info);
                        $("<p class=\"type center mytext-p\"></p>").append(item.type).appendTo(info);
                        $li.append(a).append(info).appendTo(bookUl);
                    });
                    var first = $("<li></li>").append($("<a href='#'>&laquo;</a>"));
                    first.appendTo(pageControlUl);
                    first.on("click", function () {
                        listXuanHuan(1);
                    });
                    for (var index=0;index<result.data.pages;index++){
                        var current = index+1;
                        var li = $("<li></li>").append($("<a></a>").append(current));
                        li.on("click", function () {
                            listXuanHuan($(this).text());
                        });
                        li.appendTo(pageControlUl);
                    }
                    var last = $("<li></li>").append($("<a href=\"#\">&raquo;</a>"));
                    last.on("click", function () {
                        listXuanHuan(result.data.pages)
                    });
                    last.appendTo(pageControlUl);
                }else {
                    var bookUl = $(".book-item").empty();
                    bookUl.append($("<h1 class=\"title-large\" style=\"margin:40px 0px;\">暂无数据</h1>"))
                }
            }
        })
    }
    $("#duShi").on("click",function () {
        listDuShi(1)
    });
    function listDuShi(pn){
        $.ajax({
            url:rootPath+"listDuShi?pn="+pn,
            method:"GET",
            success:function (result) {
                if (result.code==200){
                    var bookUl = $(".book-item").empty();
                    var pageControlUl = $(".pagination").empty();
                    var list = result.data.list;
                    $.each(list,function (indext,item){
                        var $li = $("<li></li>");
                        var a = $("<a class='inline-border'></a>").attr("href",rootPath+"detail?id="+item.comicId);
                        $("<img src='' alt=''>").attr("src", "/images/comics/" + item.root + "/cover.jpg").appendTo(a);
                        var info = $("<div class=\"info\"></div>");
                        var a2 = $("<a class=\"description center\"></a>").attr("href",rootPath+"detail?id="+item.comicId);
                        $("<h4 class=\"title mytext-title\"></h4>").append(item.comicName).appendTo(a2);
                        a2.appendTo(info);
                        $("<p class=\"author center mytext-p\"></p>").append(item.author).appendTo(info);
                        $("<p class=\"type center mytext-p\"></p>").append(item.type).appendTo(info);
                        $li.append(a).append(info).appendTo(bookUl);
                    });
                    var first = $("<li></li>").append($("<a href='#'>&laquo;</a>"));
                    first.appendTo(pageControlUl);
                    first.on("click", function () {
                        listDuShi(1);
                    });
                    for (var index=0;index<result.data.pages;index++){
                        var current = index+1;
                        var li = $("<li></li>").append($("<a></a>").append(current));
                        li.on("click", function () {
                            listDuShi($(this).text());
                        });
                        li.appendTo(pageControlUl);
                    }
                    var last = $("<li></li>").append($("<a href=\"#\">&raquo;</a>"));
                    last.on("click", function () {
                        listDuShi(result.data.pages)
                    });
                    last.appendTo(pageControlUl);
                }else {
                    var bookUl = $(".book-item").empty();
                    bookUl.append($("<h1 class=\"title-large\" style=\"margin:40px 0px;\">暂无数据</h1>"))
                }
            }
        })
    }
    $("#maoXian").on("click",function () {
        listMaoXian(1);
    });
    function listMaoXian(pn){
        $.ajax({
            url:rootPath+"listMaoXian?pn="+pn,
            method:"GET",
            success:function (result) {
                if (result.code==200){
                    var bookUl = $(".book-item").empty();
                    var pageControlUl = $(".pagination").empty();
                    var list = result.data.list;
                    $.each(list,function (indext,item){
                        var $li = $("<li></li>");
                        var a = $("<a class='inline-border'></a>").attr("href",rootPath+"detail?id="+item.comicId);
                        $("<img src='' alt=''>").attr("src", "/images/comics/" + item.root + "/cover.jpg").appendTo(a);
                        var info = $("<div class=\"info\"></div>");
                        var a2 = $("<a class=\"description center\"></a>").attr("href",rootPath+"detail?id="+item.comicId);
                        $("<h4 class=\"title mytext-title\"></h4>").append(item.comicName).appendTo(a2);
                        a2.appendTo(info);
                        $("<p class=\"author center mytext-p\"></p>").append(item.author).appendTo(info);
                        $("<p class=\"type center mytext-p\"></p>").append(item.type).appendTo(info);
                        $li.append(a).append(info).appendTo(bookUl);
                    });
                    var first = $("<li></li>").append($("<a href='#'>&laquo;</a>"));
                    first.appendTo(pageControlUl);
                    first.on("click", function () {
                        listMaoXian(1);
                    });
                    for (var index=0;index<result.data.pages;index++){
                        var current = index+1;
                        var li = $("<li></li>").append($("<a></a>").append(current));
                        li.on("click", function () {
                            listMaoXian($(this).text());
                        });
                        li.appendTo(pageControlUl);
                    }
                    var last = $("<li></li>").append($("<a href=\"#\">&raquo;</a>"));
                    last.on("click", function () {
                        listMaoXian(result.data.pages)
                    });
                    last.appendTo(pageControlUl);
                }else {
                    var bookUl = $(".book-item").empty();
                    bookUl.append($("<h1 class=\"title-large\" style=\"margin:40px 0px;\">暂无数据</h1>"))
                }
            }
        })
    }
    $("#xiaoYuan").on("click",function () {
        listXiaoYuan(1);
    });
    function listXiaoYuan(pn){
        $.ajax({
            url:rootPath+"listXiaoYuan?pn="+pn,
            method:"GET",
            success:function (result) {
                if (result.code==200){
                    var bookUl = $(".book-item").empty();
                    var pageControlUl = $(".pagination").empty();
                    var list = result.data.list;
                    $.each(list,function (indext,item){
                        var $li = $("<li></li>");
                        var a = $("<a class='inline-border'></a>").attr("href",rootPath+"detail?id="+item.comicId);
                        $("<img src='' alt=''>").attr("src", "/images/comics/" + item.root + "/cover.jpg").appendTo(a);
                        var info = $("<div class=\"info\"></div>");
                        var a2 = $("<a class=\"description center\"></a>").attr("href",rootPath+"detail?id="+item.comicId);
                        $("<h4 class=\"title mytext-title\"></h4>").append(item.comicName).appendTo(a2);
                        a2.appendTo(info);
                        $("<p class=\"author center mytext-p\"></p>").append(item.author).appendTo(info);
                        $("<p class=\"type center mytext-p\"></p>").append(item.type).appendTo(info);
                        $li.append(a).append(info).appendTo(bookUl);
                    });
                    var first = $("<li></li>").append($("<a href='#'>&laquo;</a>"));
                    first.appendTo(pageControlUl);
                    first.on("click", function () {
                        listXiaoYuan(1);
                    });
                    for (var index=0;index<result.data.pages;index++){
                        var current = index+1;
                        var li = $("<li></li>").append($("<a></a>").append(current));
                        li.on("click", function () {
                            listXiaoYuan($(this).text());
                        });
                        li.appendTo(pageControlUl);
                    }
                    var last = $("<li></li>").append($("<a href=\"#\">&raquo;</a>"));
                    last.on("click", function () {
                        listXiaoYuan(result.data.pages)
                    });
                    last.appendTo(pageControlUl);
                }else {
                    var bookUl = $(".book-item").empty();
                    bookUl.append($("<h1 class=\"title-large\" style=\"margin:40px 0px;\">暂无数据</h1>"))
                }
            }
        })
    }
    $("#xianXia").on("click",function () {
        listXianXia(1);
    });
    function listXianXia(pn){
        $.ajax({
            url:rootPath+"listXianXia?pn="+pn,
            method:"GET",
            success:function (result) {
                if (result.code==200){
                    var bookUl = $(".book-item").empty();
                    var pageControlUl = $(".pagination").empty();
                    var list = result.data.list;
                    $.each(list,function (indext,item){
                        var $li = $("<li></li>");
                        var a = $("<a class='inline-border'></a>").attr("href",rootPath+"detail?id="+item.comicId);
                        $("<img src='' alt=''>").attr("src", "/images/comics/" + item.root + "/cover.jpg").appendTo(a);
                        var info = $("<div class=\"info\"></div>");
                        var a2 = $("<a class=\"description center\"></a>").attr("href",rootPath+"detail?id="+item.comicId);
                        $("<h4 class=\"title mytext-title\"></h4>").append(item.comicName).appendTo(a2);
                        a2.appendTo(info);
                        $("<p class=\"author center mytext-p\"></p>").append(item.author).appendTo(info);
                        $("<p class=\"type center mytext-p\"></p>").append(item.type).appendTo(info);
                        $li.append(a).append(info).appendTo(bookUl);
                    });
                    var first = $("<li></li>").append($("<a href='#'>&laquo;</a>"));
                    first.appendTo(pageControlUl);
                    first.on("click", function () {
                        listXianXia(1);
                    });
                    for (var index=0;index<result.data.pages;index++){
                        var current = index+1;
                        var li = $("<li></li>").append($("<a></a>").append(current));
                        li.on("click", function () {
                            listXianXia($(this).text());
                        });
                        li.appendTo(pageControlUl);
                    }
                    var last = $("<li></li>").append($("<a href=\"#\">&raquo;</a>"));
                    last.on("click", function () {
                        listXianXia(result.data.pages)
                    });
                    last.appendTo(pageControlUl);
                }else {
                    var bookUl = $(".book-item").empty();
                    bookUl.append($("<h1 class=\"title-large\" style=\"margin:40px 0px;\">暂无数据</h1>"))
                }
            }
        })
    }
    $("#gaoXiao").on("click",function () {
       listGaoXiao(1);
    });
    function listGaoXiao(pn){
        $.ajax({
            url:rootPath+"listGaoXiao?pn="+pn,
            method:"GET",
            success:function (result) {
                if (result.code==200){
                    var bookUl = $(".book-item").empty();
                    var pageControlUl = $(".pagination").empty();
                    var list = result.data.list;
                    $.each(list,function (indext,item){
                        var $li = $("<li></li>");
                        var a = $("<a class='inline-border'></a>").attr("href",rootPath+"detail?id="+item.comicId);
                        $("<img src='' alt=''>").attr("src", "/images/comics/" + item.root + "/cover.jpg").appendTo(a);
                        var info = $("<div class=\"info\"></div>");
                        var a2 = $("<a class=\"description center\"></a>").attr("href",rootPath+"detail?id="+item.comicId);
                        $("<h4 class=\"title mytext-title\"></h4>").append(item.comicName).appendTo(a2);
                        a2.appendTo(info);
                        $("<p class=\"author center mytext-p\"></p>").append(item.author).appendTo(info);
                        $("<p class=\"type center mytext-p\"></p>").append(item.type).appendTo(info);
                        $li.append(a).append(info).appendTo(bookUl);
                    });
                    var first = $("<li></li>").append($("<a href='#'>&laquo;</a>"));
                    first.appendTo(pageControlUl);
                    first.on("click", function () {
                        listGaoXiao(1);
                    });
                    for (var index=0;index<result.data.pages;index++){
                        var current = index+1;
                        var li = $("<li></li>").append($("<a></a>").append(current));
                        li.on("click", function () {
                            listGaoXiao($(this).text());
                        });
                        li.appendTo(pageControlUl);
                    }
                    var last = $("<li></li>").append($("<a href=\"#\">&raquo;</a>"));
                    last.on("click", function () {
                        listGaoXiao(result.data.pages)
                    });
                    last.appendTo(pageControlUl);
                }else {
                    var bookUl = $(".book-item").empty();
                    bookUl.append($("<h1 class=\"title-large\" style=\"margin:40px 0px;\">暂无数据</h1>"))
                }
            }
        })
    }
    $("#guoMan").on("click",function () {
        listGuoMan(1);
    });
    function listGuoMan(pn){
        $.ajax({
            url:rootPath+"listGuoMan?pn="+pn,
            method:"GET",
            success:function (result) {
                if (result.code==200){
                    var bookUl = $(".book-item").empty();
                    var pageControlUl = $(".pagination").empty();
                    var list = result.data.list;
                    $.each(list,function (indext,item){
                        var $li = $("<li></li>");
                        var a = $("<a class='inline-border'></a>").attr("href",rootPath+"detail?id="+item.comicId);
                        $("<img src='' alt=''>").attr("src", "/images/comics/" + item.root + "/cover.jpg").appendTo(a);
                        var info = $("<div class=\"info\"></div>");
                        var a2 = $("<a class=\"description center\"></a>").attr("href",rootPath+"detail?id="+item.comicId);
                        $("<h4 class=\"title mytext-title\"></h4>").append(item.comicName).appendTo(a2);
                        a2.appendTo(info);
                        $("<p class=\"author center mytext-p\"></p>").append(item.author).appendTo(info);
                        $("<p class=\"type center mytext-p\"></p>").append(item.type).appendTo(info);
                        $li.append(a).append(info).appendTo(bookUl);
                    });
                    var first = $("<li></li>").append($("<a href='#'>&laquo;</a>"));
                    first.appendTo(pageControlUl);
                    first.on("click", function () {
                        listGuoMan(1);
                    });
                    for (var index=0;index<result.data.pages;index++){
                        var current = index+1;
                        var li = $("<li></li>").append($("<a></a>").append(current));
                        li.on("click", function () {
                            listGuoMan($(this).text());
                        });
                        li.appendTo(pageControlUl);
                    }
                    var last = $("<li></li>").append($("<a href=\"#\">&raquo;</a>"));
                    last.on("click", function () {
                        listGuoMan(result.data.pages)
                    });
                    last.appendTo(pageControlUl);
                }else {
                    var bookUl = $(".book-item").empty();
                    bookUl.append($("<h1 class=\"title-large\" style=\"margin:40px 0px;\">暂无数据</h1>"))
                }
            }
        })
    }
    $("#riMan").on("click",function () {
        listRiMan(1)
    });
    function listRiMan(pn){
        $.ajax({
            url:rootPath+"listRiMan?pn="+pn,
            method:"GET",
            success:function (result) {
                if (result.code==200){
                    var bookUl = $(".book-item").empty();
                    var pageControlUl = $(".pagination").empty();
                    var list = result.data.list;
                    $.each(list,function (indext,item){
                        var $li = $("<li></li>");
                        var a = $("<a class='inline-border'></a>").attr("href",rootPath+"detail?id="+item.comicId);
                        $("<img src='' alt=''>").attr("src", "/images/comics/" + item.root + "/cover.jpg").appendTo(a);
                        var info = $("<div class=\"info\"></div>");
                        var a2 = $("<a class=\"description center\"></a>").attr("href",rootPath+"detail?id="+item.comicId);
                        $("<h4 class=\"title mytext-title\"></h4>").append(item.comicName).appendTo(a2);
                        a2.appendTo(info);
                        $("<p class=\"author center mytext-p\"></p>").append(item.author).appendTo(info);
                        $("<p class=\"type center mytext-p\"></p>").append(item.type).appendTo(info);
                        $li.append(a).append(info).appendTo(bookUl);
                    });
                    var first = $("<li></li>").append($("<a href='#'>&laquo;</a>"));
                    first.appendTo(pageControlUl);
                    first.on("click", function () {
                        listRiMan(1);
                    });
                    for (var index=0;index<result.data.pages;index++){
                        var current = index+1;
                        var li = $("<li></li>").append($("<a></a>").append(current));
                        li.on("click", function () {
                            listRiMan($(this).text());
                        });
                        li.appendTo(pageControlUl);
                    }
                    var last = $("<li></li>").append($("<a href=\"#\">&raquo;</a>"));
                    last.on("click", function () {
                        listRiMan(result.data.pages)
                    });
                    last.appendTo(pageControlUl);
                }else {
                    var bookUl = $(".book-item").empty();
                    bookUl.append($("<h1 class=\"title-large\" style=\"margin:40px 0px;\">暂无数据</h1>"))
                }
            }
        })
    }
    $("#qiTa").on("click",function () {
        listQiTa(1)
    });
    function listQiTa(pn){
        $.ajax({
            url:rootPath+"listQiTa?pn="+pn,
            method:"GET",
            success:function (result) {
                if (result.code==200){
                    var bookUl = $(".book-item").empty();
                    var pageControlUl = $(".pagination").empty();
                    var list = result.data.list;
                    $.each(list,function (indext,item){
                        var $li = $("<li></li>");
                        var a = $("<a class='inline-border'></a>").attr("href",rootPath+"detail?id="+item.comicId);
                        $("<img src='' alt=''>").attr("src", "/images/comics/" + item.root + "/cover.jpg").appendTo(a);
                        var info = $("<div class=\"info\"></div>");
                        var a2 = $("<a class=\"description center\"></a>").attr("href",rootPath+"detail?id="+item.comicId);
                        $("<h4 class=\"title mytext-title\"></h4>").append(item.comicName).appendTo(a2);
                        a2.appendTo(info);
                        $("<p class=\"author center mytext-p\"></p>").append(item.author).appendTo(info);
                        $("<p class=\"type center mytext-p\"></p>").append(item.type).appendTo(info);
                        $li.append(a).append(info).appendTo(bookUl);
                    });
                    var first = $("<li></li>").append($("<a href='#'>&laquo;</a>"));
                    first.appendTo(pageControlUl);
                    first.on("click", function () {
                        listQiTa(1);
                    });
                    for (var index=0;index<result.data.pages;index++){
                        var current = index+1;
                        var li = $("<li></li>").append($("<a></a>").append(current));
                        li.on("click", function () {
                            listQiTa($(this).text());
                        });
                        li.appendTo(pageControlUl);
                    }
                    var last = $("<li></li>").append($("<a href=\"#\">&raquo;</a>"));
                    last.on("click", function () {
                        listQiTa(result.data.pages)
                    });
                    last.appendTo(pageControlUl);
                }else {
                    var bookUl = $(".book-item").empty();
                    bookUl.append($("<h1 class=\"title-large\" style=\"margin:40px 0px;\">暂无数据</h1>"))
                }
            }
        })
    }
});

//分类查找日志ajax
$(function () {
     function buildLog(list){
        var parentDiv = $(".logItemDiv");
        $.each(list,function (indext,item){
            var itemDiv = $("<div class=\"article-blog grid-system log-item\"></div>");
            var dateDiv = $("<div class=\"grid comment-date w95\"></div>");
            var date1 = $("<div class=\"circle-area circle-inline-border settings-clr no-second\">");
            var dateInner = $("<div class=\"date-blog\"></div>");
            dateInner.append($("<p class=\"day\"></p>").append(item.updateTime.substring(9,10)));
            dateInner.append($("<p class=\"month\"></p>").append(item.updateTime.substring(5,7)));
            dateInner.append($("<p class=\"year\"></p>").append(item.updateTime.substring(0,4)));
            dateInner.appendTo(date1);
            var date2 = $("<div class=\"myData\"></div>");
            date2.append($("<a  class=\"comments\">113</a>"));
            date2.append($("<a  class=\"isLike  likeIt\"></a>").attr("detail-id",item.detailId).append('💙 '+item.likes));
            date2.append($("<a  class=\"isLike delLike hideIt\"></a>").attr("detail-id",item.detailId).append('💔 '+item.likes));
            dateDiv.append(date1).append(date2);

            var contentDiv = $("<div class=\"grid article-content w775\"></div>");
            var p1 = $("<p class=\"img\"></p>");
            var a = $("<a class=\"thumb-blog\"></a>").attr("href",rootPath+"/showDetail?id="+item.detailId);
            a.append($("<img class=\"inline-border\">").attr("src","/images/comics/"+item.comic.root+"/"+item.path+"/1.jpg"));
            a.appendTo(p1);
            var div2 =$("<div class='detailInfo'></div>");
            var h1 = $("<h2 class=\"mytext-title2 mb5\"></h2>").append(item.comic.comicName);
            var h2 = $("<h2 class=\"mytext-title mb25\"></h2>").append(item.chapterName);
            var p2 = $("<p class=\"blog-excerpt mytext-p2\"></p>").append(item.generalize);
            var p3 = $("<p class=\"blog-more\"></p>");
            var button = $("<a class=\"standart-button-style inline-border mln settings-clr\"></a>").attr("href",rootPath+"showDetail?id="+item.detailId);
            button.append($("<span class=\"over5\">查看更多</span>")).appendTo(p3);
            div2.append(h1).append(h2).append(p2).append(p3);
            contentDiv.append(p1).append(div2);
            var clearDiv = $("<div class=\"clear\"></div>");
            var line = $("<span class=\"dotted-line\">&nbsp;</span>");
            itemDiv.append(dateDiv).append(contentDiv).append(clearDiv).append(line).appendTo(parentDiv);
        });
    }
    $("ul.typeName li").click(function (){
        var type = $(this).find("a").text();
        logForType(type,1);
    });
    function logForType(type,pn){
        $.ajax({
            url:rootPath+"logForType",
            data:{"type":type,"pn":pn},
            method:"GET",
            success:function (result) {
                var logDiv = $(".logItemDiv").empty();
                var pageControlUl = $(".pagination").empty();
                if (result.code==200){
                    var list = result.data.list;
                    buildLog(list);
                    var first = $("<li></li>").append($("<a href='#'>&laquo;</a>"));
                    first.appendTo(pageControlUl);
                    first.on("click", function () {
                        logForType(type,1);
                    });
                    for (var index=0;index<result.data.pages;index++){
                        var current = index+1;
                        var li = $("<li></li>").append($("<a></a>").append(current));
                        li.on("click", function () {
                            logForType(type,$(this).text());
                        });
                        li.appendTo(pageControlUl);
                    }
                    var last = $("<li></li>").append($("<a href=\"#\">&raquo;</a>"));
                    last.on("click", function () {
                        logForType(type,result.data.pages)
                    });
                    last.appendTo(pageControlUl);
                }else {
                    logDiv.append($("<h1 class=\"title-large\" style=\"margin:40px 0px;\">暂无数据</h1>"))
                }
            }
        })
    }
    $("ul.dateTime li").click(function (){
       var time = $(this).find("a").attr("dataTime");
        logForTime(time,1);
    });
    function logForTime(time,pn){
        $.ajax({
            url:rootPath+"logForTime",
            data:{"time":time,"pn":pn},
            method:"GET",
            success:function (result) {
                console.log(result);
                var logDiv = $(".logItemDiv").empty();
                var pageControlUl = $(".pagination").empty();
                if (result.code==200){
                    var list = result.data.list;
                    buildLog(list);
                    var first = $("<li></li>").append($("<a href='#'>&laquo;</a>"));
                    first.appendTo(pageControlUl);
                    first.on("click", function () {
                        logForTime(time,1);
                    });
                    for (var index=0;index<result.data.pages;index++){
                        var current = index+1;
                        var li = $("<li></li>").append($("<a></a>").append(current));
                        li.on("click", function () {
                            logForTime(time,$(this).text());
                        });
                        li.appendTo(pageControlUl);
                    }
                    var last = $("<li></li>").append($("<a href=\"#\">&raquo;</a>"));
                    last.on("click", function () {
                        logForTime(time,result.data.pages)
                    });
                    last.appendTo(pageControlUl);
                }else {
                    logDiv.append($("<h1 class=\"title-large\" style=\"margin:40px 0px;\">暂无数据</h1>"))
                }
            }
        })
    }
});
//漫画项选择案例
    $(function () {
       $(".book-item li").mouseenter(function () {
           $(this).toggleClass("more-up")
       })
    });
//漫画项选择案例
// 点赞 订阅案例
$(function () {

    $("body").on("click",".orderIt",function () {
        var id = $(this).attr("comic-id");
        $this=$(this);
        $.ajax({
            url:rootPath+"userAddOrder?id="+id,
            method:"POST",
            success:function (result) {
                if (result.code==200){
                    $this.toggleClass("hideIt").siblings(".order").toggleClass("hideIt");
                    alert(result.msg);
                }else {
                    $this.toggleClass("hideIt").siblings(".order").toggleClass("hideIt");
                    alert(result.msg);
                }
            }
        })
    });
    $("body").on("click",".delOrder",function () {
        var id = $(this).attr("comic-id");
        $this=$(this);
        $.ajax({
            url:rootPath+"userDelOrder?id="+id,
            method:"POST",
            success:function (result) {
                if (result.code==200){
                    $this.toggleClass("hideIt").siblings(".order").toggleClass("hideIt");
                    alert(result.msg);
                }else {
                    $this.toggleClass("hideIt").siblings(".order").toggleClass("hideIt");
                    alert(result.msg);
                }
            }
        })
    });
    $("body").on("click","a.likeIt",function () {
        var id = $(this).attr("detail-id");
        $this=$(this);
        $.ajax({
            url:rootPath+"addDetailLike?id="+id,
            method:"POST",
            success:function (result) {
                alert(result.msg);
                $this.siblings(".delLike").text("💔 "+result.data);
                $this.toggleClass("hideIt").siblings(".isLike").toggleClass("hideIt");
            }
        })
    });
    $("body").on("click","a.delLike",function () {
        var id = $(this).attr("detail-id");
        $this=$(this);
        $.ajax({
            url:rootPath+"delDetailLike?id="+id,
            method:"POST",
            success:function (result) {
                alert(result.msg);
                $this.siblings(".likeIt").html("💖 "+result.data);
                $this.toggleClass("hideIt").siblings(".isLike").toggleClass("hideIt");
            }
        })
    });
    $("body").on("click","button.likeIt",function () {
        var id = $(this).attr("comic-id");
        $this=$(this);
        $.ajax({
            url:rootPath+"addComicLike?id="+id,
            method:"POST",
            success:function (result) {
                alert(result.msg);
                $this.siblings(".delLike").text("不喜欢 💔 "+result.data);
                $this.toggleClass("hideIt").siblings(".isLike").toggleClass("hideIt");
            }
        })
    });
    $("body").on("click","button.delLike",function () {
        var id = $(this).attr("comic-id");
        $this=$(this);
        $.ajax({
            url:rootPath+"delComicLike?id="+id,
            method:"POST",
            success:function (result) {
                alert(result.msg);
                $this.siblings(".likeIt").text("喜欢 💖 "+result.data);
                $this.toggleClass("hideIt").siblings(".isLike").toggleClass("hideIt");
            }
        })
    });
  });

// 点赞案例
// 个人中心信息列表选择案例
$(function(){
    $("#user-info .user-tab li").mouseenter(function(){
        $(this).addClass("userlist-select").siblings().removeClass("userlist-select")
    }).mouseleave(function(){
        $(this).removeClass("userlist-select")
    }).click(function () {
        $(this).addClass("userlist-active").siblings().removeClass("userlist-active")
    })
    });
// 个人中心信息列表选择案例
//获取根路径
function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
}
//获取根路径
//登录验证码切换
    function changeCode(){
        //得到图片元素
        var img = document.getElementById("checkCodeImg");
        var rootPath = getRootPath();
        // img.src = "${pageContext.request.contextPath}/code?time="+new Date().getTime();
        img.src = rootPath+"/code?date="+new Date().getTime();
    }
//登录验证码切换
//******************Ajax*********************
var rootPath = getRootPath();
//登陆注册按钮绑定
$(function () {
    $("#loginBtn").click( function loginUser(){
        $("#doLogin").click();
    });
    $("#registerBtn").click( function loginUser(){
        $("#doRegister").click();
    });
});
//登录注册按钮绑定
// 更改个人头像
$(function () {
    $("#change-info .info-head a").click(function(){
        $("#changeIcon").click();
    });
    $("#changeIcon").change(function () {
        var filePath = $(this).val();
        var fileType = filePath.substring(filePath.lastIndexOf("."));
        if (fileType == ".jpg" ||fileType == ".JPG") {
            $(".img").attr("src", URL.createObjectURL($(this)[0].files[0]));
            $("#changeImgModal").modal({
                backdrop: "static"
            });
        } else {
            var flag = confirm("上传图片格式不正确，请重新选择(.jpg)");
        }
    });
    $("#changeImgModal").find("#change").click(function(){
        if(confirm("确定上传此图片为新头像?")){
            $.ajax({
                url:rootPath+"changePhoto",
                type:"POST",
                data:new FormData($("#form-change-icon")[0]),
                async:false,
                cache:false,
                contentType:false,
                processData:false,
                success:function (result) {
                    if (result.code=="200"){
                        alert(result.msg);
                        $("#close").click();
                        window.location.reload();
                    }else {
                        alert(result.msg);
                        $("#close").click();
                        window.location.reload();
                    }
                }
            })
        }else{
            $("#close").click();
        }
    })
});
// 更改个人头像
//更改个人信息

    $("#change-info-btn").click(function () {
        $.ajax({
            url:rootPath+"updateUser",
            type:"POST",
            data:$("#form-change-info").serialize(),
            success:function (result) {
               if (result.code=="200"){
                alert(result.msg);
                   window.location.reload();
               }else {
                       alert(result.msg);
                       window.location.reload();
                   }
               }
        })
    });
//更改个人信息
//更改密码
$(function () {
    $("#CheckNewPassword").blur(function () {
    var newPassword = $("#newPassword").val();
        if (newPassword!=$("#CheckNewPassword").val()){
            alert("两次输入的密码不一致，请检查后重试")
        }else {
            $("#change-psw-btn").click(function () {
                $.ajax({
                    url:rootPath+"changePassword",
                    type:"POST",
                    data:$("#form-change-psw").serialize(),
                    success:function (result) {
                        if (result.code=="200"){
                            alert(result.msg);
                            window.location.href=rootPath+"index";
                        }else {
                            alert(result.msg);
                        }
                    }
                })
            })
        }
    })
});
//更改密码
//评论功能
//评论功能
$(function () {
    /*动漫评论*/
    $(".comicComment").on("click",".reply-button",function () {
        var revertId = $(this).attr("bind-id");
        $("#revertId").val(revertId);
        var comment = document.getElementById("comment");
        comment.focus();
    });
    $(".comicComment").on("click",".del-button",function () {
        var revertId = $(this).attr("bind-id");
        var comicId = $(this).attr("comic-id");
        if (confirm("确定要删除这条评论么？")){
            $.ajax({
                url:rootPath+"delUserComicComment?id="+revertId+"&&comicId="+comicId,
                method:"GET",
                success:function (result) {
                    if (result.code==200){
                        alert(result.msg);
                        flashComment(result.data.replyList)
                    }else {
                        alert(result.msg);
                    }
                }

            })
        }
    });

    function flashComment(replyList){
         $("#comicCommentForm").find("#revertId").val("");
        var comments = $(".comments-area").empty();
        $.each(replyList,function (index, item) {
            var comment = $(" <div class=\"comments-block commentDiv\"></div>");
            var $div = $("<div></div>");
            $("<h3 class=\"com-author\" ></h3>").append(item.user.userName).appendTo($div);
            $(" <p class=\"com-text mytext-p3\"></p>").append(item.content).appendTo($div);
            $("<small></small>").append(item.time).appendTo($div);
            if(userId!=item.user.userId){
                var revertBtn = $(" <a href=\"#\"  class=\"blue reply-button revert\"></a>").attr("bind-id",item.commentId) .append("<label for=\"comment\">回复</label>");
                revertBtn.appendTo($div)
            }else {
                var delBtn = $(" <a href=\"#\"  class=\"red del-button revert\"></a>").attr("bind-id",item.commentId).attr("comic-id",item.comicId).append("<label>删除</label>");
                delBtn.appendTo($div)
            };
            var userImg = $(" <a class=\"revert-user\"></a>");
            $("<img alt=\"\">").attr("src","/images/user/"+item.user.photo+".jpg").appendTo(userImg);
            userImg.appendTo($div);
            $div.appendTo(comment);
            $.each(item.replyList,function (index, item2) {
                var revert = $("<div class=\"comments-block mt25 reply-comment\"></div>");
                $("<h3 class=\"com-author\" ></h3>").append(item2.user.userName+":@"+item.user.userName).appendTo(revert);
                $(" <p class=\"com-text mytext-p3\"></p>").append(item2.content).appendTo(revert);
                $("<small></small>").append(item2.time).appendTo(revert);
                if(userId!=item2.user.userId){
                    var revertBtn = $(" <a href=\"#\"  class=\"blue reply-button revert\"></a>").attr("bind-id",item2.commentId) .append("<label for=\"comment\">回复</label>");
                    revertBtn.appendTo(revert)
                }else {
                    var delBtn = $(" <a href=\"#\"  class=\"red del-button revert\"></a>").attr("bind-id",item2.commentId).attr("comic-id",item.comicId).append("<label>删除</label>");
                    delBtn.appendTo(revert)
                };
                var userImg = $(" <a class=\"revert-user\"></a>");
                $("<img alt=\"\">").attr("src","/images/user/"+item2.user.photo+".jpg").appendTo(userImg);
                userImg.appendTo(revert);
                revert.appendTo(comment)
            });
            comment.appendTo(comments);
        })
    }

    $("#comic-comment").click(function () {
        if ($("#revertId").val()==""){
            var form = $("#comicCommentForm");
            $.ajax({
                url:rootPath+"addComicComment",
                method:"POST",
                data:form.serialize(),
                success:function (result) {
                    if (result.code==200){
                        console.log(result);
                        flashComment(result.data.replyList)
                    }
                }
            })
        }else {
            var form = $("#comicCommentForm");
            $.ajax({
                url:rootPath+"addComicRevert",
                method:"POST",
                data:form.serialize(),
                success:function (result) {
                    form[0].reset();
                    if (result.code==200){
                        console.log(result);
                        flashComment(result.data.replyList)
                    }
                }
            })
        }
    });

    /*章节评论*/
    $(".detailComment").on("click",".reply-button",function () {
        var revertId = $(this).attr("bind-id");
        $("#revertId").val(revertId);
        var comment = document.getElementById("comment");
        comment.focus();
    });
    $(".detailComment").on("click",".del-button",function () {
        var revertId = $(this).attr("bind-id");
        var detailId = $(this).attr("detail-id");
        if (confirm("确定要删除这条评论么？")){
            $.ajax({
                url:rootPath+"delUserDetailComment?id="+revertId+"&&detailId="+detailId,
                method:"GET",
                success:function (result) {
                    if (result.code==200){
                        alert(result.msg);
                        flashComment2(result.data.replyList)
                    }else {
                        alert(result.msg);
                    }
                }

            })
        }
    });

    function flashComment2(replyList){
        var comments = $(".comments-area").empty();
        $("#detailCommentForm").find("#revertId").val("");
        $.each(replyList,function (index, item) {
            var comment = $(" <div class=\"comments-block commentDiv\"></div>");
            var $div = $("<div></div>");
            $("<h3 class=\"com-author\" ></h3>").append(item.user.userName).appendTo($div);
            $(" <p class=\"com-text mytext-p3\"></p>").append(item.content).appendTo($div);
            $("<small></small>").append(item.time).appendTo($div);
            if(userId!=item.user.userId){
                var revertBtn = $(" <a    class=\"blue reply-button revert\"></a>").attr("bind-id",item.commentId) .append("<label for=\"comment\">回复</label>");
                revertBtn.appendTo($div)
            }else {
                var delBtn = $(" <a    class=\"red del-button revert\"></a>").attr("bind-id",item.commentId).attr("detail-id",item.detailId).append("<label>删除</label>");
                delBtn.appendTo($div)
            };
            var userImg = $(" <a class=\"revert-user\"></a>");
            $("<img alt=\"\">").attr("src","/images/user/"+item.user.photo+".jpg").appendTo(userImg);
            userImg.appendTo($div);
            $div.appendTo(comment);
            $.each(item.replyList,function (index, item2) {
                var revert = $("<div class=\"comments-block mt25 reply-comment\"></div>");
                $("<h3 class=\"com-author\" ></h3>").append(item2.user.userName+":@"+item.user.userName).appendTo(revert);
                $(" <p class=\"com-text mytext-p3\"></p>").append(item2.content).appendTo(revert);
                $("<small></small>").append(item2.time).appendTo(revert);
                if(userId!=item2.user.userId){
                    var revertBtn = $(" <a   class=\"blue reply-button revert\"></a>").attr("bind-id",item2.commentId) .append("<label for=\"comment\">回复</label>");
                    revertBtn.appendTo(revert)
                }else {
                    var delBtn = $(" <a    class=\"red del-button revert\"></a>").attr("bind-id",item2.commentId).attr("detail-id",item.detailId).append("<label>删除</label>");
                    delBtn.appendTo(revert)
                };
                var userImg = $(" <a class=\"revert-user\"></a>");
                $("<img alt=\"\">").attr("src","/images/user/"+item2.user.photo+".jpg").appendTo(userImg);
                userImg.appendTo(revert);
                revert.appendTo(comment)
            });
            comment.appendTo(comments);
        })
    }

    $("#detail-comment").click(function () {
        if ($("#revertId").val()==""){
            var form = $("#detailCommentForm");
            $.ajax({
                url:rootPath+"addDetailComment",
                method:"POST",
                data:form.serialize(),
                success:function (result) {
                    form[0].reset();
                    if (result.code==200){
                        console.log(result);
                        flashComment2(result.data.replyList)
                    }
                }
            })
        }else {
            var form = $("#detailCommentForm");
            $.ajax({
                url:rootPath+"addDetailRevert",
                method:"POST",
                data:form.serialize(),
                success:function (result) {
                    form[0].reset();
                    if (result.code==200){
                        console.log(result);
                        flashComment2(result.data.replyList)
                    }
                }
            })
        }
    });
});

