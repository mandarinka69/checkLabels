package tests;

import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Yesterday {
    String dateNow;
    String dateYeaterday;
    @Test
    public void test(){
        getYesterdayDateString();
        dateNow();
    }

    private Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    private String getYesterdayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
       dateYeaterday =dateFormat.format(yesterday());
        System.out.println(dateYeaterday);
        return dateFormat.format(yesterday());
    }

    public void dateNow(){
        dateNow = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        System.out.println(dateNow);
    }

}
