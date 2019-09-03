//获取根路径
function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
}

var rootPath = getRootPath();

function userInfoPage(pn) {
    allRevertPage(pn);
    noReadRevertPage(pn);
    readRevertPage(pn);
    allOrderPage(pn);
    readOrderPage(pn);
    noReadOrderPage(pn);
    allHistory(pn);
    haveNewHistory(pn);
}

function allRevertPage(pn) {
    $.ajax({
        url: rootPath + "allRevertInfo?pn=" + pn,
        type: "POST",
        success: function (result) {
            console.log(result.data)
        }
    });
}

function noReadRevertPage(pn) {
    $.ajax({
        url: rootPath + "noReadRevertInfo?pn=" + pn,
        type: "POST",
        success: function (result) {
            console.log(result.data)
        }
    });
}

function readRevertPage(pn) {
    $.ajax({
        url: rootPath + "readRevertInfo?pn=" + pn,
        type: "POST",
        success: function (result) {
            console.log(result.data)
        }
    });
}

function allOrderPage(pn) {
    $.ajax({
        url: rootPath + "allOrderInfo?pn=" + pn,
        type: "POST",
        success: function (result) {
            var parentDiv = $("#allorder").empty();
            var list = result.data.list;
            if (list != null && list.length > 0) {
                var pageInfo = "当前第<span id='userCurrent'>" + result.data.pageNum + "</span>页，总共" + result.data.pages + "页，总共" + result.data.total + "条记录";
                $("<h3 class='mytext-p3 mt5'></h3>").append(pageInfo).appendTo(parentDiv);
                $.each(list, function (index, item) {
                    var childrenDiv = $("<div class='user-item'></div>");
                    var img = $("<img src='' alt=''>").attr("src", "/images/comics/" + item.comic.root + "/cover.jpg");
                    var h1 = $("<h1 class=\"mytext-p3 center mt5\" style=\"font-size: 24px\"></h1>").append(item.comic.comicName);
                    var title = $(" <p class=\"mytext-p1 left  pOver\" style=\"font-size: 15px\"></p>").append("最新章节：" + item.comic.newChapterName);
                    var newTime = $(" <p class=\"mytext-p1 left \" style=\"font-size: 14px\"></p>").append("更新时间：" + item.comic.newUpdate);
                    var btn1 = $("<a  class= \"button btn btn-primary left mt5 ml15\">继续阅读</button>").attr("href", rootPath + "detail?id=" + item.comic.comicId);
                    var btn2 = $("<button type=\"button\" class=\"btn btn-danger left mt5 ml15 delMyOrder\">取消订阅</button>").attr("comic-id", item.comic.comicId);
                    childrenDiv.append(img).append(h1).append(title).append(newTime).append(btn1).append(btn2).appendTo(parentDiv);
                });
                parentDiv.append($("<div class=\"clearfix\"></div>"));
                var pageDiv = $("<div class=\"pagecontroller pagination-small pagination-center center\"></div>");
                var pageUl = $("<ul class=\"pagination\"></ul>");
                var first = $("<li></li>").append($("<a href='#'>&laquo;</a>"));
                first.appendTo(pageUl);
                first.on("click", function () {
                    allOrderPage(1);
                });
                for (var index=0;index<result.data.pages;index++){
                    var current = index+1;
                    var li = $("<li></li>").append($("<a></a>").append(current));
                    li.on("click", function () {
                        allOrderPage($(this).text());
                    });
                    li.appendTo(pageUl);
                };
                var last = $("<li><a href=\"#\">&raquo;</a></li>");
                last.on("click", function () {
                    allOrderPage(result.data.pages)
                });
                last.appendTo(pageUl);
                pageUl.appendTo(pageDiv);
                parentDiv.append(pageDiv);
            } else {
                parentDiv.append($("<h1 class=\"title-large\" style=\"margin:40px 0px;\">暂无数据</h1>"))
            }
        }
    });
}

