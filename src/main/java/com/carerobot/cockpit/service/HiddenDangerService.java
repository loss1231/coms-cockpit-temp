package com.carerobot.cockpit.service;

import com.carerobot.cockpit.model.HiddenDanger;
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
public interface HiddenDangerService extends IService<HiddenDanger> {

    List<HiddenDanger> getEntireDataFromFile();

    void updateThenAdd(List<HiddenDanger> dataList);
}
