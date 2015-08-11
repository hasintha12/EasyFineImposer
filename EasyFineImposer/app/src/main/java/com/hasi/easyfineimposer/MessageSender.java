package com.hasi.easyfineimposer;

/**
 * Created by hasintha on 7/25/15.
 */
import android.telephony.gsm.SmsManager;

public class MessageSender {

    public void sendSMS(String phoneNumber, String message)
    {
       SmsManager sms = SmsManager.getDefault();
       sms.sendTextMessage(phoneNumber, null, message, null, null);


    }
}
