package com.carerobot.cockpit.service;

import com.carerobot.cockpit.model.EnumsParam;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wd
 * @since 2021-07-13
 */
public interface EnumsParamService extends IService<EnumsParam> {

    void updateThenAdd();

}
