package com.larimar.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Larimar
 * @time 2019/8/28 周三 16:37
 */
public class FileUtil {
    /**
     * 删除空文件夹
     * @param dir 目标文件夹
     */
    public static void doDeleteEmptyDir(String dir) {
        boolean success = (new File(dir)).delete();
        if (success) {
            System.out.println("Successfully deleted empty directory: " + dir);
        } else {
            System.out.println("Failed to delete empty directory: " + dir);
        }
    }

    /**
     * 删除文件夹 及其子文件夹
     * @param file
     */
    public static void deleteFile(File file){
        if (file.isFile()){//判断是否为文件，是，则删除
            file.delete();
        }else{//不为文件，则为文件夹
            String[] childFilePath = file.list();//获取文件夹下所有文件相对路径
            for (String path:childFilePath){
                File childFile= new File(file.getAbsoluteFile()+"/"+path);
                deleteFile(childFile);//递归，对每个都进行判断
            }
            file.delete();
        }
    }

    /**
     * 返回指定文件夹中的图片文件名
     * @param file
     * @return
     */
    public static List<String> getImages(File file){
        List<String> images = new ArrayList<>();
        if (file.exists()) {
            if (file.list()!=null){
                String[] fileList = file.list();
                for (String fileName:fileList){
                    if (fileName.substring(fileName.lastIndexOf(".")).equalsIgnoreCase(".jpg")){
                        images.add(fileName);
                    }
                }
            }
        }
        return images;
    }

    public static void main(String[] args) {
        File dest = new File("F:\\MavenDemo\\Imanb-dev\\src\\main\\webapp\\static\\images\\comics\\dpcq\\1234");
        System.out.println(getImages(dest));
    }
}
