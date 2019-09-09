//获取根路径
function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
}

var rootPath = getRootPath();
//图片上传判定
$(function () {
    //单个文件上传
    $(".newInputIcon").change(function () {
        var filePath = $(this).val();
        var fileType = filePath.substring(filePath.lastIndexOf("."));
        if (fileType == ".jpg" || fileType == ".JPG") {
            $(this).parent().siblings("img").attr("src", URL.createObjectURL($(this)[0].files[0]));
        } else {
            var flag = confirm("上传图片格式不正确，请重新选择(.jpg)");
        }
    });
// 批量上传
    $(".inputSpace .newInputPath").change(function () {
        var files = this.files;
        var show = $(this).parents(".inputSpace").find(".showImg");
        show.empty();
        for (var i = 0; i < files.length; i++) {
            var img = $("<img class='img'/>").addClass("img");
            img.attr("src", URL.createObjectURL(files[i]));
            img.appendTo(show);
        }
    });
});
//tabs控制
$(function () {
    $(".admin-tab li").on("click", function () {
        $(this).addClass("left_active").siblings().removeClass("left_active").removeClass("admin-active");
    });
    $(".admin-tab li").mouseenter(function () {
        $(this).addClass("admin-active").siblings().removeClass("admin-active")
    }).mouseleave(function () {
        $(this).removeClass("admin-active");
    });
    // 按钮控制宽度
    $(".list-btn").click(function () {
        $(".admin-tabs").toggleClass("short");
        $(".admin-tabs .admin-tab").toggleClass("short");
        $(".admin-tabs .admin-tab li").toggleClass("short");
        $("#admin-content").toggleClass("long").toggleClass("wd80")
    });
});

//选择事件
//选择事件 选择被选中的checkbox的值进行拼接
$(function () {
    $("#add_typeCheck input").click(function () {
        var types = [];
        if ($("#add_typeCheck input:checked").length <= 3) {
            //遍历选中的内容 取值
            $("#add_typeCheck input:checkbox").each(function () {
                if ($(this).prop("checked") == true) {
                    types.push($(this).val())
                }
            });
            //join方法分割数组为字符串以“”取代，替换
            $("#new_typeName").val(types.join(""))
        } else {
            //超过数量 不能继续选中
            $(this).prop("checked", false);
        }
    });
    $("#edit_typeCheck input").click(function () {
        var types = [];
        if ($("#edit_typeCheck input:checked").length <= 3) {
            //遍历选中的内容 取值
            $("#edit_typeCheck input:checkbox").each(function () {
                if ($(this).prop("checked") == true) {
                    types.push($(this).val())
                }
            });
            //join方法分割数组为字符串以“”取代，替换
            $("#edit_typeName").val(types.join(""))
        } else {
            //超过数量 不能继续选中
            $(this).prop("checked", false);
        }
    })
});
//进入页面请求（全部数据刷新）
$(function () {
    to_Page(1);
});

//请求刷新数据
function to_Page(pn) {
    userTo_page(pn);
    orderTo_page(pn);
    detailTo_page(pn);
    comicTo_page(pn);
    commentTo_page(pn);
    historyTo_page(pn);
}

function getUserSelect(pn) {
    var formData = new FormData($("#user-search")[0]);
    formData.append("pn",pn);
    $.ajax({
        url: rootPath + "userSelect",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success: function (result) {
            if (result.code==200){
                var users = result.data;
                build_user_tables2(users);
            }else {
                if (alert (result.msg)){
                    userTo_page(1)
                }
            }
        }
    })
}
function getComicSelect(pn) {
    var formData = new FormData($("#comic-search")[0]);
    formData.append("pn",pn);
    $.ajax({
        url: rootPath + "comicSelect",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success: function (result) {
            if (result.code==200){
                var comics = result.data;
                build_comic_tables2(comics);
            }else {
                if (alert (result.msg)){
                    comicTo_page(1)
                }
            }
        }
    })
}
function getDetailSelect(pn) {
    var formData = new FormData($("#detail-search")[0]);
    formData.append("pn",pn);
    $.ajax({
        url: rootPath + "detailSelect",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success: function (result) {
            if (result.code==200){
                var details = result.data;
                build_detail_tables2(details);
            }else {
                if (alert (result.msg)){
                detailTo_page(1)
                }
            }
        }
    })
}
function getOrderSelect(pn) {
    var formData = new FormData($("#order-search")[0]);
    formData.append("pn",pn);
    $.ajax({
        url: rootPath + "orderSelect",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success: function (result) {
            if (result.code==200){
                var orders = result.data;
                build_order_tables2(orders);
            }else {
                if (alert (result.msg)){
                orderTo_page(1)
                }
            }
        }
    })
}
function getCommentSelect(pn) {
    var formData = new FormData($("#comment-search")[0]);
    formData.append("pn",pn);
    $.ajax({
        url: rootPath + "commentSelect",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success: function (result) {
            if (result.code==200){
                var comments = result.data;
                build_comment_tables2(comments);
            }else {
                if (alert (result.msg)) {
                    commentTo_page(1)
                }
            }
        }
    })
}
function getHistorySelect(pn) {

    var formData = new FormData($("#history-search")[0]);
    formData.append("pn",pn);
    $.ajax({
        url: rootPath + "historySelect",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success: function (result) {
            if (result.code==200){
                var historys = result.data;
                build_history_tables2(historys);
            }else {
                if (alert (result.msg)){
                historyTo_page(1)
                }
            }
        }
    })
}


function userTo_page(pn) {
    $("#user-search")[0].reset();
    $.ajax({
        url: rootPath + "userInfo",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            var users = result.data;
            build_user_tables(users);
        }
    })
}

function orderTo_page(pn) {
    $("#order-search")[0].reset();
    $.ajax({
        url: rootPath + "orderInfo",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            var orders = result.data;
            build_order_tables(orders);
        }
    })
}

function detailTo_page(pn) {
    $("#detail-search")[0].reset();
    $.ajax({
        url: rootPath + "detailInfo",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            var details = result.data;
            build_detail_tables(details);
        }
    })
}

function comicTo_page(pn) {
    $("#comic-search")[0].reset();
    $.ajax({
        url: rootPath + "comicInfo",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            var comics = result.data;
            build_comic_tables(comics);
        }
    })
}

function commentTo_page(pn) {
    $("#comment-search")[0].reset();
    $.ajax({
        url: rootPath + "commentInfo",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            var comment = result.data;
            build_comment_tables(comment);
        }
    })
}

function historyTo_page(pn) {
    $("#history-search")[0].reset();
    $.ajax({
        url: rootPath + "historyInfo",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            var history = result.data;
            build_history_tables(history);
        }
    })
}

