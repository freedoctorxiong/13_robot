package com.yscoco.robot.controller.uploadfile;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yscoco.robot.Exception.BizException;
import com.yscoco.robot.common.result.Code;
import com.yscoco.robot.common.result.Message;
import com.yscoco.robot.entity.ZFileEntity;
import com.yscoco.robot.entity.pojo.EntityType;
import com.yscoco.robot.server.file.ZFileServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import static com.yscoco.robot.util.MultilayerFileUtil.CreateMultilayerFile;
import static com.yscoco.robot.util.MultilayerFileUtil.getName;


/**
 * @Author: Xiong
 * @Date: 2019/11/9 17:15
 */

@Slf4j
@RestController
@RequestMapping("file")
@Api(tags = {"文件接口"})
public class UploadFileController {


    @Value("${yscoco.url.uploadFilePath}")
    private String filePath;
    @Value("${yscoco.url.fileUrl}")
    private String fileUrl;
    @Autowired
    private ZFileServer zFileServer;

    @PostMapping("selectFileByEntity")
    @ResponseBody
    @ApiOperation(value = "通过实体属性查询文件", notes = "通过实体属性查询文件，实体id和实体类型查询文件信息")
    public Message selectFileByEntity(@RequestParam Long entityId, @RequestParam EntityType entityType) {
        ZFileEntity zFileEntity = zFileServer.selectFileByEntity(entityId, entityType);
        return Message.success(zFileEntity);
    }


    @PostMapping("uploadOneFile")
    @ResponseBody
    @ApiOperation(value = "单个文件上传", notes = "单个文件上传")
    public Message uploadFile(@RequestParam MultipartFile myfile) {
        if (myfile.isEmpty()) {
            throw new BizException(Code.NO_SELECT_FILE);
        }

        log.info(filePath);
        String filepath = CreateMultilayerFile(filePath);
        String fileName = getName(myfile.getOriginalFilename());

        log.info(fileName);
        //filePath = "/Users/itinypocket/workspace/temp/";
        try {
            File dest = new File(filepath + fileName);
            myfile.transferTo(dest);

            ZFileEntity zFileEntity = new ZFileEntity();
            zFileEntity.setCreatetime(new Date());
            zFileEntity.setFilename(fileName);
            // zFileEntity.setFiletype();
            zFileEntity.setFileurl(fileUrl + fileName);
            zFileEntity.setLocalpath(filepath + fileName);
            zFileServer.insertSelective(zFileEntity);
            log.info(zFileEntity.getId() + "<<<<<<<<<<<<<<<id");
            return Message.success(zFileEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Message(Code.SERVER_ERROR_MSG);
    }


    /**
     * 上传多个文件
     *
     * @param myfiles
     * @return
     */
    @JsonIgnore
    @PostMapping(value = "/uploadFiles", consumes = "multipart/form-data")
    @ApiOperation(value = "多文件上传", notes = "多文件上传")
    public Message ArrayFiles(@RequestParam("myfiles") MultipartFile[] myfiles) {
        //  String filePath = "/Users/itinypocket/workspace/temp/";

        log.info(String.valueOf(myfiles.length));
        for (int i = 0; i < myfiles.length; i++) {
            MultipartFile file = myfiles[i];
            if (file.isEmpty()) {
                throw new BizException(Code.NO_SELECT_FILE);
            }
            String filepath = CreateMultilayerFile(filePath);
            String fileName = file.getOriginalFilename();
            log.info(filepath + "-------------" + fileName);
            File dest = new File(filepath + fileName);
            try {
                file.transferTo(dest);
                log.info("第" + (i + 1) + "个文件上传成功");
            } catch (IOException e) {
                e.printStackTrace();
                //LOGGER.error(e.toString(), e);
            }
        }
        return Message.success();

    }


    /**
     * 同时上传多个文件和一个文件
     *
     * @param myfiles
     * @param name
     * @param age
     * @return
     */
    @JsonIgnore
    @PostMapping("/uploadFilesAndOneFile")
    public Message<Object> ArrayFilesAndOneFile(@RequestParam("myfiles") MultipartFile[] myfiles, MultipartFile myfile, String name, String age) {
        try {
            //用于接收文件名
            StringBuilder builder = new StringBuilder();
            //出来多个文件
            for (int i = 0; i < myfiles.length; i++) {
                String filename = myfiles[i].getOriginalFilename();
                builder.append(filename + ", ");
                myfiles[i].transferTo(new File("E:\\Young\\verfiyImg\\" + UUID.randomUUID() + "." + filename.substring(filename.lastIndexOf(".") + 1)));
            }
            //处理单个文件
            String filename = myfile.getOriginalFilename();
            myfile.transferTo(new File("E:\\Young\\verfiyImg\\" + UUID.randomUUID() + "." + filename.substring(filename.lastIndexOf(".") + 1)));
            System.out.println("接收到的name： " + name + ", age: " + age + " 多文件文件名： [" + builder.toString().substring(0, builder.toString().length() - 2) + "]" + " 单文件名：" + filename);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return Message.success();
    }

    /**
     * 对上传的文件进行限制
     * @return
     */
//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//         MultipartConfigFactory factory = new MultipartConfigFactory();
//         //// 设置文件大小限制 ,超了，页面会抛出异常信息，这时候就需要进行异常信息的处理了;
//         factory.setMaxFileSize("128KB"); //KB,MB
//         /// 设置总上传数据总大小
//         factory.setMaxRequestSize("256KB");
//         //Sets the directory location wherefiles will be stored.
//         //factory.setLocation("路径地址");
//         return factory.createMultipartConfig();
//     }


}