function readOrderPage(pn) {
    $.ajax({
        url: rootPath + "readOrderInfo?pn=" + pn,
        type: "POST",
        success: function (result) {
            var parentDiv = $("#news").empty();
            var list = result.data.list;
            if (list != null && list.length > 0) {
                var pageInfo = "当前第<span id='userCurrent'>" + result.data.pageNum + "</span>页，总共" + result.data.pages + "页，总共" + result.data.total + "条记录";
                $("<h3 class='mytext-p3 mt5'></h3>").append(pageInfo).appendTo(parentDiv);
                $.each(list, function (index, item) {
                    var childrenDiv = $("<div class='user-item'></div>");
                    var img = $("<img src='' alt=''>").attr("src", "/images/comics/" + item.comic.root + "/cover.jpg");
                    var h1 = $("<h1 class=\"mytext-p3 center mt5\" style=\"font-size: 24px\"></h1>").append(item.comic.comicName);
                    var title = $(" <p class=\"mytext-p1 left pOver\" style=\"font-size: 15px\"></p>").append("最新章节：" + item.comic.newChapterName);
                    var newTime = $(" <p class=\"mytext-p1 left\" style=\"font-size: 14px\"></p>").append("更新时间：" + item.comic.newUpdate);
                    var btn1 = $("<a  class= \"button btn btn-primary left mt5 ml15\">继续阅读</button>").attr("href", rootPath + "detail?id=" + item.comic.comicId);
                    var btn2 = $("<button type=\"button\" class=\"btn btn-danger left mt5 ml15 delMyOrder\">取消订阅</button>").attr("comic-id", item.comic.comicId);
                    childrenDiv.append(img).append(h1).append(title).append(newTime).append(btn1).append(btn2).appendTo(parentDiv);
                });
                parentDiv.append($("<div class=\"clearfix\"></div>"));
                var pageDiv = $("<div class=\"pagecontroller pagination-small pagination-center center\"></div>");
                var pageUl = $("<ul class=\"pagination\"></ul>");
                var first = $("<li></li>").append($("<a href='#'>&laquo;</a>"));
                first.appendTo(pageUl);
                first.on("click", function () {
                    readOrderPage(1);
                });
                for (var index=0;index<result.data.pages;index++){
                    var current = index+1;
                    var li = $("<li></li>").append($("<a></a>").append(current));
                    li.on("click", function () {
                        readOrderPage($(this).text());
                    });
                    li.appendTo(pageUl);
                };
                var last = $("<li></li>").append($("<a href=\"#\">&raquo;</a>"));
                last.on("click", function () {
                    readOrderPage(result.data.pages)
                });
                last.appendTo(pageUl);
                pageUl.appendTo(pageDiv);
                parentDiv.append(pageDiv);
            } else {
                parentDiv.append($("<h1 class=\"title-large\" style=\"margin:40px 0px;\">暂无数据</h1>"))
            }
        }
    });
}

