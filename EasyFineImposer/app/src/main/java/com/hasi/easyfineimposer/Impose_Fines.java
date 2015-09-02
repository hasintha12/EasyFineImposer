package com.hasi.easyfineimposer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class Impose_Fines extends Activity {
    String message="";
    int cost=0;
    String[] fines = new String[] {
            "Drive without a valid Driving Licence",
            "Driving under 18 years of age",
            "Employing person without Driving Licence",
            "Revenue licence not carried",
            "Signals by Drivers",
            "Position of Driver",
            "No of people in front seat",
            "Sound or Light warning",
            "Reversing",
            "Carriage of person in excess",
            "Persons who may be carried in lorry ",
            "Distribution of advertisements",
            "Identification plates",
            "Shape of identification plates",
            "Precautions when petrol is taken",
            "Not allowing traffic to overtake",
            "Overtaking without a clear view",
            "Improper overtaking ",
            "Obstruction while driving along side or overtaking",
            "Obstruction crossing highway",
            "Obstruction entering highway",
            "Obstructing traffic on main road",
            "Fail to give way to traffic from the right",
            "Not slowing for safe passage on narrow highway",
            "Fail to keep to left when turninng left",
            "Fail to position vehicle on center of road when turning right "

    };

    int[] fineCost= new int[]{
      2500,//Drive without a valid Driving Licence
      5000,//Driving under 18 years of age
      3000,//Employing person without Driving Licence
      500,//Revenue licence not carried
      500,//Signals by Drivers
      100,//Position of Driver
      100,//No of people in front seat
      100,//Sound or Light warning
       20,//Reversinga
      150,//Carriage of person in excess
      150,//Persons who may be carried in lorry
      100,//Distribution of advertisements
      500,//Identification plates
      100,//shape of identification plates
       20,//Precautions when petrol is taken
      500,//Not allowing traffic to overtake
      500,//Overtaking without a clear view
      500,//Improper overtaking
      500,//Overtaking without a clear view
      500,// Obstruction while driving along side or overtaking
      500,//  Obstruction crossing highway
      500,// Obstruction entering highway
      500,//Obstructing traffic on main road
      500,//Fail to give way to traffic from the right
      500,//Not slowing for safe passage on narrow highway
      500,//Fail to keep to left when turninng left
      500//Fail to position vehicle on center of road when turning right


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impose__fines);


        // Get the object reference of listview from the layout
        ListView listView = ( ListView ) findViewById(R.id.listView);

        // Instantiating an array adapter for listview
        ArrayAdapter< String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, fines);
        listView.setAdapter(adapter);

        //Defining an item click listener
        OnItemClickListener itemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                // AdapterView is the parent class of ListView
                ListView lv = (ListView) arg0;
                if(lv.isItemChecked(position)){
                    cost+=fineCost[position];
                    message+=fines[position]+",\n";
                    Toast.makeText(getBaseContext(), fines[position]+"   "+cost, Toast.LENGTH_SHORT).show();
                }else{
                    cost-=fineCost[position];
                    message=message.replace(fines[position]+",\n","");
                    Toast.makeText(getBaseContext(), fines[position]+"   "+cost, Toast.LENGTH_SHORT).show();

                }
            }
        };

        // Setting the ItemClickEvent listener for the listview
        listView.setOnItemClickListener(itemClickListener);


    }
    public void onSubmitClick(View view){

        String tempCost=String.valueOf(cost);

        Intent i=new Intent(this,SendSMS.class);

        i.putExtra("message",message);
        i.putExtra("cost",tempCost);


        startActivity(i);
    }

}
