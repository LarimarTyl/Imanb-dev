//获取根路径
function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
}
var rootPath = getRootPath();
//图片上传判定
$(function(){
    //单个文件上传
    $(".newInputIcon").change(function () {
        var filePath = $(this).val();
        var fileType = filePath.substring(filePath.lastIndexOf("."));
        if (fileType == ".jpg" ||fileType == ".JPG") {
            $(this).parent().siblings("img").attr("src", URL.createObjectURL($(this)[0].files[0]));
        } else {
            var flag = confirm("上传图片格式不正确，请重新选择(.jpg)");
        }
    });
// 批量上传
    $(".newInputPath").change(function () {
        var files = this.files;
        var show = $(".showImg");
        show.empty();
        for(var i = 0 ; i<files.length;i++){
            var img = $("<img/>").addClass("img");
            img.attr("src",URL.createObjectURL(files[i]));
            console.log(img);
            img.appendTo(show);
        }
        console.log(files);
        console.log(show)
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
    }
    function userTo_page(pn) {
        $.ajax({
            url:rootPath+"userInfo",
            data:"pn="+pn,
            type:"GET",
            success:function (result) {
                var users = result.data;
                build_user_tables(users);
            }
        })
    }
    function orderTo_page(pn) {
        $.ajax({
            url:rootPath+"orderInfo",
            data:"pn="+pn,
            type:"GET",
            success:function (result) {
                var orders = result.data;
                build_order_tables(orders);
            }
        })
    }
    function detailTo_page(pn) {
        $.ajax({
            url:rootPath+"detailInfo",
            data:"pn="+pn,
            type:"GET",
            success:function (result) {
                var details = result.data;
                build_detail_tables(details);
            }
        })
    }
    function comicTo_page(pn) {
        $.ajax({
            url:rootPath+"comicInfo",
            data:"pn="+pn,
            type:"GET",
            success:function (result) {
                var comics = result.data;
                build_comic_tables(comics);
            }
        })
    }
    //更新数据表
    function build_user_tables(users){
            var list = users.list;//获取数据
        //清空table
        $(".user-table tbody").empty();
        //添加数据
        $.each(list,function (index, item) {
            var id = $("<td></td>").append(item.userId);
            var img = $("<img alt=''  class='userPhoto'>").attr("src","/images/user/"+item.photo+".jpg");
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
                    userstatu="未激活";break;
                case (1):userstatu="已激活";break;
                case (-1):userstatu="封禁";
            }
            var status = $("<th></th>").append(userstatu);
            var edit = $("<a  class='btn btn-primary btn-xs' data-toggle='modal'  id='editUser'>修改</a>").attr("edit-id",item.userId);
            var del = $("<a  class='btn btn-danger btn-xs' id='delUser'>删除</a>").attr("del-id",item.userId);
            var handle = $("<td></td>").append(edit).append(" ").append(del);
           $("<tr></tr>").append(id).append(photo).append(username)
               .append(password).append(gender).append(email).append(qq)
               .append(nickName).append(status).append(handle).appendTo(".user-table tbody")
        });
        //添加页面信息
        $(".userPageInfo").empty().append("当前第<span id='userCurrent'>"+users.pageNum+"</span>页，总共"+users.pages+"页，总共"+users.total+"条记录");
        var totalPage = users.pages;
        var currentPage = users.pageNum;
        //页面控制
        //更新页面控制内容（清除原有的绑定事件）
        $(".userPage").empty().append($("<li><a>首页</a></li>")).append($("<li><a>上一页 </a></li>")).append($("<li><a>"+currentPage+"</a></li>"))
            .append($("<li><a>下一页</a></li>")).append($("<li><a >尾页</a></li>"));
        $("#userPageControl ul li").eq(0).click(function () {
            userTo_page(1);
        });
        $("#userPageControl ul li").eq(1).click(function () {
            if (currentPage-1>0){
                userTo_page(currentPage-1);
            }else {
                alert("已经到顶了嗷，没有更多信息啦！")
            }
        });
        $("#userPageControl ul li").eq(2).find("a").text(currentPage);
        $("#userPageControl ul li").eq(3).click(function () {

            if (currentPage+1<=totalPage){
                userTo_page(currentPage+1);
            }else {
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
        $.each(list,function (index, item) {
            var id = $("<td></td>").append(item.comicId);
            var Img = $("<img alt='' class='comicPhoto'>").attr("src","/images/comics/"+item.path+"/cover.jpg");
            var comicImg = $("<th></th>").append(Img);
            var comicName = $("<th></th>").append(item.comicName);
            var author = $("<th></th>").append(item.author);
            var type = $("<th></th>").append(item.type);
            var location = $("<th></th>").append(item.location);
            var newUpdate = $("<th></th>").append(item.newUpdate);
            var newChapterName = $("<th></th>").append(item.newChapterName);
            var text = $("<textarea name='introduction' style='border: none' id='text' class='form-control' readonly rows='3'></textarea>").append(item.introduction);
            var textarea = $("<th></th>").append(text);
            var comicstatu;
            switch (item.status) {
                case (0):comicstatu="完结";break;
                case (1):comicstatu="连载中";break;
                case (-1):comicstatu="停更";
            }
            var status = $("<th></th>").append(comicstatu);
            var mark = $("<th></th>").append(mark);
            var edit = $("<a  class='btn btn-primary btn-xs' data-toggle='modal'  id='btn-editComic'>修改</a>").attr("edit-id",item.comicId);
            var del = $("<a  class='btn btn-danger btn-xs' id='btn-delComic'>删除</a>").attr("del-id",item.comicId);
            var handle = $("<td></td>").append(edit).append(" ").append(del);
            $("<tr></tr>").append(id).append(comicImg).append(comicName).append(author).append(type)
                .append(location).append(newUpdate).append(newChapterName).append(textarea).append(status)
                .append(mark).append(handle).appendTo(".comic-table tbody");
            });
        //添加页面信息
        $(".comicPageInfo").empty().append("当前第<span id='comicCurrent'>"+comics.pageNum+"</span>页，总共"+comics.pages+"页，总共"+comics.total+"条记录");
        var totalPage = comics.pages;
        var currentPage = comics.pageNum;
        //页面控制
        //更新页面控制内容（清除原有的绑定事件）
        $(".comicPage").empty().append($("<li><a>首页</a></li>")).append($("<li><a>上一页 </a></li>")).append($("<li><a>"+currentPage+"</a></li>"))
            .append($("<li><a>下一页</a></li>")).append($("<li><a >尾页</a></li>"));
        $("#comicPageControl ul li").eq(0).click(function () {
            comicTo_page(1);
        });
        $("#comicPageControl ul li").eq(1).click(function () {
            if (currentPage-1>0){
                comicTo_page(currentPage-1);
            }else {
                alert("已经到顶了嗷，没有更多信息啦！")
            }
        });
        $("#comicPageControl ul li").eq(2).find("a").text(currentPage);
        $("#comicPageControl ul li").eq(3).click(function () {

            if (currentPage+1<=totalPage){
                comicTo_page(currentPage+1);
            }else {
                alert("已经到底了嗷，没有更多信息啦！")
            }
        });
        $("#comicPageControl ul li").eq(4).click(function () {
            comicTo_page(totalPage)
        })
    }
    function build_detail_tables(details){
        var list = details.list;//获取数据
        $(".detail-table tbody").empty();
        $.each(list,function (index, item) {
            var id = $("<td></td>").append(item.detailId);
            var comicImg = $("<img alt='' class='comicPhoto'>").attr("src","/images/comics/"+item.comic.path+"/cover.jpg");
            var comicPath = $("<th></th>").append(comicImg);
            var comicName = $("<th></th>").append(item.comic.comicName);
            var chapterName = $("<th></th>").append(item.chapterName);
            var path = $("<th></th>").append(item.path);
            var generalize = $("<textarea name='introduction' style='border: none' class='form-control' readonly rows='3'></textarea>").append(item.generalize);
            var textarea = $("<th></th>").append(generalize);
            var updateTime = $("<th></th>").append(item.updateTime);
            var edit = $("<a  class='btn btn-primary btn-xs' data-toggle='modal'  id='btn-editDetail'>修改</a>").attr("edit-id",item.detailId);
            var del = $("<a  class='btn btn-danger btn-xs' id='btn-delDetail'>删除</a>").attr("del-id",item.detailId);
            var handle = $("<td></td>").append(edit).append(" ").append(del);
            $("<tr></tr>").append(id).append(comicPath).append(comicName).append(chapterName).append(path)
                .append(textarea).append(updateTime).append(handle).appendTo(".detail-table tbody")
        });
//添加页面信息
        $(".detailPageInfo").empty().append("当前第<span id='detailCurrent'>"+details.pageNum+"</span>页，总共"+details.pages+"页，总共"+details.total+"条记录");
        var totalPage = details.pages;
        var currentPage = details.pageNum;
        //页面控制
        //更新页面控制内容（清除原有的绑定事件）
        $(".detailPage").empty().append($("<li><a>首页</a></li>")).append($("<li><a>上一页 </a></li>")).append($("<li><a>"+currentPage+"</a></li>"))
            .append($("<li><a>下一页</a></li>")).append($("<li><a >尾页</a></li>"));
        $("#detailPageControl ul li").eq(0).click(function () {
            detailTo_page(1);
        });
        $("#detailPageControl ul li").eq(1).click(function () {
            if (currentPage-1>0){
                detailTo_page(currentPage-1);
            }else {
                alert("已经到顶了嗷，没有更多信息啦！")
            }
        });
        $("#detailPageControl ul li").eq(2).find("a").text(currentPage);
        $("#detailPageControl ul li").eq(3).click(function () {

            if (currentPage+1<=totalPage){
                detailTo_page(currentPage+1);
            }else {
                alert("已经到底了嗷，没有更多信息啦！")
            }
        });
        $("#detailPageControl ul li").eq(4).click(function () {
            detailTo_page(totalPage)
        })
    }
    function build_order_tables(orders){
        var list = orders.list;//获取数据
    //清空table
        $(".order-table tbody").empty();
        $.each(list,function (index, item) {
                var id = $("<td></td>").append(item.ordersId);
                var comic = $("<th></th>").append(item.comic.comicName);
                var comicImg = $("<img alt='' class='comicPhoto'>").attr("src","/images/comics/"+item.comic.path+"/cover.jpg");
                var comicPath = $("<th></th>").append(comicImg);
                var userName = $("<th></th>").append(item.user.userName);
                var userImg = $("<img alt='' class='userPhoto'>").attr("src","/images/user/"+item.user.photo+".jpg");
                var userPath = $("<th></th>").append(userImg);
                var orderstatu;
                switch (item.status) {
                    case (0):orderstatu="未更新";break;
                    case (1):orderstatu="已更新";break;
                    case (-1):orderstatu="已读更新";
                }
                var status = $("<th></th>").append(orderstatu);
                var edit = $("<a class='btn btn-primary btn-xs' data-toggle='modal'  id='btn-editOrder'>修改</a>").attr("edit-id",item.ordersId);
                var del = $("<a  class='btn btn-danger btn-xs' id='btn-delOrder'>删除</a>").attr("del-id",item.ordersId);
                var handle = $("<td></td>").append(edit).append(" ").append(del);
                $("<tr></tr>").append(id).append(comic).append(comicPath).append(userName)
                    .append(userPath).append(status).append(handle).appendTo(".order-table tbody");
            });
        //添加页面信息
        $(".orderPageInfo").empty().append("当前第<span id='orderCurrent'>"+orders.pageNum+"</span>页，总共"+orders.pages+"页，总共"+orders.total+"条记录");
        var totalPage = orders.pages;
        var currentPage = orders.pageNum;
        //页面控制
        //更新页面控制内容（清除原有的绑定事件）
        $(".orderPage").empty().append($("<li><a>首页</a></li>")).append($("<li><a>上一页 </a></li>")).append($("<li><a>"+currentPage+"</a></li>"))
            .append($("<li><a>下一页</a></li>")).append($("<li><a >尾页</a></li>"));
        $("#orderPageControl ul li").eq(0).click(function () {
            orderTo_page(1);
        });
        $("#orderPageControl ul li").eq(1).click(function () {
            if (currentPage-1>0){
                orderTo_page(currentPage-1);
            }else {
                alert("已经到顶了嗷，没有更多信息啦！")
            }
        });
        $("#orderPageControl ul li").eq(2).find("a").text(currentPage);
        $("#orderPageControl ul li").eq(3).click(function () {

            if (currentPage+1<=totalPage){
                orderTo_page(currentPage+1);
            }else {
                alert("已经到底了嗷，没有更多信息啦！")
            }
        });
        $("#orderPageControl ul li").eq(4).click(function () {
            orderTo_page(totalPage)
        })
        }

//用户Ajax请求
$(function () {
    //添加用户请求
    $("div .modal-footer").on("click","#btn-addUser",function () {
        var file = document.getElementById("new_user_form");
        var formData = new FormData(file);
        console.log(formData);
        $.ajax({
            url:rootPath+"addUser",
            type:"POST",
            data:formData,
            processData:false,
            contentType:false,
            success:function (result) {
                if (result.code==200){
                    var users = result.data;
                    userTo_page(1);
                    $("#addUserDialog").modal("hide")
                }else {
                    confirm(result.msg)
                }
            }
        })
    });
    //修改用户请求
    $("tbody").on("click","#editUser",function () {
        console.log($("#userCurrent").text());
        console.log($(this).attr("edit-id"));
    })
   });
//Ajax请求


