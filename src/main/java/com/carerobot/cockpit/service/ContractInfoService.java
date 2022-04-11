package com.carerobot.cockpit.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.carerobot.cockpit.dto.ArchivesJsonDto;
import com.carerobot.cockpit.dto.OccupancyRateDto;
import com.carerobot.cockpit.dto.RoomJsonDto;
import com.carerobot.cockpit.dto.newOccupancyRateDto;
import com.carerobot.cockpit.model.ContractInfo;
import com.carerobot.cockpit.model.ContractLive;


import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wd
 * @since 2021-06-22
 */
public interface ContractInfoService extends IService<ContractInfo> {

    List<newOccupancyRateDto> getOccupancyRate(Date startDate, Date endDate, List OrgId, String startSnapsShot, String endSnapsShot);

    List<ContractInfo> getDataFromFile();

    boolean saveBatchIntoLocal(List<ContractInfo> dataList);

    Map GetCockpit(Integer orgId);

    List<OccupancyRateDto> getNewRoomRate(Date dateStart, Date dateEnd, String itemCode, String value, String area, String token);

    void updateThenAdd(List<ContractInfo> dataList);

    List<ContractInfo> getEntireDataFromFile();

//    BaseMapper<ContractInfo> getMapper();

    Boolean updateSend(String ids);

    List<ContractLive> getContractLiving();

    void updateThenAddContractLive(List<ContractLive> dataList);
}
