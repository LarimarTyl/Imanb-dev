package com.larimar.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.larimar.entity.Comic;
import com.larimar.entity.Detail;
import com.larimar.entity.Orders;
import com.larimar.entity.User;
import com.larimar.service.*;
import com.larimar.util.Msg;
import com.larimar.util.ValidateCodeUtil;
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
import java.util.HashMap;
import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/23 周五 8:50
 */
@Controller
public class SystemController {
    @Autowired
    UserService userService;
    @Autowired
    ComicService comicService;
    @Autowired
    DetailService detailService;
    @Autowired
    OrderService orderService;
    @Autowired
    CommentService commentService;

    /**
     * 生成二维码功能
     * @param request
     * @param response
     * @throws IOException
     */
    /*
    验证码管理
     */
    @RequestMapping("/code")
    public void changeCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
        ValidateCodeUtil.getValidateCode(request,response);
    }

    @RequestMapping("/admin")
    public String index(){
        return "admin";
    }

    @RequestMapping("/admins")
    public String admin(HttpServletRequest request){
        List<User> users = userService.queryAllUsers();
        request.getSession().setAttribute("users",users);
        List<Comic> comics = comicService.queryAllComic();
        request.getSession().setAttribute("comics",comics);
        List<Orders> orders = orderService.selectAllOrders();
        request.getSession().setAttribute("orders",orders);
        List<Detail> details = detailService.queryAllDetail();
        request.getSession().setAttribute("details",details);
        return "admin";
    }

    /*
     用户管理
     */
    @RequestMapping("/userInfo")
    @ResponseBody
    public Msg userInfo(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn,7);
        List<User> user = userService.queryAllUsers();
        PageInfo users = new PageInfo(user,5);
        return  Msg.success().add("成功", users);
    }
    @RequestMapping("/addUser")
    @ResponseBody
    public Msg addUser(User user,@RequestParam("file") MultipartFile file){
        Msg msg = new Msg();
        //获取文件名
        String filename = file.getOriginalFilename();
        //获取文件后缀
        String suffix = filename.substring(filename.lastIndexOf(".")).toLowerCase();
        String newFileName = user.getUserName();
        File dest = new File("F:\\MavenDemo\\Imanb-dev\\src\\main\\webapp\\static\\images\\user\\" + newFileName + suffix);
        try {
            if (dest.delete()) {
                System.out.println("原头像删除成功");
            }
            file.transferTo(dest);
        } catch (IOException e) {
//         //回显信息 上传失败
            msg.setCode(400);
            msg.setMsg("头像选择有误，请重试。");
            e.printStackTrace();
            return msg;
        }
        user.setPhoto(newFileName);
        if (userService.doRegister(user)) {
            msg.setCode(200);
            msg.setMsg("添加用户成功！");
        }else{
            msg.setCode(400);
            msg.setMsg("添加用户失败！");
        }
        return msg;
    };
    /*
     用户管理
     */

    /*
    漫画管理
     */
    @RequestMapping("/comicInfo")
    @ResponseBody
    public Msg comicInfo(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn,5);
        List<Comic> comic = comicService.queryAllComic();
        PageInfo comics = new PageInfo(comic,5);
        return  Msg.success().add("成功", comics);
    }
    /*
    漫画管理
     */
    
    /*
    章节详情管理
     */
    @RequestMapping("/detailInfo")
    @ResponseBody
    public Msg detailInfo(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn,5);
        List<Detail> detail = detailService.queryAllDetail();
        PageInfo details = new PageInfo(detail,5);
        return  Msg.success().add("成功", details);
    }
    /*
    章节详情管理
     */
    
    /*
    订阅管理
     */
    @RequestMapping("/orderInfo")
    @ResponseBody
    public Msg orderInfo(@RequestParam(value = "pn",defaultValue = "1")int pn){
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn,5);
        List<Orders> order = orderService.selectAllOrders();
        PageInfo orders = new PageInfo(order,5);
        return  Msg.success().add("成功", orders);
    }
    /*
    订阅管理
     */
}
