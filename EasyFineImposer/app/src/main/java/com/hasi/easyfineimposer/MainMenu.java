package com.hasi.easyfineimposer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class MainMenu extends ActionBarActivity {

    EditText vehicleNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        vehicleNo=(EditText)findViewById(R.id.numberText);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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

    public void vehicleInfoClick(View view){

        Intent i=new Intent(this,Get_Cloud_Data.class);
        i.putExtra("vehicle_no",vehicleNo.getText().toString());
        startActivity(i);

    }
    public void OnImposefineClicked(View view){

        Intent i=new Intent(this,Impose_Fines.class);
        startActivity(i);

    }
    public void OnviewFineClicked(View view){

        Intent i=new Intent(this,ViewImposedFines.class);
        startActivity(i);

    }
}
