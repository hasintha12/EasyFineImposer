package com.hasi.easyfineimposer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class MainMenu extends Activity {

    EditText vehicleNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        vehicleNo=(EditText)findViewById(R.id.numberText);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder quitMsg=new AlertDialog.Builder(this);
        quitMsg.setTitle("Logout");
        quitMsg.setMessage("Are you sure you want to quit?");
        quitMsg.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


                dialog.dismiss();
            }

        });

        quitMsg.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = quitMsg.create();
        alert.show();



    }


    public void vehicleInfoClick(View view){

        Intent i=new Intent(this,Get_Cloud_Data.class);
       if(vehicleNo.getText().toString().trim().equals("") || vehicleNo.getText().toString().length()!=6){

           Toast.makeText(this, "Enter a Valid Vehicle Number", Toast.LENGTH_SHORT).show();

       }else{

           if(this.isInternetOn()){
               i.putExtra("vehicle_no",vehicleNo.getText().toString());
               startActivity(i);}


       }
    }
    public void OnImposefineClicked(View view){

        Intent i=new Intent(this,Impose_Fines.class);
        startActivity(i);

    }
    public void OnviewFineClicked(View view){

        Intent i=new Intent(this,ViewImposedFines.class);
        startActivity(i);

    }
        /*check internet connectivity*/
    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet

            Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            Toast.makeText(this, "Internet Connection Fail", Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }


}
