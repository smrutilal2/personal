package home.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TestTimeZone
{

    public static void main(String[] args)
    {
        System.out.println(new Date());
        System.out.println(Calendar.getInstance().getTimeZone());

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd'T'hh:mm:ss.sssZ");
        System.out.println(dateFormat.format(new Date()));
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println(dateFormat.format(new Date()));
    }

}
