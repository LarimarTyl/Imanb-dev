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
        })
        });
// 分类列表选择案例
//漫画项选择案例
    $(function () {
       $(".book-item li").mouseenter(function () {
           $(this).toggleClass("more-up")
       })
    });
//漫画项选择案例
// 点赞案例
$(function () {
    $(".focus #like");
    $(".focus #unlike").addClass("hideIt");
    $(".com-data").find(".likes").click(function(){
        $(this).attr("href","").toggleClass("likes-select")
    });
    $(".focus").on("click",".isLike",function(){
        $(this).toggleClass("likes").toggleClass("unlike");
        $(".focus span").toggleClass("hideIt");
    });
    $("#like").click(function () {
        alert("点赞加1")
    });
    $("#unlike").click(function () {
        alert("点赞减1")
    })

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

