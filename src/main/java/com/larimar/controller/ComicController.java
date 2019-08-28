package com.larimar.controller;

import com.larimar.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Larimar
 * @time 2019/8/23 周五 15:37
 */
@Controller
public class ComicController {
    @Autowired
    ComicService comicService;
    @RequestMapping("/")
    public String test(){
        return "index";
    }
}
