package com.carerobot.cockpit.service;

import com.carerobot.cockpit.model.UpdataHistory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wd
 * @since 2021-07-13
 */
public interface UpdataHistoryService extends IService<UpdataHistory> {

    void deleteTableAllInfo(String tableName);

}
