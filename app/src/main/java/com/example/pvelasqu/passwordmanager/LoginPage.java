package com.example.pvelasqu.passwordmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import android.content.Intent;

public class LoginPage extends AppCompatActivity {
    Button logIn;
    Button registerButton;
    Button changePinButton;
    EditText pwText;
    TextView viewCount;
    int counter;
    String pw = "2405";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        SharedPreferences settings = getSharedPreferences("PASS", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = settings.edit();

        logIn = (Button)findViewById(R.id.loginButton);
        registerButton = (Button)findViewById(R.id.registerButton);
        changePinButton = (Button)findViewById(R.id.changePin);

        pwText = (EditText)findViewById(R.id.pwBox);
        viewCount = (TextView)findViewById(R.id.count);
        counter = 5;
        prefEditor.putString("Password", "2405");
        prefEditor.commit();
        pw = returnPass(settings);

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(pw.equals("DEFAULT IF NOT FOUND")){
                    startActivity(new Intent(LoginPage.this, registerActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Already Registered", Toast.LENGTH_SHORT).show();
                }

            }
        });


        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pwText.getText().toString().equals(pw)) {
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();

                    pwText.setVisibility(View.VISIBLE);
                    pwText.setBackgroundColor(Color.RED);
                    counter--;
                    viewCount.setText(Integer.toString(counter));

                    if (counter == 0) {
                        logIn.setEnabled(false);
                    }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_page, menu);
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
    public String returnPass(SharedPreferences settings){
        return settings.getString("Password", "DEFAULT IF NOT FOUND");

    }

}
