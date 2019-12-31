package com.yscoco.robot.shiro;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author dscom
 * @Date 2019/8/23 15:17
 **/
@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class ConfigCode {

    private String Host;
    private int Port;
    private int Expire;
    private int Timeout;

}
