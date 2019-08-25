package com.larimar.controller;

import com.larimar.entity.User;
import com.larimar.service.UserService;
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
import java.util.UUID;

/**
 * @author Larimar
 * @time 2019/8/23 周五 8:49
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/doLogin")
    public String doLogin(String userName, String password, String validateCode, HttpServletRequest request){
        System.out.println("传过来的参数是："+userName+"**"+password+"**"+validateCode);
        String code = (String) request.getSession().getAttribute("validateCode");
        
        // 1. 判断验证码
        if (!validateCode.equalsIgnoreCase(code)){
            request.getSession().setAttribute("msg","验证码错误");
            return "errors";
        //验证码成功  登陆验证
        }else {
                // 2. 判断用户是否激活
                if (userService.isActive(userName)){
                    User user = userService.doLogin(userName, password);
                    // 3. 登录判断
                    if (user!=null){
                        request.getSession().setAttribute("user",user);
                        System.out.println("登录成功");
                        return "index";
                    }else {
                        request.getSession().setAttribute("msg","用户名或密码错误");
                        return "errors";
                    }
                }else {
                    request.getSession().setAttribute("msg","该账号还未激活，请先去邮箱激活!");
                    return "active";
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
            request.getSession().setAttribute("msg","注册失败");
            return "index";
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
}
