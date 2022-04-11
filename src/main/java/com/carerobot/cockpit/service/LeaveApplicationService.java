package com.carerobot.cockpit.service;

import com.carerobot.cockpit.model.ContractInfo;
import com.carerobot.cockpit.model.LeaveApplication;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wd
 * @since 2021-05-21
 */
public interface LeaveApplicationService extends IService<LeaveApplication> {
    List<LeaveApplication> getDataFromRemote();

    void updateThenAdd(List<LeaveApplication> dataList);

    List<LeaveApplication> getEntireTableFromRoutine();
}
