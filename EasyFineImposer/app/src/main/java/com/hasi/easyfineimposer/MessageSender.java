package com.hasi.easyfineimposer;

/**
 * Created by hasintha on 7/25/15.
 */
import android.telephony.gsm.SmsManager;
import java.util.ArrayList;

public class MessageSender {

    public void sendSMS(String phoneNumber, String message)
    {
       try {
           SmsManager sms = SmsManager.getDefault();//create object of sms manager
           ArrayList<String> parts = sms.divideMessage(message);//break message in to parts if length longer than 160character

           //sms.sendTextMessage(phoneNumber, null, message, null, null);//send single message
           sms.sendMultipartTextMessage(phoneNumber, null, parts, null, null);//send all messages
       }catch (Exception e){




       }

    }
}
