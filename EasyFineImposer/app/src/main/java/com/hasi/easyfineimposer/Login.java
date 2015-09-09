package com.hasi.easyfineimposer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;


public class Login extends Activity {

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
    public void onBackPressed() {
            this.closeContextMenu();
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

        Intent i=new Intent(this,ChangePassword.class);
        startActivity(i);




    }


}
