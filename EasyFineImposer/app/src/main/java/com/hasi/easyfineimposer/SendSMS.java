package com.hasi.easyfineimposer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class SendSMS extends ActionBarActivity {

    TextView sendMessage;
    EditText number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        sendMessage=(TextView)findViewById(R.id.fineText);
        number=(EditText)findViewById(R.id.number);

        Bundle fines=getIntent().getExtras();
        if(fines==null){

            return;
        }

        String message=fines.getString("message");
        String cost=fines.getString("cost");
        sendMessage.setText("Dear Sir,\n your vehicle was subjected to following fines \n"+message+"\n Total Cost is Rs:"+cost);


    }

    public void onClick(View view){
        if(number.getText().toString()!=null) {
            MessageSender msg = new MessageSender();
            msg.sendSMS(number.getText().toString(),sendMessage.getText().toString());
            sendMessage.setText("Message sent");


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send_sm, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