//更新数据表
function build_user_tables(users) {
    var list = users.list;//获取数据
    //清空table
    $(".user-table tbody").empty();
    //添加数据
    $.each(list, function (index, item) {
        var id = $("<td></td>").append(item.userId);
        var img = $("<img alt=''  class='userPhoto'>").attr("src", "/images/user/" + item.photo + ".jpg");
        var photo = $("<th></th>").append(img);
        var username = $("<th></th>").append(item.userName);
        var password = $("<th></th>").append(item.password);
        var gender = $("<th></th>").append(item.gender);
        var email = $("<th></th>").append(item.email);
        var qq = $("<th></th>").append(item.qq);
        var nickName = $("<th></th>").append(item.nickName);
        var userstatu;
        switch (item.status) {
            case (0):
                userstatu = "未激活";
                break;
            case (1):
                userstatu = "已激活";
                break;
            case (-1):
                userstatu = "封禁";
        }
        var status = $("<th></th>").append(userstatu);
        var edit = $("<a  class='btn btn-primary btn-xs' data-toggle='modal'  id='btn-editUser'>修改</a>").attr("edit-id", item.userId);
        var del = $("<a  class='btn btn-danger btn-xs' id='btn-delUser'>删除</a>").attr("del-id", item.userId);
        var order = $("<a  class='btn btn-default btn-xs' id='user-order'>添加订阅</a>").attr("user-name", item.userName);
        var history = $("<a  class='btn btn-success btn-xs' id='user-history'>添加历史</a>").attr("user-name", item.userName);
        var comment = $("<a  class='btn btn-warning btn-xs' id='user-comment'>添加评论</a>").attr("user-name", item.userName);
        var handle = $("<td></td>").append(edit).append(" ").append(comment).append(" ").append(order).append(" ").append(history).append(" ").append(del);
        $("<tr></tr>").append(id).append(photo).append(username)
            .append(password).append(gender).append(email).append(qq)
            .append(nickName).append(status).append(handle).appendTo(".user-table tbody")
    });
    //添加页面信息
    $(".userPageInfo").empty().append("当前第<span id='userCurrent'>" + users.pageNum + "</span>页，总共" + users.pages + "页，总共" + users.total + "条记录");
    var totalPage = users.pages;
    var currentPage = users.pageNum;
    //页面控制
    //更新页面控制内容（清除原有的绑定事件）
    $(".userPage").empty().append($("<li><a>首页</a></li>")).append($("<li><a>上一页 </a></li>")).append($("<li><a>" + currentPage + "</a></li>"))
        .append($("<li><a>下一页</a></li>")).append($("<li><a >尾页</a></li>"));
    $("#userPageControl ul li").eq(0).click(function () {
        userTo_page(1);
    });
    $("#userPageControl ul li").eq(1).click(function () {
        if (currentPage - 1 > 0) {
            userTo_page(currentPage - 1);
        } else {
            alert("已经到顶了嗷，没有更多信息啦！")
        }
    });
    $("#userPageControl ul li").eq(2).find("a").text(currentPage);
    $("#userPageControl ul li").eq(3).click(function () {

        if (currentPage + 1 <= totalPage) {
            userTo_page(currentPage + 1);
        } else {
            alert("已经到底了嗷，没有更多信息啦！")
        }
    });
    $("#userPageControl ul li").eq(4).click(function () {
        userTo_page(totalPage)
    })
}

function build_comic_tables(comics) {
    var list = comics.list;//获取数据
    //清空table
    $(".comic-table tbody").empty();
    //添加数据
    $.each(list, function (index, item) {
        var id = $("<td></td>").append(item.comicId);
        var Img = $("<img alt='' class='comicPhoto'>").attr("src", "/images/comics/" + item.root + "/cover.jpg");
        var comicImg = $("<th></th>").append(Img);
        var comicName = $("<th></th>").append(item.comicName);
        var author = $("<th></th>").append(item.author);
        var type = $("<th></th>").append(item.type);
        var root = $("<th></th>").append(item.root);
        var location = $("<th></th>").append(item.location);
        var newUpdate = $("<th></th>").append(item.newUpdate);
        var newChapterName = $("<th></th>").append(item.newChapterName);
        var text = $("<textarea name='introduction' style='border: none' id='text' class='form-control' readonly rows='3'></textarea>").append(item.introduction);
        var textarea = $("<th></th>").append(text);
        var comicstatu;
        switch (item.status) {
            case (0):
                comicstatu = "完结";
                break;
            case (1):
                comicstatu = "连载中";
                break;
            case (-1):
                comicstatu = "停更";
        }
        var status = $("<th></th>").append(comicstatu);
        var mark = $("<th></th>").append(item.mark);
        var edit = $("<a  class='btn btn-primary btn-xs' data-toggle='modal'  id='btn-editComic'>修改</a>").attr("edit-id", item.comicId);
        var del = $("<a  class='btn btn-danger btn-xs' id='btn-delComic'>删除</a>").attr("del-id", item.comicId);
        var detail = $("<a  class='btn btn-success btn-xs' id='comic-detail'>添加章节</a>").attr("comic-name", item.comicName);
        var comment = $("<a  class='btn btn-default btn-xs' id='comic-comment'>添加评论</a>").attr("comic-id", item.comicId);
        var order = $("<a  class='btn btn-warning btn-xs' id='comic-order'>添加订阅</a>").attr("comic-name", item.comicName);
                var handle = $("<td></td>").append(edit).append(" ").append(order).append(" ").append(detail).append(" ").append(comment).append(" ").append(del);
        $("<tr></tr>").append(id).append(comicImg).append(comicName).append(author).append(type).append(root)
            .append(location).append(newUpdate).append(newChapterName).append(textarea).append(status)
            .append(mark).append(handle).appendTo(".comic-table tbody");
    });
    //添加页面信息
    $(".comicPageInfo").empty().append("当前第<span id='comicCurrent'>" + comics.pageNum + "</span>页，总共" + comics.pages + "页，总共" + comics.total + "条记录");
    var totalPage = comics.pages;
    var currentPage = comics.pageNum;
    //页面控制
    //更新页面控制内容（清除原有的绑定事件）
    $(".comicPage").empty().append($("<li><a>首页</a></li>")).append($("<li><a>上一页 </a></li>")).append($("<li><a>" + currentPage + "</a></li>"))
        .append($("<li><a>下一页</a></li>")).append($("<li><a >尾页</a></li>"));
    $("#comicPageControl ul li").eq(0).click(function () {
        comicTo_page(1);
    });
    $("#comicPageControl ul li").eq(1).click(function () {
        if (currentPage - 1 > 0) {
            comicTo_page(currentPage - 1);
        } else {
            alert("已经到顶了嗷，没有更多信息啦！")
        }
    });
    $("#comicPageControl ul li").eq(2).find("a").text(currentPage);
    $("#comicPageControl ul li").eq(3).click(function () {

        if (currentPage + 1 <= totalPage) {
            comicTo_page(currentPage + 1);
        } else {
            alert("已经到底了嗷，没有更多信息啦！")
        }
    });
    $("#comicPageControl ul li").eq(4).click(function () {
        comicTo_page(totalPage)
    })
}

function build_detail_tables(details) {
    var list = details.list;//获取数据
    $(".detail-table tbody").empty();
    $.each(list, function (index, item) {
        var id = $("<td></td>").append(item.detailId);
        var comicImg = $("<img alt='' class='comicPhoto'>").attr("src", "/images/comics/" + item.comic.root + "/cover.jpg");
        var comicPath = $("<th></th>").append(comicImg);
        var comicName = $("<th></th>").append(item.comic.comicName);
        var chapterName = $("<th></th>").append(item.chapterName);
        var path = $("<th></th>").append(item.path);
        var generalize = $("<textarea name='introduction' style='border: none' class='form-control' readonly rows='3'></textarea>").append(item.generalize);
        var textarea = $("<th></th>").append(generalize);
        var updateTime = $("<th></th>").append(item.updateTime);
        var edit = $("<a  class='btn btn-primary btn-xs' data-toggle='modal'  id='btn-editDetail'>修改</a>").attr("edit-id", item.detailId);
        var del = $("<a  class='btn btn-danger btn-xs' id='btn-delDetail'>删除</a>").attr("del-id", item.detailId);
        var comment = $("<a  class='btn btn-success btn-xs' id='detail-comment'>添加评论</a>").attr("detail-id",  item.detailId);
        var history = $("<a  class='btn btn-warning btn-xs' id='detail-history'>添加历史</a>").attr("detail-name", item.chapterName).attr("comic-name", item.comic.comicName);
        var handle = $("<td></td>").append(edit).append(" ").append(history).append(" ").append(comment).append(" ").append(del);
        $("<tr></tr>").append(id).append(comicPath).append(comicName).append(chapterName).append(path)
            .append(textarea).append(updateTime).append(handle).appendTo(".detail-table tbody")
    });
//添加页面信息
    $(".detailPageInfo").empty().append("当前第<span id='detailCurrent'>" + details.pageNum + "</span>页，总共" + details.pages + "页，总共" + details.total + "条记录");
    var totalPage = details.pages;
    var currentPage = details.pageNum;
    //页面控制
    //更新页面控制内容（清除原有的绑定事件）
    $(".detailPage").empty().append($("<li><a>首页</a></li>")).append($("<li><a>上一页 </a></li>")).append($("<li><a>" + currentPage + "</a></li>"))
        .append($("<li><a>下一页</a></li>")).append($("<li><a >尾页</a></li>"));
    $("#detailPageControl ul li").eq(0).click(function () {
        detailTo_page(1);
    });
    $("#detailPageControl ul li").eq(1).click(function () {
        if (currentPage - 1 > 0) {
            detailTo_page(currentPage - 1);
        } else {
            alert("已经到顶了嗷，没有更多信息啦！")
        }
    });
    $("#detailPageControl ul li").eq(2).find("a").text(currentPage);
    $("#detailPageControl ul li").eq(3).click(function () {

        if (currentPage + 1 <= totalPage) {
            detailTo_page(currentPage + 1);
        } else {
            alert("已经到底了嗷，没有更多信息啦！")
        }
    });
    $("#detailPageControl ul li").eq(4).click(function () {
        detailTo_page(totalPage)
    })
}

