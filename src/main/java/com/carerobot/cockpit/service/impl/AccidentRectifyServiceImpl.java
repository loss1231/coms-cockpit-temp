package com.carerobot.cockpit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.common.annotation.DataSource;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.model.AccidentRectify;
import com.carerobot.cockpit.mapper.AccidentRectifyMapper;
import com.carerobot.cockpit.service.AccidentRectifyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wd
 * @since 2021-10-28
 */
@Service
public class AccidentRectifyServiceImpl extends ServiceImpl<AccidentRectifyMapper, AccidentRectify> implements AccidentRectifyService {

    private Logger logger = LoggerFactory.getLogger(AccidentRectifyServiceImpl.class);

    @Override
    @DataSource(DataSourceEnum.DB2)
    public List<AccidentRectify> getEntireTableAccident() {
        return this.list(new QueryWrapper<>());
    }

    @Override
    @Transactional
    public void updateThenAdd(List<AccidentRectify> dataList) {
        //先批量更新操作
        if(this.updateBatchById(dataList)){
            logger.info("更新事故整改旧数据结束");

            //再批量插入操作
            for (AccidentRectify accident : dataList) {
                AccidentRectify byId = this.getById(accident.getId());
                if (byId==null){
                    this.save(accident);
                }
            }
            logger.info("事故整改数据同步结束,总条数：{}", dataList.size());
        }else{
            logger.info("事故整改数据更新失败");
        }
    }
}
