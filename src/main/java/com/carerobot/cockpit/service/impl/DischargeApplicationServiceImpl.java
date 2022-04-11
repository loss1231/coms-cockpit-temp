package com.carerobot.cockpit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.common.annotation.DataSource;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.common.util.DateUtils;
import com.carerobot.cockpit.model.Archivesinfo;
import com.carerobot.cockpit.model.DischargeApplication;
import com.carerobot.cockpit.mapper.DischargeApplicationMapper;
import com.carerobot.cockpit.service.DischargeApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wd
 * @since 2021-06-23
 */
@Service
public class DischargeApplicationServiceImpl extends ServiceImpl<DischargeApplicationMapper, DischargeApplication> implements DischargeApplicationService {

    public Integer getCount(Date startDate, Date endDate,Integer orgId){
        return this.baseMapper.getCount(startDate,endDate,orgId);
    }

    @DataSource(DataSourceEnum.DB2)
    @Override
    public List<DischargeApplication> getEntireDischargeFromRoutine() {
        return this.list(new QueryWrapper<DischargeApplication>());
    }

    @Override
    @Transactional
    public void updateThenAdd(List<DischargeApplication> discharge,List<DischargeApplication> dischargeApplications) {
        if (discharge.isEmpty())return;
        if (this.updateBatchById(discharge)){
            this.saveBatch(dischargeApplications);
        }
    }

    @DataSource(DataSourceEnum.DB2)
    @Override
    public List<DischargeApplication> getNewDischargeFromRoutine() {
        return this.list(new QueryWrapper<DischargeApplication>().lt("createTime",new Date()).gt("createTime", DateUtils.addDateHours(new Date(),-1)));
    }
}
