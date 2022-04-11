package com.carerobot.cockpit.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author wd
 * @since 2021-05-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="VElderRecord对象", description="VIEW")
public class VElderRecord implements Serializable {

    public static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "登记状态（在院、出院）")
    @TableField("RegisterStatus")
    public String RegisterStatus;

    @ApiModelProperty(value = "使用类别（主住、同住等）")
    @TableField("OccupyType")
    public Integer OccupyType;

    @TableField("Area")
    public String Area;

    @TableField("BedNo")
    public String BedNo;

    @TableField("Name")
    public String Name;

    @TableField("Birthday")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date Birthday;

    @TableField("CredentialsNo")
    public String CredentialsNo;

    @TableField("Gender")
    public Integer Gender;

    @TableField("EmergencyContact")
    public String EmergencyContact;

    @TableField("EmergencyContactPhone")
    public String EmergencyContactPhone;

    @ApiModelProperty(value = "入住类型（长客、短住、试住、访客等）")
    @TableField("LiveType")
    public Integer LiveType;

    @TableField("Occupation")
    public String Occupation;

    @TableField("CurrAge")
    public Integer CurrAge;

    @ApiModelProperty(value = "入院年龄")
    @TableField("Age")
    public Integer Age;

    @ApiModelProperty(value = "登记状态（在院、出院）")
    @TableField("Disease")
    public String Disease;

    @TableField("RecordTime")
    public Date RecordTime;


}
