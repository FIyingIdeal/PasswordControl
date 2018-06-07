package com.flyingideal.security.session.dao;

import com.flyingideal.dao.SessionMapper;
import com.flyingideal.utility.SerializableUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author yanchao
 * @date 2018/6/7 17:00
 * Shiro提供的CachingSessionDAO是为了
 */
public class MysqlSessionDAO extends CachingSessionDAO {

    private static final Logger logger = LoggerFactory.getLogger(MysqlSessionDAO.class);

    private SessionMapper sessionMapper;

    public MysqlSessionDAO(SessionMapper sessionMapper) {
        this.sessionMapper = sessionMapper;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        int count = this.sessionMapper.saveSession(sessionId, SerializableUtils.serialize(session));
        if (count > 0) {
            logger.info("成功保存session: {}", session);
        }
        return sessionId;
    }

    @Override
    protected void doUpdate(Session session) {
        int count = this.sessionMapper.updateSession(session.getId(), SerializableUtils.serialize(session));
        if (count > 0) {
            logger.info("成功修改session: {}", session);
        }
    }

    @Override
    protected void doDelete(Session session) {
        int deleteCount = this.sessionMapper.deleteSession(session.getId());
        if (deleteCount > 0) {
            logger.info("删除session: {}", session);
        }
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        String serializedSession = this.sessionMapper.getSerializedSession(sessionId);
        Session session = null;
        if (serializedSession != null) {
            session = SerializableUtils.deserialize(serializedSession);
            logger.info("读取到session: {}", session);
        }
        return session;
    }
}
