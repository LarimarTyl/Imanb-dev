package com.larimar.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.larimar.entity.*;
import com.larimar.service.*;
import com.larimar.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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
    @Autowired
    OrderService orderService;

    @RequestMapping("/list")
    public String portfolio(@RequestParam(value = "pn" ,defaultValue = "1") Integer pn, HttpServletRequest request){
        PageHelper.startPage(pn,10);
        List<Comic> comic = comicService.queryAllComic();
        PageInfo comics = new PageInfo(comic);
        request.getSession().setAttribute("comics",comics);
        return "list";
    }


    /**
     * 搜索漫画
     * @param comicName 漫画名 粗查询
     * @param request 用于存放结果
     * @return 返回查找数据
     */
    @RequestMapping("/searchComic")
    public String searchComic(@RequestParam(value = "comicName",defaultValue = " ") String comicName, HttpServletRequest request){
        PageHelper.startPage(1,10);
        List<Comic> comic = comicService.getComicNameLike(comicName);
        if (comic.size()>0){
            PageInfo comics = new PageInfo(comic);
            request.getSession().setAttribute("comics",comics);
            return "list";
        }else {
            request.getSession().setAttribute("msg","找不到类似的数据嗷~~~~~~");
            return "errors";
        }
    }
    @RequestMapping("/searchDetail")
    public String searchDetail(@RequestParam(value = "comicName",defaultValue = " ") String comicName,@RequestParam(value = "pn",defaultValue = "1")Integer pn,HttpServletRequest request){
        PageHelper.startPage(pn,10);
        List<Detail> updateLogs = detailService.queryComicDetailByName(comicName);
        if (updateLogs.size()>1){
            for (Detail d : updateLogs) {
                d.setLikes(likeService.selectDetailLikeNum(d.getDetailId()));
            }
            PageInfo updateLog = new PageInfo(updateLogs);
            request.getSession().setAttribute("updateLog",updateLog);
            return "updateLog";
        }else {
            request.getSession().setAttribute("msg","找不到类似的数据嗷~~~~~~");
            return "errors";
        }
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
       if (comic!=null){
           comic.setLikes(likeService.selectComicLikeNum(id));
           List<Detail> details = detailService.queryComicDetail(id);
           String type = comic.getType();
           //评论
           Reply comicReply = comicService.getComicReply(id);
           //类型相似的漫画
           ArrayList<Comic> typeLike = new ArrayList<>();
           if (type.lastIndexOf("/")!=-1){
               String[] types = type.split("/");
               for (String s : types) {
                   typeLike.addAll(comicService.queryComicTypeLike(s));
               }
           }
           request.getSession().setAttribute("comicReply",comicReply);
           request.getSession().setAttribute("comic",comic);
           request.getSession().setAttribute("details",details);
           request.getSession().setAttribute("typeLike",typeLike);
           return "detail";
       }else {
           request.getSession().setAttribute("msg","找不到该数据嗷~~~~~~");
           return "errors";
       }
    }
    @RequestMapping("/showDetail")
    public String showDetail(Integer id,HttpServletRequest request){
        String comicRoot = "F:\\MavenDemo\\Imanb-dev\\src\\main\\webapp\\static\\images\\comics\\";
        Detail detailById = detailService.getDetailById(id);
        if (detailById!=null){
            File dest = new File( comicRoot+ detailById.getComic().getRoot() + "\\" + detailById.getPath());
            // FIXME: 2019/8/29  detail添加了一个字段用于存放章节对应的目录下的 图片文件名
            detailById.setImages(getImages(dest));
            detailById.setLikes(likeService.selectDetailLikeNum(id));
            User user = (User) request.getSession().getAttribute("user");
            //有用户登录就更新历史状态
            if (user!=null){
                //判断是否是最新章节
                History newHistory = new History(user.getUserId(), detailById.getComicId(), id, localDateToString(), -1);
                historyService.addHistory(newHistory);
                //更新订阅状态  //判断是否是最新章节
                Integer newestId = detailService.selectNewestDetail(detailById.getComicId()).getDetailId();
                if (id.equals(newestId)){
                    orderService.updateUsersComicStatus(detailById.getComicId(),user.getUserId(),-1);
                }
            }
            request.getSession().setAttribute("detail",detailById);
            //章节评论
            Reply detailReply = detailService.getDetailReply(id);
            request.getSession().setAttribute("detailReply",detailReply);
            return "detailInfo";
        }else {
            request.getSession().setAttribute("msg","找不到该数据嗷~~~~~~");
            return "errors";
        }

    }

    /**
     * 分类列表
     */
    @RequestMapping("/listYanQing")
    @ResponseBody
    public Msg listYanQing(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        PageHelper.startPage(pn,10);
        List<Comic> comic = comicService.queryComicTypeLike("言情");
        if (comic.size()>0){
            PageInfo comics = new PageInfo(comic);
            return Msg.success().add(null,comics);
        }else {
            return Msg.fail();
        }
    }
    @RequestMapping("/listXuanHuan")
    @ResponseBody
    public Msg listXuanHuan(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        PageHelper.startPage(pn,10);
        List<Comic> comic = comicService.queryComicTypeLike("玄幻");
        if (comic.size()>0){
            PageInfo comics = new PageInfo(comic);
            return Msg.success().add(null,comics);
        }else {
            return Msg.fail();
        }
    }
    @RequestMapping("/listDuShi")
    @ResponseBody
    public Msg listDuShi(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        PageHelper.startPage(pn,10);
        List<Comic> comic = comicService.queryComicTypeLike("都市");
        if (comic.size()>0){
            PageInfo comics = new PageInfo(comic);
            return Msg.success().add(null,comics);
        }else {
            return Msg.fail();
        }
    }
    @RequestMapping("/listMaoXian")
    @ResponseBody
    public Msg listMaoXian(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        PageHelper.startPage(pn,10);
        List<Comic> comic = comicService.queryComicTypeLike("冒险");
        if (comic.size()>0){
            PageInfo comics = new PageInfo(comic);
            return Msg.success().add(null,comics);
        }else {
            return Msg.fail();
        }
    }
    @RequestMapping("/listXiaoYuan")
    @ResponseBody
    public Msg listXiaoYuan(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        PageHelper.startPage(pn,10);
        List<Comic> comic = comicService.queryComicTypeLike("校园");
        if (comic.size()>0){
            PageInfo comics = new PageInfo(comic);
            return Msg.success().add(null,comics);
        }else {
            return Msg.fail();
        }
    } @RequestMapping("/listXianXia")
    @ResponseBody
    public Msg listXianXia(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        PageHelper.startPage(pn,10);
        List<Comic> comic = comicService.queryComicTypeLike("仙侠");
        if (comic.size()>0){
            PageInfo comics = new PageInfo(comic);
            return Msg.success().add(null,comics);
        }else {
            return Msg.fail();
        }
    } @RequestMapping("/listGaoXiao")
    @ResponseBody
    public Msg listGaoXiao(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        PageHelper.startPage(pn,10);
        List<Comic> comic = comicService.queryComicTypeLike("搞笑");
        if (comic.size()>0){
            PageInfo comics = new PageInfo(comic);
            return Msg.success().add(null,comics);
        }else {
            return Msg.fail();
        }
    }
    @RequestMapping("/listGuoMan")
    @ResponseBody
    public Msg listGuoMan(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        PageHelper.startPage(pn,10);
        List<Comic> comic = comicService.queryComicLocation("国漫");
        if (comic.size()>0){
            PageInfo comics = new PageInfo(comic);
            return Msg.success().add(null,comics);
        }else {
            return Msg.fail();
        }
    }
    @RequestMapping("/listRiMan")
    @ResponseBody
    public Msg listRiMan(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        PageHelper.startPage(pn,10);
        List<Comic> comic = comicService.queryComicLocation("日漫");
        if (comic.size()>0){
            PageInfo comics = new PageInfo(comic);
            return Msg.success().add(null,comics);
        }else {
            return Msg.fail();
        }
    }
    @RequestMapping("/listQiTa")
    @ResponseBody
    public Msg listQiTa(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        PageHelper.startPage(pn,10);
        List<Comic> comic = comicService.queryComicLocation("其他漫画");
        if (comic.size()>0){
            PageInfo comics = new PageInfo(comic);
            return Msg.success().add(null,comics);
        }else {
            return Msg.fail();
        }
    }
    /**
     * 日志分类列表
     */
    @RequestMapping("/logForType")
    @ResponseBody
    public Msg logForType(@RequestParam(value = "type",defaultValue = "/")String type,@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        PageHelper.startPage(pn,4);
            List<Detail> detail=null;
        if (!type.equals("全部")){
            detail = detailService.selectComicDetailByType(type);
        }else {
            detail = detailService.queryAllDetail();
        }
        if (detail.size()>1){
            for (Detail d : detail) {
                d.setLikes(likeService.selectDetailLikeNum(d.getDetailId()));
            }
            PageInfo details = new PageInfo(detail);
            return Msg.success().add(null,details);
        }else {
            return Msg.fail().add("没有找到符合要求的数据",null);
        }
    };
    @RequestMapping("/logForTime")
    @ResponseBody
    public Msg logForTime(@RequestParam(value = "time")String time,@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        PageHelper.startPage(pn,4);
        List<Detail> detail = detailService.selectComicDetailByTime(time);
        if (detail.size()>1){
            for (Detail d : detail) {
                d.setLikes(likeService.selectDetailLikeNum(d.getDetailId()));
            }
            PageInfo details = new PageInfo(detail);
            return Msg.success().add(null,details);
        }else {
            return Msg.fail().add("没有找到符合要求的数据",null);
        }
    };
}
