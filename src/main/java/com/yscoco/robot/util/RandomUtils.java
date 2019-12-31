package com.yscoco.robot.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.UUID;

/**
 * @Author:   Xiong
 * @Date:     2019/10/16 14:21
 */
public class RandomUtils {

    private static final Integer DEFAULT_CODENUM = 6;//默认验证码数量
    private static final char[] CODES = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private static Random random = new Random();

    /**
     * @discription 随机生成x位验证码 , 含数字，大写字母 ,  默认6位数
     * @author lansiming
     * @created 2016-7-26  上午11:41:12
     */

    public static String getCode() {
        return getCode(DEFAULT_CODENUM);
    }

    /**
     * @discription 随机生成x位验证码 , 含数字，大写字母
     * @author lansiming
     * @created 2016-7-26  上午10:56:52
     */
    public static String getCode(int num) {
        if (num < 1) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            int r = random.nextInt(36);
            sb.append(CODES[r]);
		/*	//小于26 转换为大写字母char， 否则转换为[0-9]数字.
			if( r<26 ){
				sb.append((char)(r + 65));
			}else{
				sb.append(35-r);
			}*/
        }
        return sb.toString();
    }

    /**
     * @discription 纯数字随机数
     * @author lansiming
     * @created 2016-7-26  下午12:02:33
     */
    public static String getNumCode(int num) {
        if (num < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            int r = random.nextInt(10);
            sb.append(CODES[r]);
        }
        return sb.toString();
    }

    /**
     * @discription 纯数字随机数(默认6位)
     * @author lansiming
     * @created 2016-7-26  下午12:02:33
     */
    public static String getNumCode() {
        return getNumCode(DEFAULT_CODENUM);
    }

    /**
     * @discription 获取随机颜色color
     * @author lansiming
     * @created 2016-7-26  下午12:29:46
     */
    public static Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }

        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    //设置字母的大小,大小

    /**
     * @discription 获取字母 数字组合的验证码图片对象
     * @author lansiming
     * @created 2016-7-26  下午2:26:08
     */
    public static BufferedImage getImageCode(int width, int height, String codeStr) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(getRandColor(200, 250));
        g.fillRect(1, 1, width - 1, height - 1);
        g.setColor(new Color(102, 102, 102));
        g.drawRect(0, 0, width - 1, height - 1);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 17));

        g.setColor(getRandColor(160, 200));

        //画随机线
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            g.drawLine(x, y, x + xl, y + yl);
        }

        //从另一方向画随机线
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int xl = random.nextInt(12) + 1;
            int yl = random.nextInt(6) + 1;
            g.drawLine(x, y, x - xl, y - yl);
        }
        //生成随机数,并将随机数字转换为字母
        char[] codes = codeStr.toCharArray();
        for (int i = 0; i < codes.length; i++) {
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(String.valueOf(codes[i]), 15 * i + 10, 16);
        }
        return image;
    }

    public static String get32UUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }
}
