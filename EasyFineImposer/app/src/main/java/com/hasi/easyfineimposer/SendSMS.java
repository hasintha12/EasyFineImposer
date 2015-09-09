package com.hasi.easyfineimposer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SendSMS extends Activity {

    TextView sendMessage;
    EditText number,nameText,nicText,vehNo,location;
    String message,cost,name,nic;
    DatabaseHandler dbh;
    Time time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        sendMessage=(TextView)findViewById(R.id.fineText);
        number=(EditText)findViewById(R.id.number);
        nameText=(EditText)findViewById(R.id.nameText);
        nicText=(EditText)findViewById(R.id.nicText);
        vehNo=(EditText)findViewById(R.id.vehNo);
        location=(EditText)findViewById(R.id.location);

        dbh=new DatabaseHandler(this,null,null,1);
        time=new Time();

        Bundle fines=getIntent().getExtras();
        if(fines==null){

            return;
        }

        message=fines.getString("message");
        cost=fines.getString("cost");
        sendMessage.setText("Dear Sir,\nYour vehicle was subjected to following fines \n"+message+"\nTotal Cost is Rs:"+cost);



    }
    public void onNumberClick(View view){

    }

    public void onClick(View view){

        if( nameText.getText().toString().trim().equals(""))
        {
            Toast.makeText(getBaseContext(),"Please Enter a Valid Name ", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if( nicText.getText().toString().trim().equals(""))
            {
                Toast.makeText(getBaseContext(),"Please Enter a NIC number ", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(number.getText().toString().trim().equals("")) {

                    Toast.makeText(getBaseContext(),"Please Enter a Valid Phone Number ", Toast.LENGTH_SHORT).show();

                }else{

                    sendMessage.setText("Dear Sir,\nYour vehicle was subjected to following fines \n"+message+"\nTotal Cost is Rs:"+cost+"\nThis message is send from Sri Lanka Police to " + nameText.getText().toString() + " NIC " + nicText.getText().toString());


                    MessageSender msg = new MessageSender();
                  // msg.sendSMS(number.getText().toString(), sendMessage.getText().toString());
                    dbh.addFine(nicText.getText().toString(),nameText.getText().toString(),message,vehNo.getText().toString(),cost,location.getText().toString(),time.getDateTime());
                    Toast.makeText(getBaseContext(),"Message Sent.. ", Toast.LENGTH_SHORT).show();


                    Intent i=new Intent(this,MainMenu.class);
                    startActivity(i);




                }

            }
        }


    }




}
