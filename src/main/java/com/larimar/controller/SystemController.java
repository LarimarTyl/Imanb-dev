package com.larimar.controller;


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
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping("admin-dev")
    public String adminIndex(){
        return "admin";
    }

    @RequestMapping("/adminInfo")
    @ResponseBody
    public Msg admin2(){
        List<User> users = userService.queryAllUsers();
        List<Comic> comics = comicService.queryAllComic();
        List<Orders> orders = orderService.selectAllOrders();
        List<Detail> details = detailService.queryAllDetail();
        HashMap<String, List> adminMap = new HashMap<>();
        adminMap.put("users",users);
        adminMap.put("comics",comics);
        adminMap.put("orders",orders);
        adminMap.put("details",details);
        return  Msg.success().add("成功", adminMap);
    }
    /*
     *用户管理模块
     */

}
