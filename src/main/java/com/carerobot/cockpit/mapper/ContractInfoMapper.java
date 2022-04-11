package com.carerobot.cockpit.mapper;

import com.carerobot.cockpit.dto.newOccupancyRateDto;
import com.carerobot.cockpit.model.ContractInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wd
 * @since 2021-06-22
 */
public interface ContractInfoMapper extends BaseMapper<ContractInfo> {

    @Select({"<script> " +
            "select count(r.Id) from archives_info a,room_bedinfo r where a.Id = r.ArchivesId " +
            "<if test = 'AreaId != null'> and a.AreaId = #{AreaId} </if>" +
            "<if test = 'NurseLevelId != null'> and a.NurseLevelId = #{NurseLevelId} </if>" +
            "<if test = 'IsLongOrShort != null'> and a.IsLongOrShort = #{IsLongOrShort} </if>" +
//            " and c.endDate &gt; #{startDate}" +
            "</script>"})
    Integer selectContract(@Param("AreaId") Integer AreaId,@Param("NurseLevelId") Integer NurseLevelId,@Param("IsLongOrShort") Integer IsLongOrShort);

    @Select("select count(*) from contract_info where StartDate > #{startDate} and StartDate < #{endDate}")
    Integer selectInCount(@Param("startDate") Date startTime,@Param("endDate") Date endTime);

    Integer insertContract(@Param("contract") ContractInfo contractInfo);

    @Update("update contract_info set IsSend = 1 where id in ${ids}")
    Boolean updateContractSend(@Param("ids")String ids);

    newOccupancyRateDto selectBegin(@Param("startDATE")Date startDate, @Param("endDate")Date endDate, @Param("list")List OrgId, @Param("startSnapsShot")String startSnapsShot, @Param("endSnapsShot")String endSnapsShot);

    newOccupancyRateDto selectGMI(@Param("startDATE")Date startDate,@Param("endDate")Date endDate,@Param("list")List OrgId,@Param("startSnapsShot")String startSnapsShot,@Param("endSnapsShot")String endSnapsShot);

    newOccupancyRateDto selectMO(@Param("startDATE")Date startDate,@Param("endDate")Date endDate,@Param("list")List OrgId,@Param("startSnapsShot")String startSnapsShot,@Param("endSnapsShot")String endSnapsShot);

    newOccupancyRateDto selectNMI(@Param("startDATE")Date startDate,@Param("endDate")Date endDate,@Param("list")List OrgId,@Param("startSnapsShot")String startSnapsShot,@Param("endSnapsShot")String endSnapsShot);

    newOccupancyRateDto selectEnd(@Param("startDATE")Date startDate,@Param("endDate")Date endDate,@Param("list")List OrgId,@Param("startSnapsShot")String startSnapsShot,@Param("endSnapsShot")String endSnapsShot);

    newOccupancyRateDto selectResidents(@Param("startDATE")Date startDate,@Param("endDate")Date endDate,@Param("list")List OrgId,@Param("startSnapsShot")String startSnapsShot,@Param("endSnapsShot")String endSnapsShot);

    newOccupancyRateDto selectResidentsDays(@Param("startDATE")Date startDate,@Param("endDate")Date endDate,@Param("list")List OrgId,@Param("startSnapsShot")String startSnapsShot,@Param("endSnapsShot")String endSnapsShot);

    newOccupancyRateDto selectSaleableRoom(@Param("startDATE")Date startDate,@Param("endDate")Date endDate,@Param("list")List OrgId,@Param("startSnapsShot")String startSnapsShot,@Param("endSnapsShot")String endSnapsShot);

    newOccupancyRateDto PhysicalOccupancy(@Param("startDATE")Date startDate,@Param("endDate")Date endDate,@Param("list")List OrgId,@Param("startSnapsShot")String startSnapsShot,@Param("endSnapsShot")String endSnapsShot);

    newOccupancyRateDto DoubleOccupancy(@Param("startDATE")Date startDate,@Param("endDate")Date endDate,@Param("list")List OrgId,@Param("startSnapsShot")String startSnapsShot,@Param("endSnapsShot")String endSnapsShot);

}
