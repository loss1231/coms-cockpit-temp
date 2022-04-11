package com.carerobot.cockpit.mapper;

import com.carerobot.cockpit.model.LeaveApplication;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wd
 * @since 2021-05-21
 */
@Repository
@Mapper
public interface LeaveApplicationMapper extends BaseMapper<LeaveApplication> {

//    @Select("select * from leave_application where id = 17;")
//    @Results({
//            @Result(id = true, property = "id", column = "id"),
//            @Result(property = "archivesinfo",column = "applicant_id", javaType = Archivesinfo.class, one = @One(
//                    select = "selectLeaveForTable"
//            ))
//    })
//    List<LeaveApplication> selectLeaveForTable(@Param("AreaId") Integer AreaId, @Param("leaveTypeId") Integer leaveTypeId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
//    @Select("select * from archivesinfo where Id = #{applicant_id};")
//    Archivesinfo selectArchives(@Param("applicant_id") Integer applicant_id);

}
