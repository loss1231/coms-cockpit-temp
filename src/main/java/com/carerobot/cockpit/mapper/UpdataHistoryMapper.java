package com.carerobot.cockpit.mapper;

import com.carerobot.cockpit.model.UpdataHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wd
 * @since 2021-07-13
 */
public interface UpdataHistoryMapper extends BaseMapper<UpdataHistory> {

    //TRUNCATE TABLE tablename
    @Update("TRUNCATE TABLE ${tableName}")
    void deleteTableAllInfo(@Param("tableName") String tableName);

}
