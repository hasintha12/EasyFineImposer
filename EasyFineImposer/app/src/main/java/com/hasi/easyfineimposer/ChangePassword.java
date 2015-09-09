package com.hasi.easyfineimposer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ChangePassword extends Activity {

    EditText userName,newUser,oldPassword,newPassword,confPassword;
    Button pwButton;
    DatabaseHandler dbh=new DatabaseHandler(this,null,null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);


        userName=(EditText)findViewById(R.id.userNameText);
        oldPassword=(EditText)findViewById(R.id.oldPwText);
        newPassword=(EditText)findViewById(R.id.newPwText);
        confPassword=(EditText)findViewById(R.id.confPwText);
        newUser=(EditText)findViewById(R.id.newUserText);
        pwButton=(Button)findViewById(R.id.pwButton);





    }

    public void onPasswordChangeClick(View view){
        if( userName.getText().toString().trim().equals("")||newUser.getText().toString().trim().equals(""))
        {
            Toast.makeText(getBaseContext(), "Please Enter a Valid User Name ", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(!dbh.authenticate(userName.getText().toString(),oldPassword.getText().toString()) )
            {
                Toast.makeText(getBaseContext(),"User Nae and password does not match ", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(!newPassword.getText().toString().trim().equals(confPassword.getText().toString().trim())) {

                    Toast.makeText(getBaseContext(),"New password and confirm password does not match  ", Toast.LENGTH_SHORT).show();

                }else{


                    String message=dbh.changePW(userName.getText().toString(),newUser.getText().toString(), oldPassword.getText().toString(), newPassword.getText().toString());


                        Toast.makeText(getBaseContext(),message, Toast.LENGTH_LONG).show();



                    Intent i=new Intent(this,Login.class);
                    startActivity(i);





                }

            }
        }




    }



}