function build_order_tables(orders) {
    var list = orders.list;//获取数据
    //清空table
    $(".order-table tbody").empty();
    $.each(list, function (index, item) {
        var id = $("<td></td>").append(item.ordersId);
        var comic = $("<th></th>").append(item.comic.comicName);
        var comicImg = $("<img alt='' class='comicPhoto'>").attr("src", "/images/comics/" + item.comic.root + "/cover.jpg");
        var comicPath = $("<th></th>").append(comicImg);
        var userName = $("<th></th>").append(item.user.userName);
        var userImg = $("<img alt='' class='userPhoto'>").attr("src", "/images/user/" + item.user.photo + ".jpg");
        var userPath = $("<th></th>").append(userImg);
        var orderstatu;
        switch (item.status) {
            case (0):
                orderstatu = "未更新";
                break;
            case (1):
                orderstatu = "已更新";
                break;
            case (-1):
                orderstatu = "已读更新";
        }
        var status = $("<th></th>").append(orderstatu);
        var edit = $("<a class='btn btn-primary btn-xs' data-toggle='modal'  id='btn-editOrder'>修改</a>").attr("edit-id", item.ordersId);
        var del = $("<a  class='btn btn-danger btn-xs' id='btn-delOrder'>删除</a>").attr("del-id", item.ordersId);
        var handle = $("<td></td>").append(edit).append(" ").append(del);
        $("<tr></tr>").append(id).append(comic).append(comicPath).append(userName)
            .append(userPath).append(status).append(handle).appendTo(".order-table tbody");
    });
    //添加页面信息
    $(".orderPageInfo").empty().append("当前第<span id='orderCurrent'>" + orders.pageNum + "</span>页，总共" + orders.pages + "页，总共" + orders.total + "条记录");
    var totalPage = orders.pages;
    var currentPage = orders.pageNum;
    //页面控制
    //更新页面控制内容（清除原有的绑定事件）
    $(".orderPage").empty().append($("<li><a>首页</a></li>")).append($("<li><a>上一页 </a></li>")).append($("<li><a>" + currentPage + "</a></li>"))
        .append($("<li><a>下一页</a></li>")).append($("<li><a >尾页</a></li>"));
    $("#orderPageControl ul li").eq(0).click(function () {
        orderTo_page(1);
    });
    $("#orderPageControl ul li").eq(1).click(function () {
        if (currentPage - 1 > 0) {
            orderTo_page(currentPage - 1);
        } else {
            alert("已经到顶了嗷，没有更多信息啦！")
        }
    });
    $("#orderPageControl ul li").eq(2).find("a").text(currentPage);
    $("#orderPageControl ul li").eq(3).click(function () {

        if (currentPage + 1 <= totalPage) {
            orderTo_page(currentPage + 1);
        } else {
            alert("已经到底了嗷，没有更多信息啦！")
        }
    });
    $("#orderPageControl ul li").eq(4).click(function () {
        orderTo_page(totalPage)
    })
}

function build_comment_tables(comments) {
    var list = comments.list;//获取数据
    //清空table
    $(".comment-table tbody").empty();
    $.each(list, function (index, item) {
        var id = $("<td></td>").append(item.commentId);
        var userImg = $("<img alt='' class='userPhoto'>").attr("src", "/images/user/" + item.user.photo + ".jpg");
        var userPath = $("<th></th>").append(userImg);
        var userName = $("<th></th>").append(item.user.userName);
        var aim;
        if (item.comicId != null) {
            aim = $("<th></th>").append("漫画id：" + item.comicId);
        }
        if (item.detailId != null) {
            aim = $("<th></th>").append("章节id：" + item.detailId);
        }
        if (item.commentAim != null) {
            aim = $("<th></th>").append("回复id：" + item.commentAim);
        }
        var text = $("<textarea name='content' style='border: none' id='text' class='form-control' readonly rows='3'></textarea>").append(item.content);
        var textarea = $("<th></th>").append(text);
        var historyStatus;
        switch (item.status) {
            case (0):
                historyStatus = "未回复";
                break;
            case (1):
                historyStatus = "已回复";
                break;
            case (-1):
                historyStatus = "已读不回";
        }
        var status = $("<th></th>").append(historyStatus);
        var time = $("<th></th>").append(item.time);
        var edit = $("<a class='btn btn-primary btn-xs' data-toggle='modal'  id='btn-editComment'>修改</a>").attr("edit-id", item.commentId);
        var del = $("<a  class='btn btn-danger btn-xs' id='btn-delComment'>删除</a>").attr("del-id", item.commentId);
        var handle = $("<td></td>").append(edit).append(" ").append(del);
        $("<tr></tr>").append(id).append(userPath).append(userName).append(aim)
            .append(textarea).append(status).append(time).append(handle).appendTo(".comment-table tbody");
    });
    //添加页面信息
    $(".commentPageInfo").empty().append("当前第<span id='commentCurrent'>" + comments.pageNum + "</span>页，总共" + comments.pages + "页，总共" + comments.total + "条记录");
    var totalPage = comments.pages;
    var currentPage = comments.pageNum;
    //页面控制
    //更新页面控制内容（清除原有的绑定事件）
    $(".commentPage").empty().append($("<li><a>首页</a></li>")).append($("<li><a>上一页 </a></li>")).append($("<li><a>" + currentPage + "</a></li>"))
        .append($("<li><a>下一页</a></li>")).append($("<li><a >尾页</a></li>"));
    $("#commentPageControl ul li").eq(0).click(function () {
        commentTo_page(1);
    });
    $("#commentPageControl ul li").eq(1).click(function () {
        if (currentPage - 1 > 0) {
            commentTo_page(currentPage - 1);
        } else {
            alert("已经到顶了嗷，没有更多信息啦！")
        }
    });
    $("#commentPageControl ul li").eq(2).find("a").text(currentPage);
    $("#commentPageControl ul li").eq(3).click(function () {

        if (currentPage + 1 <= totalPage) {
            commentTo_page(currentPage + 1);
        } else {
            alert("已经到底了嗷，没有更多信息啦！")
        }
    });
    $("#commentPageControl ul li").eq(4).click(function () {
        commentTo_page(totalPage)
    })
}

