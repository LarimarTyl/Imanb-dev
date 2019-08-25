package com.larimar.util;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @author Larimar
 * @time 2019/8/23 周五 8:56
 */
public class ValidateCodeUtil {
    public static void getValidateCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        HttpSession session = request.getSession();
        int width = 80;
        int height = 30;
        //设置浏览器不要i缓存此图片
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-control","No-cache");
        response.setDateHeader("Expires",0);
        //创建图片并获得图片上的下文
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = image.getGraphics();

        //产生随机验证码 定义验证码字符数
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] rands = new char[4];
        for (int i = 0; i < 4; i++) {
            int rand = (int)(Math.random()*36);
            rands[i] = chars.charAt(rand);
        }
        //产生图形 画背景
        Color backgroundColor = new Color((int)(Math.random() *255),(int)(Math.random() *255), (int)(Math.random() *255));
        graphics.setColor(backgroundColor);
        graphics.fillRect(0,0,width,height);
        /*
         * 随机产生100个干扰点
         * */
        for (int i = 0; i < 200; i++) {
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            graphics.setColor(new Color(red, green, blue));
            graphics.drawOval(x, y, (int)(Math.random() *3), (int)(Math.random() *3));
        }
        for (int i = 0; i < 4; i++) {
            Color color1 = new Color((int)(Math.random() *255),(int)(Math.random() *255), (int)(Math.random() *255));
            graphics.setColor(color1);
            final int x = (int)(Math.random()*80);
            final int y = (int)(Math.random()*40);
            final int w = (int)(Math.random()*30+20);
            final int h = (int)(Math.random()*30+20);
            final int signA = new Random().nextBoolean() ? 1 : -1;
            final int signB = new Random().nextBoolean() ? 1 : -1;
            graphics.drawLine(x, y, x + w * signA, y + h * signB);
        }


        graphics.setFont(new Font(null, Font.ITALIC | Font.BOLD, 18));
        //在不同高度输出验证码的不同字符
        for (int i = 0; i < 4; i++) {
            Color fontColor = new Color((int)(Math.random() *255),(int)(Math.random() *255), (int)(Math.random() *255));
            graphics.setColor(fontColor);
            graphics.drawString("" + rands[i], 20*i, (int)(Math.random()*10+20));
        }
        graphics.dispose();

        //将图片传输到客户端
        ServletOutputStream sos = response.getOutputStream();
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ImageIO.write(image,"JPEG",byteOut);
        byte[] buffer = byteOut.toByteArray();
        response.setContentLength(buffer.length);
        sos.write(buffer);
        byteOut.close();
        sos.close();

        //验证码存进session
        session.setAttribute("validateCode",new String(rands));
    }
}
