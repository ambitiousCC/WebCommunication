package web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTimeDistance {

    public static void main(String[] args) throws ParseException, InterruptedException {
        Date date1 = new Date();
        Date date2 = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss.SSS");


        String d_1w_ifc_start = "2020/01/11 17:21:36.645";
        String d_1w_ifc_end = "2020/01/11 17:22:51.347";

        String d_1w_mysql_start = "2020/01/11 17:22:51.347";
        String d_1w_mysql_end = "2020/01/11 17:22:51.716";

        System.out.println(getTime(d_1w_ifc_start,d_1w_ifc_end)+"  "+getTime(d_1w_mysql_start,d_1w_mysql_end));
    }

    public static int calLastedTime(Date startDate,Date endDate) {
        long a = endDate.getTime();
        long b = startDate.getTime();
        int c = (int)((a - b) / 1000);
        return c;
    }

    // 获取两个时间相差分钟数
    public static long getTime(String oldTime,String newTime) throws ParseException {

        SimpleDateFormat df = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss.SSS");
        long NTime =df.parse(newTime).getTime();
        //从对象中拿到时间
        long OTime = df.parse(oldTime).getTime();
        long diff=(NTime-OTime);
        return diff;
    }

}