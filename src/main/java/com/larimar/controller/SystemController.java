package com.larimar.controller;

import com.larimar.util.ValidateCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Larimar
 * @time 2019/8/23 周五 8:50
 */
@Controller
public class SystemController {
    @RequestMapping("/code")
    public void changeCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
        ValidateCodeUtil.getValidateCode(request,response);
    }

}
