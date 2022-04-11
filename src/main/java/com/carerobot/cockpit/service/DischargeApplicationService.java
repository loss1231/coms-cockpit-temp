package com.carerobot.cockpit.service;

import com.carerobot.cockpit.model.Archivesinfo;
import com.carerobot.cockpit.model.DischargeApplication;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wd
 * @since 2021-06-23
 */
public interface DischargeApplicationService extends IService<DischargeApplication> {

    Integer getCount(Date startDate, Date endDate,Integer orgId);

    List<DischargeApplication> getEntireDischargeFromRoutine();

    void updateThenAdd(List<DischargeApplication> discharge,List<DischargeApplication> dischargeApplications);

    List<DischargeApplication> getNewDischargeFromRoutine();

}
