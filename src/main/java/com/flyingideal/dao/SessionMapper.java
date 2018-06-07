package com.flyingideal.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * @author yanchao
 * @date 2018/6/7 19:04
 */
@Mapper
public interface SessionMapper {

    int saveSession(@Param("sessionId")Serializable sessionId, @Param("session") String serializedSession);

    String getSerializedSession(Serializable sessionId);

    int updateSession(@Param("sessionId")Serializable sessionId, @Param("session") String serializedSession);

    int deleteSession(Serializable sessionId);
}
