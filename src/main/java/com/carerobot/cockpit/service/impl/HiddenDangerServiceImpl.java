package com.carerobot.cockpit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.common.annotation.DataSource;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.model.HiddenDanger;
import com.carerobot.cockpit.mapper.HiddenDangerMapper;
import com.carerobot.cockpit.service.HiddenDangerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wd
 * @since 2021-12-28
 */
@Service
public class HiddenDangerServiceImpl extends ServiceImpl<HiddenDangerMapper, HiddenDanger> implements HiddenDangerService {

    private Logger logger = LoggerFactory.getLogger(HiddenDangerServiceImpl.class);

    @Override
    @DataSource(DataSourceEnum.DB2)
    public List<HiddenDanger> getEntireDataFromFile() {
        return this.list(new QueryWrapper<>());
    }

    @Override
    @Transactional
    public void updateThenAdd(List<HiddenDanger> dataList) {
        //先批量更新操作
        if(this.updateBatchById(dataList)){
            logger.info("更新隐患巡查旧数据结束");

            //再批量插入操作
            for (HiddenDanger hidden : dataList) {
                HiddenDanger byId = this.getById(hidden.getId());
                if (byId==null){
                    this.save(hidden);
                }
            }
            logger.info("隐患巡查数据同步结束,总条数：{}", dataList.size());
        }else{
            logger.info("隐患巡查数据更新失败");
        }
    }
}
