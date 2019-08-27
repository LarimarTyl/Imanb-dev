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
    @RequestMapping("/code")
    public void changeCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
        ValidateCodeUtil.getValidateCode(request,response);
    }
    @RequestMapping("/admin")
    public String index(){
        return "admin";
    }
    @RequestMapping("/adminInfo")
    @ResponseBody
    public Msg admin2(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn,5);
        List<User> users = userService.queryAllUsers();
        PageInfo user = new PageInfo(users);
        System.out.println(user);
        PageHelper.startPage(pn,5);
        List<Comic> comics = comicService.queryAllComic();
        PageInfo comic = new PageInfo(comics);
        PageHelper.startPage(pn,5);
        List<Orders> orders = orderService.selectAllOrders();
        PageInfo order = new PageInfo(orders);
        PageHelper.startPage(pn,5);
        List<Detail> details = detailService.queryAllDetail();
        PageInfo detail = new PageInfo(details);
        HashMap<String, PageInfo> adminMap = new HashMap<>();
        adminMap.put("users",user);
        adminMap.put("comics",comic);
        adminMap.put("orders",order);
        adminMap.put("details",detail);
        return  Msg.success().add("成功", adminMap);
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
    @RequestMapping("/addUser")
    @ResponseBody
    public Msg addUser(User user,@RequestParam("file") MultipartFile file){
        String name = file.getOriginalFilename();
        System.out.println(user.getUserName());
        return null;
    };
    @RequestMapping("/admin-dev")
    public String adminIndex(){
        return "admin";
    }

    /*
     *用户管理模块
     */

}
