package com.carerobot.cockpit.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DataHandleUtil {

    public static String  division(int num1,int num2){
        String rate="0.00%";
        //定义格式化起始位数
        String format="0.00";
        if(num2 != 0 && num1 != 0){
            DecimalFormat dec = new DecimalFormat(format);
            rate =  dec.format((double) num1 / num2*100)+"%";
            while(true){
                if(rate.equals(format+"%")){
                    format=format+"0";
                    DecimalFormat dec1 = new DecimalFormat(format);
                    rate =  dec1.format((double) num1 / num2*100)+"%";
                }else {
                    break;
                }
            }
        }else if(num1 != 0 && num2 == 0){
            rate = "100%";
        }
        return rate;
    }

    public static BigDecimal perToDecimal(String percent){
        String decimal = percent.substring(0,percent.indexOf("%"));
        BigDecimal bigDecimal = new BigDecimal(decimal);
        bigDecimal.divide(new BigDecimal("100"), 4, BigDecimal.ROUND_HALF_UP);
        return bigDecimal;
    }
}