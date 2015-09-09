package com.hasi.easyfineimposer;

import android.app.Activity;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by hasintha on 9/3/15.
 */
public class Time extends Activity{

/*obtain the current date and time as a string*/
    String getDateTime(){


        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());

        return formattedDate;

    }
}
