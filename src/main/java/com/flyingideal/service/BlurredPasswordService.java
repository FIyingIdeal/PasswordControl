package com.flyingideal.service;

import com.flyingideal.dao.BlurredPasswordMapper;
import com.flyingideal.model.BlurredPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/1/23.
 */
@Service
public class BlurredPasswordService {

    @Autowired
    private BlurredPasswordMapper passwordMapper;

    /**
     * 模糊查询查找相关的subject，用于在输入框中输入信息后即时或得相关提示
     * @param subject
     * @return
     */
    public List<String> getReletedSubject(String subject) {
        return passwordMapper.getReletedSubject(subject);
    }

    /**
     * 查询输入subject的相关信息
     * @param subject
     * @return List<BlurredPassword> 相关主题可能对应多条记录
     */
    public List<BlurredPassword> getBlurredPassword(String subject) {
        return passwordMapper.getBlurredPassword(subject);
    }
}
