package com.carerobot.cockpit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carerobot.cockpit.dto.AllOrgComboboxDto;
import com.carerobot.cockpit.dto.ResidentsDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Repository
@Mapper
public interface AllOrgComboboxDtoMapper extends BaseMapper<AllOrgComboboxDto> {

    AllOrgComboboxDto selectOccupancyRate(@Param("startDATE") String startDate, @Param("endDATE")String endDate, @Param("list") List OrgId,@Param("month")String month);

    ResidentsDto selectResidents(@Param("endDATE")String endDATE, @Param("list") List OrgId);

    Integer selectILTotal(@Param("startDATE") String startDate, @Param("endDATE")String endDate, @Param("list") List OrgId);


    Integer selectILUnits(@Param("startDATE") String startDate, @Param("endDATE")String endDate, @Param("list") List OrgId);

    Integer selectILSRoom(@Param("startDATE") String startDate, @Param("endDATE")String endDate, @Param("list") List OrgId);

    BigDecimal selectILOccupancy(@Param("startDATE") String startDate, @Param("endDATE")String endDate, @Param("list") List OrgId);

    Integer selectALTotal(@Param("startDATE") String startDate, @Param("endDATE")String endDate, @Param("list") List OrgId);

    Integer selectALUnits(@Param("startDATE") String startDate, @Param("endDATE")String endDate, @Param("list") List OrgId);

    Integer selectALSRoom(@Param("startDATE") String startDate, @Param("endDATE")String endDate, @Param("list") List OrgId);

    BigDecimal selectALOccupancy(@Param("startDATE") String startDate, @Param("endDATE")String endDate, @Param("list") List OrgId);

    BigDecimal selectTotalOccupancy(@Param("startDATE") String startDate, @Param("endDATE")String endDate, @Param("list") List OrgId);

    Integer selectTotalUnits(@Param("startDATE") String startDate, @Param("endDATE")String endDate, @Param("list") List OrgId);

    Integer selectTotalResidents(@Param("startDATE") String startDate, @Param("endDATE")String endDate, @Param("list") List OrgId);


    BigDecimal selectIncomIl(@Param("month")String month, @Param("list") List OrgId);

    BigDecimal selectIncomAl(@Param("month")String month, @Param("list") List OrgId);

    Integer selectIncomRestaurant(@Param("month")String month, @Param("list") List OrgId);

    BigDecimal selectIncomOther(@Param("month")String month, @Param("list") List OrgId);

    BigDecimal selectTotalincom(@Param("month")String month, @Param("list") List OrgId);
}
