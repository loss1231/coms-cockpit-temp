package com.carerobot.cockpit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.common.annotation.DataSource;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.common.util.DateUtils;
import com.carerobot.cockpit.dto.CohabitantJsonDto;
import com.carerobot.cockpit.dto.RoomJsonDto;
import com.carerobot.cockpit.model.Archivesinfo;
import com.carerobot.cockpit.model.ContractCohabitant;
import com.carerobot.cockpit.mapper.ContractCohabitantMapper;
import com.carerobot.cockpit.model.RoomBedinfo;
import com.carerobot.cockpit.service.ContractCohabitantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxl.job.core.log.XxlJobLogger;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @since 2021-06-22
 */
@Service
public class ContractCohabitantServiceImpl extends ServiceImpl<ContractCohabitantMapper, ContractCohabitant> implements ContractCohabitantService {

    public Integer getCount(Integer AreaId,Integer NurseLevelId,Integer IsLongOrShort){
        return this.baseMapper.selectContract(AreaId,NurseLevelId,IsLongOrShort);
    }

    private Logger logger = LoggerFactory.getLogger(ContractCohabitantServiceImpl.class);

    @Override
    @DataSource(DataSourceEnum.DB5)
    public List<ContractCohabitant> getDataFromFile() {
        QueryWrapper<ContractCohabitant> contractInfoWrapper = new QueryWrapper<>();
        contractInfoWrapper.between("CreatedDate", DateUtils.addDateHours(new Date(),-1)
                , new Date());
        return this.list(contractInfoWrapper);
    }

    @Override
    public boolean saveBatchIntoLocal(List<ContractCohabitant> dataList) {
        logger.info("数据更新开始");
        if(this.saveBatch(dataList)){
            logger.info("数据更新结束---此次更新数据条数：{}", dataList.size());
            return true;
        }
        logger.info("数据更新失败----");
        return false;
    }

    @Override
    public void createNewCohabitantTable(String name) {
        this.baseMapper.createNewCohabitantTable(name);
    }

    @Override
    public void batchInsertTempArchive(List<CohabitantJsonDto> dtos,String name) {
        this.baseMapper.batchInsertTempArchive(dtos,name);
    }

    @Override
    public void createNewRoomTable(String tableName) {
        this.baseMapper.createNewRoomTable(tableName);
    }

    @Override
    public void batchInsertTempRoom(List<RoomJsonDto> dtos, String name) {
        this.baseMapper.batchInsertTempRoom(dtos,name);
    }

    @Transactional
    public void updateThenAdd(List<ContractCohabitant> dataList) {
        //先批量更新操作
        if(this.updateBatchById(dataList)){
            logger.info("更新旧数据结束");
            //再批量插入当天数据
//            List<AccidentReport> todayData = dataList.stream().filter(t ->
//                    t.getCreationTime().before(Objects.requireNonNull(DateUtils.stringToDate(DateUtils.dayStart, "yyyy-MM-dd HH:mm:ss")))
//                            && t.getCreationTime().after(Objects.requireNonNull(DateUtils.stringToDate(DateUtils.dayEnd, "yyyy-MM-dd HH:mm:ss"))))
//                    .collect(Collectors.toList());
            List<ContractCohabitant> todayData = getDataFromFile();
            this.saveBatch(todayData);
            logger.info("插入当日新增数据结束，条数为：{}", todayData);
            logger.info("客户数据同步结束,总条数：{}", dataList.size());
        }else{
            logger.info("数据更新失败");
        }
    }

    @Override
    @DataSource(DataSourceEnum.DB5)
    public List<ContractCohabitant> getEntireDataFromFile() {
        QueryWrapper<ContractCohabitant> contractInfoWrapper = new QueryWrapper<>();
        return this.list(contractInfoWrapper);
    }

    @DataSource(DataSourceEnum.DB5)
    @Override
    public List<ContractCohabitant> getEntireCohbitantFromFile() {
        return this.list(new QueryWrapper<ContractCohabitant>());
    }

    @Override
    @Transactional
    public void updateThenAdd(List<ContractCohabitant> contractCohabitant,List<ContractCohabitant> cohabitant) {
        if (contractCohabitant.isEmpty())return;
        if (this.updateBatchById(contractCohabitant)){
            for (ContractCohabitant bean : contractCohabitant) {
                this.baseMapper.insertCohabitants(bean);
            }
        }
    }

    @DataSource(DataSourceEnum.DB5)
    @Override
    public List<ContractCohabitant> getNewCohbitantFromFile() {
        return this.list(new QueryWrapper<ContractCohabitant>().le("CreatedDate",new Date()).ge("CreatedDate",DateUtils.addDateHours(new Date(),-1)));
    }
}
