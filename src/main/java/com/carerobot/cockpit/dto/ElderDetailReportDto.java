package com.carerobot.cockpit.dto;

import lombok.Data;

/**
 * 2022.3.21
 * 长者明细实体类
 */
@Data
public class ElderDetailReportDto {

    private String RegisterStatus;

    private String Type;

    private String Area;

    private String BedInfoBedNo;

    private String Name;

    private String Birthday;

    private String CredentialsNo;

    private String Gender;

    private String EmergencyPerson;

    private String CellPhoneNumber;

    private String OccupationStr;

    private String IsLongOrShort;

    private String OldAge;

    private String Age;

    private String PhysicalCondition;

    private String NurseLevel;

    private String OrgId;

    private String LiveDate;

    private String leaveTime;

    private String CreatedUserName;

    private String Remark;

    private String BedStatus="";

    private int currPage = 1;

    private int pageSize = 10;
}
