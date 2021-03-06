package com.larimar.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.larimar.entity.*;
import com.larimar.selectPojo.*;
import com.larimar.service.*;
import com.larimar.util.Msg;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.larimar.util.FileUtil.deleteFile;
import static com.larimar.util.FileUtil.getImages;

/**
 * @author Larimar
 * @time 2019/8/29 周四 16:30
 */

/**
 * 后台管理页面控制器
 */
@Controller
public class AdminController {
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
    HistoryService historyService;


    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

    /**
     * 用户管理
     */
    @RequestMapping("/userInfo")
    @ResponseBody
    public Msg userInfo(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 7);
        List<User> user = userService.queryAllUsers();
        PageInfo users = new PageInfo(user, 5);
        return Msg.success().add("成功", users);
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public Msg addUser(User user, @RequestParam("file") MultipartFile file) {
        Msg msg = new Msg();
        if (userService.findUserByName(user.getUserName()) == null) {
            //获取文件名
            String filename = file.getOriginalFilename();
            //判断是否选择了头像
            if (!filename.equals("")) {
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
                    //回显信息 上传失败
                    msg.setCode(400);
                    msg.setMsg("头像选择有误，请重试。");
                    e.printStackTrace();
                    return msg;
                }
                //有头像选择才设置更新photo
                user.setPhoto(newFileName);
            }
            if (userService.doRegister(user)) {
                msg.setCode(200);
                msg.setMsg("添加用户成功！");
            } else {
                msg.setCode(400);
                msg.setMsg("添加用户失败！");
            }
        } else {
            msg.setCode(400);
            msg.setMsg("该用户名已被占用，请换一个！");
        }
        return msg;
    }

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Msg getUserInfo(Integer id) {
        User user = userService.findUserById(id);
        if (user != null) {
            return Msg.success().add(null, user);
        }
        return null;
    }

    // FIXME: 2019/8/28  对应的form 应该要有 enctype="multipart/form-data"
    @RequestMapping("/editUser")
    @ResponseBody
    public Msg editUser(User user, @RequestParam("path") MultipartFile file) {
        Msg msg = new Msg();
        //获取文件名
        String filename = file.getOriginalFilename();
        //判断是否有修改头像
        if (!filename.equals("")) {
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
                //回显信息 上传失败
                msg.setCode(400);
                msg.setMsg("头像选择有误，请重试。");
                e.printStackTrace();
                return msg;
            }
            user.setPhoto(newFileName);
        }
        if (userService.updateUserInfo(user)) {
            msg.setCode(200);
            msg.setMsg("修改用户信息成功！");
        } else {
            msg.setCode(400);
            msg.setMsg("修改用户信息失败！");
        }
        return msg;
    }

    @RequestMapping("/delUser")
    @ResponseBody
    public Msg delUser(Integer id) {
        if (userService.delUser(id)) {
            return Msg.success().add("删除用户信息成功！", null);
        } else {
            return Msg.success().add("删除用户信息失败！", null);
        }
    }
    @RequestMapping("/userSelect")
    @ResponseBody
    public Msg userSelect(@RequestParam(value = "pn", defaultValue = "1") Integer pn,UserSelect userSelect){
        PageHelper.startPage(pn,7);
        List<User> user = userService.selectByOption(userSelect);
        if (user.size()>0){
             PageInfo users = new PageInfo(user);
            return Msg.success().add("成功查到",users);
        }else {
            return Msg.fail().add("没有查到符合条件的数据。",null);
        }
    }


    /**
     * 漫画管理
     */
    @RequestMapping("/comicInfo")
    @ResponseBody
    public Msg comicInfo(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        List<Comic> comic = comicService.queryAllComic();
        PageInfo comics = new PageInfo(comic, 5);
        return Msg.success().add("成功", comics);
    }

    @RequestMapping("/addComic")
    @ResponseBody
    public Msg addComic(Comic comic, @RequestParam("photo") MultipartFile file) {
        Msg msg = new Msg();
        if (comicService.getComicByName(comic.getComicName()) == null) {
            if (!comic.getNewUpdate().equals("")) {
                //判断是否有长传目录
                if (!comic.getRoot().equals("")) {
                    //获取文件名
                    String filename = file.getOriginalFilename();
                    //判断是否选择了封面
                    if (!filename.equals("")) {
                        //获取文件后缀
                        String suffix = filename.substring(filename.lastIndexOf(".")).toLowerCase();
                        File dest = new File("F:\\MavenDemo\\Imanb-dev\\src\\main\\webapp\\static\\images\\comics\\" + comic.getRoot() + "\\cover" + suffix);
                        try {
                            if (dest.delete()) {
                                System.out.println("原封面删除成功");
                            }
                            file.transferTo(dest);
                        } catch (IOException e) {
                            //回显信息 上传失败
                            msg.setCode(400);
                            msg.setMsg("封面上传失败，请重试。");
                            e.printStackTrace();
                            return msg;
                        }
                        if (comicService.addComic(comic)) {
                            msg.setCode(200);
                            msg.setMsg("添加漫画成功！");
                        } else {
                            msg.setCode(400);
                            msg.setMsg("漫画添加失败，请重试。");
                        }
                    } else {
                        msg.setCode(400);
                        msg.setMsg("请选择上传漫画封面");
                    }
                } else {
                    msg.setCode(400);
                    msg.setMsg("请选择上传漫画存放目录");
                }
            } else {
                msg.setCode(400);
                msg.setMsg("请设置漫画更新时间");
            }
        } else {
            msg.setCode(400);
            msg.setMsg("该漫画已存在哦，请不要重复添加");
        }
        return msg;
    }

    @RequestMapping("/getComicInfo")
    @ResponseBody
    public Msg getComicInfo(Integer id) {
        Comic comic = comicService.getComicById(id);
        if (comic != null) {
            return Msg.success().add(null, comic);
        }
        return null;
    }

    @RequestMapping("/editComic")
    @ResponseBody
    public Msg editComic(Comic comic, @RequestParam("photo") MultipartFile file) {
        // FIXME: 2019/8/29 删除漫画要把该漫画对应的订阅和章节表都删除 数据库外键属性 CASCADE 就可以
        Msg msg = new Msg();
        //获取文件名
        String filename = file.getOriginalFilename();
        //判断是否选择了封面
        if (!filename.equals("")) {
            //获取文件后缀
            String suffix = filename.substring(filename.lastIndexOf(".")).toLowerCase();
            File dest = new File("F:\\MavenDemo\\Imanb-dev\\src\\main\\webapp\\static\\images\\comics\\" + comic.getRoot() + "\\cover" + suffix);
            try {
                if (dest.delete()) {
                    System.out.println("原封面删除成功");
                }
                file.transferTo(dest);
            } catch (IOException e) {
                //回显信息 上传失败
                msg.setCode(400);
                msg.setMsg("封面上传失败，请重试。");
                e.printStackTrace();
                return msg;
            }
        }
        if (comicService.updateComicInfo(comic)) {
            msg.setCode(200);
            msg.setMsg("修改漫画信息成功！");
        } else {
            msg.setCode(400);
            msg.setMsg("修改漫画信息失败！");
        }
        return msg;
    }

    @RequestMapping("/delComic")
    @ResponseBody
    public Msg delComic(Integer id) {
        Comic comic = comicService.getComicById(id);
        File dest = new File("F:\\MavenDemo\\Imanb-dev\\src\\main\\webapp\\static\\images\\comics\\" + comic.getRoot());
        if (dest.exists()) {
            deleteFile(dest);
        }
        if (comicService.delComic(id)) {
            return Msg.success().add("删除漫画信息成功！", null);
        } else {
            return Msg.success().add("删除漫画信息失败！", null);
        }
    }

    @RequestMapping("/comicSelect")
    @ResponseBody
    public Msg comicSelect(@RequestParam(value = "pn", defaultValue = "1") Integer pn, ComicSelect comicSelect){
        PageHelper.startPage(pn,5);
        List<Comic> comic = comicService.selectByOption(comicSelect);
        if (comic.size()>0){
            PageInfo comics = new PageInfo(comic);
            return Msg.success().add("成功查到",comics);
        }else {
            return Msg.fail().add("没有查到符合条件的数据。",null);
        }

    }

    /**
     * 章节详情管理
     */
    @RequestMapping("/detailInfo")
    @ResponseBody
    public Msg detailInfo(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        List<Detail> detail = detailService.queryAllDetail();
        PageInfo details = new PageInfo(detail, 5);
        return Msg.success().add("成功", details);
    }

    @RequestMapping("/addDetail")
    @ResponseBody
    public Msg addDetail(Detail detail, @RequestParam("pictures") MultipartFile[] files, @RequestParam("comicName") String comicName) {
        Comic comic = comicService.getComicByName(comicName);
        if (comic == null) {
            return Msg.fail().add("该漫画不存在，请检查漫画名后重试！", null);
        } else {
            if (files != null && files.length > 0) {
                String comicPath = comic.getRoot();
                if (!detail.getPath().equals("")) {
                    for (int i = 0; i < files.length; i++) {
                        MultipartFile file = files[i];
                        String filename = file.getOriginalFilename();
                         if (!"".equals(filename)) {
                            String suffix = filename.substring(filename.lastIndexOf(".")).toLowerCase();
                            File dest = new File("F:\\MavenDemo\\Imanb-dev\\src\\main\\webapp\\static\\images\\comics\\" + comicPath + "\\" + detail.getPath() + "\\" + (i + 1) + suffix);
                            try {
                                if (dest.delete()) {
                                    System.out.println("原图片删除成功");
                                }
                                file.transferTo(dest);
                            } catch (IOException e) {
                                e.printStackTrace();
                                return Msg.fail().add("漫画图片上传失败，请检查后重试", null);
                            }
                        }
                    }
                    detail.setComicId(comic.getComicId());
                    detail.setComic(comic);
                    detailService.addDetail(detail);
                    return Msg.success().add("添加章节成功！", null);
                } else {
                    return Msg.fail().add("请填写章节图片存放路径，检查后重试", null);
                }
            } else {
                return Msg.fail().add("未检测到图片资源，请添加漫画图片后重试", null);
            }
        }
    }

    @RequestMapping("/delDetail")
    @ResponseBody
    public Msg delDetail(Integer id) {
        Detail detail = detailService.getDetailById(id);
        File dest = new File("F:\\MavenDemo\\Imanb-dev\\src\\main\\webapp\\static\\images\\comics\\" + detail.getComic().getRoot() + "\\" + detail.getPath());
        if (dest.exists()) {
            deleteFile(dest);
        }

        if (detailService.delDetail(id)) {
            return Msg.success().add("删除章节信息成功！", null);
        } else {
            return Msg.success().add("删除章节信息失败！", null);
        }
    }

    @RequestMapping("/getDetailInfo")
    @ResponseBody
    public Msg getDetailInfo(Integer id) {
        Detail detailById = detailService.getDetailById(id);
        File dest = new File("F:\\MavenDemo\\Imanb-dev\\src\\main\\webapp\\static\\images\\comics\\" + detailById.getComic().getRoot() + "\\" + detailById.getPath());
        // FIXME: 2019/8/29  detail添加了一个字段用于存放章节对应的目录下的 图片文件名
        detailById.setImages(getImages(dest));
        if (detailById != null) {
            return Msg.success().add(null, detailById);
        } else {
            return Msg.fail().add("获取信息失败", null);
        }
    }

    @RequestMapping("/editDetail")
    @ResponseBody
    public Msg editDetail(Detail detail, @RequestParam("pictures") MultipartFile[] files, @RequestParam("comicName") String comicName) {
        Comic comic = comicService.getComicByName(comicName);
        if (comic == null) {
            return Msg.fail().add("该漫画不存在，请检查漫画名后重试！", null);
        } else {
            if (files != null && files.length > 0) {
                String comicPath = comic.getRoot();
                if (!detail.getPath().equals("")) {
                    for (int i = 0; i < files.length; i++) {
                        MultipartFile file = files[i];
                        String filename = file.getOriginalFilename();
                        if (!"".equals(filename) && filename != null) {
                            String suffix = filename.substring(filename.lastIndexOf(".")).toLowerCase();
                            File dest = new File("F:\\MavenDemo\\Imanb-dev\\src\\main\\webapp\\static\\images\\comics\\" + comicPath + "\\" + detail.getPath() + "\\" + (i + 1) + suffix);
                            try {
                                if (dest.delete()) {
                                    System.out.println("原图片删除成功");
                                }
                                file.transferTo(dest);
                            } catch (IOException e) {
                                e.printStackTrace();
                                return Msg.fail().add("漫画图片上传失败，请检查后重试", null);
                            }
                        }
                    }

                }
            }
        }
        detail.setComicId(comic.getComicId());
        detail.setComic(comic);
        detailService.updateDetail(detail);
        return Msg.success().add("修改章节成功！", null);
    }

    @RequestMapping("/detailSelect")
    @ResponseBody
    public Msg detailSelect(@RequestParam(value = "pn", defaultValue = "1") Integer pn, DetailSelect detailSelect){
        PageHelper.startPage(pn,5);
        List<Detail> detail = detailService.selectByOption(detailSelect);
        if (detail.size()>0){
            PageInfo details = new PageInfo(detail);
            return Msg.success().add("成功查到",details);
        }else {
            return Msg.fail().add("没有查到符合条件的数据。",null);
        }

    }


    /**
     * 订阅管理
     */
    @RequestMapping("/orderInfo")
    @ResponseBody
    public Msg orderInfo(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        List<Orders> order = orderService.selectAllOrders();
        PageInfo orders = new PageInfo(order, 5);
        return Msg.success().add("成功", orders);
    }

    @RequestMapping("/addOrder")
    @ResponseBody
    public Msg addOrder(Orders orders, @Param("comicName") String comicName, @Param("userName") String userName) {
        Comic comicByName = comicService.getComicByName(comicName);
        if (comicByName == null) {
            return Msg.fail().add("该漫画不存在哦", null);
        }
        User userByName = userService.findUserByName(userName);
        if (userByName == null) {
            return Msg.fail().add("该用户不存在哦", null);
        }
        orders.setComic(comicByName);
        orders.setComicId(comicByName.getComicId());
        orders.setUser(userByName);
        orders.setUserId(userByName.getUserId());
        if (orderService.addOrders(orders)) {
            return Msg.success().add("添加订阅成功！", null);
        } else {
            return Msg.fail().add("已经订阅了该漫画，请勿重复订阅", null);
        }

    }

    @RequestMapping("/getOrderInfo")
    @ResponseBody
    public Msg getOrderInfo(Integer id) {
        Orders ordersById = orderService.getOrdersById(id);
        if (ordersById != null) {
            return Msg.success().add(null, ordersById);
        } else {
            return Msg.fail().add("获取信息失败", null);
        }
    }

    @RequestMapping("/editOrder")
    @ResponseBody
    public Msg editOrder(Orders orders, @Param("comicName") String comicName, @Param("userName") String userName) {
        Comic comicByName = comicService.getComicByName(comicName);
        if (comicByName == null) {
            return Msg.fail().add("修改失败，该漫画不存在哦", null);
        }
        User userByName = userService.findUserByName(userName);
        if (userByName == null) {
            return Msg.fail().add("修改失败，该用户不存在哦", null);
        }
        orders.setComic(comicByName);
        orders.setComicId(comicByName.getComicId());
        orders.setUser(userByName);
        orders.setUserId(userByName.getUserId());
        if (orderService.updateOrdersInfo(orders)) {
            return Msg.success().add("修该成功，添加订阅成功！", null);
        } else {
            return Msg.fail().add("修改订阅信息失败！", null);
        }
    }

    @RequestMapping("/delOrder")
    @ResponseBody
    public Msg delOrder(Integer id) {
        if (orderService.delOrders(id)) {
            return Msg.success().add("删除订阅信息成功！", null);
        } else {
            return Msg.success().add("删除订阅信息失败！", null);
        }
    }
    
    @RequestMapping("/orderSelect")
    @ResponseBody
    public Msg orderSelect(@RequestParam(value = "pn", defaultValue = "1") Integer pn, OrderSelect orderSelect){
        PageHelper.startPage(pn,5);
        List<Orders> order = orderService.selectByOption(orderSelect);
        if (order.size()>0){
            PageInfo orders = new PageInfo(order);
            return Msg.success().add("成功查到",orders);
        }else {
            return Msg.fail().add("没有查到符合条件的数据。",null);
        }

    }  
    
    /**
     * 评论管理
     */
    @RequestMapping("/commentInfo")
    @ResponseBody
    public Msg commentInfo(@RequestParam(value = "pn", defaultValue = "1")Integer pn){
        PageHelper.startPage(pn,5);
        List<Comment> comments = commentService.getAllComment();
        PageInfo comment = new PageInfo(comments,5);
        return Msg.success().add("成功",comment);
    }
    @RequestMapping("/addComment")
    @ResponseBody
    public Msg addComment(Comment comment, @RequestParam("userName")String userName,@RequestParam("commentType")Integer commentType,@RequestParam("aimId")Integer aimId){
        User user = userService.findUserByName(userName);
        if (user!=null){
            switch (commentType){
                case 1:
                    Detail detail = detailService.getDetailById(aimId);
                    if (detail==null){
                        return Msg.fail().add("添加失败，评论对象不存在。",null);
                    }else {
                        comment.setUser(user);
                        comment.setUserId(user.getUserId());
                        comment.setDetailId(detail.getDetailId());
                        if (commentService.addDetailComment(comment)){
                            return Msg.success();
                        }else {
                            return Msg.fail().add("添加失败",null);
                        }
                    }
                case -1:
                    Comment revert = commentService.getCommentById(aimId);
                    if (revert==null){
                        return Msg.fail().add("添加失败，评论对象不存在。",null);
                    }else {
                        comment.setUser(user);
                        comment.setUserId(user.getUserId());
                        comment.setCommentAim(revert.getCommentId());
                        if (commentService.addRevertComment(comment)){
                            return Msg.success();
                        }else {
                            return Msg.fail().add("添加失败",null);
                        }
                    }
                case 0:
                    Comic comic = comicService.getComicById(aimId);
                    if (comic==null){
                        return Msg.fail().add("添加失败，评论对象不存在。",null);
                    }else {
                        comment.setUser(user);
                        comment.setUserId(user.getUserId());
                        comment.setComicId(comic.getComicId());
                        if (commentService.addComicComment(comment)){
                            return Msg.success();
                        }else {
                            return Msg.fail().add("添加失败",null);
                        }
                    }
                default:
                    return Msg.fail().add("添加失败",null);
            }
        }else {
            return Msg.fail().add("添加失败，该用户不存在。",null);
        }
    }
    @RequestMapping("/getCommentInfo")
    @ResponseBody
    public Msg getCommentInfo(Integer id){
        Comment comment = commentService.getCommentById(id);
        if (comment!=null){
            return Msg.success().add(null,comment);
        }else {
            return Msg.fail().add("获取信息失败",null);
        }
    }
    @RequestMapping("/editComment")
    @ResponseBody
    public Msg editComment(Comment comment, @RequestParam("userName")String userName,@RequestParam("commentType")Integer commentType,@RequestParam("aimId")Integer aimId){
        User user = userService.findUserByName(userName);
        if (user!=null){
            switch (commentType){
                case 1:
                    Detail detail = detailService.getDetailById(aimId);
                    if (detail==null){
                        return Msg.fail().add("修改失败，评论对象不存在。",null);
                    }else {
                        comment.setUser(user);
                        comment.setUserId(user.getUserId());
                        comment.setDetailId(detail.getDetailId());
                        if (commentService.updateComment(comment)){
                            return Msg.success();
                        }else {
                            return Msg.fail().add("修改失败",null);
                        }
                    }
                case -1:
                    Comment revert = commentService.getCommentById(aimId);
                    if (revert==null){
                        return Msg.fail().add("修改失败，评论对象不存在。",null);
                    }else {
                        comment.setUser(user);
                        comment.setUserId(user.getUserId());
                        comment.setCommentAim(revert.getCommentId());
                        if (commentService.addRevertComment(comment)){
                            return Msg.success();
                        }else {
                            return Msg.fail().add("修改失败",null);
                        }
                    }
                case 0:
                    Comic comic = comicService.getComicById(aimId);
                    if (comic==null){
                        return Msg.fail().add("修改失败，评论对象不存在。",null);
                    }else {
                        comment.setUser(user);
                        comment.setUserId(user.getUserId());
                        comment.setComicId(comic.getComicId());
                        if (commentService.addComicComment(comment)){
                            return Msg.success();
                        }else {
                            return Msg.fail().add("修改失败",null);
                        }
                    }
                default:
                    return Msg.fail().add("修改失败",null);
            }
        }else {
            return Msg.fail().add("修改失败，该用户不存在。",null);
        }
    }
    @RequestMapping("/delComment")
    @ResponseBody
    public Msg delComment(Integer id){
        if (commentService.delComment(id)) {
            return Msg.success().add("删除评论成功",null);
        }else {
            return Msg.fail().add("删除评论失败",null);
        }
    }
    
    @RequestMapping("/commentSelect")
    @ResponseBody
    public Msg commentSelect(@RequestParam(value = "pn", defaultValue = "1") Integer pn, CommentSelect commentSelect){
        PageHelper.startPage(pn,5);
        List<Comment> comment = commentService.selectByOption(commentSelect);
        if (comment.size()>0){
            PageInfo comments = new PageInfo(comment);
            return Msg.success().add("成功查到",comments);
        }else {
            return Msg.fail().add("没有查到符合条件的数据。",null);
        }
    }

    /**
     * 历史管理
     */
    @RequestMapping("/historyInfo")
    @ResponseBody
    public Msg historyInfo(@RequestParam(value = "pn", defaultValue = "1") Integer pn){
        PageHelper.startPage(pn,5);
        List<History> histories = historyService.queryAllHistory();
        PageInfo history = new PageInfo(histories,5);
        return Msg.success().add("成功",history);
    }
    @RequestMapping("/addHistory")
    @ResponseBody
    public Msg addHistory(History history,@RequestParam("userName")String userName,@RequestParam("comicName")String comicName,@RequestParam("chapterName")String chapterName){
        User user = userService.findUserByName(userName);
        if (user!=null){
            Comic comic = comicService.getComicByName(comicName);
            if (comic!=null){
                Detail detail = detailService.getDetailByComicAndChapter(comic.getComicId(), chapterName);
                if (detail!=null){
                    history.setUserId(user.getUserId());
                    history.setComicId(comic.getComicId());
                    history.setDetailId(detail.getDetailId());
                    if (historyService.addHistory(history)) {
                        return Msg.success().add("添加浏览历史成功",null);
                    }else {
                        return Msg.success().add("已存在该浏览历史，原记录已更新",null);
                    }
                }else {
                    return Msg.fail().add("添加失败,该漫画不存在此章节。",null);
                }
            }else {
                return Msg.fail().add("添加失败,该漫画不存在。",null);
            }
        }else {
            return Msg.fail().add("添加失败，该用户不存在",null);
        }
    }
    @RequestMapping("/getHistoryInfo")
    @ResponseBody
    public Msg getHistoryInfo(Integer id){
        History history = historyService.selectHistoryById(id);
        if (history!=null){
            return Msg.success().add("成功获取信息",history);
        }else {
            return Msg.fail().add("获取信息失败",null);
        }
    }
    @RequestMapping("/editHistory")
    @ResponseBody
    public Msg editHistory(History history,@RequestParam("userName")String userName,@RequestParam("comicName")String comicName,@RequestParam("chapterName")String chapterName){
        User user = userService.findUserByName(userName);
        if (user!=null){
            Comic comic = comicService.getComicByName(comicName);
            if (comic!=null){
                Detail detail = detailService.getDetailByComicAndChapter(comic.getComicId(), chapterName);
                if (detail!=null){
                    history.setUserId(user.getUserId());
                    history.setComicId(comic.getComicId());
                    history.setDetailId(detail.getDetailId());
                    if (historyService.updateHistory(history)) {
                        return Msg.success().add("修改浏览历史成功",null);
                    }else {
                        return Msg.fail().add("修改浏览历史失败",null);
                    }
                }else {
                    return Msg.fail().add("修改失败,该漫画不存在此章节。",null);
                }
            }else {
                return Msg.fail().add("修改失败,该漫画不存在。",null);
            }
        }else {
            return Msg.fail().add("修改失败，该用户不存在",null);
        }
    }
    @RequestMapping("/delHistory")
    @ResponseBody
    public Msg delHistory(Integer id){
        if (historyService.delHistory(id)) {
            return Msg.success().add("删除该浏览历史成功",null);
        }else {
            return Msg.fail().add("删除该浏览历史失败",null);
        }
    }

    @RequestMapping("/historySelect")
    @ResponseBody
    public Msg historySelect(@RequestParam(value = "pn", defaultValue = "1") Integer pn, HistorySelect historySelect){
        PageHelper.startPage(pn,5);
        List<History> history = historyService.selectByOption(historySelect);
        if (history.size()>0){
            PageInfo historys = new PageInfo(history);
            return Msg.success().add("成功查到",historys);
        }else {
            return Msg.fail().add("没有查到符合条件的数据。",null);
        }

    }
}
