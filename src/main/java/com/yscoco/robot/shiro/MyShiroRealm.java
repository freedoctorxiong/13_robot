package com.yscoco.robot.shiro;


import com.yscoco.robot.entity.PermissionEntity;
import com.yscoco.robot.entity.RoleEntity;
import com.yscoco.robot.entity.UserEntity;
import com.yscoco.robot.server.user.PermissionServer;
import com.yscoco.robot.server.user.RoleServer;
import com.yscoco.robot.server.user.UserServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @Author dscom
 * @Date 2019/8/12 9:24
 **/
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserServer userServer;

    @Autowired
    private RoleServer roleServer;

    @Autowired
    private PermissionServer permissionServer;


    /**
     * 授权信息
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //获取登录用户名
        UserEntity user = (UserEntity) principals.getPrimaryPrincipal();
        //查询用户名称（这里可以使用缓存来做），这里根据项目情况不同，选择不同的操作
        List<RoleEntity> roles = roleServer.findByUserId(user.getId());

        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (RoleEntity role : roles) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());

            List<PermissionEntity> permissions = permissionServer.findByRoleId(role.getId());
            for (PermissionEntity permission : permissions) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return simpleAuthorizationInfo;

    }

    /**
     * Retrieves authentication data from an implementation-specific datasource (RDBMS, LDAP, etc) for the given
     * authentication token.
     * <p/>
     * For most datasources, this means just 'pulling' authentication data for an associated subject/user and nothing
     * more and letting Shiro do the rest.  But in some systems, this method could actually perform EIS specific
     * log-in logic in addition to just retrieving data - it is up to the Realm implementation.
     * <p/>
     * A {@code null} return value means that no account could be associated with the specified token.
     *
     * @param token the authentication token containing the user's principal and credentials.
     * @return an {@link AuthenticationInfo} object containing account data resulting from the
     * authentication ONLY if the lookup is successful (i.e. account exists and is valid, etc.)
     * @throws AuthenticationException if there is an error acquiring data or performing
     *                                 realm-specific authentication logic for the specified <tt>token</tt>
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        CustomToken upToken = (CustomToken) token;
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (upToken.getPrincipal() == null) {
            return null;
        }
        // 获取用户的输入的账号.
        String userinfo = (String) upToken.getPrincipal();
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + String.valueOf(userinfo));
        // 通过userinfo从数据库中查找
        UserEntity userEntity = userServer.findByUserinfo(userinfo);
        if (userEntity == null) {
            return null;
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + userEntity.getId());
        List<RoleEntity> roles = roleServer.findByUserId(userEntity.getId());
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + roles.size());
        userEntity.setRoles(roles);

      /* if (userEntity.getStatus() == 1) {
            throw new LockedAccountException();
        }*/

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userEntity, userEntity.getPassword(), getName());
        return authenticationInfo;
    }
}