function build_history_tables(historys) {
    var list = historys.list;//获取数据
    //清空table
    $(".history-table tbody").empty();
    $.each(list, function (index, item) {
        var id = $("<td></td>").append(item.historyId);
        var userImg = $("<img alt='' class='userPhoto'>").attr("src", "/images/user/" + item.user.photo + ".jpg");
        var userPath = $("<th></th>").append(userImg);
        var userName = $("<th></th>").append(item.user.userName);
        var comicImg = $("<img alt='' class='comicPhoto'>").attr("src", "/images/comics/" + item.comic.root + "/cover.jpg");
        var comicPath = $("<th></th>").append(comicImg);
        var comic = $("<th></th>").append(item.comic.comicName);
        var chapter = $("<th></th>").append(item.detail.chapterName);
        var lastTime = $("<th></th>").append(item.lastReadTime);
        var historyStatu;
        switch (item.status) {
            case (0):
                historyStatu = "未更新";
                break;
            case (1):
                historyStatu = "已更新";
                break;
            case (-1):
                historyStatu = "已读更新";
        }
        var status = $("<th></th>").append(historyStatu);
        var edit = $("<a class='btn btn-primary btn-xs' data-toggle='modal'  id='btn-editHistory'>修改</a>").attr("edit-id", item.historyId);
        var del = $("<a  class='btn btn-danger btn-xs' id='btn-delHistory'>删除</a>").attr("del-id", item.historyId);
        var handle = $("<td></td>").append(edit).append(" ").append(del);
        $("<tr></tr>").append(id).append(userPath).append(userName).append(comicPath)
            .append(comic).append(chapter).append(lastTime).append(status).append(handle).appendTo(".history-table tbody");
    });
    //添加页面信息
    $(".historyPageInfo").empty().append("当前第<span id='historyCurrent'>" + historys.pageNum + "</span>页，总共" + historys.pages + "页，总共" + historys.total + "条记录");
    var totalPage = historys.pages;
    var currentPage = historys.pageNum;
    //页面控制
    //更新页面控制内容（清除原有的绑定事件）
    $(".historyPage").empty().append($("<li><a>首页</a></li>")).append($("<li><a>上一页 </a></li>")).append($("<li><a>" + currentPage + "</a></li>"))
        .append($("<li><a>下一页</a></li>")).append($("<li><a >尾页</a></li>"));
    $("#historyPageControl ul li").eq(0).click(function () {
        historyTo_page(1);
    });
    $("#historyPageControl ul li").eq(1).click(function () {
        if (currentPage - 1 > 0) {
            historyTo_page(currentPage - 1);
        } else {
            alert("已经到顶了嗷，没有更多信息啦！")
        }
    });
    $("#historyPageControl ul li").eq(2).find("a").text(currentPage);
    $("#historyPageControl ul li").eq(3).click(function () {

        if (currentPage + 1 <= totalPage) {
            historyTo_page(currentPage + 1);
        } else {
            alert("已经到底了嗷，没有更多信息啦！")
        }
    });
    $("#historyPageControl ul li").eq(4).click(function () {
        historyTo_page(totalPage)
    })
}
//查询更新数据
function build_user_tables2(users) {
    var list = users.list;//获取数据
    //清空table
    $(".user-table tbody").empty();
    //添加数据
    $.each(list, function (index, item) {
        var id = $("<td></td>").append(item.userId);
        var img = $("<img alt=''  class='userPhoto'>").attr("src", "/images/user/" + item.photo + ".jpg");
        var photo = $("<th></th>").append(img);
        var username = $("<th></th>").append(item.userName);
        var password = $("<th></th>").append(item.password);
        var gender = $("<th></th>").append(item.gender);
        var email = $("<th></th>").append(item.email);
        var qq = $("<th></th>").append(item.qq);
        var nickName = $("<th></th>").append(item.nickName);
        var userstatu;
        switch (item.status) {
            case (0):
                userstatu = "未激活";
                break;
            case (1):
                userstatu = "已激活";
                break;
            case (-1):
                userstatu = "封禁";
        }
        var status = $("<th></th>").append(userstatu);
        var edit = $("<a  class='btn btn-primary btn-xs' data-toggle='modal'  id='btn-editUser'>修改</a>").attr("edit-id", item.userId);
        var del = $("<a  class='btn btn-danger btn-xs' id='btn-delUser'>删除</a>").attr("del-id", item.userId);
        var order = $("<a  class='btn btn-default btn-xs' id='user-order'>添加订阅</a>").attr("user-name", item.userName);
        var history = $("<a  class='btn btn-success btn-xs' id='user-history'>添加历史</a>").attr("user-name", item.userName);
        var comment = $("<a  class='btn btn-warning btn-xs' id='user-comment'>添加评论</a>").attr("user-name", item.userName);
        var handle = $("<td></td>").append(edit).append(" ").append(del).append(" ").append(order).append(" ").append(history).append(" ").append(comment);
        $("<tr></tr>").append(id).append(photo).append(username)
            .append(password).append(gender).append(email).append(qq)
            .append(nickName).append(status).append(handle).appendTo(".user-table tbody")
    });
    //添加页面信息
    $(".userPageInfo").empty().append("当前第<span id='userCurrent'>" + users.pageNum + "</span>页，总共" + users.pages + "页，总共" + users.total + "条记录");
    var totalPage = users.pages;
    var currentPage = users.pageNum;
    //页面控制
    //更新页面控制内容（清除原有的绑定事件）
    $(".userPage").empty().append($("<li><a>首页</a></li>")).append($("<li><a>上一页 </a></li>")).append($("<li><a>" + currentPage + "</a></li>"))
        .append($("<li><a>下一页</a></li>")).append($("<li><a >尾页</a></li>"));
    $("#userPageControl ul li").eq(0).click(function () {
        getUserSelect(1);
    });
    $("#userPageControl ul li").eq(1).click(function () {
        if (currentPage - 1 > 0) {
            getUserSelect(currentPage - 1);
        } else {
            alert("已经到顶了嗷，没有更多信息啦！")
        }
    });
    $("#userPageControl ul li").eq(2).find("a").text(currentPage);
    $("#userPageControl ul li").eq(3).click(function () {

        if (currentPage + 1 <= totalPage) {
            getUserSelect(currentPage + 1);
        } else {
            alert("已经到底了嗷，没有更多信息啦！")
        }
    });
    $("#userPageControl ul li").eq(4).click(function () {
        getUserSelect(totalPage)
    })
}

function build_comic_tables2(comics) {
    var list = comics.list;//获取数据
    //清空table
    $(".comic-table tbody").empty();
    //添加数据
    $.each(list, function (index, item) {
        var id = $("<td></td>").append(item.comicId);
        var Img = $("<img alt='' class='comicPhoto'>").attr("src", "/images/comics/" + item.root + "/cover.jpg");
        var comicImg = $("<th></th>").append(Img);
        var comicName = $("<th></th>").append(item.comicName);
        var author = $("<th></th>").append(item.author);
        var type = $("<th></th>").append(item.type);
        var root = $("<th></th>").append(item.root);
        var location = $("<th></th>").append(item.location);
        var newUpdate = $("<th></th>").append(item.newUpdate);
        var newChapterName = $("<th></th>").append(item.newChapterName);
        var text = $("<textarea name='introduction' style='border: none' id='text' class='form-control' readonly rows='3'></textarea>").append(item.introduction);
        var textarea = $("<th></th>").append(text);
        var comicstatu;
        switch (item.status) {
            case (0):
                comicstatu = "完结";
                break;
            case (1):
                comicstatu = "连载中";
                break;
            case (-1):
                comicstatu = "停更";
        }
        var status = $("<th></th>").append(comicstatu);
        var mark = $("<th></th>").append(item.mark);
        var edit = $("<a  class='btn btn-primary btn-xs' data-toggle='modal'  id='btn-editComic'>修改</a>").attr("edit-id", item.comicId);
        var del = $("<a  class='btn btn-danger btn-xs' id='btn-delComic'>删除</a>").attr("del-id", item.comicId);
        var detail = $("<a  class='btn btn-success btn-xs' id='comic-comment'>添加章节</a>").attr("comic-name", item.comicName);
        var comment = $("<a  class='btn btn-default btn-xs' id='comic-comment'>添加评论</a>").attr("comic-id", item.comicId);
        var order = $("<a  class='btn btn-warning btn-xs' id='comic-order'>添加订阅</a>").attr("comic-name", item.comicName);
                var handle = $("<td></td>").append(edit).append(" ").append(order).append(" ").append(detail).append(" ").append(comment).append(" ").append(del);
        $("<tr></tr>").append(id).append(comicImg).append(comicName).append(author).append(type).append(root)
            .append(location).append(newUpdate).append(newChapterName).append(textarea).append(status)
            .append(mark).append(handle).appendTo(".comic-table tbody");
    });
    //添加页面信息
    $(".comicPageInfo").empty().append("当前第<span id='comicCurrent'>" + comics.pageNum + "</span>页，总共" + comics.pages + "页，总共" + comics.total + "条记录");
    var totalPage = comics.pages;
    var currentPage = comics.pageNum;
    //页面控制
    //更新页面控制内容（清除原有的绑定事件）
    $(".comicPage").empty().append($("<li><a>首页</a></li>")).append($("<li><a>上一页 </a></li>")).append($("<li><a>" + currentPage + "</a></li>"))
        .append($("<li><a>下一页</a></li>")).append($("<li><a >尾页</a></li>"));
    $("#comicPageControl ul li").eq(0).click(function () {
        getComicSelect(1);
    });
    $("#comicPageControl ul li").eq(1).click(function () {
        if (currentPage - 1 > 0) {
            getComicSelect(currentPage - 1);
        } else {
            alert("已经到顶了嗷，没有更多信息啦！")
        }
    });
    $("#comicPageControl ul li").eq(2).find("a").text(currentPage);
    $("#comicPageControl ul li").eq(3).click(function () {

        if (currentPage + 1 <= totalPage) {
            getComicSelect(currentPage + 1);
        } else {
            alert("已经到底了嗷，没有更多信息啦！")
        }
    });
    $("#comicPageControl ul li").eq(4).click(function () {
        getComicSelect(totalPage)
    })
}

