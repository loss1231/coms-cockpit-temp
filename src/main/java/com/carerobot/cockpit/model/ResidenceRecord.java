package com.carerobot.cockpit.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wd
 * @since 2021-05-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ResidenceRecord对象", description="")
public class ResidenceRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @TableField("ArchievesId")
    private Integer ArchievesId;

    @ApiModelProperty(value = "登记状态（在院、出院）")
    @TableField("Status")
    private Integer Status;

    @ApiModelProperty(value = "使用类别（主住、同住等）")
    @TableField("OccupyType")
    private Integer OccupyType;

    @TableField("AreaId")
    private Integer AreaId;

    @TableField("Area")
    private String Area;

    @TableField("RoomNo")
    private String RoomNo;

    @TableField("BedNo")
    private String BedNo;

    @ApiModelProperty(value = "入住类型（长客、短住、试住、访客等）")
    @TableField("LiveType")
    private Integer LiveType;

    @ApiModelProperty(value = "登记时间")
    @TableField("RecordTime")
    private Date RecordTime;


}
