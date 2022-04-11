package com.carerobot.cockpit.mapper;

import com.carerobot.cockpit.model.Item;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wd
 * @since 2021-07-13
 */
public interface ItemMapper extends BaseMapper<Item> {

    @Select("select show(now) from item")
    List<Item> selectNewInfo(@Param("start") Date start,@Param("end") Date end);

    @Select("<script>select * from item where id not in <foreach item=\"item\" index=\"index\" collection=\"ids\" open=\"(\" separator=\",\" close=\")\">#{item}</foreach></script> ")
    List<Item> different(@Param("ids")List<Integer> ids);
}
