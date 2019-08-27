//获取根路径
function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
}
var rootPath = getRootPath();
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
//初始化数据
var totalPage,currentPage;//总页数、当前页
$(function () {
    to_Page(1);
});
    function to_Page(pn) {
        $.ajax({
            url:rootPath+"adminInfo",
            data:"pn="+pn,
            type:"GET",
            success:function (result) {
                console.log(result);
                var users = result.data.users;
                var details = result.data.details;
                var orders = result.data.orders;
                var comics = result.data.comics;
                build_user_tables(users);
                build_order_tables(orders);
                build_detail_tables(details);
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
            case (0):userstatu="未激活";
            case (1):userstatu="已激活";
            case (-1):userstatu="封禁";
        }
        var status = $("<th></th>").append(userstatu);
        var edit = $("<a href='#' class='btn btn-primary btn-xs' data-toggle='modal' data-target='#userEditDialog' id='editUser'>修改</a>").attr("edit-id",item.userId);
        var del = $("<a href='#' class='btn btn-danger btn-xs' id='delUser'>删除</a>").attr("del-id",item.userId);
        var handle = $("<td></td>").append(edit).append(" ").append(del);
       $("<tr></tr>").append(id).append(photo).append(username)
           .append(password).append(gender).append(email).append(qq)
           .append(nickName).append(status).append(handle).appendTo(".user-table tbody")
    });
    //添加页面信息
    $(".userPageInfo").empty().append("当前第"+users.pageNum+"页，总共"+users.pages+"页，总共"+users.total+"条记录");
    //页面控制
    $("#userPageControl ul li").eq(0).click(function () {
        to_Page(1);
    });
    $("#userPageControl ul li").eq(1).click(function () {
        if (!users.isFirstPag){
            to_Page(users.pageNum-1);
        }else {
            alert("已经到首页了")
        }
    });
    $("#userPageControl ul li").eq(2).find("a").text(users.pageNum);
    $("#userPageControl ul li").eq(3).click(function () {
        if (!users.isLastPage){
            to_Page(users.pageNum+1);
        }else {
            alert("没有下一页了嗷！")
        }
    });
    $("#userPageControl ul li").eq(4).click(function () {
        to_Page(users.pages)
    })

}
function build_comic_tables(comics) {
    var list = comics.list;//获取数据
    //清空table
    $(".comic-table tbody").empty();
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
            case (0):comicstatu="完结";
            case (1):comicstatu="连载中";
            case (-1):comicstatu="停更";
        }
        var status = $("<th></th>").append(comicstatu);
        var mark = $("<th></th>").append(mark);
        var edit = $("<a href='#' class='btn btn-primary btn-xs' data-toggle='modal' data-target='#comicEditDialog' id='btn-editComic'>修改</a>").attr("edit-id",item.comicId);
        var del = $("<a href='#' class='btn btn-danger btn-xs' id='btn-delComic'>删除</a>").attr("del-id",item.comicId);
        var handle = $("<td></td>").append(edit).append(" ").append(del);
        $("<tr></tr>").append(id).append(comicImg).append(comicName).append(author).append(type)
            .append(location).append(newUpdate).append(newChapterName).append(textarea).append(status)
            .append(mark).append(handle).appendTo(".comic-table tbody");
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
        var edit = $("<a href='#' class='btn btn-primary btn-xs' data-toggle='modal' data-target='#detailEditDialog' id='btn-editDetail'>修改</a>").attr("edit-id",item.detailId);
        var del = $("<a href='#' class='btn btn-danger btn-xs' id='btn-delDetail'>删除</a>").attr("del-id",item.detailId);
        var handle = $("<td></td>").append(edit).append(" ").append(del);
        $("<tr></tr>").append(id).append(comicPath).append(comicName).append(chapterName).append(path)
            .append(textarea).append(updateTime).append(handle).appendTo(".detail-table tbody")
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
                case (0):orderstatu="未更新";
                case (1):orderstatu="已更新";
                case (-1):orderstatu="已读更新";
            }
            var status = $("<th></th>").append(orderstatu);
            var edit = $("<a href='#' class='btn btn-primary btn-xs' data-toggle='modal' data-target='#ordersEditDialog' id='btn-editOrder'>修改</a>").attr("edit-id",item.ordersId);
            var del = $("<a href='#' class='btn btn-danger btn-xs' id='btn-delOrder'>删除</a>").attr("del-id",item.ordersId);
            var handle = $("<td></td>").append(edit).append(" ").append(del);
            $("<tr></tr>").append(id).append(comic).append(comicPath).append(userName)
                .append(userPath).append(status).append(handle).appendTo(".order-table tbody");
        })
}
//更新数据表

//Ajax请求
$(function () {
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
                    var users = result.data.users;
                    build_user_tables(users);
                    $("#addUserDialog").modal("hide")
                }else {
                    alert(result.msg)
                }
            }
        })
    });

   });


//Ajax请求

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
