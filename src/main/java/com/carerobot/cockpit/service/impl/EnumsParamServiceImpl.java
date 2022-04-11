package com.carerobot.cockpit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.common.annotation.DataSource;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.common.util.DateUtils;
import com.carerobot.cockpit.model.Account;
import com.carerobot.cockpit.model.EnumsParam;
import com.carerobot.cockpit.mapper.EnumsParamMapper;
import com.carerobot.cockpit.service.EnumsParamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @since 2021-07-13
 */
@Service
public class EnumsParamServiceImpl extends ServiceImpl<EnumsParamMapper, EnumsParam> implements EnumsParamService {

    @DataSource(DataSourceEnum.DB6)
    public List<EnumsParam> getEntireAccountFromBill() {
        return this.list(new QueryWrapper<EnumsParam>());
    }

    @Override
    @Transactional
    public void updateThenAdd() {
        List<EnumsParam> enumsParams = this.getEntireAccountFromBill();
        if (enumsParams.isEmpty())return;
        if (this.updateBatchById(enumsParams)){
            this.saveBatch(getNewAccountFromBill());
        }
    }

    @DataSource(DataSourceEnum.DB6)
    public List<EnumsParam> getNewAccountFromBill() {
        return this.list(new QueryWrapper<EnumsParam>().lt("create_time",new Date()).gt("create_time",DateUtils.addDateHours(new Date(),-1)));
    }
}
