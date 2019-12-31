package com.yscoco.robot.shiro;

import com.yscoco.robot.entity.pojo.RoleType;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * @Author: Xiong
 * @Date: 2019/10/18 9:52
 */

@Slf4j
public class MyRetryLimitCredentialsMatcher extends HashedCredentialsMatcher {


    public MyRetryLimitCredentialsMatcher() {
    }

    public MyRetryLimitCredentialsMatcher(String hashAlgorithmName) {
        super(hashAlgorithmName);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        CustomToken tk = (CustomToken) authcToken;
        if (tk.getType().equals(RoleType.NOPASSWORD)) {
            return true;
        }

        boolean matches = super.doCredentialsMatch(authcToken, info);
        return matches;
    }
}
