package com.carerobot.cockpit.mapper;

import com.carerobot.cockpit.model.ItemDetail;
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
public interface ItemDetailMapper extends BaseMapper<ItemDetail> {

    @Select("SELECT id.* FROM `item_detail` id JOIN item i ON i.id = id.item_id AND  i.create_time >= #{startDate} AND i.create_time <= #{endDate}")
    List<ItemDetail> getNewItemDetails(@Param("startDate") Date startDate,@Param("endDate") Date endDate);

}
