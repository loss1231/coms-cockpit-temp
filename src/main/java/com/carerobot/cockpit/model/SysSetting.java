package com.carerobot.cockpit.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_setting")
@ApiModel(value="SysSetting", description="系统配置表")
public class SysSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "关键字")
    @TableField("setting_key")
    private String settingKey;

    @ApiModelProperty(value = "标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "排序权重")
    @TableField("order_weight")
    private Integer orderWeight;

    @ApiModelProperty(value = "配置值")
    @TableField("setting_value")
    private String settingValue;

    @ApiModelProperty(value = "默认值")
    @TableField("default_value")
    private String defaultValue;

    @ApiModelProperty(value = "过期时间")
    @TableField("expire_time")
    private Date expireTime;

    @ApiModelProperty(value = "数据类型")
    @TableField("setting_format")
    private String settingFormat;

    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;
}
