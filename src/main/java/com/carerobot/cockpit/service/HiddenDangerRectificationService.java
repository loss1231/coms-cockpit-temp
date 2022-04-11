package com.carerobot.cockpit.service;

import com.carerobot.cockpit.model.HiddenDangerRectification;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wd
 * @since 2021-12-28
 */
public interface HiddenDangerRectificationService extends IService<HiddenDangerRectification> {

    List<HiddenDangerRectification> getEntireDataFromFile();

    void updateThenAdd(List<HiddenDangerRectification> dataList);
}
