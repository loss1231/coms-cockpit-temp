package com.carerobot.cockpit.mapper;

import com.carerobot.cockpit.model.DischargeApplication;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wd
 * @since 2021-06-23
 */
public interface DischargeApplicationMapper extends BaseMapper<DischargeApplication> {

    @Select("SELECT count(a.Id) FROM discharge_application d JOIN archives_info a ON a.Id = d.archiveId AND d.leaveTime > #{startDate} AND d.leaveTime < #{endDate} and a.OrgId = #{orgId}")
    Integer getCount(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("orgId")Integer orgId);

}
