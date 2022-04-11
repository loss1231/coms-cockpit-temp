package com.carerobot.cockpit.service;

import com.carerobot.cockpit.dto.CohabitantJsonDto;
import com.carerobot.cockpit.dto.RoomJsonDto;
import com.carerobot.cockpit.model.Archivesinfo;
import com.carerobot.cockpit.model.ContractCohabitant;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wd
 * @since 2021-06-22
 */
public interface ContractCohabitantService extends IService<ContractCohabitant> {

    Integer getCount(Integer AreaId,Integer NurseLevelId,Integer IsLongOrShort);

    List<ContractCohabitant> getDataFromFile();

    boolean saveBatchIntoLocal(List<ContractCohabitant> dataList);

    void createNewCohabitantTable(String name);

    void batchInsertTempArchive(List<CohabitantJsonDto> dtos,String name);

    void createNewRoomTable(String tableName);

    void batchInsertTempRoom(List<RoomJsonDto> data, String tableName);
    void updateThenAdd(List<ContractCohabitant> dataList);

    List<ContractCohabitant> getEntireDataFromFile();

    List<ContractCohabitant> getEntireCohbitantFromFile();

    void updateThenAdd(List<ContractCohabitant> contractCohabitant,List<ContractCohabitant> cohabitant);

    List<ContractCohabitant> getNewCohbitantFromFile();
}
