package com.carerobot.cockpit.mapper;

import com.carerobot.cockpit.dto.ElderStatisticsDto;
import com.carerobot.cockpit.model.VElderRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * VIEW Mapper 接口
 * </p>
 *
 * @author wd
 * @since 2021-05-17
 */
@Repository
@Mapper
public interface VElderRecordMapper extends BaseMapper<VElderRecord> {

    List<ElderStatisticsDto> selectElderStatistics(String strWhere);

}