function build_detail_tables2(details) {
    var list = details.list;//获取数据
    $(".detail-table tbody").empty();
    $.each(list, function (index, item) {
        var id = $("<td></td>").append(item.detailId);
        var comicImg = $("<img alt='' class='comicPhoto'>").attr("src", "/images/comics/" + item.comic.root + "/cover.jpg");
        var comicPath = $("<th></th>").append(comicImg);
        var comicName = $("<th></th>").append(item.comic.comicName);
        var chapterName = $("<th></th>").append(item.chapterName);
        var path = $("<th></th>").append(item.path);
        var generalize = $("<textarea name='introduction' style='border: none' class='form-control' readonly rows='3'></textarea>").append(item.generalize);
        var textarea = $("<th></th>").append(generalize);
        var updateTime = $("<th></th>").append(item.updateTime);
        var edit = $("<a  class='btn btn-primary btn-xs' data-toggle='modal'  id='btn-editDetail'>修改</a>").attr("edit-id", item.detailId);
        var del = $("<a  class='btn btn-danger btn-xs' id='btn-delDetail'>删除</a>").attr("del-id", item.detailId);
        var comment = $("<a  class='btn btn-success btn-xs' id='detail-comment'>添加评论</a>").attr("detail-id",  item.detailId);
        var history = $("<a  class='btn btn-warning btn-xs' id='detail-history'>添加历史</a>").attr("detail-name", item.chapterName).attr("comic-name", item.comic.comicName);
        var handle = $("<td></td>").append(edit).append(" ").append(history).append(" ").append(comment).append(" ").append(del);
        $("<tr></tr>").append(id).append(comicPath).append(comicName).append(chapterName).append(path)
            .append(textarea).append(updateTime).append(handle).appendTo(".detail-table tbody")
    });
//添加页面信息
    $(".detailPageInfo").empty().append("当前第<span id='detailCurrent'>" + details.pageNum + "</span>页，总共" + details.pages + "页，总共" + details.total + "条记录");
    var totalPage = details.pages;
    var currentPage = details.pageNum;
    //页面控制
    //更新页面控制内容（清除原有的绑定事件）
    $(".detailPage").empty().append($("<li><a>首页</a></li>")).append($("<li><a>上一页 </a></li>")).append($("<li><a>" + currentPage + "</a></li>"))
        .append($("<li><a>下一页</a></li>")).append($("<li><a >尾页</a></li>"));
    $("#detailPageControl ul li").eq(0).click(function () {
        getDetailSelect(1);
    });
    $("#detailPageControl ul li").eq(1).click(function () {
        if (currentPage - 1 > 0) {
            getDetailSelect(currentPage - 1);
        } else {
            alert("已经到顶了嗷，没有更多信息啦！")
        }
    });
    $("#detailPageControl ul li").eq(2).find("a").text(currentPage);
    $("#detailPageControl ul li").eq(3).click(function () {

        if (currentPage + 1 <= totalPage) {
            getDetailSelect(currentPage + 1);
        } else {
            alert("已经到底了嗷，没有更多信息啦！")
        }
    });
    $("#detailPageControl ul li").eq(4).click(function () {
        getDetailSelect(totalPage)
    })
}

function build_order_tables2(orders) {
    var list = orders.list;//获取数据
    //清空table
    $(".order-table tbody").empty();
    $.each(list, function (index, item) {
        var id = $("<td></td>").append(item.ordersId);
        var comic = $("<th></th>").append(item.comic.comicName);
        var comicImg = $("<img alt='' class='comicPhoto'>").attr("src", "/images/comics/" + item.comic.root + "/cover.jpg");
        var comicPath = $("<th></th>").append(comicImg);
        var userName = $("<th></th>").append(item.user.userName);
        var userImg = $("<img alt='' class='userPhoto'>").attr("src", "/images/user/" + item.user.photo + ".jpg");
        var userPath = $("<th></th>").append(userImg);
        var orderstatu;
        switch (item.status) {
            case (0):
                orderstatu = "未更新";
                break;
            case (1):
                orderstatu = "已更新";
                break;
            case (-1):
                orderstatu = "已读更新";
        }
        var status = $("<th></th>").append(orderstatu);
        var edit = $("<a class='btn btn-primary btn-xs' data-toggle='modal'  id='btn-editOrder'>修改</a>").attr("edit-id", item.ordersId);
        var del = $("<a  class='btn btn-danger btn-xs' id='btn-delOrder'>删除</a>").attr("del-id", item.ordersId);
        var handle = $("<td></td>").append(edit).append(" ").append(del);
        $("<tr></tr>").append(id).append(comic).append(comicPath).append(userName)
            .append(userPath).append(status).append(handle).appendTo(".order-table tbody");
    });
    //添加页面信息
    $(".orderPageInfo").empty().append("当前第<span id='orderCurrent'>" + orders.pageNum + "</span>页，总共" + orders.pages + "页，总共" + orders.total + "条记录");
    var totalPage = orders.pages;
    var currentPage = orders.pageNum;
    //页面控制
    //更新页面控制内容（清除原有的绑定事件）
    $(".orderPage").empty().append($("<li><a>首页</a></li>")).append($("<li><a>上一页 </a></li>")).append($("<li><a>" + currentPage + "</a></li>"))
        .append($("<li><a>下一页</a></li>")).append($("<li><a >尾页</a></li>"));
    $("#orderPageControl ul li").eq(0).click(function () {
        getOrderSelect(1);
    });
    $("#orderPageControl ul li").eq(1).click(function () {
        if (currentPage - 1 > 0) {
            getOrderSelect(currentPage - 1);
        } else {
            alert("已经到顶了嗷，没有更多信息啦！")
        }
    });
    $("#orderPageControl ul li").eq(2).find("a").text(currentPage);
    $("#orderPageControl ul li").eq(3).click(function () {

        if (currentPage + 1 <= totalPage) {
            getOrderSelect(currentPage + 1);
        } else {
            alert("已经到底了嗷，没有更多信息啦！")
        }
    });
    $("#orderPageControl ul li").eq(4).click(function () {
        getOrderSelect(totalPage)
    })
}

