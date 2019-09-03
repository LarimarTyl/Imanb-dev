package com.larimar.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.larimar.entity.Comic;
import com.larimar.entity.Detail;
import com.larimar.entity.History;
import com.larimar.entity.User;
import com.larimar.service.ComicService;
import com.larimar.service.DetailService;
import com.larimar.service.HistoryService;
import com.larimar.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.larimar.util.DateUtil.localDateToString;
import static com.larimar.util.FileUtil.getImages;

/**
 * @author Larimar
 * @time 2019/8/23 周五 15:37
 */
@Controller
public class ComicController {
    @Autowired
    ComicService comicService;
    @Autowired
    DetailService detailService;
    @Autowired
    HistoryService historyService;
    @Autowired
    LikeService likeService;

    @RequestMapping("/list")
    public String portfolio(@RequestParam(value = "pn" ,defaultValue = "1") Integer pn, HttpServletRequest request){
        PageHelper.startPage(pn,10);
        List<Comic> comic = comicService.queryAllComic();
        PageInfo comics = new PageInfo(comic);
        request.getSession().setAttribute("comics",comics);
        return "list";
    }

    @RequestMapping("/updateLog")
    public String updateLog(@RequestParam(value = "pn" ,defaultValue = "1") Integer pn,HttpServletRequest request){
        PageHelper.startPage(pn,4);
        List<Detail> updateLogs = detailService.queryAllDetail();
        for (Detail d : updateLogs) {
            d.setLikes(likeService.selectDetailLikeNum(d.getDetailId()));
        }
        PageInfo updateLog = new PageInfo(updateLogs);
        request.getSession().setAttribute("updateLog",updateLog);
        return "updateLog";
    }

    @RequestMapping("/detail")
    public String detail(Integer id, HttpServletRequest request){
        Comic comic= comicService.getComicById(id);
        comic.setLikes(likeService.selectComicLikeNum(id));
        List<Detail> details = detailService.queryComicDetail(id);
        String type = comic.getType();
        //类型相似的漫画
        ArrayList<Comic> typeLike = new ArrayList<>();
        if (type.lastIndexOf("/")!=-1){
            String[] types = type.split("/");
            for (String s : types) {
                typeLike.addAll(comicService.queryComicTypeLike(s));
            }
        }
        request.getSession().setAttribute("comic",comic);
        request.getSession().setAttribute("details",details);
        request.getSession().setAttribute("typeLike",typeLike);
        return "detail";
    }
    @RequestMapping("showDetail")
    public String showDetail(Integer id,HttpServletRequest request){
        String comicRoot = "F:\\MavenDemo\\Imanb-dev\\src\\main\\webapp\\static\\images\\comics\\";
        Detail detailById = detailService.getDetailById(id);
        File dest = new File( comicRoot+ detailById.getComic().getRoot() + "\\" + detailById.getPath());
        // FIXME: 2019/8/29  detail添加了一个字段用于存放章节对应的目录下的 图片文件名
        detailById.setImages(getImages(dest));
        User user = (User) request.getSession().getAttribute("user");
        //有用户登录就更新历史状态
        if (user!=null){
            //判断是否是最新章节
            History newHistory = new History(user.getUserId(), detailById.getComicId(), id, localDateToString(), null);

            historyService.addHistory(newHistory);
        }
        request.getSession().setAttribute("detail",detailById);
        return "detailInfo";
    }
}
