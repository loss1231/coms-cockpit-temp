package com.carerobot.cockpit.mapper;

import com.carerobot.cockpit.model.ContractLive;
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
 * @since 2021-07-20
 */
public interface ContractLiveMapper extends BaseMapper<ContractLive> {

    @Select("<script>select * from Contract_Live where id not in <foreach item=\"item\" index=\"index\" collection=\"ids\" open=\"(\" separator=\",\" close=\")\">#{item}</foreach></script> ")
    List<ContractLive> different(@Param("ids") List<Integer> ids);
}
