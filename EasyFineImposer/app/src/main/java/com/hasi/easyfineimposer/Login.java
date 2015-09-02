package com.hasi.easyfineimposer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;


public class Login extends ActionBarActivity {

    private DatabaseHandler dbHandler;
    private EditText nameText,passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nameText=(EditText)findViewById(R.id.nameText);
        passwordText=(EditText)findViewById(R.id.passwordText);
        dbHandler=new DatabaseHandler(this,null,null,1);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public void onLoginClick(View view){
        String userName=nameText.getText().toString();
        String password=passwordText.getText().toString();
        if(dbHandler.authenticate(userName,password)){
            Intent i=new Intent(this,MainMenu.class);
            startActivity(i);
            finish();


        }




    }

    public void onChangePassword(View view){






    }


}
