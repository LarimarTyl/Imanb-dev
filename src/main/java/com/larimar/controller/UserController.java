package com.larimar.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.larimar.entity.*;
import com.larimar.service.*;
import com.larimar.util.DateUtil;
import com.larimar.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author Larimar
 * @time 2019/8/23 周五 8:49
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private ComicService comicService;
    @Autowired
    private DetailService detailService;

    @RequestMapping("/user")
    public String userInfo(@RequestParam(value = "pn",defaultValue = "1")Integer pn, HttpServletRequest request){
        return "user";
    }


    @RequestMapping("/doLogin")
    public String doLogin(String userName, String password, String validateCode, HttpServletRequest request){
        String code = (String) request.getSession().getAttribute("validateCode");
        
        // 1. 判断验证码
        if (!validateCode.equalsIgnoreCase(code)){
            request.getSession().setAttribute("msg","验证码错误");
            return "errors";
        //验证码成功  登陆验证
        }else {
            User user = userService.doLogin(userName, password);
            // 2. 登录判断 成功后判断是否激活
            if (user!=null){
                // 3. 判断用户是否激活
                if (userService.isActive(userName)){
                    request.getSession().setAttribute("user",user);
                    return "index";
                }else {
                    request.getSession().setAttribute("msg","该账号还未激活，请先去邮箱激活!");
                    return "active";
                }
            }else {
                request.getSession().setAttribute("msg","用户名或密码错误");
                return "errors";
            }
            }
    }
    @RequestMapping("/doRegister")
    public String doRegister(User user, HttpServletRequest request, HttpServletResponse response){
        System.out.println(user);
        if (userService.doRegister(user)){
            request.getSession().setAttribute("msg","注册成功,请请点击邮箱激活！");
            System.out.println("注册成功，等待激活");
            return "active";
        }else {
            request.getSession().setAttribute("msg","注册失败,该用户名已被占用。");
            return "errors";
        }
    }
    @RequestMapping("/loginOut")
    public String doLoginOut(HttpServletRequest request){
        request.getSession().invalidate();
        return "index";
    }
    @RequestMapping("/updateUser")
    @ResponseBody
    public Msg updateUserInfo(User user,HttpServletRequest request){
        System.out.println(user);
        if (userService.updateUserInfo(user)) {
            //更新用户后，重新设置用户session回显
            request.getSession().setAttribute("user",userService.findUserById(user.getUserId()));
            return Msg.success().add("更新成功",null);
        }else {
            return Msg.fail().add("更新失败",null);
        }
    }
    @RequestMapping("/changePassword")
    @ResponseBody
    public Msg changePassword(String password,String newPassword,Integer userId,HttpServletRequest request){
        System.out.println("拿到的数据为"+password+"*"+newPassword+"*"+userId);
        User userById = userService.findUserById(userId);
        //判断原密码是否正确
        if (userService.doLogin(userById.getUserName(),password)!=null){
            if (userService.updatePassWord(newPassword, userId)) {
                //更新密码成功后 移除原有session 并跳转至主页重新登录
                request.getSession().invalidate();
                return Msg.success().add("密码更新成功,请使用新密码重新登录！",null);
            }else {
                return Msg.fail().add("密码更改失败，请检查后重试。",null);
            }
        }else {
            return Msg.fail().add("密码错误，请检查后重试。", null);
        }
    }
    @RequestMapping("/changePhoto")
    @ResponseBody
    public Msg changePhoto(HttpServletRequest request, @RequestParam("newPhoto")MultipartFile multipartFile){
        Msg msg = new Msg();
        //获取文件名
        String filename = multipartFile.getOriginalFilename();
        //获取文件后缀
        String suffix = filename.substring(filename.lastIndexOf(".")).toLowerCase();
//        String newFileName = UUID.randomUUID().toString();
        String newFileName = ((User) request.getSession().getAttribute("user")).getUserName();
        File dest = new File("F:\\MavenDemo\\Imanb-dev\\src\\main\\webapp\\static\\images\\user\\" + newFileName + suffix);
        try {
            if (dest.delete()) {
                System.out.println("原头像删除成功");
            }
            multipartFile.transferTo(dest);
        } catch (IOException e) {
//         //回显信息 上传失败
           msg.setCode(400);
           msg.setMsg("上传图片失败。");
            e.printStackTrace();
        }
            //回显信息上传成功
            msg.setCode(200);
            msg.setMsg("上传图片成功");
            //改数据库头像名称
            Integer userId = ((User) request.getSession().getAttribute("user")).getUserId();
            userService.updatePhoto(newFileName,userId);
            //重新获取用户session
        request.getSession().setAttribute("user",userService.findUserById(userId));
        return msg;
    }

    /**
     * 点赞功能
     */
    @RequestMapping("/addDetailLike")
    @ResponseBody
    public Msg addDetailLike(Integer id){
        if (likeService.addDetailLikes(id)) {
            return Msg.success().add("感谢点赞。",likeService.selectDetailLikeNum(id));
        }else {
            return Msg.fail().add("发生未知错误，点赞失败。",likeService.selectDetailLikeNum(id));
        }
    }

    @RequestMapping("/addComicLike")
    @ResponseBody
    public Msg addComicLike(Integer id){
        if (likeService.addComicLikes(id)) {
            return Msg.success().add("感谢点赞。",likeService.selectComicLikeNum(id));
        }else {
            return Msg.fail().add("发生未知错误，点赞失败。",likeService.selectComicLikeNum(id));
        }
    }

    @RequestMapping("/delDetailLike")
    @ResponseBody
    public Msg delDetailLike(Integer id){
        if (likeService.delDetailLikes(id)) {
                return Msg.success().add("取消点赞。",likeService.selectDetailLikeNum(id));
        }else {
            return Msg.fail().add("发生未知错误，取消点赞失败。",likeService.selectDetailLikeNum(id));
        }
    }

    @RequestMapping("/delComicLike")
    @ResponseBody
    public Msg delComicLike(Integer id){
        if (likeService.delComicLikes(id)) {
                return Msg.success().add("取消点赞。",likeService.selectComicLikeNum(id));
        }else {
            return Msg.fail().add("发生未知错误，取消点赞失败。",likeService.selectComicLikeNum(id));
        }
    }

    /**
     * 订阅功能
     */
    @RequestMapping("/userAddOrder")
    @ResponseBody
    public Msg addOrder(Integer id,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null){
            if (orderService.userAddOrders(user.getUserId(),id)) {
                return Msg.success().add("添加订阅成功！",null);
            }else {
                return Msg.fail().add("您已经订阅了该漫画，请勿重复订阅订阅。",null);
            }
        }else {
            return Msg.fail().add("请先登录后，再订阅。",null);
        }
    }
    @RequestMapping("/userDelOrder")
    @ResponseBody
    public Msg userDelOrder(Integer id,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null){
            if (orderService.userDelOrders(user.getUserId(),id)) {
                return Msg.success().add("取消订阅成功！",null);
            }else {
                return Msg.fail().add("取消订阅失败",null);
            }
        }else {
            return Msg.fail().add("请先登录后，再订阅。",null);
        }
    }
    /**
     * 用户信息内ajax控制器
     */
    @RequestMapping("/allRevertInfo")
    @ResponseBody
    public Msg allRevertInfo(@RequestParam(value = "pn",defaultValue = "1")Integer pn,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        PageHelper.startPage(pn,6);
        List<Comment> allRevert = commentService.getUsersRevertComment(user.getUserId());
        PageInfo allReverts = new PageInfo(allRevert);
        return Msg.success().add(null,allReverts);
    }
    @RequestMapping("/noReadRevertInfo")
    @ResponseBody
    public Msg noReadRevertInfo(@RequestParam(value = "pn",defaultValue = "1")Integer pn,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        PageHelper.startPage(pn,6);
        List<Comment> allRevert = commentService.getUsersRevertByStatus(user.getUserId(),0);
        PageInfo allReverts = new PageInfo(allRevert);
        return Msg.success().add(null,allReverts);
    }
    @RequestMapping("/readRevertInfo")
    @ResponseBody
    public Msg readRevertInfo(@RequestParam(value = "pn",defaultValue = "1")Integer pn,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        PageHelper.startPage(pn,6);
        List<Comment> allRevert = commentService.getUsersRevertByStatus(user.getUserId(),-1);
        PageInfo allReverts = new PageInfo(allRevert);
        return Msg.success().add(null,allReverts);
    }
    @RequestMapping("/allOrderInfo")
    @ResponseBody
    public Msg allOrderInfo(@RequestParam(value = "pn",defaultValue = "1")Integer pn,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        PageHelper.startPage(pn,8);
        List<Orders> order = orderService.selectUsersAllOrders(user.getUserId());
        PageInfo orders = new PageInfo(order);
        return Msg.success().add(null,orders);
    }
    @RequestMapping("/readOrderInfo")
    @ResponseBody
    public Msg readOrderInfo(@RequestParam(value = "pn",defaultValue = "1")Integer pn,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        PageHelper.startPage(pn,8);
        List<Orders> order = orderService.selectAllOrdersByStatus(user.getUserId(),-1);
        PageInfo orders = new PageInfo(order);
        return Msg.success().add(null,orders);
    }
    @RequestMapping("/noReadOrderInfo")
    @ResponseBody
    public Msg noReadOrderInfo(@RequestParam(value = "pn",defaultValue = "1")Integer pn,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        PageHelper.startPage(pn,8);
        List<Orders> order = orderService.selectAllOrdersByStatus(user.getUserId(),1);
        PageInfo orders = new PageInfo(order);
        return Msg.success().add(null,orders);
    }
    @RequestMapping("/allHistoryInfo")
    @ResponseBody
    public Msg allHistoryInfo(@RequestParam(value = "pn",defaultValue = "1")Integer pn,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        PageHelper.startPage(pn,8);
        List<History> historie = historyService.selectUsersAllHistory(user.getUserId());
        PageInfo histories = new PageInfo(historie);
        return Msg.success().add(null,histories);
    }
    @RequestMapping("/haveNewHistoryInfo")
    @ResponseBody
    public Msg haveNewHistoryInfo(@RequestParam(value = "pn",defaultValue = "1")Integer pn,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        PageHelper.startPage(pn,8);
        List<History> historie = historyService.selectUsersHistoryByStatus(user.getUserId(),1);
        PageInfo histories = new PageInfo(historie);
        return Msg.success().add(null,histories);
    }
    @RequestMapping("/addComicComment")
    @ResponseBody
    public Msg addComicComment(Comment comment, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        comment.setUserId(user.getUserId());
        comment.setStatus(0);
        comment.setTime(DateUtil.localDateToString());
        commentService.addComicComment(comment);
        Reply comicReply = comicService.getComicReply(comment.getComicId());
        return Msg.success().add(null,comicReply);
    }
    @RequestMapping("/addComicRevert")
    @ResponseBody
    public Msg addComicRevert(Comment comment, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        comment.setUserId(user.getUserId());
        comment.setStatus(0);
        comment.setTime(DateUtil.localDateToString());
        commentService.addRevertComment(comment);
        Reply comicReply = comicService.getComicReply(comment.getComicId());
        return Msg.success().add(null,comicReply);
    }
    @RequestMapping("/delUserComicComment")
    @ResponseBody
    public Msg delUserComicComment(Integer id,Integer comicId){
        if (commentService.delComment(id)) {
            Reply comicReply = comicService.getComicReply(comicId);
            return Msg.success().add("删除成功",comicReply);
        }
        return  Msg.fail().add("删除失败",null);
    }
    @RequestMapping("/addDetailComment")
    @ResponseBody
    public Msg addDetailComment(Comment comment, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        comment.setUserId(user.getUserId());
        comment.setTime(DateUtil.localDateToString());
        comment.setStatus(0);
        commentService.addDetailComment(comment);
        Reply detailReply = detailService.getDetailReply(comment.getDetailId());
        return Msg.success().add(null,detailReply);
    }
    @RequestMapping("/addUserDetailRevert")
    @ResponseBody
    public Msg addDetailRevert(Comment comment, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        comment.setUserId(user.getUserId());
        comment.setStatus(0);
        comment.setTime(DateUtil.localDateToString());
        commentService.addRevertComment(comment);
        Reply detailReply = detailService.getDetailReply(comment.getDetailId());
        return Msg.success().add(null,detailReply);
    }
    @RequestMapping("/delUserDetailComment")
    @ResponseBody
    public Msg delUserDetailComment(Integer id,Integer detailId){
        if (commentService.delComment(id)) {
            Reply detailReply = detailService.getDetailReply(detailId);
            return Msg.success().add("删除成功",detailReply);
        }
        return  Msg.fail().add("删除失败",null);
    }
    @RequestMapping("/userRevertComic")
    @ResponseBody
    public  Msg userRevertComic(@RequestParam(value = "pn",defaultValue = "1")Integer pn,Comment comment,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        comment.setUserId(user.getUserId());
        comment.setTime(DateUtil.localDateToString());
        commentService.addRevertComment(comment);
        return Msg.success().add("回复成功",null);
    }
    @RequestMapping("/haveRead")
    @ResponseBody
    public Msg haveRead(Integer id){
        commentService.updateStatus(id,-1);
        return Msg.success();
    }
}
