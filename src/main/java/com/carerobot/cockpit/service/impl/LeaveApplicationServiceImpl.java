package com.carerobot.cockpit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.common.annotation.DataSource;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.common.util.DateUtils;
import com.carerobot.cockpit.model.LeaveApplication;
import com.carerobot.cockpit.mapper.LeaveApplicationMapper;
import com.carerobot.cockpit.service.LeaveApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wd
 * @since 2021-05-21
 */
@Service
public class LeaveApplicationServiceImpl extends ServiceImpl<LeaveApplicationMapper, LeaveApplication> implements LeaveApplicationService {

    private Logger logger = LoggerFactory.getLogger(LeaveApplicationServiceImpl.class);

    /**
     * @Author
     * @Description  按照月为时间节点跑库
     * @Date  2021/6/21
     * @Param
     * @return java.util.List<com.carerobot.cockpit.model.LeaveApplication>
     **/
    @Override
    @DataSource(DataSourceEnum.DB2)
    public List<LeaveApplication> getDataFromRemote() {
        QueryWrapper<LeaveApplication> wrapper = new QueryWrapper<>();
        //暂时数据分布情况未知，获天/周/月节点的数据
        QueryWrapper<LeaveApplication> query = wrapperByMonth(wrapper);
        return this.list(query);
    }

    private QueryWrapper<LeaveApplication> wrapperByMonth(QueryWrapper<LeaveApplication> wrapper) {
        return wrapper.between("createTime", DateUtils.stringToDate(DateUtils.monthStart, "yyyy-MM-dd HH:mm:ss")
                , DateUtils.stringToDate(DateUtils.monthEnd, "yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public void updateThenAdd(List<LeaveApplication> dataList) {
        //先批量更新操作
        if(this.updateBatchById(dataList)){
            logger.info("更新旧数据结束");
            //再批量插入当天数据
//            List<AccidentReport> todayData = dataList.stream().filter(t ->
//                    t.getCreationTime().before(Objects.requireNonNull(DateUtils.stringToDate(DateUtils.dayStart, "yyyy-MM-dd HH:mm:ss")))
//                            && t.getCreationTime().after(Objects.requireNonNull(DateUtils.stringToDate(DateUtils.dayEnd, "yyyy-MM-dd HH:mm:ss"))))
//                    .collect(Collectors.toList());
            QueryWrapper<LeaveApplication> wrapper = new QueryWrapper<>();
            List<LeaveApplication> todayData = this.list(wrapperByDay(wrapper));
            this.saveBatch(todayData);
            logger.info("插入当日新增数据结束，条数为：{}", todayData);
            logger.info("客户数据同步结束,总条数：{}", dataList.size());
        }else{
            logger.info("数据更新失败");
        }
    }

    @Override
    @DataSource(DataSourceEnum.DB2)
    public List<LeaveApplication> getEntireTableFromRoutine() {
        QueryWrapper<LeaveApplication> wrapper = new QueryWrapper<>();
        return this.list(wrapperByDay(wrapper));
    }

    private QueryWrapper<LeaveApplication> wrapperByDay(QueryWrapper<LeaveApplication> wrapper) {
        return wrapper.between("createTime", DateUtils.stringToDate(DateUtils.dayStart, "yyyy-MM-dd HH:mm:ss")
                , DateUtils.stringToDate(DateUtils.dayEnd, "yyyy-MM-dd HH:mm:ss"));
    }
}
