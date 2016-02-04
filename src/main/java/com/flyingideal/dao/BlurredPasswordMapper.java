package com.flyingideal.dao;

import com.flyingideal.model.BlurredPassword;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2015/12/5.
 */
@Repository
public interface BlurredPasswordMapper {

    int addBlurredPassword(BlurredPassword blurredPassword);

    /**
     * 模糊查询查找相关的subject，用于在输入框中输入信息后即时或得相关提示
     * @param subject
     * @return
     */
    List<String> getReletedSubject(String subject);

    /**
     * 查询输入subject的相关信息
     * @param subject
     * @return List<BlurredPassword> 相关主题可能对应多条记录
     */
    List<BlurredPassword> getBlurredPassword(String subject);
}
