package com.carerobot.cockpit.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.carerobot.cockpit.model.AccidentReport;
import com.carerobot.cockpit.model.LeaveApplication;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author wd
 * @since 2021-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Archivesinfo对象", description="")
@TableName(value = "Archives_info")
public class Archives_info implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @TableField("Name")
    private String Name;

    @TableField("Gender")
    private Integer Gender;

    @TableField("Birthday")
    private Date Birthday;

    @TableField("CredentialsNo")
    private String CredentialsNo;

    @TableField("Age")
    private Integer Age;

    @TableField("AreaId")
    private Integer AreaId;

    @TableField("Area")
    private String Area;

    @TableField("RoomNo")
    private String RoomNo;

    @TableField("BedNo")
    private String BedNo;

    @TableField("Occupation")
    private String Occupation;

    @TableField(exist = false)
    private List<LeaveApplication> leaveApplications;

    @TableField(exist = false)
    private List<AccidentReport> accidentReports;
}
