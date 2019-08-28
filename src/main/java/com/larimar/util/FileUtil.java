package com.larimar.util;

import java.io.File;

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
}
