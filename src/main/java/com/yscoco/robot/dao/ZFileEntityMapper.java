package com.yscoco.robot.dao;

import com.yscoco.robot.entity.ZFileEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZFileEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ZFileEntity record);

    int insertSelective(ZFileEntity record);

    ZFileEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ZFileEntity record);

    int updateByPrimaryKey(ZFileEntity record);

    /**
     * 通过实体类id和类型查询文件信息
     *
     * @param entityId
     * @param entityName
     * @return
     */
    ZFileEntity selectFileByEntity(Long entityId, String entityName);
}