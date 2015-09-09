package com.hasi.easyfineimposer;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Get_Cloud_Data extends Activity {

    // Progress Dialog
    private ProgressDialog pDialog;



    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    String owner_name,engne_no,chasis_no,contact_no,vehicle_model,rev_exp_date,ins_exp_date,owner_id,vehicle_no;
    TextView vehicleNo,vehicleModel,engineNo,chassisNo,licenceExp,insuranceExp,ownerName,ownerID,contacts;

    

    // url to get all locations list from cloud database
    private static String url_vehicle_info = "http://panzer.site90.net/get_vehicle_info.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_OWNERNAME = "owner_name";
    private static final String TAG_CONTACTNO = "contact_no";
    private static final String TAG_MODEL = "vehicle_model";
    private static final String TAG_ENG = "engine_no";
    private static final String TAG_CHASSIS = "chasis_no";
    private static final String TAG_REVEXP = "rev_exp_date";
    private static final String TAG_INSEXP = "ins_exp_date";
    private static final String TAG_OWNERID = "owner_id";


    private static final String TAG_LOG = "mylog";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_info);

        vehicleNo=(TextView)findViewById(R.id.vehicleNoText);
        vehicleModel=(TextView)findViewById(R.id.vehicleModelText);
        engineNo=(TextView)findViewById(R.id.engineText);
        chassisNo=(TextView)findViewById(R.id.chassisText);
        licenceExp=(TextView)findViewById(R.id.licenceText);
        insuranceExp=(TextView)findViewById(R.id.insuranceText);
        ownerName=(TextView)findViewById(R.id.ownerNameText);
        ownerID=(TextView)findViewById(R.id.ownerIDText);
        contacts=(TextView)findViewById(R.id.contactText);

        Bundle MainMenu=getIntent().getExtras();

        if(MainMenu==null){

            return;
        }
        vehicle_no=MainMenu.getString("vehicle_no");


  try {


      new LoadVehicleInfo().execute(); //makes the instance of the inner class to load the single detail
  }catch (Exception e){

      Toast.makeText(this, "Failed to load vehicle data", Toast.LENGTH_SHORT).show();

  }
        


    }

    public void onImposeFineClicked(View view){

        Intent i=new Intent(this,Impose_Fines.class);
        startActivity(i);
    }



    /**
     * Background Async Task to Load details by making HTTP Request
     */
    class LoadVehicleInfo extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        int success;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Get_Cloud_Data.this);
            //this message will be shown in the loading window
            pDialog.setMessage("loading vehicle details...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting info from url
         */
        protected String doInBackground(String... args) {
            // Building Parameter
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("vehicle_no", vehicle_no));

            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_vehicle_info, "POST", params);
            // Check your log cat for JSON response
            Log.d(TAG_LOG, json.toString());

            try {
                // Checking for SUCCESS TAG
                success = json.getInt(TAG_SUCCESS);
                //String id = json.getString(TAG_NAME);



                if (success == 1) {

                    Log.d(TAG_LOG, "success started");
                    owner_name=json.getString(TAG_OWNERNAME);
                    engne_no=json.getString(TAG_ENG);
                    chasis_no=json.getString(TAG_CHASSIS);
                    contact_no=json.getString(TAG_CONTACTNO);
                    vehicle_model=json.getString(TAG_MODEL);
                    rev_exp_date=json.getString(TAG_REVEXP);
                    ins_exp_date=json.getString(TAG_INSEXP);
                    owner_id=json.getString(TAG_OWNERID);

                    Log.d(TAG_LOG, "success ended");

                } else {

                    Log.d(TAG_LOG, "error while reading database");
                    //clear();
                }
            } catch (JSONException e) {
                Log.d(TAG_LOG, "exception in loading vehicle details");
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * *
         */
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
            if(success==1){
                //tst.setText("success");
                vehicleNo.setText(vehicle_no);
                vehicleModel.setText(vehicle_model);
                engineNo.setText(engne_no);
                chassisNo.setText(chasis_no);
                licenceExp.setText(rev_exp_date);
                insuranceExp.setText(ins_exp_date);
                ownerName.setText(owner_name);
                ownerID.setText(owner_id);
                contacts.setText(contact_no);
            }


        }

    }



}