function noReadOrderPage(pn) {
    $.ajax({
        url: rootPath + "noReadOrderInfo?pn=" + pn,
        type: "POST",
        success: function (result) {
            var parentDiv = $("#noread").empty();
            var list = result.data.list;
            if (list != null && list.length > 0) {
                var pageInfo = "当前第<span id='userCurrent'>" + result.data.pageNum + "</span>页，总共" + result.data.pages + "页，总共" + result.data.total + "条记录";
                $("<h3 class='mytext-p3 mt5'></h3>").append(pageInfo).appendTo(parentDiv);
                $.each(list, function (index, item) {
                    var childrenDiv = $("<div class='user-item'></div>");
                    var img = $("<img src='' alt=''>").attr("src", "/images/comics/" + item.comic.root + "/cover.jpg");
                    var h1 = $("<h1 class=\"mytext-p3 center mt5\" style=\"font-size: 24px\"></h1>").append(item.comic.comicName);
                    var title = $(" <p class=\"mytext-p1 left pOver\" style=\"font-size: 15px\"></p>").append("最新章节：" + item.comic.newChapterName);
                    var newTime = $(" <p class=\"mytext-p1 left\" style=\"font-size: 14px\"></p>").append("更新时间：" + item.comic.newUpdate);
                    var btn1 = $("<a  class= \"button btn btn-primary left mt5 ml15\">继续阅读</button>").attr("href", rootPath + "detail?id=" + item.comic.comicId);
                    var btn2 = $("<button type=\"button\" class=\"btn btn-danger left mt5 ml15 delMyOrder\">取消订阅</button>").attr("comic-id", item.comic.comicId);
                    childrenDiv.append(img).append(h1).append(title).append(newTime).append(btn1).append(btn2).appendTo(parentDiv);
                });
                parentDiv.append($("<div class=\"clearfix\"></div>"));
                var pageDiv = $("<div class=\"pagecontroller pagination-small pagination-center center\"></div>");
                var pageUl = $("<ul class=\"pagination\"></ul>");
                var first = $("<li></li>").append($("<a href='#'>&laquo;</a>"));
                first.appendTo(pageUl);
                first.on("click", function () {
                    noReadOrderPage(1);
                });
                for (var index=0;index<result.data.pages;index++){
                    var current = index+1;
                    var li = $("<li></li>").append($("<a></a>").append(current));
                    li.on("click", function () {
                        noReadOrderPage($(this).text());
                    });
                    li.appendTo(pageUl);
                };
                var last = $("<li><a href=\"#\">&raquo;</a></li>");
                last.on("click", function () {
                    noReadOrderPage(result.data.pages)
                });
                last.appendTo(pageUl);
                pageUl.appendTo(pageDiv);
                parentDiv.append(pageDiv);
            } else {
                parentDiv.append($("<h1 class=\"title-large\" style=\"margin:40px 0px;\">暂无数据</h1>"))
            }
        }
    });
}

function allHistory(pn) {
    $.ajax({
        url: rootPath + "allHistoryInfo?pn=" + pn,
        type: "POST",
        success: function (result) {
            var parentDiv = $("#allHistory").empty();
            var list = result.data.list;
            if (list != null && list.length > 0) {
                var pageInfo = "当前第<span id='userCurrent'>" + result.data.pageNum + "</span>页，总共" + result.data.pages + "页，总共" + result.data.total + "条记录";
                $("<h3 class='mytext-p3 mt5'></h3>").append(pageInfo).appendTo(parentDiv);
                $.each(list, function (index, item) {
                    var childrenDiv = $("<div class='user-item'></div>");
                    var img = $("<img src='' alt=''>").attr("src", "/images/comics/" + item.comic.root + "/cover.jpg");
                    var h1 = $("<h1 class=\"mytext-p3 center mt5\" style=\"font-size: 24px\"></h1>").append(item.comic.comicName);
                    var title = $(" <p class=\"mytext-p1 left  pOver\" style=\"font-size: 15px\"></p>").append("阅读章节：" + item.detail.chapterName);
                    var newTime = $(" <p class=\"mytext-p1 left \" style=\"font-size: 14px\"></p>").append("阅读时间：" + item.lastReadTime);
                    var btn1 = $("<a  class= \"button btn btn-primary left mt5 ml15\">继续阅读</button>").attr("href", rootPath + "showDetail?id=" + item.detail.detailId);
                    var btn2 = $("<button type=\"button\" class=\"btn btn-danger left mt5 ml15 delMyOrder\">取消订阅</button>").attr("order-id", item.ordersId);
                    childrenDiv.append(img).append(h1).append(title).append(newTime).append(btn1).append(btn2).appendTo(parentDiv);
                });
                parentDiv.append($("<div class=\"clearfix \"></div>"));
                var pageDiv = $("<div class=\" pagecontroller pagination-small pagination-center center \"></div>");
                var pageUl = $("<ul class=\"pagination\"></ul>");
                var first = $("<li></li>").append($("<a href='#'>&laquo;</a>"));
                first.appendTo(pageUl);
                first.on("click", function () {
                    allHistory(1);
                });
                for (var index=0;index<result.data.pages;index++){
                    var current = index+1;
                    var li = $("<li></li>").append($("<a></a>").append(current));
                    li.on("click", function () {
                        allHistory($(this).text());
                    });
                    li.appendTo(pageUl);
                }
                var last = $("<li><a href=\"#\">&raquo;</a></li>");
                last.on("click", function () {
                    allHistory(result.data.pages)
                });
                last.appendTo(pageUl);
                pageUl.appendTo(pageDiv);
                parentDiv.append(pageDiv);
            } else {
                parentDiv.append($("<h1 class=\"title-large\" style=\"margin:40px 0px;\">暂无数据</h1>"))
            }
        }
    });
}

