//获取根路径
function getRootPath() {
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/' + webName + '/';
}
var rootPath = getRootPath();
$(function () {
    //tabs控制
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
//ajax请求

//初始化数据表
function build_user_tables(users){
    //清空table
    $(".user-table tbody").empty();
    console.log(users);
    $.each(users,function (index, item) {
        var id = $("<td></td>").append(item.id);
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
}
function  build_comic_tables(comics){

}
function build_order_tables(orders){

}
function build_detail_tables(details){

}
//初始化数据表

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
