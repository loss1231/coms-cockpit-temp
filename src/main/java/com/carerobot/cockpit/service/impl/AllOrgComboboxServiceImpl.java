package com.carerobot.cockpit.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.carerobot.cockpit.dto.AllOrgComboboxDto;
import com.carerobot.cockpit.dto.AllOrgComboboxReqDto;
import com.carerobot.cockpit.dto.ResidentsDto;
import com.carerobot.cockpit.mapper.AllOrgComboboxDtoMapper;
import com.carerobot.cockpit.service.AllOrgComboboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AllOrgComboboxServiceImpl implements AllOrgComboboxService {


    @Autowired
    AllOrgComboboxDtoMapper ac;


    @Override
    public List<AllOrgComboboxDto> getOccupancyRate(List OrgId, Date startDate, Date endDate) {
        ArrayList<AllOrgComboboxDto> ComboboxList = new ArrayList<>();

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            // 获取开始年份和开始月份
            int startYear = calendar.get(Calendar.YEAR);
            int startMonth = calendar.get(Calendar.MONTH);
            // 获取结束年份和结束月份
            calendar.setTime(endDate);
            int endYear = calendar.get(Calendar.YEAR);
            int endMonth = calendar.get(Calendar.MONTH);
            //
            List<String> list = new ArrayList<String>();
            for (int i = startYear; i <= endYear; i++) {
                String date = "";
                if (startYear == endYear) {
                    for (int j = startMonth; j <= endMonth; j++) {
                        if (j < 9) {
                            date = i + "-0" + (j + 1);
                        } else {
                            date = i + "-" + (j + 1);
                        }
                        list.add(date);
                    }

                } else {
                    if (i == startYear) {
                        for (int j = startMonth; j < 12; j++) {
                            if (j < 9) {
                                date = i + "-0" + (j + 1);
                            } else {
                                date = i + "-" + (j + 1);
                            }
                            list.add(date);
                        }
                    } else if (i == endYear) {
                        for (int j = 0; j <= endMonth; j++) {
                            if (j < 9) {
                                date = i + "-0" + (j + 1);
                            } else {
                                date = i + "-" + (j + 1);
                            }
                            list.add(date);
                        }
                    } else {
                        for (int j = 0; j < 12; j++) {
                            if (j < 9) {
                                date = i + "-0" + (j + 1);
                            } else {
                                date = i + "-" + (j + 1);
                            }
                            list.add(date);
                        }
                    }

                }

            }
            // 所有的月份已经准备好
            //System.out.println(list);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            for(int i = 0;i < list.size();i++){

                //获取当前月份的月初和月末
                Calendar cale = Calendar.getInstance();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");

                cale.setTime(formatter.parse(list.get(i)));

                cale.add(Calendar.MONTH, 0);

                cale.set(Calendar.DAY_OF_MONTH, 1);
                //月初时间
                Date date=cale.getTime();
                //对日期进行格式转换--转换格式
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //格式转换
                String str1=sdf.format(date);

                int a =cale.getActualMaximum(Calendar.DAY_OF_MONTH);
                cale.add(Calendar.DAY_OF_MONTH, a-1);


                date = cale.getTime();

                String str2 = sdf.format(date);

                AllOrgComboboxDto dto = ac.selectOccupancyRate(str1,str2,OrgId,list.get(i));
                dto.setStartDate(str1);
                dto.setEndDate(str2);
                dto.setDate(list.get(i));

            //遍历每月每天的入住人数后求总和
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
                cale.setTime(sf.parse(list.get(i)));
                cale.set(Calendar.DAY_OF_MONTH, 1); //设置月开始第一天日期
                int end = cale.getActualMaximum(Calendar.DAY_OF_MONTH); //获得月末日期
                Integer ILCount = 0;
                Integer ALCount = 0;
                for (int n=1; n<=end; n++) { //循环打印即可
                    Date date2 = cale.getTime();
                    ResidentsDto residentsDto = ac.selectResidents(sdf.format(date2), OrgId);
                    cale.add(Calendar.DAY_OF_MONTH, 1);
                    ILCount += residentsDto.getILTotal();
                    ALCount += residentsDto.getALTotal();
                }
                dto.setILOccupancyAverage(String.valueOf(ILCount / (Integer.parseInt(dto.getILSRoom()) * end)));
                dto.setALOccupancyAverage(String.valueOf(ALCount / (Integer.parseInt(dto.getALSRoom()) * end)));
                ComboboxList.add(dto);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return ComboboxList;

    }

    @Override
    public AllOrgComboboxReqDto getOccupancyRates(List OrgId, Date startDate, Date endDate) {
        JSONArray jsonArray = new JSONArray();
        AllOrgComboboxReqDto dto = new AllOrgComboboxReqDto();

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            // 获取开始年份和开始月份
            int startYear = calendar.get(Calendar.YEAR);
            int startMonth = calendar.get(Calendar.MONTH);
            // 获取结束年份和结束月份
            calendar.setTime(endDate);
            int endYear = calendar.get(Calendar.YEAR);
            int endMonth = calendar.get(Calendar.MONTH);
            //
            List<String> list = new ArrayList<String>();
            for (int i = startYear; i <= endYear; i++) {
                String date = "";
                if (startYear == endYear) {
                    for (int j = startMonth; j <= endMonth; j++) {
                        if (j < 9) {
                            date = i + "-0" + (j + 1);
                        } else {
                            date = i + "-" + (j + 1);
                        }
                        list.add(date);
                    }

                } else {
                    if (i == startYear) {
                        for (int j = startMonth; j < 12; j++) {
                            if (j < 9) {
                                date = i + "-0" + (j + 1);
                            } else {
                                date = i + "-" + (j + 1);
                            }
                            list.add(date);
                        }
                    } else if (i == endYear) {
                        for (int j = 0; j <= endMonth; j++) {
                            if (j < 9) {
                                date = i + "-0" + (j + 1);
                            } else {
                                date = i + "-" + (j + 1);
                            }
                            list.add(date);
                        }
                    } else {
                        for (int j = 0; j < 12; j++) {
                            if (j < 9) {
                                date = i + "-0" + (j + 1);
                            } else {
                                date = i + "-" + (j + 1);
                            }
                            list.add(date);
                        }
                    }

                }

            }
            // 所有的月份已经准备好
            //System.out.println(list);
            JSONObject ILTotal = new JSONObject();
            JSONObject ILUnits = new JSONObject();
            JSONObject ILSRoom = new JSONObject();
            JSONObject ILOccupancy = new JSONObject();
            JSONObject ILOccupancyAverage = new JSONObject();
            JSONObject ALTotal = new JSONObject();
            JSONObject ALUnits = new JSONObject();
            JSONObject ALSRoom = new JSONObject();
            JSONObject ALOccupancy = new JSONObject();
            JSONObject ALOccupancyAverage = new JSONObject();
            JSONObject TotalOccupancy = new JSONObject();
            JSONObject TotalUnits = new JSONObject();
            JSONObject TotalResidents = new JSONObject();
            JSONObject incomIl = new JSONObject();
            JSONObject incomAl = new JSONObject();
            JSONObject incomRestaurant = new JSONObject();
            JSONObject incomOther = new JSONObject();
            JSONObject Totalincom = new JSONObject();


            for(int i = 0;i < list.size();i++){
                JSONObject JSob = new JSONObject();

                //获取当前月份的月初和月末
                Calendar cale = Calendar.getInstance();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
                //对日期进行格式转换--转换格式
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                cale.setTime(formatter.parse(list.get(i)));

                cale.add(Calendar.MONTH, 0);
                cale.set(Calendar.DAY_OF_MONTH, 1);

                //月初时间
                Date date=cale.getTime();
                //格式转换
                String str1=sdf.format(date);

                int a =cale.getActualMaximum(Calendar.DAY_OF_MONTH);
                cale.add(Calendar.DAY_OF_MONTH, a-1);

                //月末时间
                date = cale.getTime();
                String str2 = sdf.format(date);

//                AllOrgComboboxDto dto = ac.selectOccupancyRate(str1,str2,OrgId,list.get(i));
                ILTotal.put(list.get(i),ac.selectILTotal(str1,str2,OrgId));
                ILUnits.put(list.get(i),ac.selectILUnits(str1,str2,OrgId));
                ILSRoom.put(list.get(i),ac.selectILSRoom(str1,str2,OrgId));
                ILOccupancy.put(list.get(i),ac.selectILOccupancy(str1,str2,OrgId));
                ALTotal.put(list.get(i),ac.selectALTotal(str1,str2,OrgId));
                ALUnits.put(list.get(i),ac.selectALUnits(str1,str2,OrgId));
                ALSRoom.put(list.get(i),ac.selectALSRoom(str1,str2,OrgId));
                ALOccupancy.put(list.get(i),ac.selectALOccupancy(str1,str2,OrgId));
                TotalOccupancy.put(list.get(i),ac.selectTotalOccupancy(str1,str2,OrgId));
                TotalUnits.put(list.get(i),ac.selectTotalUnits(str1,str2,OrgId));
                TotalResidents.put(list.get(i),ac.selectTotalResidents(str1,str2,OrgId));
                incomIl.put(list.get(i),ac.selectIncomIl(list.get(i),OrgId));
                incomAl.put(list.get(i),ac.selectIncomAl(list.get(i),OrgId));
                incomRestaurant.put(list.get(i),ac.selectIncomRestaurant(list.get(i),OrgId));
                incomOther.put(list.get(i),ac.selectIncomOther(list.get(i),OrgId));
                Totalincom.put(list.get(i),ac.selectTotalincom(list.get(i),OrgId));

//                dto.setStartDate(str1);
//                dto.setEndDate(str2);
//                dto.setDate(list.get(i));

                //遍历每月每天的入住人数后求总和
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
                cale.setTime(sf.parse(list.get(i)));
                cale.set(Calendar.DAY_OF_MONTH, 1); //设置月开始第一天日期
                int end = cale.getActualMaximum(Calendar.DAY_OF_MONTH); //获得月末日期
                Integer ILCount = 0;
                Integer ALCount = 0;
                for (int n=1; n<=end; n++) { //循环打印即可
                    Date date2 = cale.getTime();
                    ResidentsDto residentsDto = ac.selectResidents(sdf.format(date2), OrgId);
                    cale.add(Calendar.DAY_OF_MONTH, 1);
                    ILCount += residentsDto.getILTotal();
                    ALCount += residentsDto.getALTotal();
                }
                ILOccupancyAverage.put(list.get(i),ILCount/(ac.selectILSRoom(str1,str2,OrgId) * end));
                ALOccupancyAverage.put(list.get(i),ALCount/(ac.selectALSRoom(str1,str2,OrgId) * end));

                ILTotal.put("item1","入住");
                ILTotal.put("item2","自理入住人数");
                ILTotal.put("item3","1");
                ILTotal.put("item4","1");

                ILUnits.put("item1","入住");
                ILUnits.put("item2","自理入住单元数");
                ILUnits.put("item3","1");
                ILUnits.put("item4","1");

                ILSRoom.put("item1","入住");
                ILSRoom.put("item2","自理可入住单元数");
                ILSRoom.put("item3","1");
                ILSRoom.put("item4","1");

                ILOccupancy.put("item1","入住");
                ILOccupancy.put("item2","自理可入住率");
                ILOccupancy.put("item3","0");
                ILOccupancy.put("item4","1");

                ILOccupancyAverage.put("item1","入住");
                ILOccupancyAverage.put("item2","自理平均入住率");
                ILOccupancyAverage.put("item3","0");
                ILOccupancyAverage.put("item4","1");

                ALTotal.put("item1","入住");
                ALTotal.put("item2","护理入住人数");
                ALTotal.put("item3","1");
                ALTotal.put("item4","1");

                ALUnits.put("item1","入住");
                ALUnits.put("item2","护理入住单元数");
                ALUnits.put("item3","1");
                ALUnits.put("item4","1");

                ALSRoom.put("item1","入住");
                ALSRoom.put("item2","护理可入住单元数");
                ALSRoom.put("item3","1");
                ALSRoom.put("item4","1");

                ALOccupancy.put("item1","入住");
                ALOccupancy.put("item2","护理可入住率");
                ALOccupancy.put("item3","0");
                ALOccupancy.put("item4","1");

                ALOccupancyAverage.put("item1","入住");
                ALOccupancyAverage.put("item2","护理平均入住率");
                ALOccupancyAverage.put("item3","0");
                ALOccupancyAverage.put("item4","1");

                TotalOccupancy.put("item1","入住");
                TotalOccupancy.put("item2","入住率");
                TotalOccupancy.put("item3","0");
                TotalOccupancy.put("item4","1");

                TotalUnits.put("item1","入住");
                TotalUnits.put("item2","合计单位数");
                TotalUnits.put("item3","1");
                TotalUnits.put("item4","1");

                TotalResidents.put("item1","入住");
                TotalResidents.put("item2","住户人数");
                TotalResidents.put("item3","1");
                TotalResidents.put("item4","1");

                incomIl.put("item1","收入");
                incomIl.put("item2","自理区服务费收入");
                incomIl.put("item3","1");
                incomIl.put("item4","3");

                incomAl.put("item1","收入");
                incomAl.put("item2","护理区服务费收入");
                incomAl.put("item3","1");
                incomAl.put("item4","3");

                incomRestaurant.put("item1","收入");
                incomRestaurant.put("item2","餐饮收入");
                incomRestaurant.put("item3","1");
                incomRestaurant.put("item4","3");

                incomOther.put("item1","收入");
                incomOther.put("item2","其他运营收入");
                incomOther.put("item3","1");
                incomOther.put("item4","3");

                Totalincom.put("item1","收入");
                Totalincom.put("item2","合计收入");
                Totalincom.put("item3","0");
                Totalincom.put("item4","3");


            }
            jsonArray.add(ILTotal);
            jsonArray.add(ILUnits);
            jsonArray.add(ILSRoom);
            jsonArray.add(ILOccupancy);
            jsonArray.add(ILOccupancyAverage);
            jsonArray.add(ALTotal);
            jsonArray.add(ALUnits);
            jsonArray.add(ALSRoom);
            jsonArray.add(ALOccupancy);
            jsonArray.add(ALOccupancyAverage);
            jsonArray.add(TotalOccupancy);
            jsonArray.add(TotalUnits);
            jsonArray.add(TotalResidents);
            jsonArray.add(incomIl);
            jsonArray.add(incomAl);
            jsonArray.add(incomRestaurant);
            jsonArray.add(incomOther);
            jsonArray.add(Totalincom);

            dto.setJsonArray(jsonArray);
            dto.setList(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
}
