package com.yscoco.robot.common;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pwt
 */
@Component
public class ConfigInitializer {

    public static void init() throws IOException {
        File configPath = new File(System.getProperty("user.dir"), "config");
        if (!configPath.exists()) {
            boolean result = configPath.mkdirs();
            System.out.println("创建config目录结果:" + result);
        }
        copyConfigFile(new File(configPath, "robot.yml"));
        copyConfigFile(new File(configPath, "application.properties"));
    }

    private static void copyConfigFile(File dest) throws IOException {
        if (!dest.exists()) {
            InputStream inputStream = new ClassPathResource(dest.getName()).getInputStream();
            FileCopyUtils.copy(inputStream, new FileOutputStream(dest));
        }
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        List<Resource> resourceList = new ArrayList<>();
        String userDir = System.getProperty("user.dir");
      resourceList.add(new FileSystemResource(userDir + "/config/robot.yml"));
        yaml.setResources(resourceList.toArray(new Resource[0]));
        configurer.setProperties(yaml.getObject());
        return configurer;
    }
}
