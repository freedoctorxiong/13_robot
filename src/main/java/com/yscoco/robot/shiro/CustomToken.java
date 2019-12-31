package com.yscoco.robot.shiro;


import com.yscoco.robot.entity.pojo.RoleType;

/**
 * @Author: Xiong
 * @Date: 2019/10/18 9:36
 */
public class CustomToken extends org.apache.shiro.authc.UsernamePasswordToken {

    private static final long serialVersionUID = -2564928913725078138L;

    private RoleType type;


    public CustomToken() {
        super();
    }


    public CustomToken(String username, String password, RoleType type, boolean rememberMe, String host) {
        super(username, password, rememberMe,  host);
        this.type = type;
    }
    /**免密登录*/
    public CustomToken(String username,RoleType roleType) {
        super(username, "", false, null);
        this.type = roleType;
    }

    /**免密登录*/
    public CustomToken(String username) {
        super(username, "", false, null);
        this.type = RoleType.NOPASSWORD;
    }


    /**
     * 不同类型登录
     * @param username
     * @param pwd
     * @param type
     */
    public CustomToken(String username, String pwd,RoleType type) {
        super(username, pwd, false, null);
        this.type = type;
    }

    /**账号密码登录*/
    public CustomToken(String username, String pwd) {
        super(username, pwd, false, null);
        this.type = RoleType.PASSWORD;
    }

    public RoleType getType() {
        return type;
    }


    public void setType(RoleType type) {
        this.type = type;
    }
}