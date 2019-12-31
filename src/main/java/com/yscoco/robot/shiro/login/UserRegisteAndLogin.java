package com.yscoco.robot.shiro.login;/*
package com.dascom.catering.shiro.login;

import com.dascom.catering.entity.UserinfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;

*/
/**
 * @Author dscom
 * @Date 2019/8/13 10:45
 **//*

@Slf4j
public class UserRegisteAndLogin {
    public static void main(String[] args) {
     //  getInputPasswordCiph("123456789", "c40aa2e38965fe536dd05bafc3411703");
          encryptPassword("123456789");
    }

    */
/**
     * 用户注册时加密用户的密码
     * 输入密码明文 返回密文与盐值
     *
     * @param password
     * @return 第一个是密文  第二个是盐值
     *//*

    public static String[] encryptPassword(String password) {
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex(); //生成盐值
        log.info(salt);
        String ciphertext = new Md5Hash(password, salt, 3).toString(); //生成的密文
        log.info(ciphertext);
        String[] strings = new String[]{salt, ciphertext};
        log.info(String.valueOf(strings));
        return strings;
    }

    */
/**
     * 获得本次输入的密码的密文
     *
     * @param password
     * @param salt
     * @return
     *//*

    public static String getInputPasswordCiph(String password, String salt) {
        if (salt == null) {
            password = "";
        }
        String ciphertext = new Md5Hash(password, salt, 3).toString(); //生成的密文
        log.info(ciphertext);
        return ciphertext;
    }

    */
/**
     * 用户登录函数，在controller里调用
     *
     * @param user
     * @param model
     * @return
     *//*

    public static String userLogin(UserinfoEntity user, Model model) {
        Subject subject = SecurityUtils.getSubject(); //获得Subject对象
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword()); //将用户输入的用户名写密码封装到一个UsernamePasswordToken对象中

        //用Subject对象执行登录方法，没有抛出任何异常说明登录成功
        try {
            subject.login(token); //login()方法会调用 Realm类中执行认证逻辑的方法，并将这个参数传递给doGetAuthenticationInfo()方法
            model.addAttribute("user_name", user.getUsername());
            return "index";
        } catch (UnknownAccountException e) //抛出这个异常说明用户不存在
        {
            model.addAttribute("msg", "用户不存在");
            return "login";
        } catch (IncorrectCredentialsException e) //抛出这个异常说明密码错误
        {
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }
}
*/
