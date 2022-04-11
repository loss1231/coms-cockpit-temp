package com.carerobot.cockpit.mapper;

import com.carerobot.cockpit.model.AccountRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.carerobot.cockpit.model.Item;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 缴费记录表 Mapper 接口
 * </p>
 *
 * @author wd
 * @since 2021-07-13
 */
public interface AccountRecordMapper extends BaseMapper<AccountRecord> {

    @Select("<script>select * from account_record where id not in <foreach item=\"item\" index=\"index\" collection=\"ids\" open=\"(\" separator=\",\" close=\")\">#{item}</foreach></script> ")
    List<AccountRecord> different(@Param("ids")List<Integer> ids);

}
