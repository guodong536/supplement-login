package com.pingan.pare.bi.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
public class DateUtil {
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

   public String getDingShi(){
      return null;
   }

   public String getNowDate(){
      return sdf.format(new Date());
   }

    /**
     * return last date
     * @return
     */
    public Date getLastDate(){
        return new Date();
    }
}
