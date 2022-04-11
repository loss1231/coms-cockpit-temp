package com.carerobot.cockpit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.common.annotation.DataSource;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.common.util.DateUtils;
import com.carerobot.cockpit.model.ContractLive;
import com.carerobot.cockpit.mapper.ContractLiveMapper;
import com.carerobot.cockpit.service.ContractLiveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wd
 * @since 2021-07-20
 */
@Service
public class ContractLiveServiceImpl extends ServiceImpl<ContractLiveMapper, ContractLive> implements ContractLiveService {

    @Autowired
    private ApplicationContext context;

    @Override
    public int updateAndAddContractLive() {

        Date date = new Date();
        //获取代理对象
        ContractLiveService liveService = context.getBean(ContractLiveService.class);

        //更新
        List<ContractLive> live = liveService.getContractLive();
        this.updateBatchById(live,live.size());

        //差量增加
        List<ContractLive> list = this.list(new QueryWrapper<ContractLive>());
        List<Integer> ids = list.stream().filter(o -> o.getAddDate().getTime() > DateUtils.addDateHours(date,-2).getTime() && o.getAddDate().getTime() < DateUtils.addDateHours(date,2).getTime()).map(ContractLive::getId).collect(Collectors.toList());
        Integer diffSize = 0;
        if(ids.size() > 0){
            List<ContractLive> different = liveService.different(ids);
//        List<ContractLive> different = this.list(new QueryWrapper<ContractLive>()
//                .notIn("id",list.stream().map(ContractLive::getId).collect(Collectors.toList()))
//        .between("AddDate",DateUtils.addDateHours(date,-2),DateUtils.addDateHours(date,-1)));
            if (!different.isEmpty()){
                this.saveBatch(different,different.size());
            }
            diffSize = different.size();
        }
        return diffSize;
    }

    @Override
    @DataSource(DataSourceEnum.DB5)
    public List<ContractLive> different(List<Integer> ids) {
        return this.baseMapper.different(ids);
    }

    @Override
    @DataSource(DataSourceEnum.DB5)
    public List<ContractLive> getContractLive(){
        return this.list(new QueryWrapper<ContractLive>());
    }
}
