package com.hasi.easyfineimposer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sendSms sms=new sendSms();
        //setContentView(R.);
       // MessageSender msg=new MessageSender();
       // msg.sendSMS("0718647048","hasintha");
        DatabaseHandler dbh=new DatabaseHandler(this,null,null,1);
        dbh.addUser("h","1234");
        Intent i=new Intent(this,Login.class);
        startActivity(i);


    }




}