function haveNewHistory(pn) {
    $.ajax({
        url: rootPath + "haveNewHistoryInfo?pn=" + pn,
        type: "POST",
        success: function (result) {
            var parentDiv = $("#haveNews").empty();
            var list = result.data.list;
            if (list != null && list.length > 0) {
                var pageInfo = "当前第<span id='userCurrent'>" + result.data.pageNum + "</span>页，总共" + result.data.pages + "页，总共" + result.data.total + "条记录";
                $("<h3 class='mytext-p3 mt5'></h3>").append(pageInfo).appendTo(parentDiv);
                $.each(list, function (index, item) {
                    var childrenDiv = $("<div class='user-item'></div>");
                    var img = $("<img src='' alt=''>").attr("src", "/images/comics/" + item.comic.root + "/cover.jpg");
                    var h1 = $("<h1 class=\"mytext-p3 center mt5\" style=\"font-size: 24px\"></h1>").append(item.comic.comicName);
                    var title = $(" <p class=\"mytext-p1 left pOver\" style=\"font-size: 15px\"></p>").append("阅读章节：" + item.detail.chapterName);
                    var newTime = $(" <p class=\"mytext-p1 left \" style=\"font-size: 14px\"></p>").append("阅读时间：" + item.lastReadTime);
                    var btn1 = $("<a  class= \"button btn btn-primary left mt5 ml15\">继续阅读</button>").attr("href", rootPath + "showDetail?id=" + item.detail.detailId);
                    var btn2 = $("<button type=\"button\" class=\"btn btn-danger left mt5 ml15 delMyOrder\">删除历史</button>").attr("history-id", item.historyId);
                    childrenDiv.append(img).append(h1).append(title).append(newTime).append(btn1).append(btn2).appendTo(parentDiv);
                });
                parentDiv.append($("<div class=\"clearfix\"></div>"));
                var pageDiv = $("<div class=\"pagecontroller pagination-small pagination-center center\"></div>");
                var pageUl = $("<ul class=\"pagination\"></ul>");
                var first = $("<li></li>").append($("<a href='#'>&laquo;</a>"));
                first.appendTo(pageUl);
                first.on("click", function () {
                    haveNewHistory(1);
                });
                for (var index=0;index<result.data.pages;index++){
                    var current = index+1;
                    var li = $("<li></li>").append($("<a></a>").append(current));
                    li.on("click", function () {
                        haveNewHistory($(this).text());
                    });
                    li.appendTo(pageUl);
                };
                var last = $("<li><a href=\"#\">&raquo;</a></li>");
                last.on("click", function () {
                    haveNewHistory(result.data.pages)
                });
                last.appendTo(pageUl);
                pageUl.appendTo(pageDiv);
                parentDiv.append(pageDiv);
            } else {
                parentDiv.append($("<h1 class=\"title-large\" style=\"margin:40px 0px;\">暂无数据</h1>"))
            }
        }
    });
}

//初始加载
$(function () {
    userInfoPage(1);
});
