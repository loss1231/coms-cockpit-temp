package com.carerobot.cockpit.mapper;

import com.carerobot.cockpit.dto.ArchivesJsonDto;
import com.carerobot.cockpit.dto.ElderDetailReportDto;
import com.carerobot.cockpit.dto.ElderStatisticsDto;
import com.carerobot.cockpit.model.Archivesinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wd
 * @since 2021-05-13
 */
@Repository
@Mapper
public interface ArchivesinfoMapper extends BaseMapper<Archivesinfo> {


    List<Archivesinfo> selectLeaveForTable(@Param("areaId") Integer areaId,
                                           @Param("leaveTypeId") Integer leaveTypeId,
                                           @Param("startDate") Date startTime,
                                           @Param("endDate") Date endTime,
                                           @Param("isLongOrShort") Integer isLongOrShort);

    @Select("<script> SELECT" +
            " a.*" +
            " FROM" +
            " archives_info a" +
            " join" +
            " leave_application l" +
            " on" +
            " a.Id = l.applicant_id" +
            " <if test='areaId != null'>" +
            " and AreaId = #{areaId}" +
            " </if>" +
            " <if test='isLongOrShort != null'>" +
            " and IsLongOrShort = #{isLongOrShort}" +
            " </if></script> ")
    List<Archivesinfo> selectLeaveForTable(@Param("areaId") Integer areaId,
                                           @Param("isLongOrShort") Integer isLongOrShort);

    @Select("select count(r.Id) from archives_info a,accident_report r where a.Id = r.PatientId " +
            " and r.AccidentType = #{AccidentType}" +
            " and a.AreaId = #{AreaId}" +
            " and r.HappenTime > #{startDate}" +
            " and r.HappenTime < #{endDate}")
    Integer selectAccdientReport(@Param("AccidentType") Integer accidentType,
                                 @Param("AreaId") Integer areaId,
                                 @Param("startDate") Date startDate,
                                 @Param("endDate") Date endDate);

    @Select({"<script> " +
            "select count(r.Id) from archives_info a,accident_report r where a.Id = r.PatientId " +
            "and a.AreaId = #{AreaId}" +
            "<if test = 'IsTreatment != null'> and IsTreatment = #{IsTreatment} </if>" +
            "<if test = 'IsAmbulance != null'> and IsAmbulance = #{IsAmbulance} </if>" +
            "<if test = 'IsCallThePolice != null'> and IsCallThePolice = #{IsCallThePolice} </if>" +
            " and r.HappenTime &gt; #{startDate}" +
            " and r.HappenTime &lt; #{endDate}" +
            "</script>"})
    Integer selectResponse(@Param("AreaId") Integer areaId,
                           @Param("IsTreatment") Boolean IsTreatment,
                           @Param("IsAmbulance") Boolean IsAmbulance,
                           @Param("IsCallThePolice") Boolean IsCallThePolice,
                           @Param("startDate") Date startDate,
                           @Param("endDate") Date endDate);

    void createNewTempArchiveTable(@Param("tableName") String tableName);

    void dropTable(@Param("tableName") String tableName);

    void batchInsertTempArchive(@Param("list") List<ArchivesJsonDto> archivesJsonDtos,@Param("tableName") String name);

    List<ElderStatisticsDto> getGenderForElder(@Param("liveType") Integer liveType,@Param("tableName1") String name1,@Param("tableName2") String name2,@Param("startDate") Date startDate,@Param("endDate") Date endDate);

    List<ElderStatisticsDto> occupationForElderMap(@Param("liveType") Integer liveType,@Param("tableName1") String name1,@Param("tableName2") String name2,@Param("startDate") Date startDate,@Param("endDate") Date endDate);

    List<ElderStatisticsDto> diseaseForElderMap(@Param("liveType") Integer liveType,@Param("tableName1") String name1,@Param("tableName2") String name2,@Param("startDate") Date startDate,@Param("endDate") Date endDate);

    List<ElderStatisticsDto> ageForElderMap(@Param("liveType") Integer liveType,@Param("tableName1") String name1,@Param("tableName2") String name2,@Param("startDate") Date startDate,@Param("endDate") Date endDate);

    List<ElderStatisticsDto> curAgeForElderMap(@Param("liveType") Integer liveType,@Param("tableName1") String name1,@Param("tableName2") String name2,@Param("startDate") Date startDate,@Param("endDate") Date endDate);

    @Select("SELECT count(Id) FROM Archives_Info;")
    Integer selectTest();

    List<ElderDetailReportDto> GetElderDetails(Date dateStart, Date dateEnd, String types, String itemName, @Param("list") List<Integer> orgId, String type, String nameOrRoomNo, Integer currcount, Integer pageSize, String startSnapsShot, String endSnapsShot);

    int GetElderDetailsCounts(Date startDate, Date endDate, String types, String itemName, @Param("list") List<Integer> orgId, String type, String nameOrRoomNo, String startSnapsShot, String endSnapsShot);
}
