package com.carerobot.cockpit.mapper;

import com.carerobot.cockpit.dto.CohabitantJsonDto;
import com.carerobot.cockpit.dto.RoomJsonDto;
import com.carerobot.cockpit.model.ContractCohabitant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wd
 * @since 2021-06-22
 */
public interface ContractCohabitantMapper extends BaseMapper<ContractCohabitant> {

    @Select({"<script> " +
            "select count(c.Id) from archives_info a,contract_cohabitant c where a.Id = c.ArchivesId " +
            "<if test = 'AreaId != null'> and a.AreaId = #{AreaId} </if>" +
            "<if test = 'NurseLevelId != null'> and a.NurseLevelId = #{NurseLevelId} </if>" +
            "<if test = 'IsLongOrShort != null'> and a.IsLongOrShort = #{IsLongOrShort} </if>" +
//            " and c.endDate &gt; #{startDate}" +
            "</script>"})
    Integer selectContract(@Param("AreaId") Integer AreaId, @Param("NurseLevelId") Integer NurseLevelId, @Param("IsLongOrShort") Integer IsLongOrShort);

    void createNewCohabitantTable(@Param("tableName") String tableName);

    void batchInsertTempArchive(@Param("list") List<CohabitantJsonDto> dtos,@Param("tableName")String name);

    void createNewRoomTable(@Param("tableName") String tableName);

    void batchInsertTempRoom(@Param("list") List<RoomJsonDto> dtos, @Param("tableName")String name);

    void insertCohabitants(@Param("cohabitant") ContractCohabitant cohabitant);
}
