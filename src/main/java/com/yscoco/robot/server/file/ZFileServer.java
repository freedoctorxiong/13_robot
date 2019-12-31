package com.yscoco.robot.server.file;


import com.yscoco.robot.entity.ZFileEntity;
import com.yscoco.robot.entity.pojo.EntityType;

/**
 * @Author: Xiong
 * @Date: 2019/12/5 18:45
 */
public interface ZFileServer {

    void deleteByPrimaryKey(Long id);

    void insert(ZFileEntity record);

    void insertSelective(ZFileEntity record);

    ZFileEntity selectByPrimaryKey(Long id);

    void updateByPrimaryKeySelective(ZFileEntity record);

    void updateByPrimaryKey(ZFileEntity record);

    /**
     * 通过实体类id和类型查询文件信息
     *
     * @param entityId
     * @param entityType
     * @return
     */
    ZFileEntity selectFileByEntity(Long entityId, EntityType entityType);
}
