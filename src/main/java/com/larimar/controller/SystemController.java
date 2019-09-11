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
import org.apache.ibatis.annotations.Param;
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

import static com.larimar.util.FileUtil.deleteFile;
import static com.larimar.util.FileUtil.getImages;


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
    @Autowired
    LikeService likeService;

    /**
     * 页面初始加载数据
     */
    @RequestMapping("/")
    public String index(HttpServletRequest request){
        List<Comic> likestComic = comicService.findLikestComic();
        List<Comic> newComic = comicService.findNewComic();
        List<Comic> comicByOrderNum = comicService.findComicByOrderNum();
        PageHelper.startPage(1,5);
        List<Comic> chineseRank = comicService.findChineseRank();
        PageHelper.startPage(1,5);
        List<Comic> japaneseRank = comicService.findJapaneseRank();
        PageHelper.startPage(1,5);
        List<Comic> allRank = comicService.findAllRank();
        PageHelper.startPage(1,4);
        List<Detail> details = detailService.queryAllDetail();
        for (Detail d : details) {
            d.setLikes(likeService.selectDetailLikeNum(d.getDetailId()));
        }
        request.getSession().getServletContext().setAttribute("likestComic",likestComic);
        request.getSession().getServletContext().setAttribute("newComic",newComic);
        request.getSession().getServletContext().setAttribute("comicByOrderNum",comicByOrderNum);
        request.getSession().getServletContext().setAttribute("chineseRank",chineseRank);
        request.getSession().getServletContext().setAttribute("japaneseRank",japaneseRank);
        request.getSession().getServletContext().setAttribute("allRank",allRank);
        request.getSession().getServletContext().setAttribute("details",details);
        return "index";
    }
    @RequestMapping("/index")
    public String toIndex(){
        return "redirect:/";
    }
    /**
     *   验证码管理
     */
    @RequestMapping("/code")
    public void changeCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
        ValidateCodeUtil.getValidateCode(request,response);
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }
    @RequestMapping("/contact")
    public String contact(){
        return "contact";
    }
}
