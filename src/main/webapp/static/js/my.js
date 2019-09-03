// 轮播图js
    $(function () {
        $(".smallpics li").mouseenter(function () {
            $(".big").attr("src",$(this).children("img").attr("src"));
        })
    });
// 轮播图js
// 分类列表选择案例
    $(function(){
        $("#book-list .list li").mouseenter(function(){
            $(this).find("div").addClass("list-select").addClass("up").parents("li").siblings().find("div").removeClass("list-select").removeClass("up")
        }).click(function () {
            $(this).find("div").addClass("list-active").parents("li").siblings().find("div").removeClass("list-active")
        });
        });
// 分类列表选择案例
//分类查找ajax
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
                $this.siblings(".likeIt").html("&nbsp;❤"+result.data);
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
                $this.siblings(".likeIt").text("喜欢 ❤ "+result.data);
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
})
//更改密码

