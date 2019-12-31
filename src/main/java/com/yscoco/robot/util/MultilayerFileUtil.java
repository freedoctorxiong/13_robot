package com.yscoco.robot.util;

import java.io.File;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @Author: Xiong
 * @Date: 2019/11/11 16:29
 */
public class MultilayerFileUtil {

    public static String CreateMultilayerFile(String url) {
        LocalDate time = LocalDate.now();
        String str = url + "\\" + time + "\\";
        File f = new File(str);
        if (!f.exists()) {
            f.mkdirs();
        }
        return str;
    }

    public static void deleteFile(String root, String file) {
        if (file != null) {
            File f = new File(root + file);
            f.delete();
        }
    }

    /**
     * 重新命名
     *
     * @param root
     * @return
     */
    public static String getName(String root) {
        //获取扩展
        String extName = root.substring(root.lastIndexOf("."));//.jpg
        return UUID.randomUUID().toString() + extName;
    }


}
