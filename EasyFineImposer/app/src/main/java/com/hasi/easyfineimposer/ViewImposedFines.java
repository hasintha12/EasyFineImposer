package com.hasi.easyfineimposer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ListAdapter;
import android.widget.ListView;


public class ViewImposedFines extends Activity {

    TextView txt;
    FineData[] fData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_imposed_fines);
        DatabaseHandler dbh=new DatabaseHandler(this,null,null,1);

        ListAdapter lstadAdapter = new CustomAdapter(this, dbh.getReport());
        ListView lstView = (ListView) findViewById(R.id.listView2);
        lstView.setAdapter(lstadAdapter);



    }


}