function build_comment_tables2(comments) {
    var list = comments.list;//获取数据
    //清空table
    $(".comment-table tbody").empty();
    $.each(list, function (index, item) {
        var id = $("<td></td>").append(item.commentId);
        var userImg = $("<img alt='' class='userPhoto'>").attr("src", "/images/user/" + item.user.photo + ".jpg");
        var userPath = $("<th></th>").append(userImg);
        var userName = $("<th></th>").append(item.user.userName);
        var aim;
        if (item.comicId != null) {
            aim = $("<th></th>").append("漫画id：" + item.comicId);
        }
        if (item.detailId != null) {
            aim = $("<th></th>").append("章节id：" + item.detailId);
        }
        if (item.commentAim != null) {
            aim = $("<th></th>").append("回复id：" + item.commentAim);
        }
        var text = $("<textarea name='content' style='border: none' id='text' class='form-control' readonly rows='3'></textarea>").append(item.content);
        var textarea = $("<th></th>").append(text);
        var historyStatus;
        switch (item.status) {
            case (0):
                historyStatus = "未回复";
                break;
            case (1):
                historyStatus = "已回复";
                break;
            case (-1):
                historyStatus = "已读不回";
        }
        var status = $("<th></th>").append(historyStatus);
        var time = $("<th></th>").append(item.time);
        var edit = $("<a class='btn btn-primary btn-xs' data-toggle='modal'  id='btn-editComment'>修改</a>").attr("edit-id", item.commentId);
        var del = $("<a  class='btn btn-danger btn-xs' id='btn-delComment'>删除</a>").attr("del-id", item.commentId);
        var handle = $("<td></td>").append(edit).append(" ").append(del);
        $("<tr></tr>").append(id).append(userPath).append(userName).append(aim)
            .append(textarea).append(status).append(time).append(handle).appendTo(".comment-table tbody");
    });
    //添加页面信息
    $(".commentPageInfo").empty().append("当前第<span id='commentCurrent'>" + comments.pageNum + "</span>页，总共" + comments.pages + "页，总共" + comments.total + "条记录");
    var totalPage = comments.pages;
    var currentPage = comments.pageNum;
    //页面控制
    //更新页面控制内容（清除原有的绑定事件）
    $(".commentPage").empty().append($("<li><a>首页</a></li>")).append($("<li><a>上一页 </a></li>")).append($("<li><a>" + currentPage + "</a></li>"))
        .append($("<li><a>下一页</a></li>")).append($("<li><a >尾页</a></li>"));
    $("#commentPageControl ul li").eq(0).click(function () {
        getCommentSelect(1);
    });
    $("#commentPageControl ul li").eq(1).click(function () {
        if (currentPage - 1 > 0) {
            getCommentSelect(currentPage - 1);
        } else {
            alert("已经到顶了嗷，没有更多信息啦！")
        }
    });
    $("#commentPageControl ul li").eq(2).find("a").text(currentPage);
    $("#commentPageControl ul li").eq(3).click(function () {

        if (currentPage + 1 <= totalPage) {
            getCommentSelect(currentPage + 1);
        } else {
            alert("已经到底了嗷，没有更多信息啦！")
        }
    });
    $("#commentPageControl ul li").eq(4).click(function () {
        getCommentSelect(totalPage)
    })
}

