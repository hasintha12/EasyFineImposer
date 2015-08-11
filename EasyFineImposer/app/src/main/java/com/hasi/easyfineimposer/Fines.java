package com.hasi.easyfineimposer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;


public class Fines extends ActionBarActivity {

    String message;
    int cost=0;
    CheckBox chRev,chHead,chSignal,chWiper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fines);
        message="";

        chRev=(CheckBox) findViewById(R.id.rev);
        chHead=(CheckBox) findViewById(R.id.head);
        chSignal=(CheckBox) findViewById(R.id.signal);
        chWiper=(CheckBox) findViewById(R.id.wiper);
    }

    public void onClick(View view){

        if(chRev.isChecked()){
            message+=chRev.getText().toString()+", ";
            cost+=2500;
        }
        if(chHead.isChecked()){
            message+=chHead.getText().toString()+", ";
            cost+=500;
        }
        if(chSignal.isChecked()){
            message+=chSignal.getText().toString()+", ";
            cost+=500;
        }
        if(chWiper.isChecked()){
            message+=chWiper.getText().toString()+", ";
            cost+=500;
        }

        String tempCost=String.valueOf(cost);

        Intent i=new Intent(this,SendSMS.class);

        i.putExtra("message",message);
        i.putExtra("cost",tempCost);


        startActivity(i);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fines, menu);
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
