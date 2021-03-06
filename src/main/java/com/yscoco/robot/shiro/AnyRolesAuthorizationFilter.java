package com.yscoco.robot.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Slf4j
public class AnyRolesAuthorizationFilter extends AuthorizationFilter
{
    @Override
    protected boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object mappedValue)
        throws Exception
    {
        Subject subject = getSubject(req, resp);
        String[] rolesArray = (String[]) mappedValue;

        if (rolesArray == null || rolesArray.length == 0)
        {
            return true;
        }
        for (int i = 0; i < rolesArray.length; i++)
        {
            if (subject.hasRole(rolesArray[i]))
            {
                return true;
            }
        }
        return false;
    }

}