function build_history_tables2(historys) {
    var list = historys.list;//获取数据
    //清空table
    $(".history-table tbody").empty();
    $.each(list, function (index, item) {
        var id = $("<td></td>").append(item.historyId);
        var userImg = $("<img alt='' class='userPhoto'>").attr("src", "/images/user/" + item.user.photo + ".jpg");
        var userPath = $("<th></th>").append(userImg);
        var userName = $("<th></th>").append(item.user.userName);
        var comicImg = $("<img alt='' class='comicPhoto'>").attr("src", "/images/comics/" + item.comic.root + "/cover.jpg");
        var comicPath = $("<th></th>").append(comicImg);
        var comic = $("<th></th>").append(item.comic.comicName);
        var chapter = $("<th></th>").append(item.detail.chapterName);
        var lastTime = $("<th></th>").append(item.lastReadTime);
        var historyStatu;
        switch (item.status) {
            case (0):
                historyStatu = "未更新";
                break;
            case (1):
                historyStatu = "已更新";
                break;
            case (-1):
                historyStatu = "已读更新";
        }
        var status = $("<th></th>").append(historyStatu);
        var edit = $("<a class='btn btn-primary btn-xs' data-toggle='modal'  id='btn-editHistory'>修改</a>").attr("edit-id", item.historyId);
        var del = $("<a  class='btn btn-danger btn-xs' id='btn-delHistory'>删除</a>").attr("del-id", item.historyId);
        var handle = $("<td></td>").append(edit).append(" ").append(del);
        $("<tr></tr>").append(id).append(userPath).append(userName).append(comicPath)
            .append(comic).append(chapter).append(lastTime).append(status).append(handle).appendTo(".history-table tbody");
    });
    //添加页面信息
    $(".historyPageInfo").empty().append("当前第<span id='historyCurrent'>" + historys.pageNum + "</span>页，总共" + historys.pages + "页，总共" + historys.total + "条记录");
    var totalPage = historys.pages;
    var currentPage = historys.pageNum;
    //页面控制
    //更新页面控制内容（清除原有的绑定事件）
    $(".historyPage").empty().append($("<li><a>首页</a></li>")).append($("<li><a>上一页 </a></li>")).append($("<li><a>" + currentPage + "</a></li>"))
        .append($("<li><a>下一页</a></li>")).append($("<li><a >尾页</a></li>"));
    $("#historyPageControl ul li").eq(0).click(function () {
        getHistorySelect(1);
    });
    $("#historyPageControl ul li").eq(1).click(function () {
        if (currentPage - 1 > 0) {
            getHistorySelect(currentPage - 1);
        } else {
            alert("已经到顶了嗷，没有更多信息啦！")
        }
    });
    $("#historyPageControl ul li").eq(2).find("a").text(currentPage);
    $("#historyPageControl ul li").eq(3).click(function () {

        if (currentPage + 1 <= totalPage) {
            getHistorySelect(currentPage + 1);
        } else {
            alert("已经到底了嗷，没有更多信息啦！")
        }
    });
    $("#historyPageControl ul li").eq(4).click(function () {
        getHistorySelect(totalPage)
    })
}
//清空原表 绑定事件
$(function () {
    $(".add").on("click", function () {

        $($(this).attr("dialog") + " form")[0].reset();
        $($(this).attr("dialog") + " form img").attr("src", "");
        $($(this).attr("dialog") + " form .showImg img").removeClass("img");
        $($(this).attr("dialog")).modal({
            backdrop: "static"
        });
    });
});
//Ajax请求
$(function () {

    //左侧导航栏按钮绑定刷新事件
    $("#userPage").click(function () {
        userTo_page(1);
    });
    $("#comicPage").click(function () {
        comicTo_page(1);
    });
    $("#orderPage").click(function () {
        orderTo_page(1);
    });
    $("#detailPage").click(function () {
        detailTo_page(1);
    });
    $("#commentPage").click(function () {
        commentTo_page(1);
    });
    $("#historyPage").click(function () {
        historyTo_page(1);
    });

    //添加用户请求
    $("div .modal-footer").on("click", "#btn-addUser", function () {
        var formData = new FormData($("#new_user_form")[0]);
        $.ajax({
            url: rootPath + "addUser",
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function (result) {
                if (result.code == 200) {
                    $("#addUserDialog").modal("hide");
                    userTo_page(1);
                } else {
                    confirm(result.msg)
                }
            }
        })
    });
    //修改用户请求 1.获取指定用户信息 2.修改指定用户id
    $("tbody").on("click", "#btn-editUser", function () {
        var id = $(this).attr("edit-id");
        $.ajax({
            url: rootPath + "getUserInfo?id=" + id,
            type: "GET",
            success: function (result) {
                $("#edit_userId").val(result.data.userId);
                $("#edit_showPhoto").attr("src", "/images/user/" + result.data.photo + ".jpg");
                $("#edit_name").val(result.data.userName);
                $("#edit_password").val(result.data.password);
                $("#edit_gender").val(result.data.gender);
                $("#edit_email").val(result.data.email);
                $("#edit_qq").val(result.data.qq);
                $("#edit_nickName").val(result.data.nickName);
                $("#edit_status").val(result.data.status);
            }
        });
        $("#userEditDialog").modal({
            backdrop: "static"
        });
    });
    $("#btn-updateUser").click(function () {
        if (confirm("确定要修改" + $("#edit_name").val() + "的信息么？")) {
            var file = document.getElementById("edit_user_form");
            var formData = new FormData(file);
            $.ajax({
                url: rootPath + "editUser",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                success: function (result) {
                    if (result.code = 200) {
                        $("#userEditDialog").modal('hide');
                        userTo_page($("#userCurrent").text())
                    } else {
                        alert(result.msg);
                    }
                }
            })
        }
    });
    //删除用户请求
    $("tbody").on("click", "#btn-delUser", function () {
        if (confirm("确定要删除id为【" + $(this).attr("del-id") + "】的用户信息么？")) {
            var id = $(this).attr("del-id");
            $.ajax({
                url: rootPath + "delUser?id=" + id,
                type: "GET",
                success: function (result) {
                    alert(result.msg);
                    userTo_page($("#userCurrent").text())
                }
            })
        }
    });
    //查询用户请求
    $("#btn-search-user").click(function () {
        getUserSelect(1);
    });


    //添加漫画请求
    $("div .modal-footer").on("click", "#btn-addComic", function () {
        var formData = new FormData($("#new_comic_form")[0]);
        $.ajax({
            url: rootPath + "addComic",
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function (result) {
                if (result.code == 200) {
                    $("#addComicDialog").modal("hide");
                    comicTo_page(1);
                } else {
                    confirm(result.msg)
                }
            }
        })
    });
    //修改漫画请求 1.获取指定漫画信息 2.修改指定漫画id
    $("tbody").on("click", "#btn-editComic", function () {
        var id = $(this).attr("edit-id");
        $("#comicEditDialog form")[0].reset();
        $.ajax({
            url: rootPath + "getComicInfo?id=" + id,
            type: "GET",
            success: function (result) {
                $("#edit_id").val(result.data.comicId);
                $("#edit_showPhotos").attr("src", "/images/comics/" + result.data.root + "/cover.jpg");
                $("#edit_coverPath").val(result.data.root);
                $("#edit_comicName").val(result.data.comicName);
                $("#edit_author").val(result.data.author);
                $("#edit_location").val(result.data.location);
                $("#introduction").val(result.data.introduction);
                // fixme 需要分割 只能设置年月日 不能设置时分秒 2019-8-29 00:00:00 不可以 要切割为 2019-8-29（之前只有时分）
                //fixme 时间操作 前端用 type="datetime-local" step="01"  可以精确到秒 但是传入值的时候时间和日期之间需要加一个 T
                if (result.data.newUpdate != null) {
                    $("#edit_update").val(result.data.newUpdate.replace(" ","T"));
                }
                $("#edit_ChapterName").val(result.data.newChapterName);
                $("#edit_mark").val(result.data.mark);
                $("#edit_comicStatus").val(result.data.status);
                $("#edit_typeName").val(result.data.type);
                $("#edit_typeCheck input:checkbox").each(function (i) {
                    if (result.data.type.indexOf($(this).val()) != -1) {
                        $(this).prop("checked", true);
                    }
                });
            }
        });
        $("#comicEditDialog").modal({
            backdrop: "static"
        });
    });
    $("#btn-updateComic").click(function () {
        if (confirm("确定要修改" + $("#edit_comicName").val() + "的信息么？")) {
            var file = document.getElementById("edit_comic_form");
            var formData = new FormData(file);
            $.ajax({
                url: rootPath + "editComic",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                success: function (result) {
                    if (result.code = 200) {
                        $("#comicEditDialog").modal('hide');
                        comicTo_page($("#comicCurrent").text())
                    } else {
                        alert(result.msg);
                    }
                }
            })
        }
    });
    //删除漫画请求
    $("tbody").on("click", "#btn-delComic", function () {
        if (confirm("确定要删除id为【" + $(this).attr("del-id") + "】的漫画信息么？")) {
            var id = $(this).attr("del-id");
            $.ajax({
                url: rootPath + "delComic?id=" + id,
                type: "GET",
                success: function (result) {
                    alert(result.msg);
                    comicTo_page($("#comicCurrent").text())
                }
            })
        }
    });
    //查询漫画请求
    $("#btn-search-comic").click(function () {
        getComicSelect(1);
    });

    //添加订阅请求
    $("div .modal-footer").on("click", "#btn-addOrder", function () {
        var formData = new FormData($("#add_orders_form")[0]);
        $.ajax({
            url: rootPath + "addOrder",
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function (result) {
                if (result.code == 200) {
                    $("#newOrdersDialog").modal("hide");
                    orderTo_page(1);
                } else {
                    confirm(result.msg)
                }
            }
        })
    });
    //修改订阅请求 1.获取指定订阅信息 2.修改指定订阅id
    $("tbody").on("click", "#btn-editOrder", function () {
        var id = $(this).attr("edit-id");
        $.ajax({
            url: rootPath + "getOrderInfo?id=" + id,
            type: "GET",
            success: function (result) {
                $("#edit_orderId").val(result.data.ordersId);
                $("#edit_order_comicName").val(result.data.comic.comicName);
                $("#edit_order_userName").val(result.data.user.userName);
                $("edit_order_status").val(result.data.status)
            }
        });
        $("#ordersEditDialog").modal({
            backdrop: "static"
        });
    });
    $("#btn-updateOrder").click(function () {
        if (confirm("确定要修改id为" + $("#edit_orderId").val() + "的订阅信息么？")) {
            var file = document.getElementById("edit_orders_form");
            var formData = new FormData(file);
            $.ajax({
                url: rootPath + "editOrder",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                success: function (result) {
                    if (result.code = 200) {
                        $("#ordersEditDialog").modal('hide');
                        orderTo_page($("#orderCurrent").text())
                    } else {
                        alert(result.msg);
                    }
                }
            })
        }
    });
    //删除订阅请求
    $("tbody").on("click", "#btn-delOrder", function () {
        if (confirm("确定要删除id为【" + $(this).attr("del-id") + "】的订阅信息么？")) {
            var id = $(this).attr("del-id");
            $.ajax({
                url: rootPath + "delOrder?id=" + id,
                type: "GET",
                success: function (result) {
                    alert(result.msg);
                    orderTo_page($("#orderCurrent").text())
                }
            })
        }
    });
    //查询订阅请求
    $("#btn-search-order").click(function () {
        getOrderSelect(1);
    });

    //添加章节请求
    $("div .modal-footer").on("click", "#btn-addDetail", function () {
        var formData = new FormData($("#new_detail_form")[0]);
        $.ajax({
            url: rootPath + "addDetail",
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function (result) {
                if (result.code == 200) {
                    $("#addDetailDialog").modal("hide");
                    detailTo_page(1);
                    $("#new_detail_form .showImg").empty();
                } else {
                    confirm(result.msg)
                }
            }
        })
    });
    //修改章节请求 1.获取指定章节信息 2.修改指定章节id
    $("tbody").on("click", "#btn-editDetail", function () {
        var id = $(this).attr("edit-id");
        $("#detailEditDialog form")[0].reset();
        $("#detailEditDialog .showImg").empty();
        $.ajax({
            url: rootPath + "getDetailInfo?id=" + id,
            type: "GET",
            success: function (result) {
                $("#edit_detailId").val(result.data.detailId);
                $("#edit_detail_comicname").val(result.data.comic.comicName);
                $("#edit_detail_chaptername").val(result.data.chapterName);
                // fixme 需要分割 只能设置年月日 不能设置时分秒 2019-8-29 00:00:00 不可以 要切割为 2019-8-29（之前只有时分）
                //fixme 时间操作 前端用 type="datetime-local" step="01"  可以精确到秒 但是传入值的时候时间和日期之间需要加一个 T
                $("#edit_detail_updateTime").val(result.data.updateTime.replace(" ","T"));
                $("#generalize").val(result.data.generalize);
                $("#new_photo_path").val(result.data.path);
                var path = result.data.path;
                var root = result.data.comic.root;
                $.each(result.data.images, function (index, item) {
                    var src = "\\images\\comics\\" + root + "\\" + path + "\\" + item;
                    $("<img class='img'>").attr("src", src).appendTo($("#edit_detail_form .showImg"));
                });
            }
        });
        $("#detailEditDialog").modal({
            backdrop: "static"
        });
    });
    $("#btn-updateDetail").click(function () {
        if (confirm("确定要修改章节名为" + $("#edit_detail_chaptername").val() + "的信息么？")) {
            var file = document.getElementById("edit_detail_form");
            var formData = new FormData(file);
            $.ajax({
                url: rootPath + "editDetail",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                success: function (result) {
                    if (result.code = 200) {
                        $("#detailEditDialog").modal('hide');
                        detailTo_page($("#detailCurrent").text())
                    } else {
                        alert(result.msg);
                    }
                }
            })
        }
    });
    //删除章节请求
    $("tbody").on("click", "#btn-delDetail", function () {
        if (confirm("确定要删除id为【" + $(this).attr("del-id") + "】的章节信息么？")) {
            var id = $(this).attr("del-id");
            $.ajax({
                url: rootPath + "delDetail?id=" + id,
                type: "GET",
                success: function (result) {
                    alert(result.msg);
                    detailTo_page($("#detailCurrent").text())
                }
            })
        }
    });
    //查询章节请求
    $("#btn-search-detail").click(function () {
        getDetailSelect(1);
    });

    //添加评论请求
    $("div .modal-footer").on("click", "#btn-addComment", function () {
        var formData = new FormData($("#add_comment_form")[0]);
        $.ajax({
            url: rootPath + "addComment",
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function (result) {
                if (result.code == 200) {
                    $("#newCommentDialog").modal("hide");
                    commentTo_page(1);
                } else {
                    confirm(result.msg)
                }
            }
        })
    });
    //修改评论请求 1.获取指定评论信息 2.修改指定评论id
    $("tbody").on("click", "#btn-editComment", function () {
        var id = $(this).attr("edit-id");
        $.ajax({
            url: rootPath + "getCommentInfo?id=" + id,
            type: "GET",
            success: function (result) {
                $("#edit-commentId").val(result.data.commentId);
                $("#edit_comment_userName").val(result.data.user.userName);
                if (result.data.comicId!=null){
                    $("#edit_comment_type").val(0);
                    $("#edit_coment_aim").val(result.data.comicId)
                };
                if (result.data.detailId!=null){
                    $("#edit_comment_type").val(1);
                    $("#edit_coment_aim").val(result.data.detailId)
                };
                if (result.data.commentAim!=null){
                    $("#edit_comment_type").val(-1);
                    $("#edit_coment_aim").val(result.data.commentAim)
                };
                $("#edit_comment_status").val(result.data.status);
                $("#edit_comment_time").val(result.data.time.replace(" ","T"));
                $("#edit_comment_content").text(result.data.content)
            }
        });
        $("#commentEditDialog").modal({
            backdrop: "static"
        });
    });
    $("#btn-updateComment").click(function () {
        if (confirm("确定要修改id为" + $("#edit_orderId").val() + "的评论信息么？")) {
            var file = document.getElementById("edit_comment_form");
            var formData = new FormData(file);
            $.ajax({
                url: rootPath + "editComment",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                success: function (result) {
                    if (result.code = 200) {
                        $("#commentEditDialog").modal('hide');
                        commentTo_page($("#commentCurrent").text())
                    } else {
                        alert(result.msg);
                    }
                }
            })
        }
    });
    //删除评论请求
    $("tbody").on("click", "#btn-delComment", function () {
        if (confirm("确定要删除id为【" + $(this).attr("del-id") + "】的评论信息么？")) {
            var id = $(this).attr("del-id");
            $.ajax({
                url: rootPath + "delComment?id=" + id,
                type: "GET",
                success: function (result) {
                    alert(result.msg);
                    commentTo_page($("#commentCurrent").text())
                }
            })
        }
    });
    //查询评论请求
    $("#btn-search-comment").click(function () {
        getCommentSelect(1);
    });

    //添加浏览历史请求
    $("div .modal-footer").on("click", "#btn-addHistory", function () {
        var formData = new FormData($("#add_history_form")[0]);
        $.ajax({
            url: rootPath + "addHistory",
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function (result) {
                if (result.code == 200) {
                    alert(result.msg);
                    $("#newHistoryDialog").modal("hide");
                    historyTo_page(1);
                } else {
                    confirm(result.msg);
                }
            }
        })
    });
    //修改浏览历史请求 1.获取指定浏览历史信息 2.修改指定浏览历史id
    $("tbody").on("click", "#btn-editHistory", function () {
        var id = $(this).attr("edit-id");
        $.ajax({
            url: rootPath + "getHistoryInfo?id=" + id,
            type: "GET",
            success: function (result) {
                $("#edit-historyId").val(result.data.historyId);
                $("#edit_history_user").val(result.data.user.userName);
                $("#edit_history_comic").val(result.data.comic.comicName);
                $("#edit_history_chapter").val(result.data.detail.chapterName);
                $("#edit_history_time").val(result.data.lastReadTime.replace(" ","T"));
                $("edit_history_status").val(result.data.status)
            }
        });
        $("#historyEditDialog").modal({
            backdrop: "static"
        });
    });
    $("#btn-updateHistory").click(function () {
        if (confirm("确定要修改id为" + $("#edit_orderId").val() + "的浏览历史信息么？")) {
            var file = document.getElementById("edit_history_form");
            var formData = new FormData(file);
            $.ajax({
                url: rootPath + "editHistory",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                success: function (result) {
                    if (result.code = 200) {
                        $("#historyEditDialog").modal('hide');
                        historyTo_page($("#historyCurrent").text())
                    } else {
                        alert(result.msg);
                    }
                }
            })
        }
    });
    //删除浏览历史请求
    $("tbody").on("click", "#btn-delHistory", function () {
        if (confirm("确定要删除id为【" + $(this).attr("del-id") + "】的浏览历史信息么？")) {
            var id = $(this).attr("del-id");
            $.ajax({
                url: rootPath + "delHistory?id=" + id,
                type: "GET",
                success: function (result) {
                    alert(result.msg);
                    historyTo_page($("#historyCurrent").text())
                }
            })
        }
    });
    //查询历史请求
    $("#btn-search-history").click(function () {
        getHistorySelect(1);
    });

    //其余按钮绑定事件
    $("tbody").on("click", "#user-comment", function (){
        $("#commentPage a").click();
        $("#addComment").click();
        var form = $("#newCommentDialog form");
        form.find("#add_comment_userName").val($(this).attr("user-name"));
    });
    $("tbody").on("click", "#user-order", function (){
        $("#orderPage a").click();
        $("#addOrder").click();
        var form = $("#newOrdersDialog form");
        form.find("#edit_order_user").val($(this).attr("user-name"));
    });
    $("tbody").on("click", "#user-history", function (){
        $("#historyPage a").click();
        $("#addHistory").click();
        var form = $("#newHistoryDialog form");
        form.find("#add_history_user").val($(this).attr("user-name"));
    });
    $("tbody").on("click", "#comic-order", function (){
        $("#orderPage a").click();
        $("#addOrder").click();
        var form = $("#newOrdersDialog form");
        form.find("#add_order_comic").val($(this).attr("comic-name"));
    });
    $("tbody").on("click", "#comic-detail", function (){
        $("#detailPage a").click();
        $("#addDetail").click();
        var form = $("#addDetailDialog form");
        form.find("#new_detail_comicname").val($(this).attr("comic-name"));
    });
    $("tbody").on("click", "#comic-comment", function (){
        $("#commentPage a").click();
        $("#addComment").click();
        var form = $("#newCommentDialog form");
        form.find("#add_comment_type").val(0);
        form.find("#add_coment_aim").val($(this).attr("comic-id"));
    });
    $("tbody").on("click", "#detail-comment", function (){
        $("#commentPage a").click();
        $("#addComment").click();
        var form = $("#newCommentDialog form");
        form.find("#add_comment_type").val(1);
        form.find("#add_coment_aim").val($(this).attr("detail-id"));
    });
    $("tbody").on("click", "#detail-history", function (){
        $("#historyPage a").click();
        $("#addHistory").click();
        var form = $("#newHistoryDialog form");
        form.find("#add_history_comic").val($(this).attr("comic-name"));
        form.find("#add_history_detail").val($(this).attr("detail-name"));
    });
});
//Ajax请求


